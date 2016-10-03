package com.djangohow.training.net;

import java.util.List;

/**
 * Created by django on 2016/10/1.
 */
public class ResponseStockHistoriaclData {

    /**
     * uri : /instrument/1.0/GOOG/chartdata;type=quote;range=1m/json
     * ticker : goog
     * Company-Name : Alphabet Inc.
     * Exchange-Name : NMS
     * unit : DAY
     * timestamp :
     * first-trade : 20040819
     * last-trade : 20160930
     * currency : USD
     * previous_close_price : 767.05
     */

    private MetaBean meta;
    /**
     * min : 20160901
     * max : 20160930
     */

    private DateBean Date;
    /**
     * close : {"min":759.66,"max":787.21}
     * high : {"min":766.22,"max":789.85}
     * low : {"min":754,"max":784.15}
     * open : {"min":755.13,"max":786.59}
     * volume : {"min":893700,"max":2046000}
     */

    private RangesBean ranges;
    /**
     * Date : {"min":20160901,"max":20160930}
     * labels : [20160901,20160906,20160912,20160919,20160926]
     * ranges : {"close":{"min":759.66,"max":787.21},"high":{"min":766.22,"max":789.85},"low":{"min":754,"max":784.15},"open":{"min":755.13,"max":786.59},"volume":{"min":893700,"max":2046000}}
     * series : [{"Date":20160901,"close":768.78,"high":771.02,"low":764.3,"open":769.25,"volume":923900},{"Date":20160902,"close":771.46,"high":773.92,"low":768.41,"open":773.01,"volume":1069300},{"Date":20160906,"close":780.08,"high":782,"low":771,"open":773.45,"volume":1439900},{"Date":20160907,"close":780.35,"high":782.73,"low":776.2,"open":780,"volume":893700},{"Date":20160908,"close":775.32,"high":780.35,"low":773.58,"open":778.59,"volume":1260600},{"Date":20160909,"close":759.66,"high":773.245,"low":759.66,"open":770.1,"volume":1812200},{"Date":20160912,"close":769.02,"high":770.29,"low":754,"open":755.13,"volume":1286800},{"Date":20160913,"close":759.69,"high":766.22,"low":755.8,"open":764.48,"volume":1379900},{"Date":20160914,"close":762.49,"high":767.68,"low":759.11,"open":759.61,"volume":1087400},{"Date":20160915,"close":771.76,"high":773.8,"low":759.96,"open":762.89,"volume":1305100},{"Date":20160916,"close":768.88,"high":769.75,"low":764.66,"open":769.75,"volume":2046000},{"Date":20160919,"close":765.7,"high":774,"low":764.441,"open":772.42,"volume":1171100},{"Date":20160920,"close":771.41,"high":773.33,"low":768.53,"open":769,"volume":975300},{"Date":20160921,"close":776.22,"high":777.16,"low":768.301,"open":772.66,"volume":1151400},{"Date":20160922,"close":787.21,"high":789.85,"low":778.44,"open":780,"volume":1463600},{"Date":20160923,"close":786.9,"high":788.93,"low":784.15,"open":786.59,"volume":1409500},{"Date":20160926,"close":774.21,"high":782.74,"low":773.07,"open":782.74,"volume":1508500},{"Date":20160927,"close":783.01,"high":785.99,"low":774.308,"open":775.5,"volume":1138800},{"Date":20160928,"close":781.56,"high":781.81,"low":774.97,"open":777.85,"volume":1107900},{"Date":20160929,"close":775.01,"high":785.8,"low":774.232,"open":781.44,"volume":1301400},{"Date":20160930,"close":777.29,"high":780.94,"low":774.09,"open":776.33,"volume":1580300}]
     */

    private List<Integer> labels;
    /**
     * Date : 20160901
     * close : 768.78
     * high : 771.02
     * low : 764.3
     * open : 769.25
     * volume : 923900
     */

    private List<SeriesBean> series;

    public DateBean getDate() {
        return Date;
    }

    public void setDate(DateBean Date) {
        this.Date = Date;
    }

    public RangesBean getRanges() {
        return ranges;
    }

    public void setRanges(RangesBean ranges) {
        this.ranges = ranges;
    }

    public List<Integer> getLabels() {
        return labels;
    }

    public void setLabels(List<Integer> labels) {
        this.labels = labels;
    }

    public List<SeriesBean> getSeries() {
        return series;
    }

    public void setSeries(List<SeriesBean> series) {
        this.series = series;
    }

    public static class DateBean {
        private int min;
        private int max;

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }
    }

    public static class RangesBean {
        /**
         * min : 759.66
         * max : 787.21
         */

        private CloseBean close;
        /**
         * min : 766.22
         * max : 789.85
         */

        private HighBean high;
        /**
         * min : 754
         * max : 784.15
         */

        private LowBean low;
        /**
         * min : 755.13
         * max : 786.59
         */

        private OpenBean open;
        /**
         * min : 893700
         * max : 2046000
         */

        private VolumeBean volume;

        public CloseBean getClose() {
            return close;
        }

        public void setClose(CloseBean close) {
            this.close = close;
        }

        public HighBean getHigh() {
            return high;
        }

        public void setHigh(HighBean high) {
            this.high = high;
        }

        public LowBean getLow() {
            return low;
        }

        public void setLow(LowBean low) {
            this.low = low;
        }

        public OpenBean getOpen() {
            return open;
        }

        public void setOpen(OpenBean open) {
            this.open = open;
        }

        public VolumeBean getVolume() {
            return volume;
        }

        public void setVolume(VolumeBean volume) {
            this.volume = volume;
        }

        public static class CloseBean {
            private double min;
            private double max;

            public double getMin() {
                return min;
            }

            public void setMin(double min) {
                this.min = min;
            }

            public double getMax() {
                return max;
            }

            public void setMax(double max) {
                this.max = max;
            }
        }

        public static class HighBean {
            private double min;
            private double max;

            public double getMin() {
                return min;
            }

            public void setMin(double min) {
                this.min = min;
            }

            public double getMax() {
                return max;
            }

            public void setMax(double max) {
                this.max = max;
            }
        }

        public static class LowBean {
            private float min;
            private double max;

            public float getMin() {
                return min;
            }

            public void setMin(float min) {
                this.min = min;
            }

            public double getMax() {
                return max;
            }

            public void setMax(double max) {
                this.max = max;
            }
        }

        public static class OpenBean {
            private double min;
            private double max;

            public double getMin() {
                return min;
            }

            public void setMin(double min) {
                this.min = min;
            }

            public double getMax() {
                return max;
            }

            public void setMax(double max) {
                this.max = max;
            }
        }

        public static class VolumeBean {
            private int min;
            private int max;

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }
        }
    }

    public static class SeriesBean {
        private int Date;
        private double close;
        private double high;
        private double low;
        private double open;
        private int volume;

        public int getDate() {
            return Date;
        }

        public void setDate(int Date) {
            this.Date = Date;
        }

        public double getClose() {
            return close;
        }

        public void setClose(double close) {
            this.close = close;
        }

        public double getHigh() {
            return high;
        }

        public void setHigh(double high) {
            this.high = high;
        }

        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
        }

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }
    }
}
