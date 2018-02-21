package com.present.market;

import com.present.market.core.AbsApp;
import com.present.market.di.AppMan;

public final class App extends AbsApp {

    private AppMan mAppMan;

    @Override
    protected void onInit() {
        this.mAppMan = new AppMan(this);
    }

    public AppMan man() {
        return this.mAppMan;
    }
}
