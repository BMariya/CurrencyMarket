package com.present.market;

import com.present.market.core.AbsAppAct;
import com.present.market.core.base.AppAction;
import com.present.market.core.base.AppEx;
import com.present.market.core.base.AppCallback;
import com.present.market.core.base.AppType;
import com.present.market.core.ui.TextChangeAction;
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
        long appInitTimeMs = app().getAppInitDurationInMs();
        if (appInitTimeMs > 0) {
            this.mInitFrame = new InitFrame(getFrameContentView());
            showFrame(this.mInitFrame, appInitTimeMs);
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
        log().debug("updateData");
        this.mMainFrame.show(appStore.getTitle(), appStore.getDate(), appStore.getRefValute(),
                appStore.getRefAmount(), appStore.getValuteList(),
                new TextChangeAction() {

                    @Override
                    public void onResult(String refAmount) {
                        app().store().setRefAmount(new AppType.AppAmount(refAmount));
                    }
                }, new AppAction<Valute>() {

                    @Override
                    public void onResult(Valute valute) {
                        app().store().setRefValute(valute);
                    }
                });
    }

    @Override
    public void onError(AppEx appEx) {
        log().debug("updateError");
        showMessage(appEx.getMessage());
    }
}
