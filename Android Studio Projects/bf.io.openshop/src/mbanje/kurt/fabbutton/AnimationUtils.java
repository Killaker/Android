package mbanje.kurt.fabbutton;

import android.support.v4.view.animation.*;
import android.view.animation.*;

public class AnimationUtils
{
    public static final Interpolator DECELERATE_INTERPOLATOR;
    public static final Interpolator FAST_OUT_SLOW_IN_INTERPOLATOR;
    public static final Interpolator LINEAR_INTERPOLATOR;
    
    static {
        LINEAR_INTERPOLATOR = (Interpolator)new LinearInterpolator();
        FAST_OUT_SLOW_IN_INTERPOLATOR = (Interpolator)new FastOutSlowInInterpolator();
        DECELERATE_INTERPOLATOR = (Interpolator)new DecelerateInterpolator();
    }
    
    static float lerp(final float n, final float n2, final float n3) {
        return n + n3 * (n2 - n);
    }
    
    static int lerp(final int n, final int n2, final float n3) {
        return n + Math.round(n3 * (n2 - n));
    }
    
    public static class AnimationListenerAdapter implements Animation$AnimationListener
    {
        public void onAnimationEnd(final Animation animation) {
        }
        
        public void onAnimationRepeat(final Animation animation) {
        }
        
        public void onAnimationStart(final Animation animation) {
        }
    }
}
