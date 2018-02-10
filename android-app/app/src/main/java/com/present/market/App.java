package com.present.market;

import com.present.market.core.AbsApp;
import com.present.market.core.base.AppType;
import com.present.market.core.store.LocalSource;
import com.present.market.core.store.RemoteClient;
import com.present.market.core.store.RemoteSource;
import com.present.market.obj.Valute;
import com.present.market.obj.Valutes;

public final class App extends AbsApp {
    private final Valute mValuteRuble = new Valute("RUR", "Российский рубль", 1, "1");

    private AppStore mAppStore;
    private RemoteClient mRemoteClient;
    @Override
    protected void onInit() {
        String localFilePath = AppType.getFilePath(getAppDir(), BuildConfig.STORE_FILE);
        LocalSource<Valutes> localSource = new LocalSource<>(localFilePath, Valutes.class);
        this.mRemoteClient = new RemoteClient(BuildConfig.STORE_URL);
        RemoteSource<Valutes> remoteSource =
                new RemoteSource<>(this.mRemoteClient, Valutes.class);
        this.mAppStore = new AppStore(localSource, remoteSource, this.mValuteRuble);
    }

    public AppStore store() {
        log().debug("store");
        return this.mAppStore;
    }
}
