package android.support.v7.widget;

import android.graphics.drawable.*;
import android.content.res.*;
import android.graphics.*;

class RoundRectDrawable extends Drawable
{
    private final RectF mBoundsF;
    private final Rect mBoundsI;
    private boolean mInsetForPadding;
    private boolean mInsetForRadius;
    private float mPadding;
    private final Paint mPaint;
    private float mRadius;
    private ColorStateList mTint;
    private PorterDuffColorFilter mTintFilter;
    private PorterDuff$Mode mTintMode;
    
    public RoundRectDrawable(final int color, final float mRadius) {
        this.mInsetForPadding = false;
        this.mInsetForRadius = true;
        this.mRadius = mRadius;
        (this.mPaint = new Paint(5)).setColor(color);
        this.mBoundsF = new RectF();
        this.mBoundsI = new Rect();
    }
    
    private PorterDuffColorFilter createTintFilter(final ColorStateList list, final PorterDuff$Mode porterDuff$Mode) {
        if (list == null || porterDuff$Mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(list.getColorForState(this.getState(), 0), porterDuff$Mode);
    }
    
    private void updateBounds(Rect bounds) {
        if (bounds == null) {
            bounds = this.getBounds();
        }
        this.mBoundsF.set((float)bounds.left, (float)bounds.top, (float)bounds.right, (float)bounds.bottom);
        this.mBoundsI.set(bounds);
        if (this.mInsetForPadding) {
            this.mBoundsI.inset((int)Math.ceil(RoundRectDrawableWithShadow.calculateHorizontalPadding(this.mPadding, this.mRadius, this.mInsetForRadius)), (int)Math.ceil(RoundRectDrawableWithShadow.calculateVerticalPadding(this.mPadding, this.mRadius, this.mInsetForRadius)));
            this.mBoundsF.set(this.mBoundsI);
        }
    }
    
    public void draw(final Canvas canvas) {
        final Paint mPaint = this.mPaint;
        int n;
        if (this.mTintFilter != null && mPaint.getColorFilter() == null) {
            mPaint.setColorFilter((ColorFilter)this.mTintFilter);
            n = 1;
        }
        else {
            n = 0;
        }
        canvas.drawRoundRect(this.mBoundsF, this.mRadius, this.mRadius, mPaint);
        if (n != 0) {
            mPaint.setColorFilter((ColorFilter)null);
        }
    }
    
    public int getOpacity() {
        return -3;
    }
    
    public void getOutline(final Outline outline) {
        outline.setRoundRect(this.mBoundsI, this.mRadius);
    }
    
    float getPadding() {
        return this.mPadding;
    }
    
    public float getRadius() {
        return this.mRadius;
    }
    
    public boolean isStateful() {
        return (this.mTint != null && this.mTint.isStateful()) || super.isStateful();
    }
    
    protected void onBoundsChange(final Rect rect) {
        super.onBoundsChange(rect);
        this.updateBounds(rect);
    }
    
    protected boolean onStateChange(final int[] array) {
        if (this.mTint != null && this.mTintMode != null) {
            this.mTintFilter = this.createTintFilter(this.mTint, this.mTintMode);
            return true;
        }
        return false;
    }
    
    public void setAlpha(final int alpha) {
        this.mPaint.setAlpha(alpha);
    }
    
    public void setColor(final int color) {
        this.mPaint.setColor(color);
        this.invalidateSelf();
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }
    
    void setPadding(final float mPadding, final boolean mInsetForPadding, final boolean mInsetForRadius) {
        if (mPadding == this.mPadding && this.mInsetForPadding == mInsetForPadding && this.mInsetForRadius == mInsetForRadius) {
            return;
        }
        this.mPadding = mPadding;
        this.mInsetForPadding = mInsetForPadding;
        this.mInsetForRadius = mInsetForRadius;
        this.updateBounds(null);
        this.invalidateSelf();
    }
    
    void setRadius(final float mRadius) {
        if (mRadius == this.mRadius) {
            return;
        }
        this.mRadius = mRadius;
        this.updateBounds(null);
        this.invalidateSelf();
    }
    
    public void setTintList(final ColorStateList mTint) {
        this.mTint = mTint;
        this.mTintFilter = this.createTintFilter(this.mTint, this.mTintMode);
        this.invalidateSelf();
    }
    
    public void setTintMode(final PorterDuff$Mode mTintMode) {
        this.mTintMode = mTintMode;
        this.mTintFilter = this.createTintFilter(this.mTint, this.mTintMode);
        this.invalidateSelf();
    }
}
