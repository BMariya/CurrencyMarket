package com.present.market.core.store;

import com.present.market.core.base.AppEx;
import com.present.market.core.base.AppType;

import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class LocalSource<Type> extends AbsSource<Type> {
    private final String mFilePath;
    public LocalSource(String filePath, Class<Type> classObj) {
        super(classObj);
        log().debug("filePath=%s", classObj);
        this.mFilePath = filePath;
    }

    @Override
    protected void onLoadData(TaskResult<Type> taskResult) {
        log().todo("check date, version! sync file ?!");
        log().debug("onLoadData");
        if (new File(this.mFilePath).exists()) {
            try {
                taskResult.setResult(this.getData());
            } catch (AppEx appEx) {
                log().error(appEx, "Error loading data from Local Source");
                taskResult.setError(appEx);
            }
        }
    }

    @Override
    protected void onSaveData(Type xmlObj, TaskResult<Void> taskResult) {
        log().todo("check date, version! sync file ?!");
        log().debug("onSaveData");
        try {
            this.setData(xmlObj);
            taskResult.setResult(null);
        } catch (AppEx appEx) {
            log().error(appEx, "Error saving data to Local Source");
            taskResult.setError(appEx);
        }
    }

    private Type getData() throws AppEx {
        log().debug("getData");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(this.mFilePath);
            return new Persister().read(classObj, inputStream);
        } catch (FileNotFoundException fex) {
            throw new AppEx(fex, "Error file '%s' - not found", this.mFilePath);
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

    private void setData(Type data) throws AppEx {
        log().debug("setData.data=%s", data);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(this.mFilePath);
            new Persister().write(data, outputStream);
        } catch (FileNotFoundException fex) {
            throw new AppEx(fex, "Error file '%s' - not found", this.mFilePath);
        } catch (Exception ex) {
            throw new AppEx(ex, "Error writing '%s' to OutputStream", data);
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
