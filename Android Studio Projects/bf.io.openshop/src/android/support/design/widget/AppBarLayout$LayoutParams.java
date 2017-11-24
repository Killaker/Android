package android.support.design.widget;

import android.widget.*;
import android.content.*;
import android.util.*;
import android.support.design.*;
import android.view.animation.*;
import android.content.res.*;
import android.view.*;
import java.lang.annotation.*;

public static class LayoutParams extends LinearLayout$LayoutParams
{
    static final int FLAG_QUICK_RETURN = 5;
    static final int FLAG_SNAP = 17;
    public static final int SCROLL_FLAG_ENTER_ALWAYS = 4;
    public static final int SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED = 8;
    public static final int SCROLL_FLAG_EXIT_UNTIL_COLLAPSED = 2;
    public static final int SCROLL_FLAG_SCROLL = 1;
    public static final int SCROLL_FLAG_SNAP = 16;
    int mScrollFlags;
    Interpolator mScrollInterpolator;
    
    public LayoutParams(final int n, final int n2) {
        super(n, n2);
        this.mScrollFlags = 1;
    }
    
    public LayoutParams(final int n, final int n2, final float n3) {
        super(n, n2, n3);
        this.mScrollFlags = 1;
    }
    
    public LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
        this.mScrollFlags = 1;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.AppBarLayout_LayoutParams);
        this.mScrollFlags = obtainStyledAttributes.getInt(R.styleable.AppBarLayout_LayoutParams_layout_scrollFlags, 0);
        if (obtainStyledAttributes.hasValue(R.styleable.AppBarLayout_LayoutParams_layout_scrollInterpolator)) {
            this.mScrollInterpolator = AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(R.styleable.AppBarLayout_LayoutParams_layout_scrollInterpolator, 0));
        }
        obtainStyledAttributes.recycle();
    }
    
    public LayoutParams(final LayoutParams layoutParams) {
        super((LinearLayout$LayoutParams)layoutParams);
        this.mScrollFlags = 1;
        this.mScrollFlags = layoutParams.mScrollFlags;
        this.mScrollInterpolator = layoutParams.mScrollInterpolator;
    }
    
    public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super(viewGroup$LayoutParams);
        this.mScrollFlags = 1;
    }
    
    public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        super(viewGroup$MarginLayoutParams);
        this.mScrollFlags = 1;
    }
    
    public LayoutParams(final LinearLayout$LayoutParams linearLayout$LayoutParams) {
        super(linearLayout$LayoutParams);
        this.mScrollFlags = 1;
    }
    
    public int getScrollFlags() {
        return this.mScrollFlags;
    }
    
    public Interpolator getScrollInterpolator() {
        return this.mScrollInterpolator;
    }
    
    public void setScrollFlags(final int mScrollFlags) {
        this.mScrollFlags = mScrollFlags;
    }
    
    public void setScrollInterpolator(final Interpolator mScrollInterpolator) {
        this.mScrollInterpolator = mScrollInterpolator;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface ScrollFlags {
    }
}
