package android.support.design.widget;

import android.view.animation.*;

abstract static class Impl
{
    abstract void cancel();
    
    abstract void end();
    
    abstract float getAnimatedFloatValue();
    
    abstract float getAnimatedFraction();
    
    abstract int getAnimatedIntValue();
    
    abstract long getDuration();
    
    abstract boolean isRunning();
    
    abstract void setDuration(final int p0);
    
    abstract void setFloatValues(final float p0, final float p1);
    
    abstract void setIntValues(final int p0, final int p1);
    
    abstract void setInterpolator(final Interpolator p0);
    
    abstract void setListener(final AnimatorListenerProxy p0);
    
    abstract void setUpdateListener(final AnimatorUpdateListenerProxy p0);
    
    abstract void start();
    
    interface AnimatorListenerProxy
    {
        void onAnimationCancel();
        
        void onAnimationEnd();
        
        void onAnimationStart();
    }
    
    interface AnimatorUpdateListenerProxy
    {
        void onAnimationUpdate();
    }
}
