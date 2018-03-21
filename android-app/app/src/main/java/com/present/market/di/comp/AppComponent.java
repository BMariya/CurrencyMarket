package com.present.market.di.comp;

import com.present.market.AppActModule;
import com.present.market.di.AppMan;
import com.present.market.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(AppMan appMan);
    AppActComp plusAppActComp(AppActModule appActModule);
}
