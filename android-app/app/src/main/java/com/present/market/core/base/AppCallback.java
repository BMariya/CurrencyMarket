package com.present.market.core.base;

public interface AppCallback<Result> extends AppAction<Result> {
    void onError(AppEx appEx);
}
