package android.support.design.widget;

import android.graphics.*;
import android.content.*;
import android.util.*;
import java.util.*;
import android.support.v4.view.*;
import android.view.*;

abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior<View>
{
    private int mOverlayTop;
    private final Rect mTempRect1;
    private final Rect mTempRect2;
    private int mVerticalLayoutGap;
    
    public HeaderScrollingViewBehavior() {
        this.mTempRect1 = new Rect();
        this.mTempRect2 = new Rect();
        this.mVerticalLayoutGap = 0;
    }
    
    public HeaderScrollingViewBehavior(final Context context, final AttributeSet set) {
        super(context, set);
        this.mTempRect1 = new Rect();
        this.mTempRect2 = new Rect();
        this.mVerticalLayoutGap = 0;
    }
    
    private static int resolveGravity(int n) {
        if (n == 0) {
            n = 8388659;
        }
        return n;
    }
    
    abstract View findFirstDependency(final List<View> p0);
    
    final int getOverlapPixelsForOffset(final View view) {
        if (this.mOverlayTop == 0) {
            return 0;
        }
        return MathUtils.constrain(Math.round(this.getOverlapRatioForOffset(view) * this.mOverlayTop), 0, this.mOverlayTop);
    }
    
    float getOverlapRatioForOffset(final View view) {
        return 1.0f;
    }
    
    public final int getOverlayTop() {
        return this.mOverlayTop;
    }
    
    int getScrollRange(final View view) {
        return view.getMeasuredHeight();
    }
    
    final int getVerticalLayoutGap() {
        return this.mVerticalLayoutGap;
    }
    
    @Override
    protected void layoutChild(final CoordinatorLayout coordinatorLayout, final View view, final int n) {
        final View firstDependency = this.findFirstDependency(coordinatorLayout.getDependencies(view));
        if (firstDependency != null) {
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            final Rect mTempRect1 = this.mTempRect1;
            mTempRect1.set(coordinatorLayout.getPaddingLeft() + layoutParams.leftMargin, firstDependency.getBottom() + layoutParams.topMargin, coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight() - layoutParams.rightMargin, coordinatorLayout.getHeight() + firstDependency.getBottom() - coordinatorLayout.getPaddingBottom() - layoutParams.bottomMargin);
            final Rect mTempRect2 = this.mTempRect2;
            GravityCompat.apply(resolveGravity(layoutParams.gravity), view.getMeasuredWidth(), view.getMeasuredHeight(), mTempRect1, mTempRect2, n);
            final int overlapPixelsForOffset = this.getOverlapPixelsForOffset(firstDependency);
            view.layout(mTempRect2.left, mTempRect2.top - overlapPixelsForOffset, mTempRect2.right, mTempRect2.bottom - overlapPixelsForOffset);
            this.mVerticalLayoutGap = mTempRect2.top - firstDependency.getBottom();
            return;
        }
        super.layoutChild(coordinatorLayout, view, n);
        this.mVerticalLayoutGap = 0;
    }
    
    @Override
    public boolean onMeasureChild(final CoordinatorLayout coordinatorLayout, final View view, final int n, final int n2, final int n3, final int n4) {
        final int height = view.getLayoutParams().height;
        if (height == -1 || height == -2) {
            final View firstDependency = this.findFirstDependency(coordinatorLayout.getDependencies(view));
            if (firstDependency != null) {
                if (ViewCompat.getFitsSystemWindows(firstDependency) && !ViewCompat.getFitsSystemWindows(view)) {
                    ViewCompat.setFitsSystemWindows(view, true);
                    if (ViewCompat.getFitsSystemWindows(view)) {
                        view.requestLayout();
                        return true;
                    }
                }
                if (ViewCompat.isLaidOut(firstDependency)) {
                    int n5 = View$MeasureSpec.getSize(n3);
                    if (n5 == 0) {
                        n5 = coordinatorLayout.getHeight();
                    }
                    final int n6 = n5 - firstDependency.getMeasuredHeight() + this.getScrollRange(firstDependency);
                    int n7;
                    if (height == -1) {
                        n7 = 1073741824;
                    }
                    else {
                        n7 = Integer.MIN_VALUE;
                    }
                    coordinatorLayout.onMeasureChild(view, n, n2, View$MeasureSpec.makeMeasureSpec(n6, n7), n4);
                    return true;
                }
            }
        }
        return false;
    }
    
    public final void setOverlayTop(final int mOverlayTop) {
        this.mOverlayTop = mOverlayTop;
    }
}
