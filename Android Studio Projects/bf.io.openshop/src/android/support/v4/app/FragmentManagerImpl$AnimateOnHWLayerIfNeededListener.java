package android.support.v4.app;

import android.view.*;
import android.view.animation.*;
import android.support.v4.view.*;
import android.graphics.*;
import android.support.annotation.*;

static class AnimateOnHWLayerIfNeededListener implements Animation$AnimationListener
{
    private Animation$AnimationListener mOrignalListener;
    private boolean mShouldRunOnHWLayer;
    private View mView;
    
    public AnimateOnHWLayerIfNeededListener(final View mView, final Animation animation) {
        this.mOrignalListener = null;
        this.mShouldRunOnHWLayer = false;
        this.mView = null;
        if (mView == null || animation == null) {
            return;
        }
        this.mView = mView;
    }
    
    public AnimateOnHWLayerIfNeededListener(final View mView, final Animation animation, final Animation$AnimationListener mOrignalListener) {
        this.mOrignalListener = null;
        this.mShouldRunOnHWLayer = false;
        this.mView = null;
        if (mView == null || animation == null) {
            return;
        }
        this.mOrignalListener = mOrignalListener;
        this.mView = mView;
    }
    
    @CallSuper
    public void onAnimationEnd(final Animation animation) {
        if (this.mView != null && this.mShouldRunOnHWLayer) {
            this.mView.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    ViewCompat.setLayerType(AnimateOnHWLayerIfNeededListener.this.mView, 0, null);
                }
            });
        }
        if (this.mOrignalListener != null) {
            this.mOrignalListener.onAnimationEnd(animation);
        }
    }
    
    public void onAnimationRepeat(final Animation animation) {
        if (this.mOrignalListener != null) {
            this.mOrignalListener.onAnimationRepeat(animation);
        }
    }
    
    @CallSuper
    public void onAnimationStart(final Animation animation) {
        if (this.mView != null) {
            this.mShouldRunOnHWLayer = FragmentManagerImpl.shouldRunOnHWLayer(this.mView, animation);
            if (this.mShouldRunOnHWLayer) {
                this.mView.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        ViewCompat.setLayerType(AnimateOnHWLayerIfNeededListener.this.mView, 2, null);
                    }
                });
            }
        }
        if (this.mOrignalListener != null) {
            this.mOrignalListener.onAnimationStart(animation);
        }
    }
}
