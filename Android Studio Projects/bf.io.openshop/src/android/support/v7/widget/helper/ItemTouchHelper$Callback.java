package android.support.v7.widget.helper;

import android.view.animation.*;
import android.os.*;
import android.graphics.*;
import android.support.v7.widget.*;
import java.util.*;
import android.support.v7.recyclerview.*;
import android.support.v4.view.*;
import android.view.*;
import android.support.v4.animation.*;

public abstract static class Callback
{
    private static final int ABS_HORIZONTAL_DIR_FLAGS = 789516;
    public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
    public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
    private static final long DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS = 2000L;
    static final int RELATIVE_DIR_FLAGS = 3158064;
    private static final Interpolator sDragScrollInterpolator;
    private static final Interpolator sDragViewScrollCapInterpolator;
    private static final ItemTouchUIUtil sUICallback;
    private int mCachedMaxScrollSpeed;
    
    static {
        sDragScrollInterpolator = (Interpolator)new Interpolator() {
            public float getInterpolation(final float n) {
                return n * (n * (n * (n * n)));
            }
        };
        sDragViewScrollCapInterpolator = (Interpolator)new Interpolator() {
            public float getInterpolation(final float n) {
                final float n2 = n - 1.0f;
                return 1.0f + n2 * (n2 * (n2 * (n2 * n2)));
            }
        };
        if (Build$VERSION.SDK_INT >= 21) {
            sUICallback = new ItemTouchUIUtilImpl.Lollipop();
            return;
        }
        if (Build$VERSION.SDK_INT >= 11) {
            sUICallback = new ItemTouchUIUtilImpl.Honeycomb();
            return;
        }
        sUICallback = new ItemTouchUIUtilImpl.Gingerbread();
    }
    
    public Callback() {
        this.mCachedMaxScrollSpeed = -1;
    }
    
    public static int convertToRelativeDirection(final int n, final int n2) {
        final int n3 = n & 0xC0C0C;
        if (n3 == 0) {
            return n;
        }
        final int n4 = n & ~n3;
        if (n2 == 0) {
            return n4 | n3 << 2;
        }
        return n4 | (0xFFF3F3F3 & n3 << 1) | (0xC0C0C & n3 << 1) << 2;
    }
    
    public static ItemTouchUIUtil getDefaultUIUtil() {
        return Callback.sUICallback;
    }
    
    private int getMaxDragScroll(final RecyclerView recyclerView) {
        if (this.mCachedMaxScrollSpeed == -1) {
            this.mCachedMaxScrollSpeed = recyclerView.getResources().getDimensionPixelSize(R.dimen.item_touch_helper_max_drag_scroll_per_frame);
        }
        return this.mCachedMaxScrollSpeed;
    }
    
    private boolean hasDragFlag(final RecyclerView recyclerView, final ViewHolder viewHolder) {
        return (0xFF0000 & this.getAbsoluteMovementFlags(recyclerView, viewHolder)) != 0x0;
    }
    
    private boolean hasSwipeFlag(final RecyclerView recyclerView, final ViewHolder viewHolder) {
        return (0xFF00 & this.getAbsoluteMovementFlags(recyclerView, viewHolder)) != 0x0;
    }
    
    public static int makeFlag(final int n, final int n2) {
        return n2 << n * 8;
    }
    
    public static int makeMovementFlags(final int n, final int n2) {
        return makeFlag(0, n2 | n) | makeFlag(1, n2) | makeFlag(2, n);
    }
    
    private void onDraw(final Canvas canvas, final RecyclerView recyclerView, final ViewHolder viewHolder, final List<RecoverAnimation> list, final int n, final float n2, final float n3) {
        for (int size = list.size(), i = 0; i < size; ++i) {
            final RecoverAnimation recoverAnimation = list.get(i);
            recoverAnimation.update();
            final int save = canvas.save();
            this.onChildDraw(canvas, recyclerView, recoverAnimation.mViewHolder, recoverAnimation.mX, recoverAnimation.mY, recoverAnimation.mActionState, false);
            canvas.restoreToCount(save);
        }
        if (viewHolder != null) {
            final int save2 = canvas.save();
            this.onChildDraw(canvas, recyclerView, viewHolder, n2, n3, n, true);
            canvas.restoreToCount(save2);
        }
    }
    
