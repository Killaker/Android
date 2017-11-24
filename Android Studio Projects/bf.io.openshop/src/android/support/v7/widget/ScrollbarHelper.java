package android.support.v7.widget;

import android.view.*;

class ScrollbarHelper
{
    static int computeScrollExtent(final RecyclerView.State state, final OrientationHelper orientationHelper, final View view, final View view2, final RecyclerView.LayoutManager layoutManager, final boolean b) {
        if (layoutManager.getChildCount() == 0 || state.getItemCount() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!b) {
            return 1 + Math.abs(layoutManager.getPosition(view) - layoutManager.getPosition(view2));
        }
        return Math.min(orientationHelper.getTotalSpace(), orientationHelper.getDecoratedEnd(view2) - orientationHelper.getDecoratedStart(view));
    }
    
    static int computeScrollOffset(final RecyclerView.State state, final OrientationHelper orientationHelper, final View view, final View view2, final RecyclerView.LayoutManager layoutManager, final boolean b, final boolean b2) {
        final int childCount = layoutManager.getChildCount();
        int n = 0;
        if (childCount != 0) {
            final int itemCount = state.getItemCount();
            n = 0;
            if (itemCount != 0) {
                n = 0;
                if (view != null) {
                    n = 0;
                    if (view2 != null) {
                        final int min = Math.min(layoutManager.getPosition(view), layoutManager.getPosition(view2));
                        final int max = Math.max(layoutManager.getPosition(view), layoutManager.getPosition(view2));
                        if (b2) {
                            n = Math.max(0, -1 + (state.getItemCount() - max));
                        }
                        else {
                            n = Math.max(0, min);
                        }
                        if (b) {
                            return Math.round(Math.abs(orientationHelper.getDecoratedEnd(view2) - orientationHelper.getDecoratedStart(view)) / (1 + Math.abs(layoutManager.getPosition(view) - layoutManager.getPosition(view2))) * n + (orientationHelper.getStartAfterPadding() - orientationHelper.getDecoratedStart(view)));
                        }
                    }
                }
            }
        }
        return n;
    }
    
    static int computeScrollRange(final RecyclerView.State state, final OrientationHelper orientationHelper, final View view, final View view2, final RecyclerView.LayoutManager layoutManager, final boolean b) {
        if (layoutManager.getChildCount() == 0 || state.getItemCount() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!b) {
            return state.getItemCount();
        }
        return (orientationHelper.getDecoratedEnd(view2) - orientationHelper.getDecoratedStart(view)) / (1 + Math.abs(layoutManager.getPosition(view) - layoutManager.getPosition(view2))) * state.getItemCount();
    }
}
