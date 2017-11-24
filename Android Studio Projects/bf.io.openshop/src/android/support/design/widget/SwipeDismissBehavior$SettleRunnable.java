package android.support.design.widget;

import android.view.*;
import android.support.v4.view.*;

private class SettleRunnable implements Runnable
{
    private final boolean mDismiss;
    private final View mView;
    
    SettleRunnable(final View mView, final boolean mDismiss) {
        this.mView = mView;
        this.mDismiss = mDismiss;
    }
    
    @Override
    public void run() {
        if (SwipeDismissBehavior.access$100(SwipeDismissBehavior.this) != null && SwipeDismissBehavior.access$100(SwipeDismissBehavior.this).continueSettling(true)) {
            ViewCompat.postOnAnimation(this.mView, this);
        }
        else if (this.mDismiss && SwipeDismissBehavior.access$000(SwipeDismissBehavior.this) != null) {
            SwipeDismissBehavior.access$000(SwipeDismissBehavior.this).onDismiss(this.mView);
        }
    }
}
