package com.present.market.core.store;

import com.present.market.core.base.AppEx;
import com.present.market.core.base.AppType;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public final class RemoteSource<Type> extends AbsSource<Type> {
    private final String mUrl;
    public RemoteSource(String url, Class<Type> classObj) {
        super(classObj);
        log().debug("url=%s", url);
        this.mUrl = url;
    }

    @Override
    protected void onLoadData(TaskResult<Type> taskResult) {
        log().todo("check date, version!");
        log().debug("load");
        try {
            taskResult.setResult(this.getData());
        } catch (AppEx appEx) {
            log().error(appEx, "Error loading data from remote Source");
            taskResult.setError(appEx);
        }
    }

    @Override
    protected void onSaveData(Type xmlObj, TaskResult<Void> taskResult) {
        log().todo("empty?");
    }

    private Type getData() throws AppEx {
        log().debug("getData");
        InputStream inputStream = null;
        try {
            inputStream = new URL(this.mUrl).openStream();
            Type data = new Persister(new AnnotationStrategy()).read(classObj, inputStream);
            return data;
        } catch (MalformedURLException mex) {
            throw new AppEx(mex, "Error url '%s'", this.mUrl);
        } catch (IOException ioex) {
            throw new AppEx(ioex, "Error opening url connection '%s'", this.mUrl);
        } catch (Exception ex) {
            throw new AppEx(ex, "Error reading '%s' from InputStream", classObj);
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
