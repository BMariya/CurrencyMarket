package com.present.market;

import android.support.annotation.NonNull;

import com.present.market.core.store.LocalSource;
import com.present.market.core.store.RemoteSource;
import com.present.market.di.scope.SingleActivity;
import com.present.market.obj.Valute;
import com.present.market.obj.Valutes;

import dagger.Module;
import dagger.Provides;
@Module
public class StoreModule {
    @SingleActivity
    @NonNull
    @Provides
    public AppStore provideAppStore(LocalSource<Valutes> localSource, RemoteSource<Valutes> remoteSource,
                                    Valute refMarketValute) {
        return new AppStore(localSource, remoteSource, refMarketValute);
    }
}
