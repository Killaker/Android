package android.support.design.widget;

import android.support.v4.view.*;
import android.view.*;

private class FlingRunnable implements Runnable
{
    private final V mLayout;
    private final CoordinatorLayout mParent;
    
    FlingRunnable(final CoordinatorLayout mParent, final V mLayout) {
        this.mParent = mParent;
        this.mLayout = mLayout;
    }
    
    @Override
    public void run() {
        if (this.mLayout != null && HeaderBehavior.access$000(HeaderBehavior.this) != null) {
            if (!HeaderBehavior.access$000(HeaderBehavior.this).computeScrollOffset()) {
                HeaderBehavior.this.onFlingFinished(this.mParent, this.mLayout);
                return;
            }
            HeaderBehavior.this.setHeaderTopBottomOffset(this.mParent, this.mLayout, HeaderBehavior.access$000(HeaderBehavior.this).getCurrY());
            ViewCompat.postOnAnimation(this.mLayout, this);
        }
    }
}
