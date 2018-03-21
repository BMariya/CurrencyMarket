package com.present.market;

import android.app.Application;

import com.present.market.di.AppMan;

public final class App extends Application {
    private static final long sAppInitDurationInMs = 5000;
    private long mAppStartTimeInMs;
    private AppMan mAppMan;

    @Override
    public final void onCreate() {
        super.onCreate();
        this.mAppMan = new AppMan(this);
        this.mAppStartTimeInMs = System.currentTimeMillis();
    }

    public AppMan man() {
        return this.mAppMan;
    }

    public final long getAppInitDurationInMs() {
        return sAppInitDurationInMs - (System.currentTimeMillis() - this.mAppStartTimeInMs);
    }
}
