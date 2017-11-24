package android.support.v4.view;

import android.content.*;
import android.os.*;
import android.view.*;

static class GestureDetectorCompatImplJellybeanMr2 implements GestureDetectorCompatImpl
{
    private final GestureDetector mDetector;
    
    public GestureDetectorCompatImplJellybeanMr2(final Context context, final GestureDetector$OnGestureListener gestureDetector$OnGestureListener, final Handler handler) {
        this.mDetector = new GestureDetector(context, gestureDetector$OnGestureListener, handler);
    }
    
    @Override
    public boolean isLongpressEnabled() {
        return this.mDetector.isLongpressEnabled();
    }
    
    @Override
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return this.mDetector.onTouchEvent(motionEvent);
    }
    
    @Override
    public void setIsLongpressEnabled(final boolean isLongpressEnabled) {
        this.mDetector.setIsLongpressEnabled(isLongpressEnabled);
    }
    
    @Override
    public void setOnDoubleTapListener(final GestureDetector$OnDoubleTapListener onDoubleTapListener) {
        this.mDetector.setOnDoubleTapListener(onDoubleTapListener);
    }
}
