package com.present.market.core.base;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class AppType {
    private static final Locale sAppLocale = Locale.getDefault();

    public static boolean OBJ_IS_NULL(Object obj) {
        return (obj == null);
    }
    public static boolean OBJ_IS_NOT_NULL(Object obj) {
        return !OBJ_IS_NULL(obj);
    }

    private static final String sSeparator = File.separator;
    public static String getFilePath(String dirPath, String fileName) {
        return dirPath.concat(sSeparator).concat(fileName);
    }
    //=============================================================================================
//TODO:!
    public static final class AppAmount extends AbsObj {
        private static String sDisplayFormat = "%.4f";
        public float value;
        public AppAmount(float amount) {
            super();
            log().trace("amount=%s", amount);
            this.value = amount;
        }
        public AppAmount(String amount) {
            this(Float.parseFloat(amount.replace(",", ".")));
        }

        public final String toDisplay() {
            String result = String.format(sAppLocale, sDisplayFormat, this.value);
            log().trace("toDisplay.result=%s", result);
            return result;
        }
        public final String toDisplay(String unit) {
            String result = String.format(sAppLocale, "%s %s", this.toDisplay(), unit);
            log().trace("toDisplay.result=%s", result);
            return result;
        }

        public final boolean equals(AppAmount appAmount) {
            return (this.value == appAmount.value);
        }
    }
    //=============================================================================================
    public static final class AppDate extends AbsObj {
        private static final String sDefaultFormat = "dd.MM.yyyy";
        private static String sDisplayDefault = "--.--.----";

        private Calendar value;

        public AppDate() {
            super();
        }
        public AppDate(String date, String format) {
            this();
            log().trace("date=%s,format=%s", date, format);
            SimpleDateFormat dateFormat = new SimpleDateFormat(format, sAppLocale);
            try {
                Date parseDate = dateFormat.parse(date);
                this.value = Calendar.getInstance();
                this.value.setTime(parseDate);
            } catch (ParseException ex) {
                log().error(ex, "Error parse date '%s' by format '%s'", date, format);
            }
        }
        public AppDate(String date) {
            this(date, sDefaultFormat);
        }

        public final String toDisplay() {
            String result;
            if (OBJ_IS_NULL(this.value)) result = sDisplayDefault;
            else result = String.format("%s %s %s, %s", this.value.get(Calendar.DAY_OF_MONTH),
                    this.value.getDisplayName(Calendar.MONTH, Calendar.LONG, sAppLocale),
                    this.value.get(Calendar.YEAR),
                    value.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, sAppLocale));
            log().trace("toDisplay.result=%s", result);
            return result;
        }
    }
}
