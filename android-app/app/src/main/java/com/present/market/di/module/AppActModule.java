package com.present.market.di.module;

import android.support.annotation.NonNull;

import com.present.market.AppStore;
import com.present.market.core.store.LocalSource;
import com.present.market.core.store.RemoteSource;
import com.present.market.di.scope.SingleActivity;
import com.present.market.obj.Valute;
import com.present.market.obj.Valutes;
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
