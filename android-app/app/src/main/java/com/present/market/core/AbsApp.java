package com.present.market.core;

import android.app.Application;

import com.present.market.core.base.AppLog;

public abstract class AbsApp extends Application {
    private static final long sAppInitDurationInMs = 5000;
    private long mAppStartTimeInMs;
    @Override
    public final void onCreate() {
        super.onCreate();
        this.mAppStartTimeInMs = System.currentTimeMillis();
        log().info_init();
        this.onInit();
    }

    protected abstract void onInit();

    public final long getAppInitDurationInMs() {
        long result = sAppInitDurationInMs - (System.currentTimeMillis() - this.mAppStartTimeInMs);
        log().debug("getAppInitDurationInMs.result=%s", result);
        return result;
    }

    private AppLog mLog;
    protected final AppLog log() {
        if (this.mLog == null) this.mLog = new AppLog(this);
        return this.mLog;
    }
}
