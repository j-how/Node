package com.djangohow.training.service;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.djangohow.training.R;
import com.djangohow.training.data.QuoteColumns;
import com.djangohow.training.data.QuoteHistoricalDataColumns;
import com.djangohow.training.data.QuoteProvider;
import com.djangohow.training.net.API;
import com.djangohow.training.net.QuoteStock;
import com.djangohow.training.net.ResponseStock;
import com.djangohow.training.net.ResponseStockHistoriaclData;
import com.djangohow.training.net.ResponseStocks;
import com.djangohow.training.net.Utils;
import com.djangohow.training.ui.StockListActivity;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by django on 2016/9/21.
 */
public class StockTaskService extends GcmTaskService{
    private Context mContext;
    StockExistListener mListener;
    public static interface StockExistListener{
        void tell(Boolean aBoolean);
    }
    private OkHttpClient mClient = new OkHttpClient();
    private StringBuilder mStoredSymbols = new StringBuilder();
    private boolean isUpdate;
    public final static String TAG_PERIODIC = "periodic";
    private final static String INIT_QUOTES = "\"YHOO\",\"AAPL\",\"GOOG\",\"MSFT\"";
    private boolean isOnlyOneStock = false;
    public StockTaskService() {
    }

    public StockTaskService(Context context) {
        mContext = context;
    }

    String fetchData(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = mClient.newCall(request).execute();
        return response.body().string();
    }
    @Override
    public int onRunTask(TaskParams taskParams) {
        if (mContext==null){
            return GcmNetworkManager.RESULT_FAILURE;
        }
        StringBuilder urlStringBuilder = new StringBuilder();
        urlStringBuilder.append(API.BASE_URL);
        urlStringBuilder.append(API.QUERY_URL_HEAD);
        urlStringBuilder.append(buildUrl(taskParams));
        urlStringBuilder.append(API.QUERY_URL_END);

        try {
            if (taskParams.getTag().equals(StockIntentService.ACTION_INIT)){
                if (isOnlyOneStock){
                    addOrOnlyOneStock(urlStringBuilder);
                }else {
                    String response = fetchData(urlStringBuilder.toString());
                    ResponseStocks stocks = new Gson().fromJson(response, ResponseStocks.class);
                    ArrayList<QuoteStock> quotes =
                            (ArrayList<QuoteStock>) stocks.getQuery().getResults().getQuote();
                    saveQuotes2Database(quotes);
                }

            }else {
                addOrOnlyOneStock(urlStringBuilder);
            }
            return  GcmNetworkManager.RESULT_SUCCESS;
        } catch (IOException  e) {
            e.printStackTrace();
            Log.e("tag", e.getMessage(), e);
            return GcmNetworkManager.RESULT_FAILURE;
        }
    }

    private void addOrOnlyOneStock(StringBuilder urlStringBuilder) throws IOException {
        ArrayList<QuoteStock> quotes = new ArrayList<>();
        String response = fetchData(urlStringBuilder.toString());
        ResponseStock stock = new Gson().fromJson(response,ResponseStock.class);
        QuoteStock quote = stock.getQuery().getResults().getQuote();
        quotes.add(quote);
        saveQuotes2Database(quotes);
    }
    private void saveQuotes2Database(ArrayList<QuoteStock> quotes) {
        ContentResolver resolver = mContext.getContentResolver();
        ArrayList<ContentProviderOperation> batchOperations = new ArrayList<>();
        mListener = StockListActivity.sListener;
        for (QuoteStock quote : quotes) {
            if (quote.getAsk()==null){
                mListener.tell(Boolean.TRUE);
                return;
            }{
                batchOperations.add(Utils.buildBatchOperation(quote));
            }
        }
        if (isUpdate) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(QuoteColumns.ISCURRENT, 0);
            resolver.update(QuoteProvider.Quotes.CONTENT_URI, contentValues,
                    null, null);
        }

        try {
            resolver.applyBatch(QuoteProvider.AUTHORITY, batchOperations);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
        for (QuoteStock quote : quotes) {
            // Load historical data for the quote
            loadHistoricalData(quote);
        }
    }

    private void loadHistoricalData(QuoteStock quote) {
        StringBuilder urlStringBuilder = new StringBuilder();
        urlStringBuilder.append(API.CHART_URL);
        urlStringBuilder.append(quote.getSymbol());
        urlStringBuilder.append(API.CHART_DATA);

        try {
            String response = fetchData(urlStringBuilder.toString());
            String str = response.substring(29,response.length()-1);
            ResponseStockHistoriaclData historiaclData = new Gson().fromJson(str,ResponseStockHistoriaclData.class);
            ArrayList<ResponseStockHistoriaclData.SeriesBean> seriesBeens =
                    (ArrayList<ResponseStockHistoriaclData.SeriesBean>) historiaclData.getSeries();
            if (historiaclData!=null){
                saveQuoteHistoricalData2Database(seriesBeens,quote.getSymbol());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveQuoteHistoricalData2Database(ArrayList<ResponseStockHistoriaclData.SeriesBean> seriesBeens, String symbol) {
        ContentResolver resolver = mContext.getContentResolver();
        ArrayList<ContentProviderOperation> batchOperations = new ArrayList<>();
        for (ResponseStockHistoriaclData.SeriesBean seriesBeen:seriesBeens){
            // First, we have to delete outdated date from DB.
            resolver.delete(QuoteProvider.QuotesHistoricData.CONTENT_URI,
                    QuoteHistoricalDataColumns.SYMBOL + " = \"" + symbol + "\"", null);

            batchOperations.add(Utils.buildBatchOperation(seriesBeen,symbol));
        }
        try {
            resolver.applyBatch(QuoteProvider.AUTHORITY, batchOperations);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }


    private String buildUrl(TaskParams taskParams) {
        ContentResolver resolver = mContext.getContentResolver();
        if (taskParams.getTag().equals(StockIntentService.ACTION_INIT)
                || taskParams.getTag().equals(TAG_PERIODIC)){
            isUpdate = true;
            Cursor cursor = resolver.query(QuoteProvider.Quotes.CONTENT_URI,
                    new String[]{"Distinct " + QuoteColumns.SYMBOL}, null,
                    null, null);
            if (cursor != null && cursor.getCount() == 0 || cursor == null) {
                // Init task. Populates DB with quotes for the symbols seen below
                return INIT_QUOTES;
            }else {
                if (cursor.getCount()==1){
                    isOnlyOneStock = true;
                    String stockInput = taskParams.getExtras().getString(StockIntentService.EXTRA_SYMBOL);
                    return "\"" + stockInput + "\"";
                }
                DatabaseUtils.dumpCursor(cursor);
                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    mStoredSymbols.append("\"");
                    mStoredSymbols.append(cursor.getString(
                            cursor.getColumnIndex(QuoteColumns.SYMBOL)));
                    mStoredSymbols.append("\",");
                    cursor.moveToNext();
                }
                mStoredSymbols.replace(mStoredSymbols.length() - 1, mStoredSymbols.length(), "");
                return mStoredSymbols.toString();
            }
        }else if (taskParams.getTag().equals(StockIntentService.ACTION_ADD)) {
            isUpdate = false;
            // Get symbol from params.getExtra and build query
            String stockInput = taskParams.getExtras().getString(StockIntentService.EXTRA_SYMBOL);
            return "\"" + stockInput + "\"";
        } else {
            throw new IllegalStateException(mContext.getResources().getString(R.string.error_action));
        }
    }

}
