package com.present.market.ui;

import android.view.ViewGroup;

import com.present.market.R;
import com.present.market.core.ui.AbsView;

public final class InitFrame extends AbsView {
    public InitFrame(ViewGroup contentView) {
        super(contentView);
    }

    @Override
    protected int onGetLayoutRes() {
        return R.layout.frame_init;
    }

    @Override
    protected void onInit() {
    }
}
