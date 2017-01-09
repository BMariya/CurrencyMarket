package com.present.market.core;

import android.app.Application;
import android.os.Environment;

import com.present.market.BuildConfig;
import com.present.market.core.base.AppLog;
import com.present.market.core.base.AppType;

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

    private String mAppDir;
    protected final String getAppDir() {
        log().debug("getAppDir");
        if (AppType.OBJ_IS_NULL(this.mAppDir)) this.mAppDir = this.onGetAppDir();
        return this.mAppDir;
    }

    private String onGetAppDir() {
        log().debug("onGetAppDir");
        String result;
        if (BuildConfig.DEBUG) result = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        else result = getFilesDir().getAbsolutePath();
        log().debug("onGetAppDir.result=%s", result);
        return result;
    }

    private AppLog mLog;
    protected final AppLog log() {
        if (AppType.OBJ_IS_NULL(this.mLog)) this.mLog = new AppLog(this);
        return this.mLog;
    }
}
