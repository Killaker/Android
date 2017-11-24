package android.support.v4.view;

import java.util.*;
import android.view.*;
import android.view.animation.*;
import java.lang.ref.*;

static class BaseViewPropertyAnimatorCompatImpl implements ViewPropertyAnimatorCompatImpl
{
    WeakHashMap<View, Runnable> mStarterMap;
    
    BaseViewPropertyAnimatorCompatImpl() {
        this.mStarterMap = null;
    }
    
    private void postStartMessage(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        final WeakHashMap<View, Runnable> mStarterMap = this.mStarterMap;
        Runnable runnable = null;
        if (mStarterMap != null) {
            runnable = this.mStarterMap.get(view);
        }
        if (runnable == null) {
            runnable = viewPropertyAnimatorCompat.new Starter(view);
            if (this.mStarterMap == null) {
                this.mStarterMap = new WeakHashMap<View, Runnable>();
            }
            this.mStarterMap.put(view, runnable);
        }
        view.removeCallbacks(runnable);
        view.post(runnable);
    }
    
    private void removeStartMessage(final View view) {
        if (this.mStarterMap != null) {
            final Runnable runnable = this.mStarterMap.get(view);
            if (runnable != null) {
                view.removeCallbacks(runnable);
            }
        }
    }
    
    private void startAnimation(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        final Object tag = view.getTag(2113929216);
        final boolean b = tag instanceof ViewPropertyAnimatorListener;
        ViewPropertyAnimatorListener viewPropertyAnimatorListener = null;
        if (b) {
            viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
        }
        final Runnable access$100 = ViewPropertyAnimatorCompat.access$100(viewPropertyAnimatorCompat);
        final Runnable access$101 = ViewPropertyAnimatorCompat.access$000(viewPropertyAnimatorCompat);
        ViewPropertyAnimatorCompat.access$102(viewPropertyAnimatorCompat, null);
        ViewPropertyAnimatorCompat.access$002(viewPropertyAnimatorCompat, null);
        if (access$100 != null) {
            access$100.run();
        }
        if (viewPropertyAnimatorListener != null) {
            viewPropertyAnimatorListener.onAnimationStart(view);
            viewPropertyAnimatorListener.onAnimationEnd(view);
        }
        if (access$101 != null) {
            access$101.run();
        }
        if (this.mStarterMap != null) {
            this.mStarterMap.remove(view);
        }
    }
    
    @Override
    public void alpha(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void alphaBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void cancel(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public long getDuration(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        return 0L;
    }
    
    @Override
    public Interpolator getInterpolator(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        return null;
    }
    
    @Override
    public long getStartDelay(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        return 0L;
    }
    
    @Override
    public void rotation(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void rotationBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void rotationX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void rotationXBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void rotationY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void rotationYBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void scaleX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void scaleXBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void scaleY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void scaleYBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void setDuration(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final long n) {
    }
    
    @Override
    public void setInterpolator(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Interpolator interpolator) {
    }
    
    @Override
    public void setListener(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        view.setTag(2113929216, (Object)viewPropertyAnimatorListener);
    }
    
    @Override
    public void setStartDelay(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final long n) {
    }
    
    @Override
    public void setUpdateListener(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
    }
    
    @Override
    public void start(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        this.removeStartMessage(view);
        this.startAnimation(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void translationX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void translationXBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void translationY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void translationYBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void translationZ(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
    }
    
    @Override
    public void translationZBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
    }
    
    @Override
    public void withEndAction(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Runnable runnable) {
        ViewPropertyAnimatorCompat.access$002(viewPropertyAnimatorCompat, runnable);
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void withLayer(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
    }
    
    @Override
    public void withStartAction(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Runnable runnable) {
        ViewPropertyAnimatorCompat.access$102(viewPropertyAnimatorCompat, runnable);
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void x(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void xBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void y(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void yBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void z(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
    }
    
    @Override
    public void zBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
    }
    
    class Starter implements Runnable
    {
        WeakReference<View> mViewRef;
        ViewPropertyAnimatorCompat mVpa;
        
        private Starter(final ViewPropertyAnimatorCompat mVpa, final View view) {
            this.mViewRef = new WeakReference<View>(view);
            this.mVpa = mVpa;
        }
        
        @Override
        public void run() {
            final View view = this.mViewRef.get();
            if (view != null) {
                BaseViewPropertyAnimatorCompatImpl.this.startAnimation(this.mVpa, view);
            }
        }
    }
}
