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

    private static AppComponent component;
    private StoreComponent storeComponent;
    public AppComponent getComponent() {
        return component;
    }
    @Override
    protected void onInit() {
        component = buildComponent();
//        storeComponent = DaggerStoreComponent.builder().appComponent(component).build();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public StoreComponent plusStoreComponent() {
        if (storeComponent == null) {
            storeComponent = component.plusStoreComponent(new StoreModule());
        }
        return storeComponent;
    }

    public void clearStoreComponent() {
        storeComponent = null;
    }
}
