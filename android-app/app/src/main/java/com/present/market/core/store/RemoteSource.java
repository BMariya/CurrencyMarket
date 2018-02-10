package com.present.market.core.store;

import com.present.market.core.base.AppEx;
import com.squareup.okhttp.Response;

import org.simpleframework.xml.core.Persister;

import java.io.IOException;

import retrofit2.Call;

public final class RemoteSource<Type> extends AbsSource<Type> {
    private Call<Type> mRemoteClient;
    public RemoteSource(Call<Type> client, Class<Type> classObj) {
        super(classObj);
        this.mRemoteClient = client;
    }

    @Override
    protected void onLoadData(TaskResult<Type> taskResult) {
        log().todo("check date, version!");
        log().debug("onLoadData");
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
        try {
            retrofit2.Response<Type> response = this.mRemoteClient.execute();
            return response.body();
//            return new Persister().read(classObj, response.body().string());
//        } catch (MalformedURLException mex) {
//            throw new AppEx(mex, "Error url '%s'", this.mUrl);
        } catch (IOException ioex) {
            throw new AppEx(ioex, "Error opening url connection");
        } catch (Exception ex) {
            throw new AppEx(ex, "Error reading '%s' from InputStream", classObj);
        }
    }
}
