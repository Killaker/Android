package android.support.design.widget;

import android.support.v4.widget.*;
import android.view.*;
import android.support.v4.view.*;

class BottomSheetBehavior$1 extends Callback {
    @Override
    public int clampViewPositionHorizontal(final View view, final int n, final int n2) {
        return view.getLeft();
    }
    
    @Override
    public int clampViewPositionVertical(final View view, final int n, final int n2) {
        final int access$700 = BottomSheetBehavior.access$700(BottomSheetBehavior.this);
        int n3;
        if (BottomSheetBehavior.access$800(BottomSheetBehavior.this)) {
            n3 = BottomSheetBehavior.access$1000(BottomSheetBehavior.this);
        }
        else {
            n3 = BottomSheetBehavior.access$1100(BottomSheetBehavior.this);
        }
        return MathUtils.constrain(n, access$700, n3);
    }
    
    @Override
    public int getViewVerticalDragRange(final View view) {
        if (BottomSheetBehavior.access$800(BottomSheetBehavior.this)) {
            return BottomSheetBehavior.access$1000(BottomSheetBehavior.this) - BottomSheetBehavior.access$700(BottomSheetBehavior.this);
        }
        return BottomSheetBehavior.access$1100(BottomSheetBehavior.this) - BottomSheetBehavior.access$700(BottomSheetBehavior.this);
    }
    
    @Override
    public void onViewDragStateChanged(final int n) {
        if (n == 1) {
            BottomSheetBehavior.access$600(BottomSheetBehavior.this, 1);
        }
    }
    
    @Override
    public void onViewPositionChanged(final View view, final int n, final int n2, final int n3, final int n4) {
        BottomSheetBehavior.access$500(BottomSheetBehavior.this, n2);
    }
    
    @Override
    public void onViewReleased(final View view, final float n, final float n2) {
        int n3;
        int n4;
        if (n2 < 0.0f) {
            n3 = BottomSheetBehavior.access$700(BottomSheetBehavior.this);
            n4 = 3;
        }
        else if (BottomSheetBehavior.access$800(BottomSheetBehavior.this) && BottomSheetBehavior.access$900(BottomSheetBehavior.this, view, n2)) {
            n3 = BottomSheetBehavior.access$1000(BottomSheetBehavior.this);
            n4 = 5;
        }
        else if (n2 == 0.0f) {
            final int top = view.getTop();
            if (Math.abs(top - BottomSheetBehavior.access$700(BottomSheetBehavior.this)) < Math.abs(top - BottomSheetBehavior.access$1100(BottomSheetBehavior.this))) {
                n3 = BottomSheetBehavior.access$700(BottomSheetBehavior.this);
                n4 = 3;
            }
            else {
                n3 = BottomSheetBehavior.access$1100(BottomSheetBehavior.this);
                n4 = 4;
            }
        }
        else {
            n3 = BottomSheetBehavior.access$1100(BottomSheetBehavior.this);
            n4 = 4;
        }
        if (BottomSheetBehavior.access$1200(BottomSheetBehavior.this).settleCapturedViewAt(view.getLeft(), n3)) {
            BottomSheetBehavior.access$600(BottomSheetBehavior.this, 2);
            ViewCompat.postOnAnimation(view, new SettleRunnable(view, n4));
            return;
        }
        BottomSheetBehavior.access$600(BottomSheetBehavior.this, n4);
    }
    
    @Override
    public boolean tryCaptureView(final View view, final int n) {
        boolean b = true;
        if (BottomSheetBehavior.access$000(BottomSheetBehavior.this) != (b ? 1 : 0) && !BottomSheetBehavior.access$100(BottomSheetBehavior.this)) {
            if (BottomSheetBehavior.access$000(BottomSheetBehavior.this) == 3 && BottomSheetBehavior.access$200(BottomSheetBehavior.this) == n) {
                final View view2 = (View)BottomSheetBehavior.access$300(BottomSheetBehavior.this).get();
                if (view2 != null && ViewCompat.canScrollVertically(view2, -1)) {
                    return false;
                }
            }
            if (BottomSheetBehavior.access$400(BottomSheetBehavior.this) == null || BottomSheetBehavior.access$400(BottomSheetBehavior.this).get() != view) {
                b = false;
            }
            return b;
        }
        return false;
    }
}