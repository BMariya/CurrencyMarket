package com.present.market.di.module;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.present.market.AppStore;
import com.present.market.BuildConfig;
import com.present.market.core.base.AppType;
import com.present.market.core.store.LocalSource;
import com.present.market.core.store.RemoteClient;
import com.present.market.core.store.RemoteSource;
import com.present.market.net.ValuteService;
import com.present.market.model.Valute;
import com.present.market.model.Valutes;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        super();
        this.mContext = context;
    }

    @Singleton
    @NonNull
    @Provides
    public Context provideContext() {
        return this.mContext;
    }

    @Singleton
    @NonNull
    @Provides
    public Valute provideValuteRuble() {
        return new Valute("RUR", "Российский рубль", 1, "1");
    }

    @Singleton
    @NonNull
    @Provides
    @Named("appDir")
    public String provideAppDir(Context context) {
        String result;
        if (BuildConfig.DEBUG) result = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        else result = context.getFilesDir().getAbsolutePath();
        return result;
    }

    @Singleton
    @NonNull
    @Provides
    @Named("localFilePath")
    public String provideLocalFilePath(@Named("appDir") String appDir) {
        return AppType.getFilePath(appDir, BuildConfig.STORE_FILE);
    }

    @Singleton
    @NonNull
    @Provides
    public LocalSource<Valutes> provideLocalSource(@Named("localFilePath") String localFilePath) {
        return new LocalSource<>(localFilePath, Valutes.class);
    }

    @Singleton
    @NonNull
    @Provides
    public RemoteSource<Valutes> provideRemoteSource() {
        RemoteClient remoteClient = new RemoteClient(BuildConfig.STORE_URL);
        ValuteService valuteService = remoteClient.getRetrofit().create(ValuteService.class);
        return new RemoteSource<>(valuteService.listValutes(), Valutes.class);
    }

    @Singleton
    @NonNull
    @Provides
    public AppStore provideAppStore(LocalSource<Valutes> localSource, RemoteSource<Valutes> remoteSource,
                                    Valute refMarketValute) {
        return new AppStore(localSource, remoteSource, refMarketValute);
    }
}
