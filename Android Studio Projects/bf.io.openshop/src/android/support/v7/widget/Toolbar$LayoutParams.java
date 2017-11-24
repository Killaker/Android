package android.support.v7.widget;

import android.support.v7.app.*;
import android.content.*;
import android.support.annotation.*;
import android.util.*;
import android.view.*;

public static class LayoutParams extends ActionBar.LayoutParams
{
    static final int CUSTOM = 0;
    static final int EXPANDED = 2;
    static final int SYSTEM = 1;
    int mViewType;
    
    public LayoutParams(final int n) {
        this(-2, -1, n);
    }
    
    public LayoutParams(final int n, final int n2) {
        super(n, n2);
        this.mViewType = 0;
        this.gravity = 8388627;
    }
    
    public LayoutParams(final int n, final int n2, final int gravity) {
        super(n, n2);
        this.mViewType = 0;
        this.gravity = gravity;
    }
    
    public LayoutParams(@NonNull final Context context, final AttributeSet set) {
        super(context, set);
        this.mViewType = 0;
    }
    
    public LayoutParams(final ActionBar.LayoutParams layoutParams) {
        super(layoutParams);
        this.mViewType = 0;
    }
    
    public LayoutParams(final LayoutParams layoutParams) {
        super((ActionBar.LayoutParams)layoutParams);
        this.mViewType = 0;
        this.mViewType = layoutParams.mViewType;
    }
    
    public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super(viewGroup$LayoutParams);
        this.mViewType = 0;
    }
    
    public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        super((ViewGroup$LayoutParams)viewGroup$MarginLayoutParams);
        this.mViewType = 0;
        this.copyMarginsFromCompat(viewGroup$MarginLayoutParams);
    }
    
    void copyMarginsFromCompat(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        this.leftMargin = viewGroup$MarginLayoutParams.leftMargin;
        this.topMargin = viewGroup$MarginLayoutParams.topMargin;
        this.rightMargin = viewGroup$MarginLayoutParams.rightMargin;
        this.bottomMargin = viewGroup$MarginLayoutParams.bottomMargin;
    }
}
