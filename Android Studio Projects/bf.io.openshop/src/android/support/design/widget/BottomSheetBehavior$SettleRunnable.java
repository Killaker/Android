package android.support.design.widget;

import android.view.*;
import android.support.v4.view.*;

private class SettleRunnable implements Runnable
{
    private final int mTargetState;
    private final View mView;
    
    SettleRunnable(final View mView, final int mTargetState) {
        this.mView = mView;
        this.mTargetState = mTargetState;
    }
    
    @Override
    public void run() {
        if (BottomSheetBehavior.access$1200(BottomSheetBehavior.this) != null && BottomSheetBehavior.access$1200(BottomSheetBehavior.this).continueSettling(true)) {
            ViewCompat.postOnAnimation(this.mView, this);
            return;
        }
        BottomSheetBehavior.access$600(BottomSheetBehavior.this, this.mTargetState);
    }
}
