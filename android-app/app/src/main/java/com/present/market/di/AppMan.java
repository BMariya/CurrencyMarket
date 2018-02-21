package com.present.market.di;

import android.content.Context;

import com.present.market.AppStore;
import com.present.market.di.comp.AppActComp;
import com.present.market.di.module.AppModule;

import javax.inject.Inject;

public class AppMan {

    private AppComponent mAppComponent;
    private AppActComp mAppActComp;
    @Inject public AppStore mAppStore;

    public AppMan(Context context) {
        super();
        this.buildAppComponent(context).inject(this);
    }

    public AppStore store() {
        return this.mAppStore;
    }

    public AppComponent buildAppComponent(Context context) {
        this.mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(context))
                .build();
        return this.mAppComponent;
    }

    public AppActComp plusAppActComp() {
        if (this.mAppActComp == null) {
            this.mAppActComp = this.mAppComponent.plusAppActComp(new com.present.market.AppActModule());
        }
        return this.mAppActComp;
    }
    public void clearAppActComp() {
        this.mAppActComp = null;
    }
}
