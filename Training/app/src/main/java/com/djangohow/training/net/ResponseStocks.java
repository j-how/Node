package com.djangohow.training.net;

import java.util.List;

/**
 * Created by django on 2016/9/30.
 */
public class ResponseStocks {

    /**
     * count : 2
     * created : 2016-09-30T08:28:00Z
     * lang : zh-CN
     * results : {"quote":[{"symbol":"YHOO","Ask":"42.62","AverageDailyVolume":"11941700","Bid":"42.61","AskRealtime":null,"BidRealtime":null,"BookValue":"29.83","Change_PercentChange":"-1.08 - -2.47%","Change":"-1.08","Commission":null,"Currency":"USD","ChangeRealtime":null,"AfterHoursChangeRealtime":null,"DividendShare":null,"LastTradeDate":"9/29/2016","TradeDate":null,"EarningsShare":"-5.18","ErrorIndicationreturnedforsymbolchangedinvalid":null,"EPSEstimateCurrentYear":"0.49","EPSEstimateNextYear":"0.57","EPSEstimateNextQuarter":"0.17","DaysLow":"42.46","DaysHigh":"43.30","YearLow":"26.15","YearHigh":"44.92","HoldingsGainPercent":null,"AnnualizedGain":null,"HoldingsGain":null,"HoldingsGainPercentRealtime":null,"HoldingsGainRealtime":null,"MoreInfo":null,"OrderBookRealtime":null,"MarketCapitalization":"40.56B","MarketCapRealtime":null,"EBITDA":"151.08M","ChangeFromYearLow":"16.46","PercentChangeFromYearLow":"+62.94%","LastTradeRealtimeWithTime":null,"ChangePercentRealtime":null,"ChangeFromYearHigh":"-2.31","PercebtChangeFromYearHigh":"-5.14%","LastTradeWithTime":"12:47pm - <b>42.61<\/b>","LastTradePriceOnly":"42.61","HighLimit":null,"LowLimit":null,"DaysRange":"42.46 - 43.30","DaysRangeRealtime":null,"FiftydayMovingAverage":"42.96","TwoHundreddayMovingAverage":"38.53","ChangeFromTwoHundreddayMovingAverage":"4.08","PercentChangeFromTwoHundreddayMovingAverage":"+10.57%","ChangeFromFiftydayMovingAverage":"-0.35","PercentChangeFromFiftydayMovingAverage":"-0.80%","Name":"Yahoo! Inc.","Notes":null,"Open":"43.17","PreviousClose":"43.69","PricePaid":null,"ChangeinPercent":"-2.47%","PriceSales":"8.50","PriceBook":"1.46","ExDividendDate":null,"PERatio":null,"DividendPayDate":null,"PERatioRealtime":null,"PEGRatio":"-24.06","PriceEPSEstimateCurrentYear":"86.96","PriceEPSEstimateNextYear":"74.75","Symbol":"YHOO","SharesOwned":null,"ShortRatio":"4.48","LastTradeTime":"12:47pm","TickerTrend":null,"OneyrTargetPrice":"42.75","Volume":"4834748","HoldingsValue":null,"HoldingsValueRealtime":null,"YearRange":"26.15 - 44.92","DaysValueChange":null,"DaysValueChangeRealtime":null,"StockExchange":"NMS","DividendYield":null,"PercentChange":"-2.47%"},{"symbol":"GOOG","Ask":"778.56","AverageDailyVolume":"1320000","Bid":"778.25","AskRealtime":null,"BidRealtime":null,"BookValue":"186.20","Change_PercentChange":"-3.26 - -0.42%","Change":"-3.26","Commission":null,"Currency":"USD","ChangeRealtime":null,"AfterHoursChangeRealtime":null,"DividendShare":null,"LastTradeDate":"9/29/2016","TradeDate":null,"EarningsShare":"25.81","ErrorIndicationreturnedforsymbolchangedinvalid":null,"EPSEstimateCurrentYear":"34.30","EPSEstimateNextYear":"40.63","EPSEstimateNextQuarter":"9.67","DaysLow":"778.23","DaysHigh":"785.80","YearLow":"599.85","YearHigh":"789.87","HoldingsGainPercent":null,"AnnualizedGain":null,"HoldingsGain":null,"HoldingsGainPercentRealtime":null,"HoldingsGainRealtime":null,"MoreInfo":null,"OrderBookRealtime":null,"MarketCapitalization":"534.90B","MarketCapRealtime":null,"EBITDA":"26.90B","ChangeFromYearLow":"178.45","PercentChangeFromYearLow":"+29.75%","LastTradeRealtimeWithTime":null,"ChangePercentRealtime":null,"ChangeFromYearHigh":"-11.57","PercebtChangeFromYearHigh":"-1.46%","LastTradeWithTime":"12:47pm - <b>778.30<\/b>","LastTradePriceOnly":"778.30","HighLimit":null,"LowLimit":null,"DaysRange":"778.23 - 785.80","DaysRangeRealtime":null,"FiftydayMovingAverage":"774.28","TwoHundreddayMovingAverage":"738.30","ChangeFromTwoHundreddayMovingAverage":"40.00","PercentChangeFromTwoHundreddayMovingAverage":"+5.42%","ChangeFromFiftydayMovingAverage":"4.02","PercentChangeFromFiftydayMovingAverage":"+0.52%","Name":"Alphabet Inc.","Notes":null,"Open":"781.44","PreviousClose":"781.56","PricePaid":null,"ChangeinPercent":"-0.42%","PriceSales":"6.57","PriceBook":"4.20","ExDividendDate":null,"PERatio":"30.16","DividendPayDate":null,"PERatioRealtime":null,"PEGRatio":"1.26","PriceEPSEstimateCurrentYear":"22.69","PriceEPSEstimateNextYear":"19.16","Symbol":"GOOG","SharesOwned":null,"ShortRatio":"1.62","LastTradeTime":"12:47pm","TickerTrend":null,"OneyrTargetPrice":"929.08","Volume":"632758","HoldingsValue":null,"HoldingsValueRealtime":null,"YearRange":"599.85 - 789.87","DaysValueChange":null,"DaysValueChangeRealtime":null,"StockExchange":"NMS","DividendYield":null,"PercentChange":"-0.42%"}]}
     */

