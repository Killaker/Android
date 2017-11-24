package android.support.v7.app;

import android.support.v7.widget.*;
import android.graphics.*;

class AppCompatDelegateImplV7$3 implements OnFitSystemWindowsListener {
    @Override
    public void onFitSystemWindows(final Rect rect) {
        rect.top = AppCompatDelegateImplV7.access$300(AppCompatDelegateImplV7.this, rect.top);
    }
}