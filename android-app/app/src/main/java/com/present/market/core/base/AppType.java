package com.present.market.core.base;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
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

    //=============================================================================================

    public static final class AppAmount extends AbsObj {
        private static String sDisplayFormat = "%.4f";
        public float value;
        public AppAmount(float amount) {
            super();
            log().debug("amount=%s", amount);
            this.value = amount;
            log().debug("value=%s", this.value);
        }
        public AppAmount(String amount) {
            this(Float.parseFloat(amount.replace(",", ".")));
        }

        public final String toDisplay() {
            String result = String.format(sAppLocale, sDisplayFormat, this.value);
            log().debug("toDisplay.result=%s", result);
            return result;
        }
        public final String toDisplay(String unit) {
            String result = String.format(sAppLocale, "%s %s", this.toDisplay(), unit);
            log().debug("toDisplay.result=%s", result);
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
            log().debug("date=%s,format=%s", date, format);
            SimpleDateFormat dateFormat = new SimpleDateFormat(format, sAppLocale);
            try {
                Date parseDate = dateFormat.parse(date);
                this.value = Calendar.getInstance();
                this.value.setTime(parseDate);
            } catch (ParseException ex) {
                log().error(ex, "Error parse date '%s' by format '%s'", date, format);
            }
            log().debug("value=%s", this.value);
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
            log().debug("toDisplay.result=%s", result);
            return result;
        }
    }

    //=============================================================================================

    public static final class AppFile extends AbsObj {
        private static final String sSeparator = File.separator;

        private final String mPath;
        public AppFile(String dirPath, String fileName) {
            super();
            log().debug("dirPath=%s,fileName=%s", dirPath, fileName);
            this.mPath = dirPath.concat(sSeparator).concat(fileName);
            log().debug("mPath=%s", this.mPath);
        }

        public boolean exists() {
            boolean result = new File(this.mPath).exists();
            log().debug("exists.result=%s", result);
            return result;
        }

        public <XmlObj> XmlObj readXml(Class<XmlObj> className) throws AppEx {
            log().debug("readXml.className=%s", className);
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(this.mPath);
                //TODO: AnnotationStrategy???
                XmlObj xmlObj = new Persister(new AnnotationStrategy()).read(className, inputStream);
                return xmlObj;
            } catch (FileNotFoundException fex) {
                throw new AppEx(fex, "Error file '%s' - not found", this.mPath);
            } catch (Exception ex) {
                throw new AppEx(ex, "Error reading '%s' from InputStream", className);
            } finally {
                if (AppType.OBJ_IS_NOT_NULL(inputStream)) {
                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        log().error(ex, "Error close stream Closeable");
                    }
                }
            }
        }

        public <XmlObj> void writeXml(XmlObj xmlObj) throws AppEx {
            log().debug("writeXml.xmlObj=%s", xmlObj);
            OutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(this.mPath);
                //TODO: AnnotationStrategy???
                new Persister(new AnnotationStrategy()).write(xmlObj, outputStream);
            } catch (FileNotFoundException fex) {
                throw new AppEx(fex, "Error file '%s' - not found", this.mPath);
            } catch (Exception ex) {
                throw new AppEx(ex, "Error writing '%s' to OutputStream", xmlObj);
            } finally {
                if (AppType.OBJ_IS_NOT_NULL(outputStream)) {
                    try {
                        outputStream.close();
                    } catch (IOException ex) {
                        log().error(ex, "Error close stream Closeable");
                    }
                }
            }
        }
    }

    //=============================================================================================

    //TODO: AppSource
    public static final class AppUrl extends AbsObj {
        private final String mUrl;
        public AppUrl(String url) {
            super();
            log().debug("url=%s", url);
            this.mUrl = url;
        }

        public <XmlObj> XmlObj readXml(Class<XmlObj> className) throws AppEx {
            log().debug("readXml.className=%s", className);
            InputStream inputStream = null;
            try {
                inputStream = new URL(this.mUrl).openStream();
                XmlObj values = new Persister(new AnnotationStrategy()).read(className, inputStream);
                return values;
            } catch (MalformedURLException mex) {
                throw new AppEx(mex, "Error url '%s'", this.mUrl);
            } catch (IOException ioex) {
                throw new AppEx(ioex, "Error opening url connection '%s'", this.mUrl);
            } catch (Exception ex) {
                throw new AppEx(ex, "Error reading '%s' from InputStream", className);
            } finally {
                if (AppType.OBJ_IS_NOT_NULL(inputStream)) {
                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        log().error(ex, "Error close stream Closeable");
                    }
                }
            }
        }
    }
}
