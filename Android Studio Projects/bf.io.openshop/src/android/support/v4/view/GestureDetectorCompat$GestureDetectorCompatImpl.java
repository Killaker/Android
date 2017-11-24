package android.support.v4.view;

import android.view.*;

interface GestureDetectorCompatImpl
{
    boolean isLongpressEnabled();
    
    boolean onTouchEvent(final MotionEvent p0);
    
    void setIsLongpressEnabled(final boolean p0);
    
    void setOnDoubleTapListener(final GestureDetector$OnDoubleTapListener p0);
}
