package android.support.design.widget;

import android.widget.*;
import android.content.*;
import android.util.*;
import android.support.design.*;
import android.content.res.*;
import android.view.*;

public static class LayoutParams extends FrameLayout$LayoutParams
{
    public static final int COLLAPSE_MODE_OFF = 0;
    public static final int COLLAPSE_MODE_PARALLAX = 2;
    public static final int COLLAPSE_MODE_PIN = 1;
    private static final float DEFAULT_PARALLAX_MULTIPLIER = 0.5f;
    int mCollapseMode;
    float mParallaxMult;
    
    public LayoutParams(final int n, final int n2) {
        super(n, n2);
        this.mCollapseMode = 0;
        this.mParallaxMult = 0.5f;
    }
    
    public LayoutParams(final int n, final int n2, final int n3) {
        super(n, n2, n3);
        this.mCollapseMode = 0;
        this.mParallaxMult = 0.5f;
    }
    
    public LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
        this.mCollapseMode = 0;
        this.mParallaxMult = 0.5f;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.CollapsingAppBarLayout_LayoutParams);
        this.mCollapseMode = obtainStyledAttributes.getInt(R.styleable.CollapsingAppBarLayout_LayoutParams_layout_collapseMode, 0);
        this.setParallaxMultiplier(obtainStyledAttributes.getFloat(R.styleable.CollapsingAppBarLayout_LayoutParams_layout_collapseParallaxMultiplier, 0.5f));
        obtainStyledAttributes.recycle();
    }
    
    public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super(viewGroup$LayoutParams);
        this.mCollapseMode = 0;
        this.mParallaxMult = 0.5f;
    }
    
    public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        super(viewGroup$MarginLayoutParams);
        this.mCollapseMode = 0;
        this.mParallaxMult = 0.5f;
    }
    
    public LayoutParams(final FrameLayout$LayoutParams frameLayout$LayoutParams) {
        super(frameLayout$LayoutParams);
        this.mCollapseMode = 0;
        this.mParallaxMult = 0.5f;
    }
    
    public int getCollapseMode() {
        return this.mCollapseMode;
    }
    
    public float getParallaxMultiplier() {
        return this.mParallaxMult;
    }
    
    public void setCollapseMode(final int mCollapseMode) {
        this.mCollapseMode = mCollapseMode;
    }
    
    public void setParallaxMultiplier(final float mParallaxMult) {
        this.mParallaxMult = mParallaxMult;
    }
}