    private void onDrawOver(final Canvas canvas, final RecyclerView recyclerView, final ViewHolder viewHolder, final List<RecoverAnimation> list, final int n, final float n2, final float n3) {
        final int size = list.size();
        for (int i = 0; i < size; ++i) {
            final RecoverAnimation recoverAnimation = list.get(i);
            final int save = canvas.save();
            this.onChildDrawOver(canvas, recyclerView, recoverAnimation.mViewHolder, recoverAnimation.mX, recoverAnimation.mY, recoverAnimation.mActionState, false);
            canvas.restoreToCount(save);
        }
        if (viewHolder != null) {
            final int save2 = canvas.save();
            this.onChildDrawOver(canvas, recyclerView, viewHolder, n2, n3, n, true);
            canvas.restoreToCount(save2);
        }
        boolean b = false;
        for (int j = size - 1; j >= 0; --j) {
            final RecoverAnimation recoverAnimation2 = list.get(j);
            if (recoverAnimation2.mEnded && !recoverAnimation2.mIsPendingCleanup) {
                list.remove(j);
            }
            else if (!recoverAnimation2.mEnded) {
                b = true;
            }
        }
        if (b) {
            recyclerView.invalidate();
        }
    }
    
    public boolean canDropOver(final RecyclerView recyclerView, final ViewHolder viewHolder, final ViewHolder viewHolder2) {
        return true;
    }
    
    public ViewHolder chooseDropTarget(final ViewHolder viewHolder, final List<ViewHolder> list, final int n, final int n2) {
        final int n3 = n + viewHolder.itemView.getWidth();
        final int n4 = n2 + viewHolder.itemView.getHeight();
        ViewHolder viewHolder2 = null;
        int n5 = -1;
        final int n6 = n - viewHolder.itemView.getLeft();
        final int n7 = n2 - viewHolder.itemView.getTop();
        for (int size = list.size(), i = 0; i < size; ++i) {
            final ViewHolder viewHolder3 = list.get(i);
            if (n6 > 0) {
                final int n8 = viewHolder3.itemView.getRight() - n3;
                if (n8 < 0 && viewHolder3.itemView.getRight() > viewHolder.itemView.getRight()) {
                    final int abs = Math.abs(n8);
                    if (abs > n5) {
                        n5 = abs;
                        viewHolder2 = viewHolder3;
                    }
                }
            }
            if (n6 < 0) {
                final int n9 = viewHolder3.itemView.getLeft() - n;
                if (n9 > 0 && viewHolder3.itemView.getLeft() < viewHolder.itemView.getLeft()) {
                    final int abs2 = Math.abs(n9);
                    if (abs2 > n5) {
                        n5 = abs2;
                        viewHolder2 = viewHolder3;
                    }
                }
            }
            if (n7 < 0) {
                final int n10 = viewHolder3.itemView.getTop() - n2;
                if (n10 > 0 && viewHolder3.itemView.getTop() < viewHolder.itemView.getTop()) {
                    final int abs3 = Math.abs(n10);
                    if (abs3 > n5) {
                        n5 = abs3;
                        viewHolder2 = viewHolder3;
                    }
                }
            }
            if (n7 > 0) {
                final int n11 = viewHolder3.itemView.getBottom() - n4;
                if (n11 < 0 && viewHolder3.itemView.getBottom() > viewHolder.itemView.getBottom()) {
                    final int abs4 = Math.abs(n11);
                    if (abs4 > n5) {
                        n5 = abs4;
                        viewHolder2 = viewHolder3;
                    }
                }
            }
        }
        return viewHolder2;
    }
    
    public void clearView(final RecyclerView recyclerView, final ViewHolder viewHolder) {
        Callback.sUICallback.clearView(viewHolder.itemView);
    }
    
    public int convertToAbsoluteDirection(final int n, final int n2) {
        final int n3 = n & 0x303030;
        if (n3 == 0) {
            return n;
        }
        final int n4 = n & ~n3;
        if (n2 == 0) {
            return n4 | n3 >> 2;
        }
        return n4 | (0xFFCFCFCF & n3 >> 1) | (0x303030 & n3 >> 1) >> 2;
    }
    
