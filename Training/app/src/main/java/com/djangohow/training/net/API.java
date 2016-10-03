package com.djangohow.training.net;

/**
 * Created by django on 2016/9/30.
 */
public interface API {
    public static final String BASE_URL = "http://query.yahooapis.com/v1/public/yql?format=json&env=store://datatables.org/alltableswithkeys&q=";
    public static final String QUERY_URL_HEAD = "select * from yahoo.finance.quotes where symbol in (";
    public static final String QUERY_URL_END = ")";
    public static final String CHART_URL = "http://chartapi.finance.yahoo.com/instrument/1.0/";
    //range can be 1m month/1y year/ 1d day       format json xml csv
    public static final String CHART_DATA = "/chartdata;type=quote;range=1m/json";
}
