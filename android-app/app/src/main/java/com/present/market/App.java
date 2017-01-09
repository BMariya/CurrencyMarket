package com.present.market;

import com.present.market.core.base.AppType;
import com.present.market.core.store.LocalSource;
import com.present.market.core.store.RemoteSource;
import com.present.market.core.AbsApp;
import com.present.market.obj.Valute;
import com.present.market.obj.Valutes;

public final class App extends AbsApp {
    private final Valute mValuteRuble = new Valute("RUR", "Российский рубль", 1, "1");

    private AppStore mAppStore;
    @Override
    protected void onInit() {
        String localFilePath = AppType.getFilePath(getAppDir(), BuildConfig.STORE_FILE);
        LocalSource<Valutes> localSource = new LocalSource<>(localFilePath, Valutes.class);
        RemoteSource<Valutes> remoteSource =
                new RemoteSource<>(BuildConfig.STORE_URL, Valutes.class);
        this.mAppStore = new AppStore(localSource, remoteSource, this.mValuteRuble);
    }

    public AppStore store() {
        log().debug("store");
        return this.mAppStore;
    }
}
