package android.support.v4.widget;

import android.view.*;
import android.support.v4.view.*;
import android.graphics.*;

private class DisableLayerRunnable implements Runnable
{
    final View mChildView;
    
    DisableLayerRunnable(final View mChildView) {
        this.mChildView = mChildView;
    }
    
    @Override
    public void run() {
        if (this.mChildView.getParent() == SlidingPaneLayout.this) {
            ViewCompat.setLayerType(this.mChildView, 0, null);
            SlidingPaneLayout.access$1000(SlidingPaneLayout.this, this.mChildView);
        }
        SlidingPaneLayout.access$1100(SlidingPaneLayout.this).remove(this);
    }
}
