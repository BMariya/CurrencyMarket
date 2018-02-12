package com.present.market.ui;

import com.present.market.core.base.AbsObj;

public class AppActPresenter extends AbsObj {
    private AppAct mAppAct;
    public AppActPresenter() {
        super();
    }

    public void bind(AppAct appAct) {
        this.mAppAct = appAct;
    }

    public void unbind() {
        this.mAppAct = null;
    }
}
