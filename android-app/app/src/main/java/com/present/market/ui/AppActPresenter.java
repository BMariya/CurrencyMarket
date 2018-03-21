package com.present.market.ui;

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
