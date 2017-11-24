package android.support.v7.widget;

import android.view.*;
import android.support.v4.view.*;

protected class VisibilityAnimListener implements ViewPropertyAnimatorListener
{
    private boolean mCanceled;
    private int mFinalVisibility;
    
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
        ScrollingTabContainerView.this.mVisibilityAnim = null;
        ScrollingTabContainerView.this.setVisibility(this.mFinalVisibility);
    }
    
    @Override
    public void onAnimationStart(final View view) {
        ScrollingTabContainerView.this.setVisibility(0);
        this.mCanceled = false;
    }
    
    public VisibilityAnimListener withFinalVisibility(final ViewPropertyAnimatorCompat mVisibilityAnim, final int mFinalVisibility) {
        this.mFinalVisibility = mFinalVisibility;
        ScrollingTabContainerView.this.mVisibilityAnim = mVisibilityAnim;
        return this;
    }
}
