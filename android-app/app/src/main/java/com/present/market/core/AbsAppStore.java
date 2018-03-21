package com.present.market.core;

import com.present.market.core.base.AppCallback;
import com.present.market.core.base.AppEx;

import java.util.ArrayList;

public abstract class AbsAppStore<Store extends AbsAppStore, Type> implements AppCallback<Type> {
    public AbsAppStore() {
        super();
    }

    protected abstract Store store();

    private AppEx mAppEx;
    @Override
    public final void onError(AppEx appEx) {
        this.mAppEx = appEx;
        this.updateAppCallbacks();
    }

    private final ArrayList<AppCallback<Store>> mAppCallbackList = new ArrayList<>();
    public final void register(AppCallback<Store> appCallback) {
        this.mAppCallbackList.add(appCallback);
        this.updateAppCallbacks();
    }

    public final void unregister(AppCallback appCallback) {
        this.mAppCallbackList.remove(appCallback);
    }

    protected final void updateAppCallbacks() {
        for (AppCallback<Store> appCallback: mAppCallbackList) {
            appCallback.onResult(store());
            if (this.mAppEx == null) return;
            appCallback.onError(this.mAppEx);
        }
        this.mAppEx = null;
    }
}
