package android.support.v4.view;

import android.view.*;
import android.content.*;
import android.util.*;
import android.content.res.*;

public static class LayoutParams extends ViewGroup$LayoutParams
{
    int childIndex;
    public int gravity;
    public boolean isDecor;
    boolean needsMeasure;
    int position;
    float widthFactor;
    
    public LayoutParams() {
        super(-1, -1);
        this.widthFactor = 0.0f;
    }
    
    public LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
        this.widthFactor = 0.0f;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, ViewPager.access$400());
        this.gravity = obtainStyledAttributes.getInteger(0, 48);
        obtainStyledAttributes.recycle();
    }
}
