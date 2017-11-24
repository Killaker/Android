package android.support.v7.app;

import android.support.v4.view.*;
import android.view.*;

class WindowDecorActionBar$3 implements ViewPropertyAnimatorUpdateListener {
    @Override
    public void onAnimationUpdate(final View view) {
        ((View)WindowDecorActionBar.access$200(WindowDecorActionBar.this).getParent()).invalidate();
    }
}