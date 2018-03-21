package com.present.market.core.base;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class AppType {
    private static final Locale sAppLocale = Locale.getDefault();
    private static final Locale sDefaultLocale = Locale.UK;

    private static final String sSeparator = File.separator;
    public static String getFilePath(String dirPath, String fileName) {
        return dirPath.concat(sSeparator).concat(fileName);
    }
    //=============================================================================================

    public static final class AppAmount {
        private static String sDisplayFormat = "%.3f";
        public float value;
        public AppAmount(float amount) {
            super();
            this.value = amount;
        }
        public AppAmount(String amount) {
            this(Float.parseFloat(amount.replace(",", ".")));
        }

        public String toDisplay() {
            String result = String.format(sDefaultLocale, sDisplayFormat, this.value);
            return result;
        }
        public String toDisplay(String unit) {
            String result = String.format(sDefaultLocale, "%s %s", this.toDisplay(), unit);
            return result;
        }

        public boolean equals(AppAmount appAmount) {
            return (this.value == appAmount.value);
        }
    }
    //=============================================================================================

    public static final class AppDate {
        private static final String sDefaultFormat = "dd.MM.yyyy";
        private final String mDisplayDefault = "--.--.----";

        public Calendar value;

        public AppDate() {
            super();
        }
        public AppDate(String date, String format) {
            this();
            SimpleDateFormat dateFormat = new SimpleDateFormat(format, sDefaultLocale);
            try {
                Date parseDate = dateFormat.parse(date);
                this.value = Calendar.getInstance();
                this.value.setTime(parseDate);
            } catch (ParseException ex) {
            }
        }
        public AppDate(String date) {
            this(date, sDefaultFormat);
        }

        public String toDisplay() {
            String result;
            if (this.value == null) result = this.mDisplayDefault;
            else result = String.format("%s %s %s, %s", this.value.get(Calendar.DAY_OF_MONTH),
                    this.value.getDisplayName(Calendar.MONTH, Calendar.LONG, sAppLocale),
                    this.value.get(Calendar.YEAR),
                    this.value.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, sAppLocale));
            return result;
        }
    }
}
