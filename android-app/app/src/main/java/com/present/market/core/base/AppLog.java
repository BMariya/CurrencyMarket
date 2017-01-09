package com.present.market.core.base;

import android.util.Log;

public final class AppLog {
    private final String mTag;
    public AppLog(Object obj) {
        super();
        this.mTag = obj.getClass().getSimpleName();
    }

    public void info(String frmtMsg, Object... args) {
        Log.i(this.mTag, String.format(frmtMsg, args));
    }
    public void info_init() {
        this.info("INIT");
    }
    public void info_out() {
        this.info("OUT");
    }

    public void trace(String frmtMsg, Object... args) {
        Log.v(this.mTag, String.format(frmtMsg, args));
    }

    public void debug(String frmtMsg, Object... args) {
        Log.d(this.mTag, String.format(frmtMsg, args));
    }

    public void error(Exception ex, String frmtMsg, Object... args) {
        Log.e(this.mTag, String.format(frmtMsg, args), ex);
    }

    public void todo(String frmtMsg, Object... args) {
        Log.w(this.mTag, "TODO: ".concat(String.format(frmtMsg, args)));
    }
    public void refactor(String frmtMsg, Object... args) {
        Log.e(this.mTag, "REFACTOR: ".concat(String.format(frmtMsg, args)));
    }
}
