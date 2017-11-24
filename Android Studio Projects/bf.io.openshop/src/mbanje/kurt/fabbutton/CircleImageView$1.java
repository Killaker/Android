package mbanje.kurt.fabbutton;

import android.animation.*;

class CircleImageView$1 extends AnimatorListenerAdapter {
    public void onAnimationEnd(final Animator animator) {
        if (CircleImageView.access$000(CircleImageView.this) != null) {
            CircleImageView.access$000(CircleImageView.this).onProgressVisibilityChanged(CircleImageView.access$100(CircleImageView.this));
        }
    }
}