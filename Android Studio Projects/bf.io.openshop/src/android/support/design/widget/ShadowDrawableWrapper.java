package android.support.design.widget;

import android.support.v7.graphics.drawable.*;
import android.content.res.*;
import android.graphics.drawable.*;
import android.support.design.*;
import android.graphics.*;

class ShadowDrawableWrapper extends DrawableWrapper
{
    static final double COS_45 = 0.0;
    static final float SHADOW_BOTTOM_SCALE = 1.0f;
    static final float SHADOW_HORIZ_SCALE = 0.5f;
    static final float SHADOW_MULTIPLIER = 1.5f;
    static final float SHADOW_TOP_SCALE = 0.25f;
    private boolean mAddPaddingForCorners;
    final RectF mContentBounds;
    float mCornerRadius;
    final Paint mCornerShadowPaint;
    Path mCornerShadowPath;
    private boolean mDirty;
    final Paint mEdgeShadowPaint;
    float mMaxShadowSize;
    private boolean mPrintedShadowClipWarning;
    float mRawMaxShadowSize;
    float mRawShadowSize;
    private float mRotation;
    private final int mShadowEndColor;
    private final int mShadowMiddleColor;
    float mShadowSize;
    private final int mShadowStartColor;
    
    public ShadowDrawableWrapper(final Resources resources, final Drawable drawable, final float n, final float n2, final float n3) {
        super(drawable);
        this.mDirty = true;
        this.mAddPaddingForCorners = true;
        this.mPrintedShadowClipWarning = false;
        this.mShadowStartColor = resources.getColor(R.color.design_fab_shadow_start_color);
        this.mShadowMiddleColor = resources.getColor(R.color.design_fab_shadow_mid_color);
        this.mShadowEndColor = resources.getColor(R.color.design_fab_shadow_end_color);
        (this.mCornerShadowPaint = new Paint(5)).setStyle(Paint$Style.FILL);
        this.mCornerRadius = Math.round(n);
        this.mContentBounds = new RectF();
        (this.mEdgeShadowPaint = new Paint(this.mCornerShadowPaint)).setAntiAlias(false);
        this.setShadowSize(n2, n3);
    }
    
    private void buildComponents(final Rect rect) {
        final float n = 1.5f * this.mRawMaxShadowSize;
        this.mContentBounds.set(rect.left + this.mRawMaxShadowSize, n + rect.top, rect.right - this.mRawMaxShadowSize, rect.bottom - n);
        this.getWrappedDrawable().setBounds((int)this.mContentBounds.left, (int)this.mContentBounds.top, (int)this.mContentBounds.right, (int)this.mContentBounds.bottom);
        this.buildShadowCorners();
    }
    
    private void buildShadowCorners() {
        final RectF rectF = new RectF(-this.mCornerRadius, -this.mCornerRadius, this.mCornerRadius, this.mCornerRadius);
        final RectF rectF2 = new RectF(rectF);
        rectF2.inset(-this.mShadowSize, -this.mShadowSize);
        if (this.mCornerShadowPath == null) {
            this.mCornerShadowPath = new Path();
        }
        else {
            this.mCornerShadowPath.reset();
        }
        this.mCornerShadowPath.setFillType(Path$FillType.EVEN_ODD);
        this.mCornerShadowPath.moveTo(-this.mCornerRadius, 0.0f);
        this.mCornerShadowPath.rLineTo(-this.mShadowSize, 0.0f);
        this.mCornerShadowPath.arcTo(rectF2, 180.0f, 90.0f, false);
        this.mCornerShadowPath.arcTo(rectF, 270.0f, -90.0f, false);
        this.mCornerShadowPath.close();
        final float n = -rectF2.top;
        if (n > 0.0f) {
            final float n2 = this.mCornerRadius / n;
            this.mCornerShadowPaint.setShader((Shader)new RadialGradient(0.0f, 0.0f, n, new int[] { 0, this.mShadowStartColor, this.mShadowMiddleColor, this.mShadowEndColor }, new float[] { 0.0f, n2, n2 + (1.0f - n2) / 2.0f, 1.0f }, Shader$TileMode.CLAMP));
        }
        this.mEdgeShadowPaint.setShader((Shader)new LinearGradient(0.0f, rectF.top, 0.0f, rectF2.top, new int[] { this.mShadowStartColor, this.mShadowMiddleColor, this.mShadowEndColor }, new float[] { 0.0f, 0.5f, 1.0f }, Shader$TileMode.CLAMP));
        this.mEdgeShadowPaint.setAntiAlias(false);
    }
    
    public static float calculateHorizontalPadding(float n, final float n2, final boolean b) {
        if (b) {
            n += (float)((1.0 - ShadowDrawableWrapper.COS_45) * n2);
        }
        return n;
    }
    
    public static float calculateVerticalPadding(final float n, final float n2, final boolean b) {
        if (b) {
            return (float)(1.5f * n + (1.0 - ShadowDrawableWrapper.COS_45) * n2);
        }
        return 1.5f * n;
    }
    
