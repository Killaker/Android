package android.support.v7.widget;

import android.view.animation.*;

class SwitchCompat$1 implements Animation$AnimationListener {
    final /* synthetic */ boolean val$newCheckedState;
    
    public void onAnimationEnd(final Animation animation) {
        if (SwitchCompat.access$100(SwitchCompat.this) == animation) {
            final SwitchCompat this$0 = SwitchCompat.this;
            float n;
            if (this.val$newCheckedState) {
                n = 1.0f;
            }
            else {
                n = 0.0f;
            }
            SwitchCompat.access$200(this$0, n);
            SwitchCompat.access$102(SwitchCompat.this, null);
        }
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
    }
}