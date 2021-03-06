package com.djangohow.training.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by django on 2016/9/28.
 */
@ContentProvider(authority = QuoteProvider.AUTHORITY, database = QuoteDatabase.class)
public class QuoteProvider {
    public static final String AUTHORITY = "com.djangohow.training.data.QuoteProvider";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    interface Path{
        String QUOTES = "quotes";
        String QUOTES_HISTORIC_DATA = "quotes_historical_data";
    }
    private static Uri buildUri(String... paths){
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path:paths){
            builder.appendPath(path);
        }
        return builder.build();
    }
    @TableEndpoint(table = QuoteDatabase.QUOTES_HISTORICAL_DATA)
    public static class QuotesHistoricData {
        @ContentUri(
                path = Path.QUOTES_HISTORIC_DATA,
                type = "vnd.android.cursor.dir/quote_historical_data"
        )
        public static final Uri CONTENT_URI = buildUri(Path.QUOTES_HISTORIC_DATA);
    }
    @TableEndpoint(table = QuoteDatabase.QUOTES)
    public static class Quotes{
        @ContentUri(
                path = Path.QUOTES,
                type = "vnd.android.cursor.dir/quote"
        )
        public static final Uri CONTENT_URI = buildUri(Path.QUOTES);
        @InexactContentUri(
                name = "QUOTE_ID",
                path = Path.QUOTES + "/*",
                type = "vnd.android.cursor.item/quote",
                whereColumn = QuoteColumns.SYMBOL,
                pathSegment = 1
        )
        public static Uri withSymbol(String symbol){
            return buildUri(Path.QUOTES, symbol);
        }
    }
}
