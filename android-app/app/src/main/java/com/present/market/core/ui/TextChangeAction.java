package com.present.market.core.ui;

import android.text.Editable;
import android.text.TextWatcher;

import com.present.market.core.base.AppAction;

public final class TextChangeAction implements TextWatcher {
    private AppAction<String> mAppAction;
    public TextChangeAction() {
        super();
    }

    public void afterTextChanged(Editable s) {
        if (this.mAppAction == null) return;
        this.mAppAction.onResult(s.toString());
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    public void setAction(AppAction<String> appAction) {
        this.mAppAction = appAction;
    }
}
