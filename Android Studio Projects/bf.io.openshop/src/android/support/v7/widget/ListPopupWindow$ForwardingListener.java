package android.support.v7.widget;

import android.os.*;
import android.view.*;
import android.support.v4.view.*;

public abstract static class ForwardingListener implements View$OnTouchListener
{
    private int mActivePointerId;
    private Runnable mDisallowIntercept;
    private boolean mForwarding;
    private final int mLongPressTimeout;
    private final float mScaledTouchSlop;
    private final View mSrc;
    private final int mTapTimeout;
    private final int[] mTmpLocation;
    private Runnable mTriggerLongPress;
    private boolean mWasLongPress;
    
    public ForwardingListener(final View mSrc) {
        this.mTmpLocation = new int[2];
        this.mSrc = mSrc;
        this.mScaledTouchSlop = ViewConfiguration.get(mSrc.getContext()).getScaledTouchSlop();
        this.mTapTimeout = ViewConfiguration.getTapTimeout();
        this.mLongPressTimeout = (this.mTapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }
    
    private void clearCallbacks() {
        if (this.mTriggerLongPress != null) {
            this.mSrc.removeCallbacks(this.mTriggerLongPress);
        }
        if (this.mDisallowIntercept != null) {
            this.mSrc.removeCallbacks(this.mDisallowIntercept);
        }
    }
    
    private void onLongPress() {
        this.clearCallbacks();
        final View mSrc = this.mSrc;
        if (mSrc.isEnabled() && !mSrc.isLongClickable() && this.onForwardingStarted()) {
            mSrc.getParent().requestDisallowInterceptTouchEvent(true);
            final long uptimeMillis = SystemClock.uptimeMillis();
            final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            mSrc.onTouchEvent(obtain);
            obtain.recycle();
            this.mForwarding = true;
            this.mWasLongPress = true;
        }
    }
    
    private boolean onTouchForwarded(final MotionEvent motionEvent) {
        boolean b = true;
        final View mSrc = this.mSrc;
        final ListPopupWindow popup = this.getPopup();
        if (popup != null && popup.isShowing()) {
            final DropDownListView access$600 = ListPopupWindow.access$600(popup);
            if (access$600 != null && access$600.isShown()) {
                final MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                this.toGlobalMotionEvent(mSrc, obtainNoHistory);
                this.toLocalMotionEvent((View)access$600, obtainNoHistory);
                final boolean onForwardedEvent = access$600.onForwardedEvent(obtainNoHistory, this.mActivePointerId);
                obtainNoHistory.recycle();
                final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
                final boolean b2 = actionMasked != (b ? 1 : 0) && actionMasked != 3 && b;
                if (!onForwardedEvent || !b2) {
                    b = false;
                }
                return b;
            }
        }
        return false;
    }
    
    private boolean onTouchObserved(final MotionEvent motionEvent) {
        final View mSrc = this.mSrc;
        if (mSrc.isEnabled()) {
            switch (MotionEventCompat.getActionMasked(motionEvent)) {
                default: {
                    return false;
                }
                case 0: {
                    this.mActivePointerId = motionEvent.getPointerId(0);
                    this.mWasLongPress = false;
                    if (this.mDisallowIntercept == null) {
                        this.mDisallowIntercept = new DisallowIntercept();
                    }
                    mSrc.postDelayed(this.mDisallowIntercept, (long)this.mTapTimeout);
                    if (this.mTriggerLongPress == null) {
                        this.mTriggerLongPress = new TriggerLongPress();
                    }
                    mSrc.postDelayed(this.mTriggerLongPress, (long)this.mLongPressTimeout);
                    return false;
                }
                case 2: {
                    final int pointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (pointerIndex >= 0 && !pointInView(mSrc, motionEvent.getX(pointerIndex), motionEvent.getY(pointerIndex), this.mScaledTouchSlop)) {
                        this.clearCallbacks();
                        mSrc.getParent().requestDisallowInterceptTouchEvent(true);
                        return true;
                    }
                    break;
                }
                case 1:
                case 3: {
                    this.clearCallbacks();
                    return false;
                }
            }
        }
        return false;
    }
    
    private static boolean pointInView(final View view, final float n, final float n2, final float n3) {
        return n >= -n3 && n2 >= -n3 && n < n3 + (view.getRight() - view.getLeft()) && n2 < n3 + (view.getBottom() - view.getTop());
    }
    
    private boolean toGlobalMotionEvent(final View view, final MotionEvent motionEvent) {
        final int[] mTmpLocation = this.mTmpLocation;
        view.getLocationOnScreen(mTmpLocation);
        motionEvent.offsetLocation((float)mTmpLocation[0], (float)mTmpLocation[1]);
        return true;
    }
    
    private boolean toLocalMotionEvent(final View view, final MotionEvent motionEvent) {
        final int[] mTmpLocation = this.mTmpLocation;
        view.getLocationOnScreen(mTmpLocation);
        motionEvent.offsetLocation((float)(-mTmpLocation[0]), (float)(-mTmpLocation[1]));
        return true;
    }
    
    public abstract ListPopupWindow getPopup();
    
    protected boolean onForwardingStarted() {
        final ListPopupWindow popup = this.getPopup();
        if (popup != null && !popup.isShowing()) {
            popup.show();
        }
        return true;
    }
    
    protected boolean onForwardingStopped() {
        final ListPopupWindow popup = this.getPopup();
        if (popup != null && popup.isShowing()) {
            popup.dismiss();
        }
        return true;
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        final boolean mForwarding = this.mForwarding;
        boolean onTouchForwarded;
        if (mForwarding) {
            if (this.mWasLongPress) {
                onTouchForwarded = this.onTouchForwarded(motionEvent);
            }
            else {
                onTouchForwarded = (this.onTouchForwarded(motionEvent) || !this.onForwardingStopped());
            }
        }
        else {
            onTouchForwarded = (this.onTouchObserved(motionEvent) && this.onForwardingStarted());
            if (onTouchForwarded) {
                final long uptimeMillis = SystemClock.uptimeMillis();
                final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                this.mSrc.onTouchEvent(obtain);
                obtain.recycle();
            }
        }
        if (!(this.mForwarding = onTouchForwarded)) {
            final boolean b = false;
            if (!mForwarding) {
                return b;
            }
        }
        return true;
    }
    
    private class DisallowIntercept implements Runnable
    {
        @Override
        public void run() {
            ForwardingListener.this.mSrc.getParent().requestDisallowInterceptTouchEvent(true);
        }
    }
    
    private class TriggerLongPress implements Runnable
    {
        @Override
        public void run() {
            ForwardingListener.this.onLongPress();
        }
    }
}
