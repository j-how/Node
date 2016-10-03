package com.djangohow.training.net;

import android.content.ContentProviderOperation;

import com.djangohow.training.data.QuoteColumns;
import com.djangohow.training.data.QuoteHistoricalDataColumns;
import com.djangohow.training.data.QuoteProvider;

/**
 * Created by sam_chordas on 10/8/15.
 */
public class Utils {

  private static String LOG_TAG = Utils.class.getSimpleName();

  public static boolean showPercent = true;


  public static String truncateBidPrice(String bidPrice){
    bidPrice = String.format("%.2f", Float.parseFloat(bidPrice));
    return bidPrice;
  }

  public static String truncateChange(String change, boolean isPercentChange){
    String weight = change.substring(0,1);
    String ampersand = "";
    if (isPercentChange){
      ampersand = change.substring(change.length() - 1, change.length());
      change = change.substring(0, change.length() - 1);
    }
    change = change.substring(1, change.length());
    double round = (double) Math.round(Double.parseDouble(change) * 100) / 100;
    change = String.format("%.2f", round);
    StringBuffer changeBuffer = new StringBuffer(change);
    changeBuffer.insert(0, weight);
    changeBuffer.append(ampersand);
    change = changeBuffer.toString();
    return change;
  }

  public static ContentProviderOperation buildBatchOperation(QuoteStock quote){
    ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(
        QuoteProvider.Quotes.CONTENT_URI);

      String change = quote.getChange();
      builder.withValue(QuoteColumns.SYMBOL, quote.getSymbol());
      builder.withValue(QuoteColumns.BIDPRICE, truncateBidPrice(quote.getBid()));
      builder.withValue(QuoteColumns.PERCENT_CHANGE, truncateChange(
          quote.getChangeinPercent(), true));
      builder.withValue(QuoteColumns.CHANGE, truncateChange(change, false));
      builder.withValue(QuoteColumns.ISCURRENT, 1);
      if (change.charAt(0) == '-'){
        builder.withValue(QuoteColumns.ISUP, 0);
      }else{
        builder.withValue(QuoteColumns.ISUP, 1);
      }
    builder.withValue(QuoteColumns.NAME, quote.getName());
    return builder.build();
  }

  public static ContentProviderOperation buildBatchOperation(ResponseStockHistoriaclData.SeriesBean seriesBeen, String symbol) {
    ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(
            QuoteProvider.QuotesHistoricData.CONTENT_URI);
    builder.withValue(QuoteHistoricalDataColumns.SYMBOL, symbol);
    builder.withValue(QuoteHistoricalDataColumns.BIDPRICE, seriesBeen.getOpen());
    builder.withValue(QuoteHistoricalDataColumns.DATE, seriesBeen.getDate());
    return builder.build();
  }
}
