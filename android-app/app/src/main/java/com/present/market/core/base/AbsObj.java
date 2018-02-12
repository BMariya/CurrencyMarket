package com.present.market.core.base;

public abstract class AbsObj {
    public AbsObj() {
        super();
        log().trace_init();
    }

    private AppLog mLog;
    protected final AppLog log() {
        if (this.mLog == null) this.mLog = new AppLog(this);
        return this.mLog;
    }
}
