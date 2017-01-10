package com.present.market.core;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Toast;

import com.present.market.R;
import com.present.market.core.base.AppLog;
import com.present.market.core.base.AppType;
import com.present.market.core.ui.AbsFrame;

public abstract class AbsAppAct extends AppCompatActivity {
    private ViewGroup mFrameContentView;
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core_activity);
        this.mFrameContentView = (ViewGroup) findViewById(R.id.core_activity__frame_content);
        log().info_init();
        this.onInit();
    }

    protected abstract void onInit();

    public final void onDestroy() {
        log().info_out();
        this.onOut();
        super.onDestroy();
    }
    protected abstract void onOut();

    protected final ViewGroup getFrameContentView() {
        return this.mFrameContentView;
    }

    public final void showFrame(AbsFrame frame) {
        log().debug("showFrame");
        this.mFrameContentView.addView(frame.getView());
    }

    public final void showFrame(AbsFrame frame, long durationInMs) {
        log().debug("showFrame.durationInMs=%s", durationInMs);
        this.showFrame(frame);
        frame.hide(durationInMs);
    }

    public final void showMessage(String message) {
        log().debug("showMessage.message=%s", message);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private AppLog mLog;
    protected final AppLog log() {
        if (AppType.OBJ_IS_NULL(this.mLog)) this.mLog = new AppLog(this);
        return this.mLog;
    }

    private AbsApp mApp;
    protected AbsApp app() {
        if (AppType.OBJ_IS_NULL(this.mApp)) this.mApp = (AbsApp) getApplication();
        return this.mApp;
    }
}
