package com.present.market.core.ui;

import android.text.InputFilter;
import android.text.Spanned;

import com.present.market.core.base.AbsObj;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AmountInputFilter extends AbsObj implements InputFilter {
    private final Pattern mPattern = Pattern.compile("(0|[1-9][0-9]{0,9})?(\\.[0-9]{0,3})?");
    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {
        String input = dest.subSequence(0, dstart) + source.toString()
                + dest.subSequence(dend, dest.length());
        Matcher matcher = mPattern.matcher(input);
        if (!matcher.matches()) return dest.subSequence(dstart, dend);
        return null;
    }
}
