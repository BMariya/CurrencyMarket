package com.present.market;

import com.present.market.core.AbsApp;
import com.present.market.core.store.LocalSource;
import com.present.market.core.store.RemoteSource;
import com.present.market.obj.Valute;
import com.present.market.obj.Valutes;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Valute valuteRuble();
    LocalSource<Valutes> localSource();
    RemoteSource<Valutes> remoteSource();
//    void inject(AppAct appAct);
}
