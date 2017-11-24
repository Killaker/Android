package android.support.v7.widget;

import android.content.*;
import android.util.*;
import android.view.*;

public static class LayoutParams extends RecyclerView.LayoutParams
{
    public static final int INVALID_SPAN_ID = -1;
    private int mSpanIndex;
    private int mSpanSize;
    
    public LayoutParams(final int n, final int n2) {
        super(n, n2);
        this.mSpanIndex = -1;
        this.mSpanSize = 0;
    }
    
    public LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
        this.mSpanIndex = -1;
        this.mSpanSize = 0;
    }
    
    public LayoutParams(final RecyclerView.LayoutParams layoutParams) {
        super(layoutParams);
        this.mSpanIndex = -1;
        this.mSpanSize = 0;
    }
    
    public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super(viewGroup$LayoutParams);
        this.mSpanIndex = -1;
        this.mSpanSize = 0;
    }
    
    public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        super(viewGroup$MarginLayoutParams);
        this.mSpanIndex = -1;
        this.mSpanSize = 0;
    }
    
    public int getSpanIndex() {
        return this.mSpanIndex;
    }
    
    public int getSpanSize() {
        return this.mSpanSize;
    }
}
