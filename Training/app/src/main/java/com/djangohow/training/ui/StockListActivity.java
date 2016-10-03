package com.djangohow.training.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.InputType;
import android.view.View;
import android.widget.LinearLayout;


import com.afollestad.materialdialogs.MaterialDialog;
import com.djangohow.training.R;
import com.djangohow.training.data.QuoteColumns;
import com.djangohow.training.data.QuoteProvider;
import com.djangohow.training.rest.QuoteCursorAdapter;
import com.djangohow.training.rest.RecyclerViewItemClickListener;
import com.djangohow.training.service.StockIntentService;
import com.djangohow.training.service.StockTaskService;
import com.djangohow.training.touch_helper.SimpleItemTouchHelperCallback;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class StockListActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>  ,RecyclerViewItemClickListener.OnItemClickListener{


    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.empty_state_no_connection)
    LinearLayout mEmptyStateNoConnection;
    @Bind(R.id.empty_state_no_stocks)
    View mEmptyStateNoStocks;
    @Bind(R.id.CoordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    private Context mContext;
    private Intent mServiceIntent;
    boolean isConnected;
    private MaterialDialog mDialog;
    private final static int CURSOR_LOADER_ID = 0;
    public static MyListener sListener;
    private QuoteCursorAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        sListener = new MyListener();
        //这个类用来查询当前网络状态，通知网络状态变化。
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(CONNECTIVITY_SERVICE);
        //描述目前网络的状态
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        isConnected = networkInfo != null &&
                networkInfo.isConnectedOrConnecting();
        // The intent service is for executing immediate pulls from the Yahoo API
        // GCMTaskService can only schedule tasks, they cannot execute immediately
        mServiceIntent = new Intent(this, StockIntentService.class);
        if (savedInstanceState == null) {
            // Run the initialize task service so that some stocks appear upon an empty database
            mServiceIntent.putExtra(StockIntentService.EXTRA_TAG, StockIntentService.ACTION_INIT);
            if (isConnected) {
                startService(mServiceIntent);
            } else {
                Snackbar.make(mCoordinatorLayout, getString(R.string.no_internet_connection),
                        Snackbar.LENGTH_LONG).show();
            }
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(this, this));

        mAdapter = new QuoteCursorAdapter(this, null);
        mRecyclerView.setAdapter(mAdapter);

        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        if (isConnected) {
            long period = 3600L;
            long flex = 10L;
            String periodicTag = "periodic";

            // create a periodic task to pull stocks once every hour after the app has been opened.
            // This is so Widget data stays up to date.
            PeriodicTask periodicTask = new PeriodicTask.Builder()
                    .setService(StockTaskService.class)
                    .setPeriod(period)
                    .setFlex(flex)
                    .setTag(periodicTag)
                    .setRequiredNetwork(Task.NETWORK_STATE_CONNECTED)
                    .setRequiresCharging(false)
                    .build();
            // Schedule task with tag "periodic."
            //This ensure that only the stocks present in the DB are updated.
            GcmNetworkManager.getInstance(this).schedule(periodicTask);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(mContext, QuoteProvider.Quotes.CONTENT_URI,
                new String[]{QuoteColumns._ID, QuoteColumns.SYMBOL, QuoteColumns.BIDPRICE,
                QuoteColumns.PERCENT_CHANGE, QuoteColumns.CHANGE, QuoteColumns.ISUP},
                QuoteColumns.ISCURRENT + " = ?",
                new String[]{"1"},null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data==null){
            if (!isConnected){
                mEmptyStateNoConnection.setVisibility(View.VISIBLE);
            } else {
                mEmptyStateNoStocks.setVisibility(View.VISIBLE);
            }
        } else {
            mAdapter.swapCursor(data);

            mEmptyStateNoConnection.setVisibility(View.GONE);
            mEmptyStateNoStocks.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void addStockQuote(String s) {
        final String str = s.toUpperCase();
        new AsyncTask<Void, Void, Boolean>(){

            @Override
            protected Boolean doInBackground(Void... params) {
                Cursor cursor = getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,
                        new String[]{QuoteColumns.SYMBOL},
                        QuoteColumns.SYMBOL + "= ?",
                        new String[]{str},
                        null);
                if (cursor != null) {
                    cursor.close();
                    return cursor.getCount() != 0;
                }
                return Boolean.FALSE;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean) {
                    Snackbar.make(mCoordinatorLayout, R.string.stock_already_saved,
                            Snackbar.LENGTH_LONG).show();
                } else {
                    Intent stockIntentService = new Intent(StockListActivity.this,
                            StockIntentService.class);
                    stockIntentService.putExtra(StockIntentService.EXTRA_TAG, StockIntentService.ACTION_ADD);
                    stockIntentService.putExtra(StockIntentService.EXTRA_SYMBOL, str);
                    startService(stockIntentService);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @OnClick(R.id.fab)
    public void showDialogForAddingStock() {
        if (isConnected) {
            mDialog = new MaterialDialog.Builder(mContext)
                    .title(R.string.add_symbol)
                    .autoDismiss(true)
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .positiveText(R.string.add)
                    .negativeText(R.string.disagree)
                    .input(null, null, false, new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                            addStockQuote(input.toString());
                        }
                    })
                    .build();
            mDialog.show();
        } else {
            Snackbar.make(mCoordinatorLayout, R.string.no_internet_connection, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.try_again, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showDialogForAddingStock();
                        }
                    }).show();
        }
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(mContext, StockDetailActivity.class);
        intent.putExtra(Intent.EXTRA_REFERRER_NAME, mAdapter.getSymbol(position));
        startActivity(intent);
    }

    public class MyListener implements StockTaskService.StockExistListener{

        @Override
        public void tell(Boolean aBoolean) {
            Snackbar.make(mCoordinatorLayout,R.string.stock_is_not_exist,Snackbar.LENGTH_LONG).show();
        }
    }

}
