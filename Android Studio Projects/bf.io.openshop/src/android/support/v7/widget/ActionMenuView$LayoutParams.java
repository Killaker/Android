package android.support.v7.widget;

import android.content.*;
import android.util.*;
import android.view.*;

public static class LayoutParams extends LinearLayoutCompat.LayoutParams
{
    @ViewDebug$ExportedProperty
    public int cellsUsed;
    @ViewDebug$ExportedProperty
    public boolean expandable;
    boolean expanded;
    @ViewDebug$ExportedProperty
    public int extraPixels;
    @ViewDebug$ExportedProperty
    public boolean isOverflowButton;
    @ViewDebug$ExportedProperty
    public boolean preventEdgeOffset;
    
    public LayoutParams(final int n, final int n2) {
        super(n, n2);
        this.isOverflowButton = false;
    }
    
    LayoutParams(final int n, final int n2, final boolean isOverflowButton) {
        super(n, n2);
        this.isOverflowButton = isOverflowButton;
    }
    
    public LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public LayoutParams(final LayoutParams layoutParams) {
        super((ViewGroup$LayoutParams)layoutParams);
        this.isOverflowButton = layoutParams.isOverflowButton;
    }
    
    public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super(viewGroup$LayoutParams);
    }
}
