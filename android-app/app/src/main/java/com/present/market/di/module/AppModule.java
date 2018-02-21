package com.present.market.di.module;

import android.os.Environment;
import android.support.annotation.NonNull;

import com.present.market.AppStore;
import com.present.market.BuildConfig;
import com.present.market.core.AbsApp;
import com.present.market.core.base.AppType;
import com.present.market.core.store.LocalSource;
import com.present.market.core.store.RemoteClient;
import com.present.market.core.store.RemoteSource;
import com.present.market.di.scope.SingleActivity;
import com.present.market.net.ValuteService;
import com.present.market.obj.Valute;
import com.present.market.obj.Valutes;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private AbsApp mApp;

    public AppModule(AbsApp app) {
        super();
        this.mApp = app;
    }

    @Singleton
    @NonNull
    @Provides
    public AbsApp provideApp() {
        return this.mApp;
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
    public String provideAppDir(AbsApp app) {
        String result;
        if (BuildConfig.DEBUG) result = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        else result = app.getFilesDir().getAbsolutePath();
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
