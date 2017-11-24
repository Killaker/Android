package android.support.v7.widget;

import android.support.v4.view.*;
import android.view.*;

class ToolbarWidgetWrapper$2 extends ViewPropertyAnimatorListenerAdapter {
    private boolean mCanceled = false;
    final /* synthetic */ int val$visibility;
    
    @Override
    public void onAnimationCancel(final View view) {
        this.mCanceled = true;
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        if (!this.mCanceled) {
            ToolbarWidgetWrapper.access$000(ToolbarWidgetWrapper.this).setVisibility(this.val$visibility);
        }
    }
    
    @Override
    public void onAnimationStart(final View view) {
        ToolbarWidgetWrapper.access$000(ToolbarWidgetWrapper.this).setVisibility(0);
    }
}