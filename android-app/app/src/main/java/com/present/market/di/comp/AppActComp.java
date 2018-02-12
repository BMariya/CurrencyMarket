package com.present.market.di.comp;

import com.present.market.ui.AppAct;
import com.present.market.di.module.AppActModule;
import com.present.market.di.scope.SingleActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {AppActModule.class})
@SingleActivity
public interface AppActComp {
    void inject(AppAct appAct);
}
