package com.present.market.core;

import com.present.market.core.base.AbsObj;
import com.present.market.core.base.AppCallback;
import com.present.market.core.base.AppEx;
import com.present.market.core.base.AppType;

import java.util.ArrayList;

public abstract class AbsAppStore<Store extends AbsAppStore, Type>
        extends AbsObj implements AppCallback<Type> {
    public AbsAppStore() {
        super();
    }

    protected abstract Store store();

    private AppEx mAppEx;
    @Override
    public final void onError(AppEx appEx) {
        log().debug("setError");
        this.mAppEx = appEx;
        this.updateAppCallbacks();
    }

    private final ArrayList<AppCallback<Store>> mAppCallbackList = new ArrayList<>();
    public final void register(AppCallback<Store> appCallback) {
        log().debug("register");
        this.mAppCallbackList.add(appCallback);
        this.updateAppCallbacks();
    }

    public final void unregister(AppCallback appCallback) {
        log().debug("unregister");
        this.mAppCallbackList.remove(appCallback);
    }

    protected final void updateAppCallbacks() {
        log().debug("updateAppCallbacks");
        for (AppCallback<Store> appCallback: mAppCallbackList) {
            appCallback.onResult(store());
            if (AppType.OBJ_IS_NULL(this.mAppEx)) return;
            appCallback.onError(this.mAppEx);
        }
        this.mAppEx = null;
    }
}
