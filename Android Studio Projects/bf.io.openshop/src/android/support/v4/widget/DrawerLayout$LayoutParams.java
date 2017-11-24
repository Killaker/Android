package android.support.v4.widget;

import android.content.*;
import android.util.*;
import android.content.res.*;
import android.view.*;

public static class LayoutParams extends ViewGroup$MarginLayoutParams
{
    private static final int FLAG_IS_CLOSING = 4;
    private static final int FLAG_IS_OPENED = 1;
    private static final int FLAG_IS_OPENING = 2;
    public int gravity;
    private boolean isPeeking;
    private float onScreen;
    private int openState;
    
    public LayoutParams(final int n, final int n2) {
        super(n, n2);
        this.gravity = 0;
    }
    
    public LayoutParams(final int n, final int n2, final int gravity) {
        this(n, n2);
        this.gravity = gravity;
    }
    
    public LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
        this.gravity = 0;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, DrawerLayout.access$400());
        this.gravity = obtainStyledAttributes.getInt(0, 0);
        obtainStyledAttributes.recycle();
    }
    
    public LayoutParams(final LayoutParams layoutParams) {
        super((ViewGroup$MarginLayoutParams)layoutParams);
        this.gravity = 0;
        this.gravity = layoutParams.gravity;
    }
    
    public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super(viewGroup$LayoutParams);
        this.gravity = 0;
    }
    
    public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        super(viewGroup$MarginLayoutParams);
        this.gravity = 0;
    }
}
