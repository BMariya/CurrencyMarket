package com.present.market;

import com.present.market.di.scope.SingleActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {StoreModule.class})
@SingleActivity
public interface StoreComponent {
    void inject(AppAct appAct);
}
