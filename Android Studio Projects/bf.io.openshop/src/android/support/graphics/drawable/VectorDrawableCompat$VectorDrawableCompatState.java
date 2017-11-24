package android.support.graphics.drawable;

import android.graphics.drawable.*;
import android.content.res.*;
import android.support.v4.util.*;
import android.graphics.*;

private static class VectorDrawableCompatState extends Drawable$ConstantState
{
    boolean mAutoMirrored;
    boolean mCacheDirty;
    boolean mCachedAutoMirrored;
    Bitmap mCachedBitmap;
    int mCachedRootAlpha;
    int[] mCachedThemeAttrs;
    ColorStateList mCachedTint;
    PorterDuff$Mode mCachedTintMode;
    int mChangingConfigurations;
    Paint mTempPaint;
    ColorStateList mTint;
    PorterDuff$Mode mTintMode;
    VPathRenderer mVPathRenderer;
    
    public VectorDrawableCompatState() {
        this.mTint = null;
        this.mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
        this.mVPathRenderer = new VPathRenderer();
    }
    
    public VectorDrawableCompatState(final VectorDrawableCompatState vectorDrawableCompatState) {
        this.mTint = null;
        this.mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
        if (vectorDrawableCompatState != null) {
            this.mChangingConfigurations = vectorDrawableCompatState.mChangingConfigurations;
            this.mVPathRenderer = new VPathRenderer(vectorDrawableCompatState.mVPathRenderer);
            if (vectorDrawableCompatState.mVPathRenderer.mFillPaint != null) {
                this.mVPathRenderer.mFillPaint = new Paint(vectorDrawableCompatState.mVPathRenderer.mFillPaint);
            }
            if (vectorDrawableCompatState.mVPathRenderer.mStrokePaint != null) {
                this.mVPathRenderer.mStrokePaint = new Paint(vectorDrawableCompatState.mVPathRenderer.mStrokePaint);
            }
            this.mTint = vectorDrawableCompatState.mTint;
            this.mTintMode = vectorDrawableCompatState.mTintMode;
            this.mAutoMirrored = vectorDrawableCompatState.mAutoMirrored;
        }
    }
    
    public boolean canReuseBitmap(final int n, final int n2) {
        return n == this.mCachedBitmap.getWidth() && n2 == this.mCachedBitmap.getHeight();
    }
    
    public boolean canReuseCache() {
        return !this.mCacheDirty && this.mCachedTint == this.mTint && this.mCachedTintMode == this.mTintMode && this.mCachedAutoMirrored == this.mAutoMirrored && this.mCachedRootAlpha == this.mVPathRenderer.getRootAlpha();
    }
    
    public void createCachedBitmapIfNeeded(final int n, final int n2) {
        if (this.mCachedBitmap == null || !this.canReuseBitmap(n, n2)) {
            this.mCachedBitmap = Bitmap.createBitmap(n, n2, Bitmap$Config.ARGB_8888);
            this.mCacheDirty = true;
        }
    }
    
    public void drawCachedBitmapWithRootAlpha(final Canvas canvas, final ColorFilter colorFilter, final Rect rect) {
        canvas.drawBitmap(this.mCachedBitmap, (Rect)null, rect, this.getPaint(colorFilter));
    }
    
    public int getChangingConfigurations() {
        return this.mChangingConfigurations;
    }
    
    public Paint getPaint(final ColorFilter colorFilter) {
        if (!this.hasTranslucentRoot() && colorFilter == null) {
            return null;
        }
        if (this.mTempPaint == null) {
            (this.mTempPaint = new Paint()).setFilterBitmap(true);
        }
        this.mTempPaint.setAlpha(this.mVPathRenderer.getRootAlpha());
        this.mTempPaint.setColorFilter(colorFilter);
        return this.mTempPaint;
    }
    
    public boolean hasTranslucentRoot() {
        return this.mVPathRenderer.getRootAlpha() < 255;
    }
    
    public Drawable newDrawable() {
        return new VectorDrawableCompat(this, null);
    }
    
    public Drawable newDrawable(final Resources resources) {
        return new VectorDrawableCompat(this, null);
    }
    
    public void updateCacheStates() {
        this.mCachedTint = this.mTint;
        this.mCachedTintMode = this.mTintMode;
        this.mCachedRootAlpha = this.mVPathRenderer.getRootAlpha();
        this.mCachedAutoMirrored = this.mAutoMirrored;
        this.mCacheDirty = false;
    }
    
    public void updateCachedBitmap(final int n, final int n2) {
        this.mCachedBitmap.eraseColor(0);
        this.mVPathRenderer.draw(new Canvas(this.mCachedBitmap), n, n2, null);
    }
}
