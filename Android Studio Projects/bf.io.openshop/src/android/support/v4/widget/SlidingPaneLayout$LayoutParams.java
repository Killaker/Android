package android.support.v4.widget;

import android.graphics.*;
import android.content.*;
import android.util.*;
import android.content.res.*;
import android.view.*;

public static class LayoutParams extends ViewGroup$MarginLayoutParams
{
    private static final int[] ATTRS;
    Paint dimPaint;
    boolean dimWhenOffset;
    boolean slideable;
    public float weight;
    
    static {
        ATTRS = new int[] { 16843137 };
    }
    
    public LayoutParams() {
        super(-1, -1);
        this.weight = 0.0f;
    }
    
    public LayoutParams(final int n, final int n2) {
        super(n, n2);
        this.weight = 0.0f;
    }
    
    public LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
        this.weight = 0.0f;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, LayoutParams.ATTRS);
        this.weight = obtainStyledAttributes.getFloat(0, 0.0f);
        obtainStyledAttributes.recycle();
    }
    
    public LayoutParams(final LayoutParams layoutParams) {
        super((ViewGroup$MarginLayoutParams)layoutParams);
        this.weight = 0.0f;
        this.weight = layoutParams.weight;
    }
    
    public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super(viewGroup$LayoutParams);
        this.weight = 0.0f;
    }
    
    public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        super(viewGroup$MarginLayoutParams);
        this.weight = 0.0f;
    }
}
