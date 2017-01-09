package com.present.market.core.ui;

import android.view.ViewGroup;

public abstract class AbsFrame extends AbsView {
    public AbsFrame(ViewGroup contentView) {
        super(contentView);
        log().refactor("convert to fragment?");
    }
}
