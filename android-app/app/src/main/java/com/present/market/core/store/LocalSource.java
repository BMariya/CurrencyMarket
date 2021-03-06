package com.present.market.core.store;

import com.present.market.core.base.AppEx;

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
        this.mFilePath = filePath;
    }

    @Override
    protected void onLoadData(TaskResult<Type> taskResult) {
        if (new File(this.mFilePath).exists()) {
            try {
                taskResult.setResult(this.getData());
            } catch (AppEx appEx) {
                taskResult.setError(appEx);
            }
        }
    }

    @Override
    protected void onSaveData(Type xmlObj, TaskResult<Void> taskResult) {
        try {
            this.setData(xmlObj);
            taskResult.setResult(null);
        } catch (AppEx appEx) {
            taskResult.setError(appEx);
        }
    }

    private Type getData() throws AppEx {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(this.mFilePath);
            return new Persister().read(classObj, inputStream);
        } catch (FileNotFoundException fex) {
            throw new AppEx(fex, "Error file '%s' - not found", this.mFilePath);
        } catch (Exception ex) {
            throw new AppEx(ex, "Error reading '%s' from InputStream", classObj);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    private void setData(Type data) throws AppEx {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(this.mFilePath);
            new Persister().write(data, outputStream);
        } catch (FileNotFoundException fex) {
            throw new AppEx(fex, "Error file '%s' - not found", this.mFilePath);
        } catch (Exception ex) {
            throw new AppEx(ex, "Error writing '%s' to OutputStream", data);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                }
            }
        }
    }
}
