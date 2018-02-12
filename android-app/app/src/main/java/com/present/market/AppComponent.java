package com.present.market;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
//    Valute valuteRuble();
//    LocalSource<Valutes> localSource();
//    RemoteSource<Valutes> remoteSource();

    StoreComponent plusStoreComponent(StoreModule storeModule);
//    void inject(AppAct appAct);
}
