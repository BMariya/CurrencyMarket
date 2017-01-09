package com.present.market.ui;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.present.market.R;
import com.present.market.core.base.AppAction;
import com.present.market.core.base.AppType;
import com.present.market.core.ui.AbsListFrame;
import com.present.market.core.ui.TextChangeAction;
import com.present.market.obj.Valute;

import java.util.List;

public final class MainFrame extends AbsListFrame<ValuteView, Valute> {
    public MainFrame(ViewGroup contentView) {
        super(contentView);
    }

    @Override
    protected int onGetLayoutRes() {
        return R.layout.frame_main;
    }

    @Override
    protected int onGetRecyclerViewIdRes() {
        return R.id.frame_main__rv_valutes;
    }

    private TextView mTvTitle;
    private TextView mTvDate;
    private EditText mEtvRefValuteAmount;
    private TextView mTvRefValuteCode;
    @Override
    protected void onInit() {
        this.mTvTitle = getTextView(R.id.frame_main__tv_title);
        this.mTvDate = getTextView(R.id.frame_main__tv_date);
        this.mEtvRefValuteAmount = getEditTextView(R.id.frame_main__etv_ref_valute_amount);
        this.mTvRefValuteCode = getTextView(R.id.frame_main__tv_ref_valute_code);
    }

    private AppType.AppAmount mRefAmount;
    private Valute mRefValute;
    public void show(String title, AppType.AppDate date, Valute refValute,
                     AppType.AppAmount refAmount, List<Valute> valuteList,
                     TextChangeAction amountChangeAction, AppAction<Valute> valuteClickAction) {
        log().refactor("show.Params vs Object!? abstract void show!?");
        log().debug("show");
        this.mTvTitle.setText(title);
        this.mTvDate.setText(date.toDisplay());
        this.mEtvRefValuteAmount.setText(refAmount.toDisplay());
        this.mTvRefValuteCode.setText(refValute.getCharCode());
        setTextChangeAction(this.mEtvRefValuteAmount, amountChangeAction);
        this.mRefValute = refValute;
        this.mRefAmount = refAmount;
        super.show(valuteList, valuteClickAction);
    }

    @Override
    protected ValuteView onCreateListItemView(ViewGroup contentView) {
        return new ValuteView(contentView);
    }

    @Override
    protected void onSetListItemView(ValuteView valuteView, Valute valute) {
        valuteView.show(
                valute.getRefValue(this.mRefValute, this.mRefAmount)
                        .toDisplay(valute.getCharCode()),
                valute.getNominal().toDisplay(valute.getName()),
                this.mRefValute.getRefValue(valute, valute.getNominal())
                        .toDisplay(this.mRefValute.getCharCode()));
    }
}
