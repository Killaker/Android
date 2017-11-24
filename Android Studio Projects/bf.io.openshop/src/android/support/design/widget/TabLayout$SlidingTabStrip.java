package android.support.design.widget;

import android.content.*;
import android.support.v4.view.*;
import android.graphics.*;
import android.view.*;
import android.widget.*;

private class SlidingTabStrip extends LinearLayout
{
    private ValueAnimatorCompat mIndicatorAnimator;
    private int mIndicatorLeft;
    private int mIndicatorRight;
    private int mSelectedIndicatorHeight;
    private final Paint mSelectedIndicatorPaint;
    private int mSelectedPosition;
    private float mSelectionOffset;
    
    SlidingTabStrip(final Context context) {
        super(context);
        this.mSelectedPosition = -1;
        this.mIndicatorLeft = -1;
        this.mIndicatorRight = -1;
        this.setWillNotDraw(false);
        this.mSelectedIndicatorPaint = new Paint();
    }
    
    private void setIndicatorPosition(final int mIndicatorLeft, final int mIndicatorRight) {
        if (mIndicatorLeft != this.mIndicatorLeft || mIndicatorRight != this.mIndicatorRight) {
            this.mIndicatorLeft = mIndicatorLeft;
            this.mIndicatorRight = mIndicatorRight;
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    private void updateIndicatorPosition() {
        final View child = this.getChildAt(this.mSelectedPosition);
        int left;
        int right;
        if (child != null && child.getWidth() > 0) {
            left = child.getLeft();
            right = child.getRight();
            if (this.mSelectionOffset > 0.0f && this.mSelectedPosition < -1 + this.getChildCount()) {
                final View child2 = this.getChildAt(1 + this.mSelectedPosition);
                left = (int)(this.mSelectionOffset * child2.getLeft() + (1.0f - this.mSelectionOffset) * left);
                right = (int)(this.mSelectionOffset * child2.getRight() + (1.0f - this.mSelectionOffset) * right);
            }
        }
        else {
            right = (left = -1);
        }
        this.setIndicatorPosition(left, right);
    }
    
    void animateIndicatorToPosition(final int n, final int duration) {
        if (this.mIndicatorAnimator != null && this.mIndicatorAnimator.isRunning()) {
            this.mIndicatorAnimator.cancel();
        }
        final boolean b = ViewCompat.getLayoutDirection((View)this) == 1;
        final View child = this.getChildAt(n);
        if (child == null) {
            this.updateIndicatorPosition();
        }
        else {
            final int left = child.getLeft();
            final int right = child.getRight();
            int mIndicatorLeft;
            int mIndicatorRight;
            if (Math.abs(n - this.mSelectedPosition) <= 1) {
                mIndicatorLeft = this.mIndicatorLeft;
                mIndicatorRight = this.mIndicatorRight;
            }
            else {
                final int access$2100 = TabLayout.access$2100(TabLayout.this, 24);
                if (n < this.mSelectedPosition) {
                    if (b) {
                        mIndicatorRight = (mIndicatorLeft = left - access$2100);
                    }
                    else {
                        mIndicatorRight = (mIndicatorLeft = right + access$2100);
                    }
                }
                else if (b) {
                    mIndicatorRight = (mIndicatorLeft = right + access$2100);
                }
                else {
                    mIndicatorRight = (mIndicatorLeft = left - access$2100);
                }
            }
            if (mIndicatorLeft != left || mIndicatorRight != right) {
                final ValueAnimatorCompat animator = ViewUtils.createAnimator();
                (this.mIndicatorAnimator = animator).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                animator.setDuration(duration);
                animator.setFloatValues(0.0f, 1.0f);
                animator.setUpdateListener((ValueAnimatorCompat.AnimatorUpdateListener)new ValueAnimatorCompat.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
                        final float animatedFraction = valueAnimatorCompat.getAnimatedFraction();
                        SlidingTabStrip.this.setIndicatorPosition(AnimationUtils.lerp(mIndicatorLeft, left, animatedFraction), AnimationUtils.lerp(mIndicatorRight, right, animatedFraction));
                    }
                });
                animator.setListener((ValueAnimatorCompat.AnimatorListener)new ValueAnimatorCompat.AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(final ValueAnimatorCompat valueAnimatorCompat) {
                        SlidingTabStrip.this.mSelectedPosition = n;
                        SlidingTabStrip.this.mSelectionOffset = 0.0f;
                    }
                });
                animator.start();
            }
        }
    }
    
    boolean childrenNeedLayout() {
        for (int i = 0; i < this.getChildCount(); ++i) {
            if (this.getChildAt(i).getWidth() <= 0) {
                return true;
            }
        }
        return false;
    }
    
    public void draw(final Canvas canvas) {
        super.draw(canvas);
        if (this.mIndicatorLeft >= 0 && this.mIndicatorRight > this.mIndicatorLeft) {
            canvas.drawRect((float)this.mIndicatorLeft, (float)(this.getHeight() - this.mSelectedIndicatorHeight), (float)this.mIndicatorRight, (float)this.getHeight(), this.mSelectedIndicatorPaint);
        }
    }
    
    float getIndicatorPosition() {
        return this.mSelectedPosition + this.mSelectionOffset;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        if (this.mIndicatorAnimator != null && this.mIndicatorAnimator.isRunning()) {
            this.mIndicatorAnimator.cancel();
            this.animateIndicatorToPosition(this.mSelectedPosition, Math.round((1.0f - this.mIndicatorAnimator.getAnimatedFraction()) * this.mIndicatorAnimator.getDuration()));
            return;
        }
        this.updateIndicatorPosition();
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        if (View$MeasureSpec.getMode(n) == 1073741824 && TabLayout.access$1800(TabLayout.this) == 1 && TabLayout.access$2200(TabLayout.this) == 1) {
            final int childCount = this.getChildCount();
            int max = 0;
            for (int i = 0; i < childCount; ++i) {
                final View child = this.getChildAt(i);
                if (child.getVisibility() == 0) {
                    max = Math.max(max, child.getMeasuredWidth());
                }
            }
            if (max > 0) {
                final int access$2100 = TabLayout.access$2100(TabLayout.this, 16);
                boolean b = false;
                if (max * childCount <= this.getMeasuredWidth() - access$2100 * 2) {
                    for (int j = 0; j < childCount; ++j) {
                        final LinearLayout$LayoutParams linearLayout$LayoutParams = (LinearLayout$LayoutParams)this.getChildAt(j).getLayoutParams();
                        if (linearLayout$LayoutParams.width != max || linearLayout$LayoutParams.weight != 0.0f) {
                            linearLayout$LayoutParams.width = max;
                            linearLayout$LayoutParams.weight = 0.0f;
                            b = true;
                        }
                    }
                }
                else {
                    TabLayout.access$2202(TabLayout.this, 0);
                    TabLayout.access$2300(TabLayout.this, false);
                    b = true;
                }
                if (b) {
                    super.onMeasure(n, n2);
                }
            }
        }
    }
    
    void setIndicatorPositionFromTabPosition(final int mSelectedPosition, final float mSelectionOffset) {
        if (this.mIndicatorAnimator != null && this.mIndicatorAnimator.isRunning()) {
            this.mIndicatorAnimator.cancel();
        }
        this.mSelectedPosition = mSelectedPosition;
        this.mSelectionOffset = mSelectionOffset;
        this.updateIndicatorPosition();
    }
    
    void setSelectedIndicatorColor(final int color) {
        if (this.mSelectedIndicatorPaint.getColor() != color) {
            this.mSelectedIndicatorPaint.setColor(color);
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    void setSelectedIndicatorHeight(final int mSelectedIndicatorHeight) {
        if (this.mSelectedIndicatorHeight != mSelectedIndicatorHeight) {
            this.mSelectedIndicatorHeight = mSelectedIndicatorHeight;
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
}
