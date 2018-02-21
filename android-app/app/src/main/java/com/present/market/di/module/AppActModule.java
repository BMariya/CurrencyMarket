package com.present.market;

import android.support.annotation.NonNull;

import com.present.market.di.scope.SingleActivity;
import com.present.market.ui.AppActPresenter;

import dagger.Module;
import dagger.Provides;
@Module
public class AppActModule {
    @SingleActivity
    @NonNull
    @Provides
    public AppActPresenter provideAppActPresenter() {
        return new AppActPresenter();
    }
}
