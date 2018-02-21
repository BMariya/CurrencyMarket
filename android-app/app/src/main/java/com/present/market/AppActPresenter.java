package com.present.market;

import com.present.market.ui.AppAct;

public class AppActPresenter {

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
