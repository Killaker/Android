package android.support.v7.app;

import android.view.*;
import android.support.v4.view.*;

class AppCompatDelegateImplV7$2 implements OnApplyWindowInsetsListener {
    @Override
    public WindowInsetsCompat onApplyWindowInsets(final View view, WindowInsetsCompat replaceSystemWindowInsets) {
        final int systemWindowInsetTop = replaceSystemWindowInsets.getSystemWindowInsetTop();
        final int access$300 = AppCompatDelegateImplV7.access$300(AppCompatDelegateImplV7.this, systemWindowInsetTop);
        if (systemWindowInsetTop != access$300) {
            replaceSystemWindowInsets = replaceSystemWindowInsets.replaceSystemWindowInsets(replaceSystemWindowInsets.getSystemWindowInsetLeft(), access$300, replaceSystemWindowInsets.getSystemWindowInsetRight(), replaceSystemWindowInsets.getSystemWindowInsetBottom());
        }
        return ViewCompat.onApplyWindowInsets(view, replaceSystemWindowInsets);
    }
}