    private QueryBean query;

    public QueryBean getQuery() {
        return query;
    }

    public void setQuery(QueryBean query) {
        this.query = query;
    }

    public static class QueryBean {
        private int count;
        private String created;
        private String lang;
        private ResultsBean results;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public ResultsBean getResults() {
            return results;
        }

        public void setResults(ResultsBean results) {
            this.results = results;
        }

        public static class ResultsBean {
            /**
             * symbol : YHOO
             * Ask : 42.62
             * AverageDailyVolume : 11941700
             * Bid : 42.61
             * AskRealtime : null
             * BidRealtime : null
             * BookValue : 29.83
             * Change_PercentChange : -1.08 - -2.47%
             * Change : -1.08
             * Commission : null
             * Currency : USD
             * ChangeRealtime : null
             * AfterHoursChangeRealtime : null
             * DividendShare : null
             * LastTradeDate : 9/29/2016
             * TradeDate : null
             * EarningsShare : -5.18
             * ErrorIndicationreturnedforsymbolchangedinvalid : null
             * EPSEstimateCurrentYear : 0.49
             * EPSEstimateNextYear : 0.57
             * EPSEstimateNextQuarter : 0.17
             * DaysLow : 42.46
             * DaysHigh : 43.30
             * YearLow : 26.15
             * YearHigh : 44.92
             * HoldingsGainPercent : null
             * AnnualizedGain : null
             * HoldingsGain : null
             * HoldingsGainPercentRealtime : null
             * HoldingsGainRealtime : null
             * MoreInfo : null
             * OrderBookRealtime : null
             * MarketCapitalization : 40.56B
             * MarketCapRealtime : null
             * EBITDA : 151.08M
             * ChangeFromYearLow : 16.46
             * PercentChangeFromYearLow : +62.94%
             * LastTradeRealtimeWithTime : null
             * ChangePercentRealtime : null
             * ChangeFromYearHigh : -2.31
             * PercebtChangeFromYearHigh : -5.14%
             * LastTradeWithTime : 12:47pm - <b>42.61</b>
             * LastTradePriceOnly : 42.61
             * HighLimit : null
             * LowLimit : null
             * DaysRange : 42.46 - 43.30
             * DaysRangeRealtime : null
             * FiftydayMovingAverage : 42.96
             * TwoHundreddayMovingAverage : 38.53
             * ChangeFromTwoHundreddayMovingAverage : 4.08
             * PercentChangeFromTwoHundreddayMovingAverage : +10.57%
             * ChangeFromFiftydayMovingAverage : -0.35
             * PercentChangeFromFiftydayMovingAverage : -0.80%
             * Name : Yahoo! Inc.
             * Notes : null
             * Open : 43.17
             * PreviousClose : 43.69
             * PricePaid : null
             * ChangeinPercent : -2.47%
             * PriceSales : 8.50
             * PriceBook : 1.46
             * ExDividendDate : null
             * PERatio : null
             * DividendPayDate : null
             * PERatioRealtime : null
             * PEGRatio : -24.06
             * PriceEPSEstimateCurrentYear : 86.96
             * PriceEPSEstimateNextYear : 74.75
             * Symbol : YHOO
             * SharesOwned : null
             * ShortRatio : 4.48
             * LastTradeTime : 12:47pm
             * TickerTrend : null
             * OneyrTargetPrice : 42.75
             * Volume : 4834748
             * HoldingsValue : null
             * HoldingsValueRealtime : null
             * YearRange : 26.15 - 44.92
             * DaysValueChange : null
             * DaysValueChangeRealtime : null
             * StockExchange : NMS
             * DividendYield : null
             * PercentChange : -2.47%
             */

            private List<QuoteStock> quote;

            public List<QuoteStock> getQuote() {
                return quote;
            }

            public void setQuote(List<QuoteStock> quote) {
                this.quote = quote;
            }

        }
    }
}
