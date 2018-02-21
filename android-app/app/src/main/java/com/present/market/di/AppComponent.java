package com.present.market;

import com.present.market.App;
import com.present.market.di.comp.AppActComp;
import com.present.market.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(App app);
    AppActComp plusAppActComp(com.present.market.AppActModule appActModule);
}
