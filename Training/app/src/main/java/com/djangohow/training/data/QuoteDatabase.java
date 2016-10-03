package com.djangohow.training.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by django on 2016/9/21.
 */
@Database(version = QuoteDatabase.VERSION)
public class QuoteDatabase {
    private QuoteDatabase(){};
    public static final int VERSION = 2;
    @Table(QuoteColumns.class)
    public static final String QUOTES = "quotes";
    @Table(QuoteHistoricalDataColumns.class)
    public static final String QUOTES_HISTORICAL_DATA = "quotes_historical_data";
}
