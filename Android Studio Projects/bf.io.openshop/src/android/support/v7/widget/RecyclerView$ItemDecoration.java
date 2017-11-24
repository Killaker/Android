package android.support.v7.widget;

import android.view.*;
import android.graphics.*;

public abstract static class ItemDecoration
{
    @Deprecated
    public void getItemOffsets(final Rect rect, final int n, final RecyclerView recyclerView) {
        rect.set(0, 0, 0, 0);
    }
    
    public void getItemOffsets(final Rect rect, final View view, final RecyclerView recyclerView, final State state) {
        this.getItemOffsets(rect, ((LayoutParams)view.getLayoutParams()).getViewLayoutPosition(), recyclerView);
    }
    
    @Deprecated
    public void onDraw(final Canvas canvas, final RecyclerView recyclerView) {
    }
    
    public void onDraw(final Canvas canvas, final RecyclerView recyclerView, final State state) {
        this.onDraw(canvas, recyclerView);
    }
    
    @Deprecated
    public void onDrawOver(final Canvas canvas, final RecyclerView recyclerView) {
    }
    
    public void onDrawOver(final Canvas canvas, final RecyclerView recyclerView, final State state) {
        this.onDrawOver(canvas, recyclerView);
    }
}
