package com.present.market.core.base;

public abstract class AbsObj {
    public AbsObj() {
        super();
        log().info_init();
    }

    private AppLog mLog;
    protected final AppLog log() {
        if (AppType.OBJ_IS_NULL(this.mLog)) this.mLog = new AppLog(this);
        return this.mLog;
    }
}
