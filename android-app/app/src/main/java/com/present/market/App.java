package com.present.market;

import com.present.market.core.AbsApp;
import com.present.market.di.comp.AppActComp;
import com.present.market.di.module.AppActModule;
import com.present.market.di.module.AppModule;

import javax.inject.Inject;

public final class App extends AbsApp {

    private AppComponent mAppComponent;
    private AppActComp mAppActComp;
    @Inject AppStore mAppStore;
    @Override
    protected void onInit() {
        this.mAppComponent = buildComponent();
        this.mAppComponent.inject(this);
    }
    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppStore store() {
        return this.mAppStore;
    }

    public AppActComp plusAppActComp() {
        if (this.mAppActComp == null) {
            this.mAppActComp = this.mAppComponent.plusAppActComp(new AppActModule());
        }
        return this.mAppActComp;
    }
    public void clearAppActComp() {
        this.mAppActComp = null;
    }
}
