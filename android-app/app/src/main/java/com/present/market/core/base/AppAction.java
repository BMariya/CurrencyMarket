package com.present.market.core.base;

public interface AppAction<Result> {
    void onResult(Result result);
}
