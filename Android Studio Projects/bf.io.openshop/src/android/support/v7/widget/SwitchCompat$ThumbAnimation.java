package android.support.v7.widget;

import android.view.animation.*;

private class ThumbAnimation extends Animation
{
    final float mDiff;
    final float mEndPosition;
    final float mStartPosition;
    
    private ThumbAnimation(final float mStartPosition, final float mEndPosition) {
        this.mStartPosition = mStartPosition;
        this.mEndPosition = mEndPosition;
        this.mDiff = mEndPosition - mStartPosition;
    }
    
    protected void applyTransformation(final float n, final Transformation transformation) {
        SwitchCompat.access$200(SwitchCompat.this, this.mStartPosition + n * this.mDiff);
    }
}
