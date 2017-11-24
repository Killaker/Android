package android.support.v7.widget;

import android.content.*;
import android.util.*;
import android.view.*;

public static class LayoutParams extends RecyclerView.LayoutParams
{
    public static final int INVALID_SPAN_ID = -1;
    boolean mFullSpan;
    Span mSpan;
    
    public LayoutParams(final int n, final int n2) {
        super(n, n2);
    }
    
    public LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public LayoutParams(final RecyclerView.LayoutParams layoutParams) {
        super(layoutParams);
    }
    
    public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super(viewGroup$LayoutParams);
    }
    
    public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        super(viewGroup$MarginLayoutParams);
    }
    
    public final int getSpanIndex() {
        if (this.mSpan == null) {
            return -1;
        }
        return this.mSpan.mIndex;
    }
    
    public boolean isFullSpan() {
        return this.mFullSpan;
    }
    
    public void setFullSpan(final boolean mFullSpan) {
        this.mFullSpan = mFullSpan;
    }
}
