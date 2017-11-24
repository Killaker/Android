package android.support.v7.widget;

import android.widget.*;
import android.os.*;
import android.content.*;
import android.util.*;
import android.support.v7.cardview.*;
import android.graphics.*;
import android.content.res.*;
import android.view.*;

public class CardView extends FrameLayout implements CardViewDelegate
{
    private static final int[] COLOR_BACKGROUND_ATTR;
    private static final CardViewImpl IMPL;
    private boolean mCompatPadding;
    private final Rect mContentPadding;
    private boolean mPreventCornerOverlap;
    private final Rect mShadowBounds;
    private int mUserSetMinHeight;
    private int mUserSetMinWidth;
    
    static {
        COLOR_BACKGROUND_ATTR = new int[] { 16842801 };
        if (Build$VERSION.SDK_INT >= 21) {
            IMPL = new CardViewApi21();
        }
        else if (Build$VERSION.SDK_INT >= 17) {
            IMPL = new CardViewJellybeanMr1();
        }
        else {
            IMPL = new CardViewEclairMr1();
        }
        CardView.IMPL.initStatic();
    }
    
    public CardView(final Context context) {
        super(context);
        this.mContentPadding = new Rect();
        this.mShadowBounds = new Rect();
        this.initialize(context, null, 0);
    }
    
    public CardView(final Context context, final AttributeSet set) {
        super(context, set);
        this.mContentPadding = new Rect();
        this.mShadowBounds = new Rect();
        this.initialize(context, set, 0);
    }
    
