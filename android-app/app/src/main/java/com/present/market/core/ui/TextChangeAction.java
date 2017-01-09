package com.present.market.core.ui;

import android.text.Editable;
import android.text.TextWatcher;

import com.present.market.core.base.AbsObj;
import com.present.market.core.base.AppAction;

public abstract class TextChangeAction extends AbsObj implements TextWatcher, AppAction<String> {
    public TextChangeAction() {
        super();
    }

    public void afterTextChanged(Editable s) {
        onResult(s.toString());
    }

    public final void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    public final void onTextChanged(CharSequence s, int start, int before, int count) {}
}
