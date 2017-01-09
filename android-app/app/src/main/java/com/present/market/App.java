package com.present.market;

import com.present.market.core.AbsApp;
import com.present.market.core.base.AppType;
import com.present.market.obj.Valute;
import com.present.market.store.LocalStore;
import com.present.market.store.RemoteStore;

public final class App extends AbsApp {
    private final Valute mValuteRuble = new Valute("RUR", "Российский рубль", 1, "1");

    private AppStore mAppStore;
    @Override
    protected void onInit() {
        AppType.AppFile localStoreFile = new AppType.AppFile(getAppDir(), BuildConfig.STORE_FILE);
        LocalStore localStore = new LocalStore(localStoreFile);

        AppType.AppUrl remoteStoreUrl = new AppType.AppUrl(BuildConfig.STORE_URL);
        RemoteStore remoteStore = new RemoteStore(remoteStoreUrl);

        this.mAppStore = new AppStore(localStore, remoteStore, this.mValuteRuble);
    }

    public AppStore store() {
        log().debug("store");
        return this.mAppStore;
    }
}
