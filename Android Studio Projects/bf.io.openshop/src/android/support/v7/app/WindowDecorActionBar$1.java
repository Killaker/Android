package android.support.v7.app;

import android.view.*;
import android.support.v4.view.*;
import android.support.v7.view.*;

class WindowDecorActionBar$1 extends ViewPropertyAnimatorListenerAdapter {
    @Override
    public void onAnimationEnd(final View view) {
        if (WindowDecorActionBar.access$000(WindowDecorActionBar.this) && WindowDecorActionBar.access$100(WindowDecorActionBar.this) != null) {
            ViewCompat.setTranslationY(WindowDecorActionBar.access$100(WindowDecorActionBar.this), 0.0f);
            ViewCompat.setTranslationY((View)WindowDecorActionBar.access$200(WindowDecorActionBar.this), 0.0f);
        }
        WindowDecorActionBar.access$200(WindowDecorActionBar.this).setVisibility(8);
        WindowDecorActionBar.access$200(WindowDecorActionBar.this).setTransitioning(false);
        WindowDecorActionBar.access$302(WindowDecorActionBar.this, null);
        WindowDecorActionBar.this.completeDeferredDestroyActionMode();
        if (WindowDecorActionBar.access$400(WindowDecorActionBar.this) != null) {
            ViewCompat.requestApplyInsets((View)WindowDecorActionBar.access$400(WindowDecorActionBar.this));
        }
    }
}