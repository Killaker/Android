package android.support.v4.view;

import java.lang.reflect.*;
import java.util.*;
import android.util.*;
import android.content.res.*;
import android.graphics.drawable.*;
import android.view.accessibility.*;
import android.support.v4.view.accessibility.*;
import android.os.*;
import android.view.*;
import android.graphics.*;

static class BaseViewCompatImpl implements ViewCompatImpl
{
    private Method mDispatchFinishTemporaryDetach;
    private Method mDispatchStartTemporaryDetach;
    private boolean mTempDetachBound;
    WeakHashMap<View, ViewPropertyAnimatorCompat> mViewPropertyAnimatorCompatMap;
    
    BaseViewCompatImpl() {
        this.mViewPropertyAnimatorCompatMap = null;
    }
    
    private void bindTempDetach() {
        while (true) {
            try {
                this.mDispatchStartTemporaryDetach = View.class.getDeclaredMethod("dispatchStartTemporaryDetach", (Class<?>[])new Class[0]);
                this.mDispatchFinishTemporaryDetach = View.class.getDeclaredMethod("dispatchFinishTemporaryDetach", (Class<?>[])new Class[0]);
                this.mTempDetachBound = true;
            }
            catch (NoSuchMethodException ex) {
                Log.e("ViewCompat", "Couldn't find method", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    private boolean canScrollingViewScrollHorizontally(final ScrollingView scrollingView, final int n) {
        boolean b = true;
        final int computeHorizontalScrollOffset = scrollingView.computeHorizontalScrollOffset();
        final int n2 = scrollingView.computeHorizontalScrollRange() - scrollingView.computeHorizontalScrollExtent();
        if (n2 == 0) {
            b = false;
        }
        else if (n < 0) {
            if (computeHorizontalScrollOffset <= 0) {
                return false;
            }
        }
        else if (computeHorizontalScrollOffset >= n2 - 1) {
            return false;
        }
        return b;
    }
    
    private boolean canScrollingViewScrollVertically(final ScrollingView scrollingView, final int n) {
        boolean b = true;
        final int computeVerticalScrollOffset = scrollingView.computeVerticalScrollOffset();
        final int n2 = scrollingView.computeVerticalScrollRange() - scrollingView.computeVerticalScrollExtent();
        if (n2 == 0) {
            b = false;
        }
        else if (n < 0) {
            if (computeVerticalScrollOffset <= 0) {
                return false;
            }
        }
        else if (computeVerticalScrollOffset >= n2 - 1) {
            return false;
        }
        return b;
    }
    
    @Override
    public ViewPropertyAnimatorCompat animate(final View view) {
        return new ViewPropertyAnimatorCompat(view);
    }
    
    @Override
    public boolean canScrollHorizontally(final View view, final int n) {
        return view instanceof ScrollingView && this.canScrollingViewScrollHorizontally((ScrollingView)view, n);
    }
    
    @Override
    public boolean canScrollVertically(final View view, final int n) {
        return view instanceof ScrollingView && this.canScrollingViewScrollVertically((ScrollingView)view, n);
    }
    
    @Override
    public int combineMeasuredStates(final int n, final int n2) {
        return n | n2;
    }
    
    @Override
    public WindowInsetsCompat dispatchApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        return windowInsetsCompat;
    }
    
    @Override
    public void dispatchFinishTemporaryDetach(final View view) {
        if (!this.mTempDetachBound) {
            this.bindTempDetach();
        }
        if (this.mDispatchFinishTemporaryDetach != null) {
            try {
                this.mDispatchFinishTemporaryDetach.invoke(view, new Object[0]);
                return;
            }
            catch (Exception ex) {
                Log.d("ViewCompat", "Error calling dispatchFinishTemporaryDetach", (Throwable)ex);
                return;
            }
        }
        view.onFinishTemporaryDetach();
    }
    
    @Override
    public boolean dispatchNestedFling(final View view, final float n, final float n2, final boolean b) {
        return view instanceof NestedScrollingChild && ((NestedScrollingChild)view).dispatchNestedFling(n, n2, b);
    }
    
    @Override
    public boolean dispatchNestedPreFling(final View view, final float n, final float n2) {
        return view instanceof NestedScrollingChild && ((NestedScrollingChild)view).dispatchNestedPreFling(n, n2);
    }
    
    @Override
    public boolean dispatchNestedPreScroll(final View view, final int n, final int n2, final int[] array, final int[] array2) {
        return view instanceof NestedScrollingChild && ((NestedScrollingChild)view).dispatchNestedPreScroll(n, n2, array, array2);
    }
    
    @Override
    public boolean dispatchNestedScroll(final View view, final int n, final int n2, final int n3, final int n4, final int[] array) {
        return view instanceof NestedScrollingChild && ((NestedScrollingChild)view).dispatchNestedScroll(n, n2, n3, n4, array);
    }
    
    @Override
    public void dispatchStartTemporaryDetach(final View view) {
        if (!this.mTempDetachBound) {
            this.bindTempDetach();
        }
        if (this.mDispatchStartTemporaryDetach != null) {
            try {
                this.mDispatchStartTemporaryDetach.invoke(view, new Object[0]);
                return;
            }
            catch (Exception ex) {
                Log.d("ViewCompat", "Error calling dispatchStartTemporaryDetach", (Throwable)ex);
                return;
            }
        }
        view.onStartTemporaryDetach();
    }
    
    @Override
    public int getAccessibilityLiveRegion(final View view) {
        return 0;
    }
    
    @Override
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(final View view) {
        return null;
    }
    
    @Override
    public float getAlpha(final View view) {
        return 1.0f;
    }
    
    @Override
    public ColorStateList getBackgroundTintList(final View view) {
        return ViewCompatBase.getBackgroundTintList(view);
    }
    
    @Override
    public PorterDuff$Mode getBackgroundTintMode(final View view) {
        return ViewCompatBase.getBackgroundTintMode(view);
    }
    
    @Override
    public Rect getClipBounds(final View view) {
        return null;
    }
    
    @Override
    public float getElevation(final View view) {
        return 0.0f;
    }
    
    @Override
    public boolean getFitsSystemWindows(final View view) {
        return false;
    }
    
    long getFrameTime() {
        return 10L;
    }
    
    @Override
    public int getImportantForAccessibility(final View view) {
        return 0;
    }
    
    @Override
    public int getLabelFor(final View view) {
        return 0;
    }
    
    @Override
    public int getLayerType(final View view) {
        return 0;
    }
    
    @Override
    public int getLayoutDirection(final View view) {
        return 0;
    }
    
    @Override
    public int getMeasuredHeightAndState(final View view) {
        return view.getMeasuredHeight();
    }
    
    @Override
    public int getMeasuredState(final View view) {
        return 0;
    }
    
    @Override
    public int getMeasuredWidthAndState(final View view) {
        return view.getMeasuredWidth();
    }
    
    @Override
    public int getMinimumHeight(final View view) {
        return ViewCompatBase.getMinimumHeight(view);
    }
    
    @Override
    public int getMinimumWidth(final View view) {
        return ViewCompatBase.getMinimumWidth(view);
    }
    
    @Override
    public int getOverScrollMode(final View view) {
        return 2;
    }
    
    @Override
    public int getPaddingEnd(final View view) {
        return view.getPaddingRight();
    }
    
    @Override
    public int getPaddingStart(final View view) {
        return view.getPaddingLeft();
    }
    
    @Override
    public ViewParent getParentForAccessibility(final View view) {
        return view.getParent();
    }
    
    @Override
    public float getPivotX(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getPivotY(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getRotation(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getRotationX(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getRotationY(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getScaleX(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getScaleY(final View view) {
        return 0.0f;
    }
    
    @Override
    public int getScrollIndicators(final View view) {
        return 0;
    }
    
    @Override
    public String getTransitionName(final View view) {
        return null;
    }
    
    @Override
    public float getTranslationX(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getTranslationY(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getTranslationZ(final View view) {
        return 0.0f;
    }
    
    @Override
    public int getWindowSystemUiVisibility(final View view) {
        return 0;
    }
    
    @Override
    public float getX(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getY(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getZ(final View view) {
        return this.getTranslationZ(view) + this.getElevation(view);
    }
    
    @Override
    public boolean hasAccessibilityDelegate(final View view) {
        return false;
    }
    
    @Override
    public boolean hasNestedScrollingParent(final View view) {
        return view instanceof NestedScrollingChild && ((NestedScrollingChild)view).hasNestedScrollingParent();
    }
    
    @Override
    public boolean hasOnClickListeners(final View view) {
        return false;
    }
    
    @Override
    public boolean hasOverlappingRendering(final View view) {
        return true;
    }
    
    @Override
    public boolean hasTransientState(final View view) {
        return false;
    }
    
    @Override
    public boolean isAttachedToWindow(final View view) {
        return ViewCompatBase.isAttachedToWindow(view);
    }
    
    @Override
    public boolean isImportantForAccessibility(final View view) {
        return true;
    }
    
    @Override
    public boolean isLaidOut(final View view) {
        return ViewCompatBase.isLaidOut(view);
    }
    
    @Override
    public boolean isNestedScrollingEnabled(final View view) {
        return view instanceof NestedScrollingChild && ((NestedScrollingChild)view).isNestedScrollingEnabled();
    }
    
    @Override
    public boolean isOpaque(final View view) {
        final Drawable background = view.getBackground();
        boolean b = false;
        if (background != null) {
            final int opacity = background.getOpacity();
            b = false;
            if (opacity == -1) {
                b = true;
            }
        }
        return b;
    }
    
    @Override
    public boolean isPaddingRelative(final View view) {
        return false;
    }
    
    @Override
    public void jumpDrawablesToCurrentState(final View view) {
    }
    
    @Override
    public void offsetLeftAndRight(final View view, final int n) {
        ViewCompatBase.offsetLeftAndRight(view, n);
    }
    
    @Override
    public void offsetTopAndBottom(final View view, final int n) {
        ViewCompatBase.offsetTopAndBottom(view, n);
    }
    
    @Override
    public WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        return windowInsetsCompat;
    }
    
    @Override
    public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }
    
    @Override
    public void onPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
    }
    
    @Override
    public boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
        return false;
    }
    
    @Override
    public void postInvalidateOnAnimation(final View view) {
        view.invalidate();
    }
    
    @Override
    public void postInvalidateOnAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
        view.invalidate(n, n2, n3, n4);
    }
    
    @Override
    public void postOnAnimation(final View view, final Runnable runnable) {
        view.postDelayed(runnable, this.getFrameTime());
    }
    
    @Override
    public void postOnAnimationDelayed(final View view, final Runnable runnable, final long n) {
        view.postDelayed(runnable, n + this.getFrameTime());
    }
    
    @Override
    public void requestApplyInsets(final View view) {
    }
    
    @Override
    public int resolveSizeAndState(final int n, final int n2, final int n3) {
        return View.resolveSize(n, n2);
    }
    
    @Override
    public void setAccessibilityDelegate(final View view, final AccessibilityDelegateCompat accessibilityDelegateCompat) {
    }
    
    @Override
    public void setAccessibilityLiveRegion(final View view, final int n) {
    }
    
    @Override
    public void setActivated(final View view, final boolean b) {
    }
    
    @Override
    public void setAlpha(final View view, final float n) {
    }
    
    @Override
    public void setBackgroundTintList(final View view, final ColorStateList list) {
        ViewCompatBase.setBackgroundTintList(view, list);
    }
    
    @Override
    public void setBackgroundTintMode(final View view, final PorterDuff$Mode porterDuff$Mode) {
        ViewCompatBase.setBackgroundTintMode(view, porterDuff$Mode);
    }
    
    @Override
    public void setChildrenDrawingOrderEnabled(final ViewGroup viewGroup, final boolean b) {
    }
    
    @Override
    public void setClipBounds(final View view, final Rect rect) {
    }
    
    @Override
    public void setElevation(final View view, final float n) {
    }
    
    @Override
    public void setFitsSystemWindows(final View view, final boolean b) {
    }
    
    @Override
    public void setHasTransientState(final View view, final boolean b) {
    }
    
    @Override
    public void setImportantForAccessibility(final View view, final int n) {
    }
    
    @Override
    public void setLabelFor(final View view, final int n) {
    }
    
    @Override
    public void setLayerPaint(final View view, final Paint paint) {
    }
    
    @Override
    public void setLayerType(final View view, final int n, final Paint paint) {
    }
    
    @Override
    public void setLayoutDirection(final View view, final int n) {
    }
    
    @Override
    public void setNestedScrollingEnabled(final View view, final boolean nestedScrollingEnabled) {
        if (view instanceof NestedScrollingChild) {
            ((NestedScrollingChild)view).setNestedScrollingEnabled(nestedScrollingEnabled);
        }
    }
    
    @Override
    public void setOnApplyWindowInsetsListener(final View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
    }
    
    @Override
    public void setOverScrollMode(final View view, final int n) {
    }
    
    @Override
    public void setPaddingRelative(final View view, final int n, final int n2, final int n3, final int n4) {
        view.setPadding(n, n2, n3, n4);
    }
    
    @Override
    public void setPivotX(final View view, final float n) {
    }
    
    @Override
    public void setPivotY(final View view, final float n) {
    }
    
    @Override
    public void setRotation(final View view, final float n) {
    }
    
    @Override
    public void setRotationX(final View view, final float n) {
    }
    
    @Override
    public void setRotationY(final View view, final float n) {
    }
    
    @Override
    public void setSaveFromParentEnabled(final View view, final boolean b) {
    }
    
    @Override
    public void setScaleX(final View view, final float n) {
    }
    
    @Override
    public void setScaleY(final View view, final float n) {
    }
    
    @Override
    public void setScrollIndicators(final View view, final int n) {
    }
    
    @Override
    public void setScrollIndicators(final View view, final int n, final int n2) {
    }
    
    @Override
    public void setTransitionName(final View view, final String s) {
    }
    
    @Override
    public void setTranslationX(final View view, final float n) {
    }
    
    @Override
    public void setTranslationY(final View view, final float n) {
    }
    
    @Override
    public void setTranslationZ(final View view, final float n) {
    }
    
    @Override
    public void setX(final View view, final float n) {
    }
    
    @Override
    public void setY(final View view, final float n) {
    }
    
    @Override
    public boolean startNestedScroll(final View view, final int n) {
        return view instanceof NestedScrollingChild && ((NestedScrollingChild)view).startNestedScroll(n);
    }
    
    @Override
    public void stopNestedScroll(final View view) {
        if (view instanceof NestedScrollingChild) {
            ((NestedScrollingChild)view).stopNestedScroll();
        }
    }
}
