package com.present.market.core.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.present.market.core.base.AbsObj;

public abstract class AbsView extends AbsObj {
    private Context mContext;
    private View mView;
    public AbsView(ViewGroup contentView) {
        super();
        this.setView(contentView);
        this.onInit();
    }

    protected abstract void onInit();

    @CallSuper
    protected void setView(ViewGroup contentView) {
        log().debug("setView");
        this.mContext = contentView.getContext();
        this.mView = LayoutInflater.from(this.mContext)
                .inflate(this.onGetLayoutRes(), contentView, false);
    }

    @LayoutRes
    protected abstract int onGetLayoutRes();

    public final View getView() {
        log().trace("getView");
        return this.mView;
    }
    private View getView(@IdRes int viewIdRes) {
        log().trace("getView.viewIdRes=%s", viewIdRes);
        return this.getView().findViewById(viewIdRes);
    }
    protected final TextView getTextView(@IdRes int tvIdRes) {
        log().trace("getTextView.tvIdRes=%s", tvIdRes);
        return (TextView) this.getView(tvIdRes);
    }
    protected final EditText getEditTextView(@IdRes int etvIdRes) {
        log().trace("getEditTextView.etdIdRes=%s", etvIdRes);
        return (EditText) this.getView(etvIdRes);
    }
    protected final RecyclerView getRecyclerView(@IdRes int rvIdRes) {
        log().trace("getRecyclerView.rvIdRes=%s", rvIdRes);
        RecyclerView recyclerView = (RecyclerView) this.getView(rvIdRes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        return recyclerView;
    }

    protected final boolean isVisible() {
        boolean result = (this.getView().getVisibility() == View.VISIBLE);
        log().trace("isVisible.result=%s", result);
        return result;
    }

    protected final void hide() {
        log().debug("hide");
        this.getView().setVisibility(View.INVISIBLE);
    }

    public final void show(long durationInMs) {
        log().debug("show.durationInMs=%s", durationInMs);
        if (this.isVisible()) {
            if (durationInMs <= 0) {
                this.hide();
                return;
            }
            this.getView().animate().alpha(0.0f).setDuration(durationInMs)
                    .setListener(new AnimatorListenerAdapter() {

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            AbsView.this.hide();
                        }
                    });
        }
    }

    protected final void setTextChangeAction(TextView textView, TextChangeAction textChangeAction) {
        log().debug("setTextChangeAction");
        textView.addTextChangedListener(textChangeAction);
    }
}
