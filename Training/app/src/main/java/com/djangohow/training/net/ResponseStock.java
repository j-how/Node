package com.djangohow.training.net;

/**
 * Created by django on 2016/9/30.
 */
public class ResponseStock {

    /**
     * count : 1
     * created : 2016-09-30T08:23:44Z
     * lang : zh-CN
     * results : {"quote":{"symbol":"YHOO","Ask":"42.84","AverageDailyVolume":"11984500","Bid":"42.39","AskRealtime":null,"BidRealtime":null,"BookValue":"29.83","Change_PercentChange":"-1.12 - -2.56%","Change":"-1.12","Commission":null,"Currency":"USD","ChangeRealtime":null,"AfterHoursChangeRealtime":null,"DividendShare":null,"LastTradeDate":"9/29/2016","TradeDate":null,"EarningsShare":"-5.18","ErrorIndicationreturnedforsymbolchangedinvalid":null,"EPSEstimateCurrentYear":"0.49","EPSEstimateNextYear":"0.57","EPSEstimateNextQuarter":"0.17","DaysLow":"42.43","DaysHigh":"43.30","YearLow":"26.15","YearHigh":"44.92","HoldingsGainPercent":null,"AnnualizedGain":null,"HoldingsGain":null,"HoldingsGainPercentRealtime":null,"HoldingsGainRealtime":null,"MoreInfo":null,"OrderBookRealtime":null,"MarketCapitalization":"40.52B","MarketCapRealtime":null,"EBITDA":"151.08M","ChangeFromYearLow":"16.42","PercentChangeFromYearLow":"+62.79%","LastTradeRealtimeWithTime":null,"ChangePercentRealtime":null,"ChangeFromYearHigh":"-2.35","PercebtChangeFromYearHigh":"-5.23%","LastTradeWithTime":"4:00pm - <b>42.57<\/b>","LastTradePriceOnly":"42.57","HighLimit":null,"LowLimit":null,"DaysRange":"42.43 - 43.30","DaysRangeRealtime":null,"FiftydayMovingAverage":"43.03","TwoHundreddayMovingAverage":"38.56","ChangeFromTwoHundreddayMovingAverage":"4.01","PercentChangeFromTwoHundreddayMovingAverage":"+10.39%","ChangeFromFiftydayMovingAverage":"-0.46","PercentChangeFromFiftydayMovingAverage":"-1.07%","Name":"Yahoo! Inc.","Notes":null,"Open":"43.17","PreviousClose":"43.69","PricePaid":null,"ChangeinPercent":"-2.56%","PriceSales":"8.50","PriceBook":"1.46","ExDividendDate":null,"PERatio":null,"DividendPayDate":null,"PERatioRealtime":null,"PEGRatio":"-24.06","PriceEPSEstimateCurrentYear":"86.88","PriceEPSEstimateNextYear":"74.68","Symbol":"YHOO","SharesOwned":null,"ShortRatio":"4.48","LastTradeTime":"4:00pm","TickerTrend":null,"OneyrTargetPrice":"42.75","Volume":"13720746","HoldingsValue":null,"HoldingsValueRealtime":null,"YearRange":"26.15 - 44.92","DaysValueChange":null,"DaysValueChangeRealtime":null,"StockExchange":"NMS","DividendYield":null,"PercentChange":"-2.56%"}}
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
        /**
         * quote : {"symbol":"YHOO","Ask":"42.84","AverageDailyVolume":"11984500","Bid":"42.39","AskRealtime":null,"BidRealtime":null,"BookValue":"29.83","Change_PercentChange":"-1.12 - -2.56%","Change":"-1.12","Commission":null,"Currency":"USD","ChangeRealtime":null,"AfterHoursChangeRealtime":null,"DividendShare":null,"LastTradeDate":"9/29/2016","TradeDate":null,"EarningsShare":"-5.18","ErrorIndicationreturnedforsymbolchangedinvalid":null,"EPSEstimateCurrentYear":"0.49","EPSEstimateNextYear":"0.57","EPSEstimateNextQuarter":"0.17","DaysLow":"42.43","DaysHigh":"43.30","YearLow":"26.15","YearHigh":"44.92","HoldingsGainPercent":null,"AnnualizedGain":null,"HoldingsGain":null,"HoldingsGainPercentRealtime":null,"HoldingsGainRealtime":null,"MoreInfo":null,"OrderBookRealtime":null,"MarketCapitalization":"40.52B","MarketCapRealtime":null,"EBITDA":"151.08M","ChangeFromYearLow":"16.42","PercentChangeFromYearLow":"+62.79%","LastTradeRealtimeWithTime":null,"ChangePercentRealtime":null,"ChangeFromYearHigh":"-2.35","PercebtChangeFromYearHigh":"-5.23%","LastTradeWithTime":"4:00pm - <b>42.57<\/b>","LastTradePriceOnly":"42.57","HighLimit":null,"LowLimit":null,"DaysRange":"42.43 - 43.30","DaysRangeRealtime":null,"FiftydayMovingAverage":"43.03","TwoHundreddayMovingAverage":"38.56","ChangeFromTwoHundreddayMovingAverage":"4.01","PercentChangeFromTwoHundreddayMovingAverage":"+10.39%","ChangeFromFiftydayMovingAverage":"-0.46","PercentChangeFromFiftydayMovingAverage":"-1.07%","Name":"Yahoo! Inc.","Notes":null,"Open":"43.17","PreviousClose":"43.69","PricePaid":null,"ChangeinPercent":"-2.56%","PriceSales":"8.50","PriceBook":"1.46","ExDividendDate":null,"PERatio":null,"DividendPayDate":null,"PERatioRealtime":null,"PEGRatio":"-24.06","PriceEPSEstimateCurrentYear":"86.88","PriceEPSEstimateNextYear":"74.68","Symbol":"YHOO","SharesOwned":null,"ShortRatio":"4.48","LastTradeTime":"4:00pm","TickerTrend":null,"OneyrTargetPrice":"42.75","Volume":"13720746","HoldingsValue":null,"HoldingsValueRealtime":null,"YearRange":"26.15 - 44.92","DaysValueChange":null,"DaysValueChangeRealtime":null,"StockExchange":"NMS","DividendYield":null,"PercentChange":"-2.56%"}
         */

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
             * Ask : 42.84
             * AverageDailyVolume : 11984500
             * Bid : 42.39
             * AskRealtime : null
             * BidRealtime : null
             * BookValue : 29.83
             * Change_PercentChange : -1.12 - -2.56%
             * Change : -1.12
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
             * DaysLow : 42.43
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
             * MarketCapitalization : 40.52B
             * MarketCapRealtime : null
             * EBITDA : 151.08M
             * ChangeFromYearLow : 16.42
             * PercentChangeFromYearLow : +62.79%
             * LastTradeRealtimeWithTime : null
             * ChangePercentRealtime : null
             * ChangeFromYearHigh : -2.35
             * PercebtChangeFromYearHigh : -5.23%
             * LastTradeWithTime : 4:00pm - <b>42.57</b>
             * LastTradePriceOnly : 42.57
             * HighLimit : null
             * LowLimit : null
             * DaysRange : 42.43 - 43.30
             * DaysRangeRealtime : null
             * FiftydayMovingAverage : 43.03
             * TwoHundreddayMovingAverage : 38.56
             * ChangeFromTwoHundreddayMovingAverage : 4.01
             * PercentChangeFromTwoHundreddayMovingAverage : +10.39%
             * ChangeFromFiftydayMovingAverage : -0.46
             * PercentChangeFromFiftydayMovingAverage : -1.07%
             * Name : Yahoo! Inc.
             * Notes : null
             * Open : 43.17
             * PreviousClose : 43.69
             * PricePaid : null
             * ChangeinPercent : -2.56%
             * PriceSales : 8.50
             * PriceBook : 1.46
             * ExDividendDate : null
             * PERatio : null
             * DividendPayDate : null
             * PERatioRealtime : null
             * PEGRatio : -24.06
             * PriceEPSEstimateCurrentYear : 86.88
             * PriceEPSEstimateNextYear : 74.68
             * Symbol : YHOO
             * SharesOwned : null
             * ShortRatio : 4.48
             * LastTradeTime : 4:00pm
             * TickerTrend : null
             * OneyrTargetPrice : 42.75
             * Volume : 13720746
             * HoldingsValue : null
             * HoldingsValueRealtime : null
             * YearRange : 26.15 - 44.92
             * DaysValueChange : null
             * DaysValueChangeRealtime : null
             * StockExchange : NMS
             * DividendYield : null
             * PercentChange : -2.56%
             */

            private QuoteStock quote;

            public QuoteStock getQuote() {
                return quote;
            }

            public void setQuote(QuoteStock quote) {
                this.quote = quote;
            }

        }
    }
}
