package com.present.market.core;

import com.present.market.core.base.AbsObj;
import com.present.market.core.base.AppCallback;
import com.present.market.core.base.AppEx;
import com.present.market.core.base.AppType;

import java.util.ArrayList;

public abstract class AbsAppStore extends AbsObj {
    private final ArrayList<AppCallback> mAppCallbackList = new ArrayList<>();

    public AbsAppStore() {
        super();
    }

    private AppEx mAppEx;
    public final void onError(AppEx appEx) {
        log().debug("setError");
        this.mAppEx = appEx;
        this.updateAppCallbacks();
    }

    public final void register(AppCallback appCallback) {
        log().debug("register");
        this.mAppCallbackList.add(appCallback);
        this.updateAppCallbacks();
    }

    public final void unregister(AppCallback appCallback) {
        log().debug("unregister");
        this.mAppCallbackList.remove(appCallback);
    }

    public final void updateAppCallbacks() {
        log().debug("updateAppCallbacks");
        for (AppCallback appCallback: mAppCallbackList) {
            appCallback.onResult(this);
            if (AppType.OBJ_IS_NULL(this.mAppEx)) return;
            appCallback.onError(this.mAppEx);
        }
    }
}
