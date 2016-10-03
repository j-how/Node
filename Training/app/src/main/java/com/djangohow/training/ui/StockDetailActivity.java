package com.djangohow.training.ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.djangohow.training.R;
import com.djangohow.training.data.QuoteColumns;
import com.djangohow.training.data.QuoteHistoricalDataColumns;
import com.djangohow.training.data.QuoteProvider;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StockDetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private BarData mBarData;
    private String mSymbol;
    private static final int CURSOR_LOADER_ID = 1;
    private static final int CURSOR_LOADER_ID_FOR_LINE_CHART = 2;
    public static final String ARG_SYMBOL = "ARG_SYMBOL";
    @Bind(R.id.chart)
    BarChart mChart;
    @Bind(R.id.seekBar2)
    SeekBar mSeekBar2;
    @Bind(R.id.seekBar1)
    SeekBar mSeekBar1;
    @Bind(R.id.tvXMax)
    TextView mTvXMax;
    @Bind(R.id.tvYMax)
    TextView mTvYMax;
    @Bind(R.id.stock_name)
    TextView mStockName;
    @Bind(R.id.stock_symbol)
    TextView mStockSymbol;
    @Bind(R.id.stock_bidprice)
    TextView mStockBidprice;
    @Bind(R.id.stock_change)
    TextView mStockChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_stock_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mSymbol = intent.getStringExtra(Intent.EXTRA_REFERRER_NAME);
        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
        getLoaderManager().initLoader(CURSOR_LOADER_ID_FOR_LINE_CHART, null, this);
    }

    /**
     * 用来处理视图的方法
     */
    private void showBarChart(BarChart chart, BarData barData) {

        chart.setData(barData); // 设置数据
        chart.setDescriptionColor(Color.GRAY);//数据的颜色
        chart.setBackgroundColor(Color.YELLOW);// 设置整个图表控件的背景
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id == CURSOR_LOADER_ID) {
            return new CursorLoader(this, QuoteProvider.Quotes.CONTENT_URI,
                    new String[]{QuoteColumns._ID, QuoteColumns.SYMBOL, QuoteColumns.BIDPRICE,
                            QuoteColumns.PERCENT_CHANGE, QuoteColumns.CHANGE, QuoteColumns.ISUP,
                            QuoteColumns.NAME},
                    QuoteColumns.SYMBOL + " = \"" + mSymbol + "\"",
                    null, null);
        } else if (id == CURSOR_LOADER_ID_FOR_LINE_CHART) {

            String sortOrder = QuoteColumns._ID + " ASC";

            return new CursorLoader(this, QuoteProvider.QuotesHistoricData.CONTENT_URI,
                    new String[]{QuoteHistoricalDataColumns._ID, QuoteHistoricalDataColumns.SYMBOL,
                            QuoteHistoricalDataColumns.BIDPRICE, QuoteHistoricalDataColumns.DATE},
                    QuoteHistoricalDataColumns.SYMBOL + " = \"" + mSymbol + "\"",
                    null, sortOrder);
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (loader.getId() == CURSOR_LOADER_ID && data != null && data.moveToFirst()) {

            String symbol = data.getString(data.getColumnIndex(QuoteColumns.SYMBOL));
            mStockSymbol.setText(getString(R.string.stock_detail_tab_header, symbol));

            String price = data.getString(data.getColumnIndex(QuoteColumns.BIDPRICE));
            mStockBidprice.setText(price);

            String name = data.getString(data.getColumnIndex(QuoteColumns.NAME));
            mStockName.setText(name);

            String change = data.getString(data.getColumnIndex(QuoteColumns.CHANGE));
            String percentChange = data.getString(data.getColumnIndex(QuoteColumns.PERCENT_CHANGE));
            String mixedChange = change + " (" + percentChange + ")";
            mStockChange.setText(mixedChange);

        } else if (loader.getId() == CURSOR_LOADER_ID_FOR_LINE_CHART && data != null &&
                data.moveToFirst()) {
            updateChart(data);
        }
    }

    private void updateChart(Cursor data) {
        ArrayList<String> xValues = new ArrayList<String>();
        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();

        int counter = -1;
        do {
            counter++;

            String date = data.getString(data.getColumnIndex(
                    QuoteHistoricalDataColumns.DATE));
            Float bidPrice = data.getFloat(data.getColumnIndex(
                    QuoteHistoricalDataColumns.BIDPRICE));

            // We have to show chart in right order.
            int x = data.getCount() - 1 - counter;
            xValues.add(date);
            yValues.add(new BarEntry(bidPrice,x));

        } while (data.moveToNext());
        // y轴的数据集合
        BarDataSet barDataSet = new BarDataSet(yValues, "collection");

        BarData barData = new BarData(xValues, barDataSet);
        showBarChart(mChart, barData);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
