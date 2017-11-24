package android.support.design.internal;

import android.view.*;
import android.graphics.*;
import android.support.v4.view.*;

class ScrimInsetsFrameLayout$1 implements OnApplyWindowInsetsListener {
    @Override
    public WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        if (ScrimInsetsFrameLayout.access$000(ScrimInsetsFrameLayout.this) == null) {
            ScrimInsetsFrameLayout.access$002(ScrimInsetsFrameLayout.this, new Rect());
        }
        ScrimInsetsFrameLayout.access$000(ScrimInsetsFrameLayout.this).set(windowInsetsCompat.getSystemWindowInsetLeft(), windowInsetsCompat.getSystemWindowInsetTop(), windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom());
        ScrimInsetsFrameLayout.this.onInsetsChanged(ScrimInsetsFrameLayout.access$000(ScrimInsetsFrameLayout.this));
        ScrimInsetsFrameLayout.this.setWillNotDraw(ScrimInsetsFrameLayout.access$000(ScrimInsetsFrameLayout.this).isEmpty() || ScrimInsetsFrameLayout.access$100(ScrimInsetsFrameLayout.this) == null);
        ViewCompat.postInvalidateOnAnimation((View)ScrimInsetsFrameLayout.this);
        return windowInsetsCompat.consumeSystemWindowInsets();
    }
}