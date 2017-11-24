package android.support.v7.widget;

import android.widget.*;
import android.util.*;
import android.support.v7.appcompat.*;
import android.graphics.drawable.*;
import android.support.v4.content.*;

public class AppCompatImageHelper
{
    private final AppCompatDrawableManager mDrawableManager;
    private final ImageView mView;
    
    public AppCompatImageHelper(final ImageView mView, final AppCompatDrawableManager mDrawableManager) {
        this.mView = mView;
        this.mDrawableManager = mDrawableManager;
    }
    
    public void loadFromAttributes(final AttributeSet set, final int n) {
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), set, R.styleable.AppCompatImageView, n, 0);
        try {
            final Drawable drawableIfKnown = obtainStyledAttributes.getDrawableIfKnown(R.styleable.AppCompatImageView_android_src);
            if (drawableIfKnown != null) {
                this.mView.setImageDrawable(drawableIfKnown);
            }
            final int resourceId = obtainStyledAttributes.getResourceId(R.styleable.AppCompatImageView_srcCompat, -1);
            if (resourceId != -1) {
                final Drawable drawable = this.mDrawableManager.getDrawable(this.mView.getContext(), resourceId);
                if (drawable != null) {
                    this.mView.setImageDrawable(drawable);
                }
            }
            final Drawable drawable2 = this.mView.getDrawable();
            if (drawable2 != null) {
                DrawableUtils.fixDrawable(drawable2);
            }
        }
        finally {
            obtainStyledAttributes.recycle();
        }
    }
    
    public void setImageResource(final int n) {
        if (n != 0) {
            Drawable imageDrawable;
            if (this.mDrawableManager != null) {
                imageDrawable = this.mDrawableManager.getDrawable(this.mView.getContext(), n);
            }
            else {
                imageDrawable = ContextCompat.getDrawable(this.mView.getContext(), n);
            }
            if (imageDrawable != null) {
                DrawableUtils.fixDrawable(imageDrawable);
            }
            this.mView.setImageDrawable(imageDrawable);
            return;
        }
        this.mView.setImageDrawable((Drawable)null);
    }
}