    public CardView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mContentPadding = new Rect();
        this.mShadowBounds = new Rect();
        this.initialize(context, set, n);
    }
    
    private void initialize(final Context context, final AttributeSet set, final int n) {
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.CardView, n, R.style.CardView);
        int n2;
        if (obtainStyledAttributes.hasValue(R.styleable.CardView_cardBackgroundColor)) {
            n2 = obtainStyledAttributes.getColor(R.styleable.CardView_cardBackgroundColor, 0);
        }
        else {
            final TypedArray obtainStyledAttributes2 = this.getContext().obtainStyledAttributes(CardView.COLOR_BACKGROUND_ATTR);
            final int color = obtainStyledAttributes2.getColor(0, 0);
            obtainStyledAttributes2.recycle();
            final float[] array = new float[3];
            Color.colorToHSV(color, array);
            if (array[2] > 0.5f) {
                n2 = this.getResources().getColor(R.color.cardview_light_background);
            }
            else {
                n2 = this.getResources().getColor(R.color.cardview_dark_background);
            }
        }
        final float dimension = obtainStyledAttributes.getDimension(R.styleable.CardView_cardCornerRadius, 0.0f);
        final float dimension2 = obtainStyledAttributes.getDimension(R.styleable.CardView_cardElevation, 0.0f);
        float dimension3 = obtainStyledAttributes.getDimension(R.styleable.CardView_cardMaxElevation, 0.0f);
        this.mCompatPadding = obtainStyledAttributes.getBoolean(R.styleable.CardView_cardUseCompatPadding, false);
        this.mPreventCornerOverlap = obtainStyledAttributes.getBoolean(R.styleable.CardView_cardPreventCornerOverlap, true);
        final int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_contentPadding, 0);
        this.mContentPadding.left = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_contentPaddingLeft, dimensionPixelSize);
        this.mContentPadding.top = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_contentPaddingTop, dimensionPixelSize);
        this.mContentPadding.right = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_contentPaddingRight, dimensionPixelSize);
        this.mContentPadding.bottom = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_contentPaddingBottom, dimensionPixelSize);
        if (dimension2 > dimension3) {
            dimension3 = dimension2;
        }
        this.mUserSetMinWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_android_minWidth, 0);
        this.mUserSetMinHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_android_minHeight, 0);
        obtainStyledAttributes.recycle();
        CardView.IMPL.initialize(this, context, n2, dimension, dimension2, dimension3);
    }
    
    public float getCardElevation() {
        return CardView.IMPL.getElevation(this);
    }
    
    public int getContentPaddingBottom() {
        return this.mContentPadding.bottom;
    }
    
    public int getContentPaddingLeft() {
        return this.mContentPadding.left;
    }
    
    public int getContentPaddingRight() {
        return this.mContentPadding.right;
    }
    
    public int getContentPaddingTop() {
        return this.mContentPadding.top;
    }
    
    public float getMaxCardElevation() {
        return CardView.IMPL.getMaxElevation(this);
    }
    
    public boolean getPreventCornerOverlap() {
        return this.mPreventCornerOverlap;
    }
    
    public float getRadius() {
        return CardView.IMPL.getRadius(this);
    }
    
    public boolean getUseCompatPadding() {
        return this.mCompatPadding;
    }
    
    protected void onMeasure(int measureSpec, int measureSpec2) {
        if (!(CardView.IMPL instanceof CardViewApi21)) {
            final int mode = View$MeasureSpec.getMode(measureSpec);
            switch (mode) {
                case Integer.MIN_VALUE:
                case 1073741824: {
                    measureSpec = View$MeasureSpec.makeMeasureSpec(Math.max((int)Math.ceil(CardView.IMPL.getMinWidth(this)), View$MeasureSpec.getSize(measureSpec)), mode);
                    break;
                }
            }
            final int mode2 = View$MeasureSpec.getMode(measureSpec2);
            switch (mode2) {
                case Integer.MIN_VALUE:
                case 1073741824: {
                    measureSpec2 = View$MeasureSpec.makeMeasureSpec(Math.max((int)Math.ceil(CardView.IMPL.getMinHeight(this)), View$MeasureSpec.getSize(measureSpec2)), mode2);
                    break;
                }
            }
            super.onMeasure(measureSpec, measureSpec2);
            return;
        }
        super.onMeasure(measureSpec, measureSpec2);
    }
    
    public void setCardBackgroundColor(final int n) {
        CardView.IMPL.setBackgroundColor(this, n);
    }
    
    public void setCardElevation(final float n) {
        CardView.IMPL.setElevation(this, n);
    }
    
    public void setContentPadding(final int n, final int n2, final int n3, final int n4) {
        this.mContentPadding.set(n, n2, n3, n4);
        CardView.IMPL.updatePadding(this);
    }
    
    public void setMaxCardElevation(final float n) {
        CardView.IMPL.setMaxElevation(this, n);
    }
    
    public void setMinWidthHeightInternal(final int minimumWidth, final int minimumHeight) {
        if (minimumWidth > this.mUserSetMinWidth) {
            super.setMinimumWidth(minimumWidth);
        }
        if (minimumHeight > this.mUserSetMinHeight) {
            super.setMinimumHeight(minimumHeight);
        }
    }
    
    public void setMinimumHeight(final int mUserSetMinHeight) {
        super.setMinimumHeight(this.mUserSetMinHeight = mUserSetMinHeight);
    }
    
    public void setMinimumWidth(final int mUserSetMinWidth) {
        super.setMinimumWidth(this.mUserSetMinWidth = mUserSetMinWidth);
    }
    
    public void setPadding(final int n, final int n2, final int n3, final int n4) {
    }
    
    public void setPaddingRelative(final int n, final int n2, final int n3, final int n4) {
    }
    
    public void setPreventCornerOverlap(final boolean mPreventCornerOverlap) {
        if (mPreventCornerOverlap == this.mPreventCornerOverlap) {
            return;
        }
        this.mPreventCornerOverlap = mPreventCornerOverlap;
        CardView.IMPL.onPreventCornerOverlapChanged(this);
    }
    
    public void setRadius(final float n) {
        CardView.IMPL.setRadius(this, n);
    }
    
    public void setShadowPadding(final int n, final int n2, final int n3, final int n4) {
        this.mShadowBounds.set(n, n2, n3, n4);
        super.setPadding(n + this.mContentPadding.left, n2 + this.mContentPadding.top, n3 + this.mContentPadding.right, n4 + this.mContentPadding.bottom);
    }
    
    public void setUseCompatPadding(final boolean mCompatPadding) {
        if (this.mCompatPadding == mCompatPadding) {
            return;
        }
        this.mCompatPadding = mCompatPadding;
        CardView.IMPL.onCompatPaddingChanged(this);
    }
}
