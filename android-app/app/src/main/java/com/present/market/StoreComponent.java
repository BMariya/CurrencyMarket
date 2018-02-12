package com.present.market;

import dagger.Subcomponent;

@Subcomponent(modules = {StoreModule.class})
@SourceScope
public interface StoreComponent {
    void inject(AppAct appAct);
}
