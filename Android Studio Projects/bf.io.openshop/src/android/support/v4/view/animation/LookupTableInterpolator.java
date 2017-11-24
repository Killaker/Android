package android.support.v4.view.animation;

import android.view.animation.*;

abstract class LookupTableInterpolator implements Interpolator
{
    private final float mStepSize;
    private final float[] mValues;
    
    public LookupTableInterpolator(final float[] mValues) {
        this.mValues = mValues;
        this.mStepSize = 1.0f / (-1 + this.mValues.length);
    }
    
    public float getInterpolation(final float n) {
        if (n >= 1.0f) {
            return 1.0f;
        }
        if (n <= 0.0f) {
            return 0.0f;
        }
        final int min = Math.min((int)(n * (-1 + this.mValues.length)), -2 + this.mValues.length);
        return this.mValues[min] + (n - min * this.mStepSize) / this.mStepSize * (this.mValues[min + 1] - this.mValues[min]);
    }
}
