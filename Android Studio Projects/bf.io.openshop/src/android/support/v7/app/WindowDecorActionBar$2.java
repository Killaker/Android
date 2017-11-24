package android.support.v7.app;

import android.support.v4.view.*;
import android.view.*;
import android.support.v7.view.*;

class WindowDecorActionBar$2 extends ViewPropertyAnimatorListenerAdapter {
    @Override
    public void onAnimationEnd(final View view) {
        WindowDecorActionBar.access$302(WindowDecorActionBar.this, null);
        WindowDecorActionBar.access$200(WindowDecorActionBar.this).requestLayout();
    }
}