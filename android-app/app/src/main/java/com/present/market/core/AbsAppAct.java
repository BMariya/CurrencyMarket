package com.present.market.core;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Toast;

import com.present.market.R;
import com.present.market.core.ui.AbsView;

public abstract class AbsAppAct extends AppCompatActivity {
    private ViewGroup mFrameContentView;
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core_activity);
        this.mFrameContentView = findViewById(R.id.core_activity__frame_content);
        this.onInit();
    }

    protected abstract void onInit();

    public final void onDestroy() {
        this.onOut();
        super.onDestroy();
    }
    protected abstract void onOut();

    protected final ViewGroup getFrameContentView() {
        return this.mFrameContentView;
    }

    public final void showFrame(AbsView frame) {
        this.mFrameContentView.addView(frame.getView());
    }

    public final void showFrame(AbsView frame, long durationInMs) {
        this.showFrame(frame);
        frame.hide(durationInMs);
    }

    public final void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
