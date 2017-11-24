package android.support.v7.widget;

import android.view.*;

public interface OnItemTouchListener
{
    boolean onInterceptTouchEvent(final RecyclerView p0, final MotionEvent p1);
    
    void onRequestDisallowInterceptTouchEvent(final boolean p0);
    
    void onTouchEvent(final RecyclerView p0, final MotionEvent p1);
}
