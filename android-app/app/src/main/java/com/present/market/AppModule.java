package com.present.market;

import android.support.annotation.NonNull;

import com.present.market.obj.Valute;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @NonNull
    @Provides
    public Valute provideValuteRuble() {
        return new Valute("RUR", "Российский рубль", 1, "1");
    }

//    @Singleton
//    @NonNull
//    @Provides
//    public AppStore provideAppStore(LocalSource<Valutes> localSource, RemoteSource<Valutes> remoteSource,
//                                    Valute refMarketValute) {
//        return new AppStore(localSource, remoteSource, refMarketValute);
//    }
}
