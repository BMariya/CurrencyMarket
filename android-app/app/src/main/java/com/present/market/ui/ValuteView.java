package com.present.market.ui;

import android.view.ViewGroup;
import android.widget.TextView;

import com.present.market.R;
import com.present.market.core.ui.AbsView;

public final class ValuteView extends AbsView {
    public ValuteView(ViewGroup contentView) {
        super(contentView);
    }

    @Override
    public int onGetLayoutRes() {
        return R.layout.view_valute;
    }

    private TextView mTvValue;
    private TextView mTvNominal;
    private TextView mTvRefNominal;
    @Override
    public void onInit() {
        this.mTvValue = getTextView(R.id.view_valute__tv_value);
        this.mTvNominal = getTextView(R.id.view_valute__tv_nominal);
        this.mTvRefNominal = getTextView(R.id.view_valute__tv_ref_nominal);
    }

    public void show(String value, String nominal, String refNominal) {
        log().todo("show.Params vs Object? abstract void show ?...");
        log().debug("show.value=%s,nominal=%s,refNominal=%s", value, nominal, refNominal);
        this.mTvValue.setText(value);
        this.mTvNominal.setText(nominal);
        this.mTvRefNominal.setText(refNominal);
    }
}
