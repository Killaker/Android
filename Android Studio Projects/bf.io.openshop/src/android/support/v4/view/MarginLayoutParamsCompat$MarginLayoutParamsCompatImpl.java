package android.support.v4.view;

import android.view.*;

interface MarginLayoutParamsCompatImpl
{
    int getLayoutDirection(final ViewGroup$MarginLayoutParams p0);
    
    int getMarginEnd(final ViewGroup$MarginLayoutParams p0);
    
    int getMarginStart(final ViewGroup$MarginLayoutParams p0);
    
    boolean isMarginRelative(final ViewGroup$MarginLayoutParams p0);
    
    void resolveLayoutDirection(final ViewGroup$MarginLayoutParams p0, final int p1);
    
    void setLayoutDirection(final ViewGroup$MarginLayoutParams p0, final int p1);
    
    void setMarginEnd(final ViewGroup$MarginLayoutParams p0, final int p1);
    
    void setMarginStart(final ViewGroup$MarginLayoutParams p0, final int p1);
}
