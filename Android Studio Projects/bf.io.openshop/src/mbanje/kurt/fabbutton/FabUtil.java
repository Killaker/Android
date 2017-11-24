package mbanje.kurt.fabbutton;

import android.view.*;
import android.view.animation.*;
import android.animation.*;

public class FabUtil
{
    public static final int ANIMATION_STEPS = 4;
    public static final float INDETERMINANT_MIN_SWEEP = 15.0f;
    
    public static AnimatorSet createIndeterminateAnimator(final View view, final float n, final int n2, final OnFabValueCallback onFabValueCallback) {
        final float n3 = -90.0f + 270.0f * n;
        final ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[] { 15.0f, 285.0f });
        ofFloat.setDuration((long)(n2 / 4 / 2));
        ofFloat.setInterpolator((TimeInterpolator)new DecelerateInterpolator(1.0f));
        ofFloat.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new ValueAnimator$AnimatorUpdateListener() {
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                onFabValueCallback.onIndeterminateValuesChanged((float)valueAnimator.getAnimatedValue(), -1.0f, -1.0f, -1.0f);
                view.invalidate();
            }
        });
        final ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[] { 720.0f * n / 4.0f, 720.0f * (0.5f + n) / 4.0f });
        ofFloat2.setDuration((long)(n2 / 4 / 2));
        ofFloat2.setInterpolator((TimeInterpolator)new LinearInterpolator());
        ofFloat2.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new ValueAnimator$AnimatorUpdateListener() {
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                onFabValueCallback.onIndeterminateValuesChanged(-1.0f, (float)valueAnimator.getAnimatedValue(), -1.0f, -1.0f);
            }
        });
        final ValueAnimator ofFloat3 = ValueAnimator.ofFloat(new float[] { n3, 285.0f + n3 - 15.0f });
        ofFloat3.setDuration((long)(n2 / 4 / 2));
        ofFloat3.setInterpolator((TimeInterpolator)new DecelerateInterpolator(1.0f));
        ofFloat3.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new ValueAnimator$AnimatorUpdateListener() {
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                final float floatValue = (float)valueAnimator.getAnimatedValue();
                onFabValueCallback.onIndeterminateValuesChanged(285.0f - floatValue + n3, -1.0f, floatValue, -1.0f);
                view.invalidate();
            }
        });
        final ValueAnimator ofFloat4 = ValueAnimator.ofFloat(new float[] { 720.0f * (0.5f + n) / 4.0f, 720.0f * (1.0f + n) / 4.0f });
        ofFloat4.setDuration((long)(n2 / 4 / 2));
        ofFloat4.setInterpolator((TimeInterpolator)new LinearInterpolator());
        ofFloat4.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new ValueAnimator$AnimatorUpdateListener() {
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                onFabValueCallback.onIndeterminateValuesChanged(-1.0f, (float)valueAnimator.getAnimatedValue(), -1.0f, -1.0f);
            }
        });
        final AnimatorSet set = new AnimatorSet();
        set.play((Animator)ofFloat).with((Animator)ofFloat2);
        set.play((Animator)ofFloat3).with((Animator)ofFloat4).after((Animator)ofFloat2);
        return set;
    }
    
    public static ValueAnimator createProgressAnimator(final View view, final float n, final float n2, final OnFabValueCallback onFabValueCallback) {
        final ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[] { n, n2 });
        ofFloat.setDuration(500L);
        ofFloat.setInterpolator((TimeInterpolator)new LinearInterpolator());
        ofFloat.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new ValueAnimator$AnimatorUpdateListener() {
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                onFabValueCallback.onIndeterminateValuesChanged(-1.0f, -1.0f, -1.0f, (float)valueAnimator.getAnimatedValue());
                view.invalidate();
            }
        });
        return ofFloat;
    }
    
    public static ValueAnimator createStartAngleAnimator(final View view, final float n, final float n2, final OnFabValueCallback onFabValueCallback) {
        final ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[] { n, n2 });
        ofFloat.setDuration(5000L);
        ofFloat.setInterpolator((TimeInterpolator)new DecelerateInterpolator(2.0f));
        ofFloat.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new ValueAnimator$AnimatorUpdateListener() {
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                onFabValueCallback.onIndeterminateValuesChanged(-1.0f, -1.0f, (float)valueAnimator.getAnimatedValue(), -1.0f);
                view.invalidate();
            }
        });
        return ofFloat;
    }
    
    public interface OnFabValueCallback
    {
        void onIndeterminateValuesChanged(final float p0, final float p1, final float p2, final float p3);
    }
}
