package com.present.market;

import com.present.market.core.AbsApp;
import com.present.market.core.base.AppType;
import com.present.market.core.store.LocalSource;
import com.present.market.core.store.RemoteClient;
import com.present.market.core.store.RemoteSource;
import com.present.market.obj.Valute;
import com.present.market.obj.Valutes;

import javax.inject.Inject;

public final class App extends AbsApp {
//    private final Valute mValuteRuble = new Valute("RUR", "Российский рубль", 1, "1");

    private static AppComponent component;
    public static AppComponent getComponent() {
        return component;
    }
    private AppStore mAppStore;
    @Inject Valute mValuteRuble;
    private RemoteClient mRemoteClient;
    @Override
    protected void onInit() {
        component = buildComponent();
        String localFilePath = AppType.getFilePath(getAppDir(), BuildConfig.STORE_FILE);
        LocalSource<Valutes> localSource = new LocalSource<>(localFilePath, Valutes.class);
        this.mRemoteClient = new RemoteClient(BuildConfig.STORE_URL);
        ValuteService valuteService = this.mRemoteClient.getRetrofit().create(ValuteService.class);
        RemoteSource<Valutes> remoteSource =
                new RemoteSource<>(valuteService.listValutes(), Valutes.class);
        getComponent().inject(this);
        this.mAppStore = new AppStore(localSource, remoteSource, this.mValuteRuble);
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }
    public AppStore store() {
        log().debug("store");
        return this.mAppStore;
    }
}