    final int getAbsoluteMovementFlags(final RecyclerView recyclerView, final ViewHolder viewHolder) {
        return this.convertToAbsoluteDirection(this.getMovementFlags(recyclerView, viewHolder), ViewCompat.getLayoutDirection((View)recyclerView));
    }
    
    public long getAnimationDuration(final RecyclerView recyclerView, final int n, final float n2, final float n3) {
        final RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
        if (itemAnimator == null) {
            if (n == 8) {
                return 200L;
            }
            return 250L;
        }
        else {
            if (n == 8) {
                return itemAnimator.getMoveDuration();
            }
            return itemAnimator.getRemoveDuration();
        }
    }
    
    public int getBoundingBoxMargin() {
        return 0;
    }
    
    public float getMoveThreshold(final ViewHolder viewHolder) {
        return 0.5f;
    }
    
    public abstract int getMovementFlags(final RecyclerView p0, final ViewHolder p1);
    
    public float getSwipeEscapeVelocity(final float n) {
        return n;
    }
    
    public float getSwipeThreshold(final ViewHolder viewHolder) {
        return 0.5f;
    }
    
    public float getSwipeVelocityThreshold(final float n) {
        return n;
    }
    
    public int interpolateOutOfBoundsScroll(final RecyclerView recyclerView, final int n, final int n2, final int n3, final long n4) {
        final int n5 = (int)((int)Math.signum(n2) * this.getMaxDragScroll(recyclerView) * Callback.sDragViewScrollCapInterpolator.getInterpolation(Math.min(1.0f, 1.0f * Math.abs(n2) / n)));
        float n6;
        if (n4 > 2000L) {
            n6 = 1.0f;
        }
        else {
            n6 = n4 / 2000.0f;
        }
        final int n7 = (int)(n5 * Callback.sDragScrollInterpolator.getInterpolation(n6));
        if (n7 != 0) {
            return n7;
        }
        if (n2 > 0) {
            return 1;
        }
        return -1;
    }
    
    public boolean isItemViewSwipeEnabled() {
        return true;
    }
    
    public boolean isLongPressDragEnabled() {
        return true;
    }
    
    public void onChildDraw(final Canvas canvas, final RecyclerView recyclerView, final ViewHolder viewHolder, final float n, final float n2, final int n3, final boolean b) {
        Callback.sUICallback.onDraw(canvas, recyclerView, viewHolder.itemView, n, n2, n3, b);
    }
    
    public void onChildDrawOver(final Canvas canvas, final RecyclerView recyclerView, final ViewHolder viewHolder, final float n, final float n2, final int n3, final boolean b) {
        Callback.sUICallback.onDrawOver(canvas, recyclerView, viewHolder.itemView, n, n2, n3, b);
    }
    
    public abstract boolean onMove(final RecyclerView p0, final ViewHolder p1, final ViewHolder p2);
    
    public void onMoved(final RecyclerView recyclerView, final ViewHolder viewHolder, final int n, final ViewHolder viewHolder2, final int n2, final int n3, final int n4) {
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof ViewDropHandler) {
            ((ViewDropHandler)layoutManager).prepareForDrop(viewHolder.itemView, viewHolder2.itemView, n3, n4);
        }
        else {
            if (layoutManager.canScrollHorizontally()) {
                if (layoutManager.getDecoratedLeft(viewHolder2.itemView) <= recyclerView.getPaddingLeft()) {
                    recyclerView.scrollToPosition(n2);
                }
                if (layoutManager.getDecoratedRight(viewHolder2.itemView) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                    recyclerView.scrollToPosition(n2);
                }
            }
            if (layoutManager.canScrollVertically()) {
                if (layoutManager.getDecoratedTop(viewHolder2.itemView) <= recyclerView.getPaddingTop()) {
                    recyclerView.scrollToPosition(n2);
                }
                if (layoutManager.getDecoratedBottom(viewHolder2.itemView) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                    recyclerView.scrollToPosition(n2);
                }
            }
        }
    }
    
    public void onSelectedChanged(final ViewHolder viewHolder, final int n) {
        if (viewHolder != null) {
            Callback.sUICallback.onSelected(viewHolder.itemView);
        }
    }
    
    public abstract void onSwiped(final ViewHolder p0, final int p1);
}
