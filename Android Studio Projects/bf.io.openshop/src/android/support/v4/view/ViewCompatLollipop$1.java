package android.support.v4.view;

import android.view.*;

static final class ViewCompatLollipop$1 implements View$OnApplyWindowInsetsListener {
    final /* synthetic */ OnApplyWindowInsetsListener val$listener;
    
    public WindowInsets onApplyWindowInsets(final View view, final WindowInsets windowInsets) {
        return ((WindowInsetsCompatApi21)this.val$listener.onApplyWindowInsets(view, new WindowInsetsCompatApi21(windowInsets))).unwrap();
    }
}