package android.support.v7.view;

import android.support.v4.view.*;
import android.view.*;

class ViewPropertyAnimatorCompatSet$1 extends ViewPropertyAnimatorListenerAdapter {
    private int mProxyEndCount = 0;
    private boolean mProxyStarted = false;
    
    @Override
    public void onAnimationEnd(final View view) {
        final int mProxyEndCount = 1 + this.mProxyEndCount;
        this.mProxyEndCount = mProxyEndCount;
        if (mProxyEndCount == ViewPropertyAnimatorCompatSet.access$200(ViewPropertyAnimatorCompatSet.this).size()) {
            if (ViewPropertyAnimatorCompatSet.access$000(ViewPropertyAnimatorCompatSet.this) != null) {
                ViewPropertyAnimatorCompatSet.access$000(ViewPropertyAnimatorCompatSet.this).onAnimationEnd(null);
            }
            this.onEnd();
        }
    }
    
    @Override
    public void onAnimationStart(final View view) {
        if (!this.mProxyStarted) {
            this.mProxyStarted = true;
            if (ViewPropertyAnimatorCompatSet.access$000(ViewPropertyAnimatorCompatSet.this) != null) {
                ViewPropertyAnimatorCompatSet.access$000(ViewPropertyAnimatorCompatSet.this).onAnimationStart(null);
            }
        }
    }
    
    void onEnd() {
        this.mProxyEndCount = 0;
        this.mProxyStarted = false;
        ViewPropertyAnimatorCompatSet.access$100(ViewPropertyAnimatorCompatSet.this);
    }
}