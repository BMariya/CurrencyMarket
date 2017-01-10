package com.present.market;

import com.present.market.core.AbsAppAct;
import com.present.market.core.base.AppAction;
import com.present.market.core.base.AppCallback;
import com.present.market.core.base.AppEx;
import com.present.market.core.base.AppType;
import com.present.market.obj.Valute;
import com.present.market.ui.InitFrame;
import com.present.market.ui.MainFrame;

public final class AppAct extends AbsAppAct implements AppCallback<AppStore> {
    private InitFrame mInitFrame;
    private MainFrame mMainFrame;
    @Override
    protected void onInit() {
        this.mMainFrame = new MainFrame(getFrameContentView());
        showFrame(this.mMainFrame);
        long appInitTimeInMs = app().getAppInitDurationInMs();
        if (appInitTimeInMs > 0) {
            this.mInitFrame = new InitFrame(getFrameContentView());
            showFrame(this.mInitFrame, appInitTimeInMs);
        }
        app().store().register(this);
    }

    @Override
    public void onOut() {
        app().store().unregister(this);
    }

    @Override
    protected final App app() {
        return (App) super.app();
    }

    @Override
    public void onResult(AppStore appStore) {
        log().debug("onResult");
        AppAction<String> amountChangeAction = new AppAction<String>() {
            @Override
            public void onResult(String refAmount) {
                if (refAmount.isEmpty()) refAmount = "0";
                app().store().setRefAmount(new AppType.AppAmount(refAmount));
            }
        };
        AppAction<Valute> valuteClickAction = new AppAction<Valute>() {
            @Override
            public void onResult(Valute valute) {
                app().store().setRefValute(valute);
            }
        };
        this.mMainFrame.show(appStore.getTitle(), appStore.getDate(), appStore.getRefValute(),
                appStore.getRefAmount(), appStore.getValuteList(),
                amountChangeAction, valuteClickAction);
    }

    @Override
    public void onError(AppEx appEx) {
        log().debug("onError");
        showMessage(appEx.getMessage());
    }
}
