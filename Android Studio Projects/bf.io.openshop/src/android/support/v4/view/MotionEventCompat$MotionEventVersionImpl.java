package android.support.v4.view;

import android.view.*;

interface MotionEventVersionImpl
{
    int findPointerIndex(final MotionEvent p0, final int p1);
    
    float getAxisValue(final MotionEvent p0, final int p1);
    
    float getAxisValue(final MotionEvent p0, final int p1, final int p2);
    
    int getPointerCount(final MotionEvent p0);
    
    int getPointerId(final MotionEvent p0, final int p1);
    
    int getSource(final MotionEvent p0);
    
    float getX(final MotionEvent p0, final int p1);
    
    float getY(final MotionEvent p0, final int p1);
}
