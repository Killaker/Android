package android.support.v4.view;

import java.lang.ref.*;
import android.view.*;

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
            BaseViewPropertyAnimatorCompatImpl.access$200(BaseViewPropertyAnimatorCompatImpl.this, this.mVpa, view);
        }
    }
}