    private void drawShadow(final Canvas canvas) {
        final int save = canvas.save();
        canvas.rotate(this.mRotation, this.mContentBounds.centerX(), this.mContentBounds.centerY());
        final float n = -this.mCornerRadius - this.mShadowSize;
        final float mCornerRadius = this.mCornerRadius;
        boolean b;
        if (this.mContentBounds.width() - 2.0f * mCornerRadius > 0.0f) {
            b = true;
        }
        else {
            b = false;
        }
        boolean b2;
        if (this.mContentBounds.height() - 2.0f * mCornerRadius > 0.0f) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        final float n2 = this.mRawShadowSize - 0.25f * this.mRawShadowSize;
        final float n3 = this.mRawShadowSize - 0.5f * this.mRawShadowSize;
        final float n4 = this.mRawShadowSize - 1.0f * this.mRawShadowSize;
        final float n5 = mCornerRadius / (mCornerRadius + n3);
        final float n6 = mCornerRadius / (mCornerRadius + n2);
        final float n7 = mCornerRadius / (mCornerRadius + n4);
        final int save2 = canvas.save();
        canvas.translate(mCornerRadius + this.mContentBounds.left, mCornerRadius + this.mContentBounds.top);
        canvas.scale(n5, n6);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (b) {
            canvas.scale(1.0f / n5, 1.0f);
            canvas.drawRect(0.0f, n, this.mContentBounds.width() - 2.0f * mCornerRadius, -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas.restoreToCount(save2);
        final int save3 = canvas.save();
        canvas.translate(this.mContentBounds.right - mCornerRadius, this.mContentBounds.bottom - mCornerRadius);
        canvas.scale(n5, n7);
        canvas.rotate(180.0f);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (b) {
            canvas.scale(1.0f / n5, 1.0f);
            canvas.drawRect(0.0f, n, this.mContentBounds.width() - 2.0f * mCornerRadius, -this.mCornerRadius + this.mShadowSize, this.mEdgeShadowPaint);
        }
        canvas.restoreToCount(save3);
        final int save4 = canvas.save();
        canvas.translate(mCornerRadius + this.mContentBounds.left, this.mContentBounds.bottom - mCornerRadius);
        canvas.scale(n5, n7);
        canvas.rotate(270.0f);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (b2) {
            canvas.scale(1.0f / n7, 1.0f);
            canvas.drawRect(0.0f, n, this.mContentBounds.height() - 2.0f * mCornerRadius, -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas.restoreToCount(save4);
        final int save5 = canvas.save();
        canvas.translate(this.mContentBounds.right - mCornerRadius, mCornerRadius + this.mContentBounds.top);
        canvas.scale(n5, n6);
        canvas.rotate(90.0f);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (b2) {
            canvas.scale(1.0f / n6, 1.0f);
            canvas.drawRect(0.0f, n, this.mContentBounds.height() - 2.0f * mCornerRadius, -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas.restoreToCount(save5);
        canvas.restoreToCount(save);
    }
    
    private static int toEven(final float n) {
        int round = Math.round(n);
        if (round % 2 == 1) {
            --round;
        }
        return round;
    }
    
    @Override
    public void draw(final Canvas canvas) {
        if (this.mDirty) {
            this.buildComponents(this.getBounds());
            this.mDirty = false;
        }
        this.drawShadow(canvas);
        super.draw(canvas);
    }
    
    public float getCornerRadius() {
        return this.mCornerRadius;
    }
    
    public float getMaxShadowSize() {
        return this.mRawMaxShadowSize;
    }
    
    public float getMinHeight() {
        return 2.0f * Math.max(this.mRawMaxShadowSize, this.mCornerRadius + 1.5f * this.mRawMaxShadowSize / 2.0f) + 2.0f * (1.5f * this.mRawMaxShadowSize);
    }
    
    public float getMinWidth() {
        return 2.0f * Math.max(this.mRawMaxShadowSize, this.mCornerRadius + this.mRawMaxShadowSize / 2.0f) + 2.0f * this.mRawMaxShadowSize;
    }
    
    @Override
    public int getOpacity() {
        return -3;
    }
    
    @Override
    public boolean getPadding(final Rect rect) {
        final int n = (int)Math.ceil(calculateVerticalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
        final int n2 = (int)Math.ceil(calculateHorizontalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
        rect.set(n2, n, n2, n);
        return true;
    }
    
    public float getShadowSize() {
        return this.mRawShadowSize;
    }
    
    @Override
    protected void onBoundsChange(final Rect rect) {
        this.mDirty = true;
    }
    
    public void setAddPaddingForCorners(final boolean mAddPaddingForCorners) {
        this.mAddPaddingForCorners = mAddPaddingForCorners;
        this.invalidateSelf();
    }
    
    @Override
    public void setAlpha(final int alpha) {
        super.setAlpha(alpha);
        this.mCornerShadowPaint.setAlpha(alpha);
        this.mEdgeShadowPaint.setAlpha(alpha);
    }
    
    public void setCornerRadius(final float n) {
        final float mCornerRadius = Math.round(n);
        if (this.mCornerRadius == mCornerRadius) {
            return;
        }
        this.mCornerRadius = mCornerRadius;
        this.mDirty = true;
        this.invalidateSelf();
    }
    
    public void setMaxShadowSize(final float n) {
        this.setShadowSize(this.mRawShadowSize, n);
    }
    
    final void setRotation(final float mRotation) {
        if (this.mRotation != mRotation) {
            this.mRotation = mRotation;
            this.invalidateSelf();
        }
    }
    
    public void setShadowSize(final float n) {
        this.setShadowSize(n, this.mRawMaxShadowSize);
    }
    
    void setShadowSize(final float n, final float n2) {
        if (n < 0.0f || n2 < 0.0f) {
            throw new IllegalArgumentException("invalid shadow size");
        }
        float mRawShadowSize = toEven(n);
        final float n3 = toEven(n2);
        if (mRawShadowSize > n3) {
            mRawShadowSize = n3;
            if (!this.mPrintedShadowClipWarning) {
                this.mPrintedShadowClipWarning = true;
            }
        }
        if (this.mRawShadowSize == mRawShadowSize && this.mRawMaxShadowSize == n3) {
            return;
        }
        this.mRawShadowSize = mRawShadowSize;
        this.mRawMaxShadowSize = n3;
        this.mShadowSize = Math.round(1.5f * mRawShadowSize);
        this.mMaxShadowSize = n3;
        this.mDirty = true;
        this.invalidateSelf();
    }
}
