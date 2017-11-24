package android.support.v7.widget;

import android.view.*;
import android.support.v4.view.*;

protected class VisibilityAnimListener implements ViewPropertyAnimatorListener
{
    private boolean mCanceled;
    int mFinalVisibility;
    
    protected VisibilityAnimListener() {
        this.mCanceled = false;
    }
    
    @Override
    public void onAnimationCancel(final View view) {
        this.mCanceled = true;
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        if (this.mCanceled) {
            return;
        }
        AbsActionBarView.this.mVisibilityAnim = null;
        AbsActionBarView.access$101(AbsActionBarView.this, this.mFinalVisibility);
    }
    
    @Override
    public void onAnimationStart(final View view) {
        AbsActionBarView.access$001(AbsActionBarView.this, 0);
        this.mCanceled = false;
    }
    
    public VisibilityAnimListener withFinalVisibility(final ViewPropertyAnimatorCompat mVisibilityAnim, final int mFinalVisibility) {
        AbsActionBarView.this.mVisibilityAnim = mVisibilityAnim;
        this.mFinalVisibility = mFinalVisibility;
        return this;
    }
}
