package android.support.v7.widget;

import android.support.v4.view.*;
import android.view.*;

class ActionBarOverlayLayout$3 implements Runnable {
    @Override
    public void run() {
        ActionBarOverlayLayout.access$200(ActionBarOverlayLayout.this);
        ActionBarOverlayLayout.access$002(ActionBarOverlayLayout.this, ViewCompat.animate((View)ActionBarOverlayLayout.access$400(ActionBarOverlayLayout.this)).translationY(-ActionBarOverlayLayout.access$400(ActionBarOverlayLayout.this).getHeight()).setListener(ActionBarOverlayLayout.access$300(ActionBarOverlayLayout.this)));
    }
}