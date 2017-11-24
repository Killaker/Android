package android.support.v4.view;

import android.view.*;
import android.graphics.*;
import android.os.*;

static class MyVpaListener implements ViewPropertyAnimatorListener
{
    boolean mAnimEndCalled;
    ViewPropertyAnimatorCompat mVpa;
    
    MyVpaListener(final ViewPropertyAnimatorCompat mVpa) {
        this.mVpa = mVpa;
    }
    
    @Override
    public void onAnimationCancel(final View view) {
        final Object tag = view.getTag(2113929216);
        final boolean b = tag instanceof ViewPropertyAnimatorListener;
        ViewPropertyAnimatorListener viewPropertyAnimatorListener = null;
        if (b) {
            viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
        }
        if (viewPropertyAnimatorListener != null) {
            viewPropertyAnimatorListener.onAnimationCancel(view);
        }
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        if (ViewPropertyAnimatorCompat.access$400(this.mVpa) >= 0) {
            ViewCompat.setLayerType(view, ViewPropertyAnimatorCompat.access$400(this.mVpa), null);
            ViewPropertyAnimatorCompat.access$402(this.mVpa, -1);
        }
        if (Build$VERSION.SDK_INT >= 16 || !this.mAnimEndCalled) {
            if (ViewPropertyAnimatorCompat.access$000(this.mVpa) != null) {
                final Runnable access$000 = ViewPropertyAnimatorCompat.access$000(this.mVpa);
                ViewPropertyAnimatorCompat.access$002(this.mVpa, null);
                access$000.run();
            }
            final Object tag = view.getTag(2113929216);
            final boolean b = tag instanceof ViewPropertyAnimatorListener;
            ViewPropertyAnimatorListener viewPropertyAnimatorListener = null;
            if (b) {
                viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
            }
            if (viewPropertyAnimatorListener != null) {
                viewPropertyAnimatorListener.onAnimationEnd(view);
            }
            this.mAnimEndCalled = true;
        }
    }
    
    @Override
    public void onAnimationStart(final View view) {
        this.mAnimEndCalled = false;
        if (ViewPropertyAnimatorCompat.access$400(this.mVpa) >= 0) {
            ViewCompat.setLayerType(view, 2, null);
        }
        if (ViewPropertyAnimatorCompat.access$100(this.mVpa) != null) {
            final Runnable access$100 = ViewPropertyAnimatorCompat.access$100(this.mVpa);
            ViewPropertyAnimatorCompat.access$102(this.mVpa, null);
            access$100.run();
        }
        final Object tag = view.getTag(2113929216);
        final boolean b = tag instanceof ViewPropertyAnimatorListener;
        ViewPropertyAnimatorListener viewPropertyAnimatorListener = null;
        if (b) {
            viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
        }
        if (viewPropertyAnimatorListener != null) {
            viewPropertyAnimatorListener.onAnimationStart(view);
        }
    }
}
