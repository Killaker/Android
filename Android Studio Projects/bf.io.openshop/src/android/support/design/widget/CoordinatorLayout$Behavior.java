package android.support.design.widget;

import android.content.*;
import android.util.*;
import android.support.v4.view.*;
import android.os.*;
import android.view.*;

public abstract static class Behavior<V extends View>
{
    public Behavior() {
    }
    
    public Behavior(final Context context, final AttributeSet set) {
    }
    
    public static Object getTag(final View view) {
        return ((LayoutParams)view.getLayoutParams()).mBehaviorTag;
    }
    
    public static void setTag(final View view, final Object mBehaviorTag) {
        ((LayoutParams)view.getLayoutParams()).mBehaviorTag = mBehaviorTag;
    }
    
    public boolean blocksInteractionBelow(final CoordinatorLayout coordinatorLayout, final V v) {
        return this.getScrimOpacity(coordinatorLayout, v) > 0.0f;
    }
    
    public int getScrimColor(final CoordinatorLayout coordinatorLayout, final V v) {
        return -16777216;
    }
    
    public float getScrimOpacity(final CoordinatorLayout coordinatorLayout, final V v) {
        return 0.0f;
    }
    
    public boolean isDirty(final CoordinatorLayout coordinatorLayout, final V v) {
        return false;
    }
    
    public boolean layoutDependsOn(final CoordinatorLayout coordinatorLayout, final V v, final View view) {
        return false;
    }
    
    public WindowInsetsCompat onApplyWindowInsets(final CoordinatorLayout coordinatorLayout, final V v, final WindowInsetsCompat windowInsetsCompat) {
        return windowInsetsCompat;
    }
    
    public boolean onDependentViewChanged(final CoordinatorLayout coordinatorLayout, final V v, final View view) {
        return false;
    }
    
    public void onDependentViewRemoved(final CoordinatorLayout coordinatorLayout, final V v, final View view) {
    }
    
    public boolean onInterceptTouchEvent(final CoordinatorLayout coordinatorLayout, final V v, final MotionEvent motionEvent) {
        return false;
    }
    
    public boolean onLayoutChild(final CoordinatorLayout coordinatorLayout, final V v, final int n) {
        return false;
    }
    
    public boolean onMeasureChild(final CoordinatorLayout coordinatorLayout, final V v, final int n, final int n2, final int n3, final int n4) {
        return false;
    }
    
    public boolean onNestedFling(final CoordinatorLayout coordinatorLayout, final V v, final View view, final float n, final float n2, final boolean b) {
        return false;
    }
    
    public boolean onNestedPreFling(final CoordinatorLayout coordinatorLayout, final V v, final View view, final float n, final float n2) {
        return false;
    }
    
    public void onNestedPreScroll(final CoordinatorLayout coordinatorLayout, final V v, final View view, final int n, final int n2, final int[] array) {
    }
    
    public void onNestedScroll(final CoordinatorLayout coordinatorLayout, final V v, final View view, final int n, final int n2, final int n3, final int n4) {
    }
    
    public void onNestedScrollAccepted(final CoordinatorLayout coordinatorLayout, final V v, final View view, final View view2, final int n) {
    }
    
    public void onRestoreInstanceState(final CoordinatorLayout coordinatorLayout, final V v, final Parcelable parcelable) {
    }
    
    public Parcelable onSaveInstanceState(final CoordinatorLayout coordinatorLayout, final V v) {
        return (Parcelable)View$BaseSavedState.EMPTY_STATE;
    }
    
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final V v, final View view, final View view2, final int n) {
        return false;
    }
    
    public void onStopNestedScroll(final CoordinatorLayout coordinatorLayout, final V v, final View view) {
    }
    
    public boolean onTouchEvent(final CoordinatorLayout coordinatorLayout, final V v, final MotionEvent motionEvent) {
        return false;
    }
}
