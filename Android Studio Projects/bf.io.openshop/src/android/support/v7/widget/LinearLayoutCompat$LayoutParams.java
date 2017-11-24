package android.support.v7.widget;

import android.content.*;
import android.util.*;
import android.support.v7.appcompat.*;
import android.content.res.*;
import android.view.*;

public static class LayoutParams extends ViewGroup$MarginLayoutParams
{
    public int gravity;
    public float weight;
    
    public LayoutParams(final int n, final int n2) {
        super(n, n2);
        this.gravity = -1;
        this.weight = 0.0f;
    }
    
    public LayoutParams(final int n, final int n2, final float weight) {
        super(n, n2);
        this.gravity = -1;
        this.weight = weight;
    }
    
    public LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
        this.gravity = -1;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.LinearLayoutCompat_Layout);
        this.weight = obtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
        this.gravity = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
        obtainStyledAttributes.recycle();
    }
    
    public LayoutParams(final LayoutParams layoutParams) {
        super((ViewGroup$MarginLayoutParams)layoutParams);
        this.gravity = -1;
        this.weight = layoutParams.weight;
        this.gravity = layoutParams.gravity;
    }
    
    public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super(viewGroup$LayoutParams);
        this.gravity = -1;
    }
    
    public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        super(viewGroup$MarginLayoutParams);
        this.gravity = -1;
    }
}
