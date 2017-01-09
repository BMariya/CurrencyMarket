package com.present.market.core.base;

public final class AppEx extends Exception {
    public AppEx(Exception ex, String frmtMsg, Object... args) {
        super(String.format(frmtMsg, args), ex);
    }
}
