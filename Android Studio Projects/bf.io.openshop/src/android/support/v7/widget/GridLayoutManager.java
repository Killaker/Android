package android.support.v7.widget;

import android.graphics.*;
import android.content.*;
import android.util.*;
import android.view.*;
import java.util.*;
import android.support.v4.view.accessibility.*;

public class GridLayoutManager extends LinearLayoutManager
{
    private static final boolean DEBUG = false;
    public static final int DEFAULT_SPAN_COUNT = -1;
    private static final String TAG = "GridLayoutManager";
    int[] mCachedBorders;
    final Rect mDecorInsets;
    boolean mPendingSpanCountChange;
    final SparseIntArray mPreLayoutSpanIndexCache;
    final SparseIntArray mPreLayoutSpanSizeCache;
    View[] mSet;
    int mSpanCount;
    SpanSizeLookup mSpanSizeLookup;
    
    public GridLayoutManager(final Context context, final int spanCount) {
        super(context);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = (SpanSizeLookup)new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.setSpanCount(spanCount);
    }
    
    public GridLayoutManager(final Context context, final int spanCount, final int n, final boolean b) {
        super(context, n, b);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = (SpanSizeLookup)new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.setSpanCount(spanCount);
    }
    
    public GridLayoutManager(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = (SpanSizeLookup)new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.setSpanCount(RecyclerView.LayoutManager.getProperties(context, set, n, n2).spanCount);
    }
    
    private void assignSpans(final Recycler recycler, final State state, final int n, final int n2, final boolean b) {
        int n3;
        int n4;
        int n5;
        if (b) {
            n3 = 0;
            n4 = n;
            n5 = 1;
        }
        else {
            n3 = n - 1;
            n4 = -1;
            n5 = -1;
        }
        int n6;
        int n7;
        if (this.mOrientation == 1 && this.isLayoutRTL()) {
            n6 = -1 + this.mSpanCount;
            n7 = -1;
        }
        else {
            n7 = 1;
            n6 = 0;
        }
        for (int i = n3; i != n4; i += n5) {
            final View view = this.mSet[i];
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            layoutParams.mSpanSize = this.getSpanSize(recycler, state, ((RecyclerView.LayoutManager)this).getPosition(view));
            if (n7 == -1 && layoutParams.mSpanSize > 1) {
                layoutParams.mSpanIndex = n6 - (-1 + layoutParams.mSpanSize);
            }
            else {
                layoutParams.mSpanIndex = n6;
            }
            n6 += n7 * layoutParams.mSpanSize;
        }
    }
    
    private void cachePreLayoutSpanMapping() {
        for (int childCount = ((RecyclerView.LayoutManager)this).getChildCount(), i = 0; i < childCount; ++i) {
            final LayoutParams layoutParams = (LayoutParams)((RecyclerView.LayoutManager)this).getChildAt(i).getLayoutParams();
            final int viewLayoutPosition = ((RecyclerView.LayoutParams)layoutParams).getViewLayoutPosition();
            this.mPreLayoutSpanSizeCache.put(viewLayoutPosition, layoutParams.getSpanSize());
            this.mPreLayoutSpanIndexCache.put(viewLayoutPosition, layoutParams.getSpanIndex());
        }
    }
    
    private void calculateItemBorders(final int n) {
        this.mCachedBorders = calculateItemBorders(this.mCachedBorders, this.mSpanCount, n);
    }
    
    static int[] calculateItemBorders(int[] array, final int n, final int n2) {
        if (array == null || array.length != n + 1 || array[-1 + array.length] != n2) {
            array = new int[n + 1];
        }
        array[0] = 0;
        final int n3 = n2 / n;
        final int n4 = n2 % n;
        int n5 = 0;
        int n6 = 0;
        for (int i = 1; i <= n; ++i) {
            int n7 = n3;
            n6 += n4;
            if (n6 > 0 && n - n6 < n4) {
                ++n7;
                n6 -= n;
            }
            n5 += n7;
            array[i] = n5;
        }
        return array;
    }
    
    private void clearPreLayoutSpanMappingCache() {
        this.mPreLayoutSpanSizeCache.clear();
        this.mPreLayoutSpanIndexCache.clear();
    }
    
    private void ensureAnchorIsInCorrectSpan(final Recycler recycler, final State state, final AnchorInfo anchorInfo, final int n) {
        int n2 = 1;
        if (n != n2) {
            n2 = 0;
        }
        int n3 = this.getSpanIndex(recycler, state, anchorInfo.mPosition);
        if (n2 != 0) {
            while (n3 > 0 && anchorInfo.mPosition > 0) {
                --anchorInfo.mPosition;
                n3 = this.getSpanIndex(recycler, state, anchorInfo.mPosition);
            }
        }
        else {
            final int n4 = -1 + state.getItemCount();
            int i = anchorInfo.mPosition;
            int n5 = n3;
            while (i < n4) {
                final int spanIndex = this.getSpanIndex(recycler, state, i + 1);
                if (spanIndex <= n5) {
                    break;
                }
                ++i;
                n5 = spanIndex;
            }
            anchorInfo.mPosition = i;
        }
    }
    
    private void ensureViewSet() {
        if (this.mSet == null || this.mSet.length != this.mSpanCount) {
            this.mSet = new View[this.mSpanCount];
        }
    }
    
    private int getSpanGroupIndex(final Recycler recycler, final State state, final int n) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getSpanGroupIndex(n, this.mSpanCount);
        }
        final int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(n);
        if (convertPreLayoutPositionToPostLayout == -1) {
            Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + n);
            return 0;
        }
        return this.mSpanSizeLookup.getSpanGroupIndex(convertPreLayoutPositionToPostLayout, this.mSpanCount);
    }
    
    private int getSpanIndex(final Recycler recycler, final State state, final int n) {
        int n2;
        if (!state.isPreLayout()) {
            n2 = this.mSpanSizeLookup.getCachedSpanIndex(n, this.mSpanCount);
        }
        else {
            n2 = this.mPreLayoutSpanIndexCache.get(n, -1);
            if (n2 == -1) {
                final int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(n);
                if (convertPreLayoutPositionToPostLayout == -1) {
                    Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + n);
                    return 0;
                }
                return this.mSpanSizeLookup.getCachedSpanIndex(convertPreLayoutPositionToPostLayout, this.mSpanCount);
            }
        }
        return n2;
    }
    
    private int getSpanSize(final Recycler recycler, final State state, final int n) {
        int n2;
        if (!state.isPreLayout()) {
            n2 = this.mSpanSizeLookup.getSpanSize(n);
        }
        else {
            n2 = this.mPreLayoutSpanSizeCache.get(n, -1);
            if (n2 == -1) {
                final int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(n);
                if (convertPreLayoutPositionToPostLayout == -1) {
                    Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + n);
                    return 1;
                }
                return this.mSpanSizeLookup.getSpanSize(convertPreLayoutPositionToPostLayout);
            }
        }
        return n2;
    }
    
    private void guessMeasurement(final float n, final int n2) {
        this.calculateItemBorders(Math.max(Math.round(n * this.mSpanCount), n2));
    }
    
    private void measureChildWithDecorationsAndMargin(final View view, int updateSpecWithExtra, int updateSpecWithExtra2, final boolean b, final boolean b2) {
        ((RecyclerView.LayoutManager)this).calculateItemDecorationsForChild(view, this.mDecorInsets);
        final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
        if (b || this.mOrientation == 1) {
            updateSpecWithExtra = this.updateSpecWithExtra(updateSpecWithExtra, layoutParams.leftMargin + this.mDecorInsets.left, layoutParams.rightMargin + this.mDecorInsets.right);
        }
        if (b || this.mOrientation == 0) {
            updateSpecWithExtra2 = this.updateSpecWithExtra(updateSpecWithExtra2, layoutParams.topMargin + this.mDecorInsets.top, layoutParams.bottomMargin + this.mDecorInsets.bottom);
        }
        boolean b3;
        if (b2) {
            b3 = ((RecyclerView.LayoutManager)this).shouldReMeasureChild(view, updateSpecWithExtra, updateSpecWithExtra2, layoutParams);
        }
        else {
            b3 = ((RecyclerView.LayoutManager)this).shouldMeasureChild(view, updateSpecWithExtra, updateSpecWithExtra2, layoutParams);
        }
        if (b3) {
            view.measure(updateSpecWithExtra, updateSpecWithExtra2);
        }
    }
    
    private void updateMeasurements() {
        int n;
        if (this.getOrientation() == 1) {
            n = ((RecyclerView.LayoutManager)this).getWidth() - ((RecyclerView.LayoutManager)this).getPaddingRight() - ((RecyclerView.LayoutManager)this).getPaddingLeft();
        }
        else {
            n = ((RecyclerView.LayoutManager)this).getHeight() - ((RecyclerView.LayoutManager)this).getPaddingBottom() - ((RecyclerView.LayoutManager)this).getPaddingTop();
        }
        this.calculateItemBorders(n);
    }
    
    private int updateSpecWithExtra(final int n, final int n2, final int n3) {
        if (n2 != 0 || n3 != 0) {
            final int mode = View$MeasureSpec.getMode(n);
            if (mode == Integer.MIN_VALUE || mode == 1073741824) {
                return View$MeasureSpec.makeMeasureSpec(Math.max(0, View$MeasureSpec.getSize(n) - n2 - n3), mode);
            }
        }
        return n;
    }
    
    @Override
    public boolean checkLayoutParams(final RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }
    
    @Override
    View findReferenceChild(final Recycler recycler, final State state, final int n, final int n2, final int n3) {
        this.ensureLayoutState();
        View view = null;
        View view2 = null;
        final int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        final int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int n4;
        if (n2 > n) {
            n4 = 1;
        }
        else {
            n4 = -1;
        }
        for (int i = n; i != n2; i += n4) {
            final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
            final int position = ((RecyclerView.LayoutManager)this).getPosition(child);
            if (position >= 0 && position < n3 && this.getSpanIndex(recycler, state, position) == 0) {
                if (((RecyclerView.LayoutParams)child.getLayoutParams()).isItemRemoved()) {
                    if (view == null) {
                        view = child;
                    }
                }
                else {
                    if (this.mOrientationHelper.getDecoratedStart(child) < endAfterPadding && this.mOrientationHelper.getDecoratedEnd(child) >= startAfterPadding) {
                        return child;
                    }
                    if (view2 == null) {
                        view2 = child;
                    }
                }
            }
        }
        if (view2 == null) {
            view2 = view;
        }
        return view2;
    }
    
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new LayoutParams(-2, -1);
        }
        return new LayoutParams(-1, -2);
    }
    
    @Override
    public RecyclerView.LayoutParams generateLayoutParams(final Context context, final AttributeSet set) {
        return new LayoutParams(context, set);
    }
    
    @Override
    public RecyclerView.LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            return new LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        return new LayoutParams(viewGroup$LayoutParams);
    }
    
    @Override
    public int getColumnCountForAccessibility(final Recycler recycler, final State state) {
        if (this.mOrientation == 1) {
            return this.mSpanCount;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return 1 + this.getSpanGroupIndex(recycler, state, -1 + state.getItemCount());
    }
    
    @Override
    public int getRowCountForAccessibility(final Recycler recycler, final State state) {
        if (this.mOrientation == 0) {
            return this.mSpanCount;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return 1 + this.getSpanGroupIndex(recycler, state, -1 + state.getItemCount());
    }
    
    public int getSpanCount() {
        return this.mSpanCount;
    }
    
    public SpanSizeLookup getSpanSizeLookup() {
        return this.mSpanSizeLookup;
    }
    
    @Override
    void layoutChunk(final Recycler recycler, final State state, final LayoutState layoutState, final LayoutChunkResult layoutChunkResult) {
        final int modeInOther = this.mOrientationHelper.getModeInOther();
        boolean b;
        if (modeInOther != 1073741824) {
            b = true;
        }
        else {
            b = false;
        }
        int n;
        if (((RecyclerView.LayoutManager)this).getChildCount() > 0) {
            n = this.mCachedBorders[this.mSpanCount];
        }
        else {
            n = 0;
        }
        if (b) {
            this.updateMeasurements();
        }
        final boolean b2 = layoutState.mItemDirection == 1;
        int mSpanCount = this.mSpanCount;
        int n2 = 0;
        int n3 = 0;
        if (!b2) {
            mSpanCount = this.getSpanIndex(recycler, state, layoutState.mCurrentPosition) + this.getSpanSize(recycler, state, layoutState.mCurrentPosition);
        }
        while (n2 < this.mSpanCount && layoutState.hasMore(state) && mSpanCount > 0) {
            final int mCurrentPosition = layoutState.mCurrentPosition;
            final int spanSize = this.getSpanSize(recycler, state, mCurrentPosition);
            if (spanSize > this.mSpanCount) {
                throw new IllegalArgumentException("Item at position " + mCurrentPosition + " requires " + spanSize + " spans but GridLayoutManager has only " + this.mSpanCount + " spans.");
            }
            mSpanCount -= spanSize;
            if (mSpanCount < 0) {
                break;
            }
            final View next = layoutState.next(recycler);
            if (next == null) {
                break;
            }
            n3 += spanSize;
            this.mSet[n2] = next;
            ++n2;
        }
        if (n2 == 0) {
            layoutChunkResult.mFinished = true;
            return;
        }
        int mConsumed = 0;
        float n4 = 0.0f;
        this.assignSpans(recycler, state, n2, n3, b2);
        for (int i = 0; i < n2; ++i) {
            final View view = this.mSet[i];
            if (layoutState.mScrapList == null) {
                if (b2) {
                    ((RecyclerView.LayoutManager)this).addView(view);
                }
                else {
                    ((RecyclerView.LayoutManager)this).addView(view, 0);
                }
            }
            else if (b2) {
                ((RecyclerView.LayoutManager)this).addDisappearingView(view);
            }
            else {
                ((RecyclerView.LayoutManager)this).addDisappearingView(view, 0);
            }
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            final int n5 = this.mCachedBorders[layoutParams.mSpanIndex + layoutParams.mSpanSize] - this.mCachedBorders[layoutParams.mSpanIndex];
            int n6;
            if (this.mOrientation == 0) {
                n6 = layoutParams.height;
            }
            else {
                n6 = layoutParams.width;
            }
            final int childMeasureSpec = RecyclerView.LayoutManager.getChildMeasureSpec(n5, modeInOther, 0, n6, false);
            final int totalSpace = this.mOrientationHelper.getTotalSpace();
            final int mode = this.mOrientationHelper.getMode();
            int n7;
            if (this.mOrientation == 1) {
                n7 = layoutParams.height;
            }
            else {
                n7 = layoutParams.width;
            }
            final int childMeasureSpec2 = RecyclerView.LayoutManager.getChildMeasureSpec(totalSpace, mode, 0, n7, true);
            if (this.mOrientation == 1) {
                this.measureChildWithDecorationsAndMargin(view, childMeasureSpec, childMeasureSpec2, layoutParams.height == -1, false);
            }
            else {
                this.measureChildWithDecorationsAndMargin(view, childMeasureSpec2, childMeasureSpec, layoutParams.width == -1, false);
            }
            final int decoratedMeasurement = this.mOrientationHelper.getDecoratedMeasurement(view);
            if (decoratedMeasurement > mConsumed) {
                mConsumed = decoratedMeasurement;
            }
            final float n8 = 1.0f * this.mOrientationHelper.getDecoratedMeasurementInOther(view) / layoutParams.mSpanSize;
            if (n8 > n4) {
                n4 = n8;
            }
        }
        if (b) {
            this.guessMeasurement(n4, n);
            mConsumed = 0;
            for (int j = 0; j < n2; ++j) {
                final View view2 = this.mSet[j];
                final LayoutParams layoutParams2 = (LayoutParams)view2.getLayoutParams();
                final int n9 = this.mCachedBorders[layoutParams2.mSpanIndex + layoutParams2.mSpanSize] - this.mCachedBorders[layoutParams2.mSpanIndex];
                int n10;
                if (this.mOrientation == 0) {
                    n10 = layoutParams2.height;
                }
                else {
                    n10 = layoutParams2.width;
                }
                final int childMeasureSpec3 = RecyclerView.LayoutManager.getChildMeasureSpec(n9, 1073741824, 0, n10, false);
                final int totalSpace2 = this.mOrientationHelper.getTotalSpace();
                final int mode2 = this.mOrientationHelper.getMode();
                int n11;
                if (this.mOrientation == 1) {
                    n11 = layoutParams2.height;
                }
                else {
                    n11 = layoutParams2.width;
                }
                final int childMeasureSpec4 = RecyclerView.LayoutManager.getChildMeasureSpec(totalSpace2, mode2, 0, n11, true);
                if (this.mOrientation == 1) {
                    this.measureChildWithDecorationsAndMargin(view2, childMeasureSpec3, childMeasureSpec4, false, true);
                }
                else {
                    this.measureChildWithDecorationsAndMargin(view2, childMeasureSpec4, childMeasureSpec3, false, true);
                }
                final int decoratedMeasurement2 = this.mOrientationHelper.getDecoratedMeasurement(view2);
                if (decoratedMeasurement2 > mConsumed) {
                    mConsumed = decoratedMeasurement2;
                }
            }
        }
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(mConsumed, 1073741824);
        for (int k = 0; k < n2; ++k) {
            final View view3 = this.mSet[k];
            if (this.mOrientationHelper.getDecoratedMeasurement(view3) != mConsumed) {
                final LayoutParams layoutParams3 = (LayoutParams)view3.getLayoutParams();
                final int n12 = this.mCachedBorders[layoutParams3.mSpanIndex + layoutParams3.mSpanSize] - this.mCachedBorders[layoutParams3.mSpanIndex];
                int n13;
                if (this.mOrientation == 0) {
                    n13 = layoutParams3.height;
                }
                else {
                    n13 = layoutParams3.width;
                }
                final int childMeasureSpec5 = RecyclerView.LayoutManager.getChildMeasureSpec(n12, 1073741824, 0, n13, false);
                if (this.mOrientation == 1) {
                    this.measureChildWithDecorationsAndMargin(view3, childMeasureSpec5, measureSpec, true, true);
                }
                else {
                    this.measureChildWithDecorationsAndMargin(view3, measureSpec, childMeasureSpec5, true, true);
                }
            }
        }
        layoutChunkResult.mConsumed = mConsumed;
        int mOffset = 0;
        int mOffset2 = 0;
        int mOffset3;
        int mOffset4;
        if (this.mOrientation == 1) {
            if (layoutState.mLayoutDirection == -1) {
                mOffset3 = layoutState.mOffset;
                mOffset4 = mOffset3 - mConsumed;
            }
            else {
                mOffset4 = layoutState.mOffset;
                mOffset3 = mOffset4 + mConsumed;
                mOffset = 0;
                mOffset2 = 0;
            }
        }
        else if (layoutState.mLayoutDirection == -1) {
            mOffset2 = layoutState.mOffset;
            mOffset = mOffset2 - mConsumed;
            mOffset3 = 0;
            mOffset4 = 0;
        }
        else {
            mOffset = layoutState.mOffset;
            mOffset2 = mOffset + mConsumed;
            mOffset3 = 0;
            mOffset4 = 0;
        }
        for (int l = 0; l < n2; ++l) {
            final View view4 = this.mSet[l];
            final LayoutParams layoutParams4 = (LayoutParams)view4.getLayoutParams();
            if (this.mOrientation == 1) {
                if (this.isLayoutRTL()) {
                    mOffset2 = ((RecyclerView.LayoutManager)this).getPaddingLeft() + this.mCachedBorders[layoutParams4.mSpanIndex + layoutParams4.mSpanSize];
                    mOffset = mOffset2 - this.mOrientationHelper.getDecoratedMeasurementInOther(view4);
                }
                else {
                    mOffset = ((RecyclerView.LayoutManager)this).getPaddingLeft() + this.mCachedBorders[layoutParams4.mSpanIndex];
                    mOffset2 = mOffset + this.mOrientationHelper.getDecoratedMeasurementInOther(view4);
                }
            }
            else {
                mOffset4 = ((RecyclerView.LayoutManager)this).getPaddingTop() + this.mCachedBorders[layoutParams4.mSpanIndex];
                mOffset3 = mOffset4 + this.mOrientationHelper.getDecoratedMeasurementInOther(view4);
            }
            ((RecyclerView.LayoutManager)this).layoutDecorated(view4, mOffset + layoutParams4.leftMargin, mOffset4 + layoutParams4.topMargin, mOffset2 - layoutParams4.rightMargin, mOffset3 - layoutParams4.bottomMargin);
            if (((RecyclerView.LayoutParams)layoutParams4).isItemRemoved() || ((RecyclerView.LayoutParams)layoutParams4).isItemChanged()) {
                layoutChunkResult.mIgnoreConsumed = true;
            }
            layoutChunkResult.mFocusable |= view4.isFocusable();
        }
        Arrays.fill(this.mSet, null);
    }
    
    @Override
    void onAnchorReady(final Recycler recycler, final State state, final AnchorInfo anchorInfo, final int n) {
        super.onAnchorReady(recycler, state, anchorInfo, n);
        this.updateMeasurements();
        if (state.getItemCount() > 0 && !state.isPreLayout()) {
            this.ensureAnchorIsInCorrectSpan(recycler, state, anchorInfo, n);
        }
        this.ensureViewSet();
    }
    
    @Override
    public View onFocusSearchFailed(final View view, final int n, final Recycler recycler, final State state) {
        final View containingItemView = ((RecyclerView.LayoutManager)this).findContainingItemView(view);
        View child;
        if (containingItemView == null) {
            child = null;
        }
        else {
            final LayoutParams layoutParams = (LayoutParams)containingItemView.getLayoutParams();
            final int access$000 = layoutParams.mSpanIndex;
            final int n2 = layoutParams.mSpanIndex + layoutParams.mSpanSize;
            if (super.onFocusSearchFailed(view, n, recycler, state) == null) {
                return null;
            }
            int n3;
            if ((this.convertFocusDirectionToLayoutDirection(n) == 1 && true) != this.mShouldReverseLayout) {
                n3 = 1;
            }
            else {
                n3 = 0;
            }
            int n4;
            int n5;
            int childCount;
            if (n3 != 0) {
                n4 = -1 + ((RecyclerView.LayoutManager)this).getChildCount();
                n5 = -1;
                childCount = -1;
            }
            else {
                n5 = 1;
                childCount = ((RecyclerView.LayoutManager)this).getChildCount();
                n4 = 0;
            }
            final boolean b = this.mOrientation == 1 && this.isLayoutRTL();
            View view2 = null;
            int access$2 = -1;
            int n6 = 0;
            for (int i = n4; i != childCount; i += n5) {
                child = ((RecyclerView.LayoutManager)this).getChildAt(i);
                if (child == containingItemView) {
                    break;
                }
                if (child.isFocusable()) {
                    final LayoutParams layoutParams2 = (LayoutParams)child.getLayoutParams();
                    final int access$3 = layoutParams2.mSpanIndex;
                    final int n7 = layoutParams2.mSpanIndex + layoutParams2.mSpanSize;
                    if (access$3 == access$000 && n7 == n2) {
                        return child;
                    }
                    int n8;
                    if (view2 == null) {
                        n8 = 1;
                    }
                    else {
                        final int n9 = Math.min(n7, n2) - Math.max(access$3, access$000);
                        if (n9 > n6) {
                            n8 = 1;
                        }
                        else {
                            final int n10 = n6;
                            n8 = 0;
                            if (n9 == n10) {
                                final boolean b2 = access$3 > access$2 && true;
                                final boolean b3 = b;
                                final boolean b4 = b2;
                                n8 = 0;
                                if (b3 == b4) {
                                    n8 = 1;
                                }
                            }
                        }
                    }
                    if (n8 != 0) {
                        view2 = child;
                        access$2 = layoutParams2.mSpanIndex;
                        n6 = Math.min(n7, n2) - Math.max(access$3, access$000);
                    }
                }
            }
            return view2;
        }
        return child;
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfoForItem(final Recycler recycler, final State state, final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        final ViewGroup$LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        final LayoutParams layoutParams2 = (LayoutParams)layoutParams;
        final int spanGroupIndex = this.getSpanGroupIndex(recycler, state, ((RecyclerView.LayoutParams)layoutParams2).getViewLayoutPosition());
        if (this.mOrientation == 0) {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(layoutParams2.getSpanIndex(), layoutParams2.getSpanSize(), spanGroupIndex, 1, this.mSpanCount > 1 && layoutParams2.getSpanSize() == this.mSpanCount, false));
            return;
        }
        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(spanGroupIndex, 1, layoutParams2.getSpanIndex(), layoutParams2.getSpanSize(), this.mSpanCount > 1 && layoutParams2.getSpanSize() == this.mSpanCount, false));
    }
    
    @Override
    public void onItemsAdded(final RecyclerView recyclerView, final int n, final int n2) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsChanged(final RecyclerView recyclerView) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsMoved(final RecyclerView recyclerView, final int n, final int n2, final int n3) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsRemoved(final RecyclerView recyclerView, final int n, final int n2) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsUpdated(final RecyclerView recyclerView, final int n, final int n2, final Object o) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onLayoutChildren(final Recycler recycler, final State state) {
        if (state.isPreLayout()) {
            this.cachePreLayoutSpanMapping();
        }
        super.onLayoutChildren(recycler, state);
        this.clearPreLayoutSpanMappingCache();
        if (!state.isPreLayout()) {
            this.mPendingSpanCountChange = false;
        }
    }
    
    @Override
    public int scrollHorizontallyBy(final int n, final Recycler recycler, final State state) {
        this.updateMeasurements();
        this.ensureViewSet();
        return super.scrollHorizontallyBy(n, recycler, state);
    }
    
    @Override
    public int scrollVerticallyBy(final int n, final Recycler recycler, final State state) {
        this.updateMeasurements();
        this.ensureViewSet();
        return super.scrollVerticallyBy(n, recycler, state);
    }
    
    @Override
    public void setMeasuredDimension(final Rect rect, final int n, final int n2) {
        if (this.mCachedBorders == null) {
            super.setMeasuredDimension(rect, n, n2);
        }
        final int n3 = ((RecyclerView.LayoutManager)this).getPaddingLeft() + ((RecyclerView.LayoutManager)this).getPaddingRight();
        final int n4 = ((RecyclerView.LayoutManager)this).getPaddingTop() + ((RecyclerView.LayoutManager)this).getPaddingBottom();
        int n5;
        int n6;
        if (this.mOrientation == 1) {
            n5 = RecyclerView.LayoutManager.chooseSize(n2, n4 + rect.height(), ((RecyclerView.LayoutManager)this).getMinimumHeight());
            n6 = RecyclerView.LayoutManager.chooseSize(n, n3 + this.mCachedBorders[-1 + this.mCachedBorders.length], ((RecyclerView.LayoutManager)this).getMinimumWidth());
        }
        else {
            n6 = RecyclerView.LayoutManager.chooseSize(n, n3 + rect.width(), ((RecyclerView.LayoutManager)this).getMinimumWidth());
            n5 = RecyclerView.LayoutManager.chooseSize(n2, n4 + this.mCachedBorders[-1 + this.mCachedBorders.length], ((RecyclerView.LayoutManager)this).getMinimumHeight());
        }
        ((RecyclerView.LayoutManager)this).setMeasuredDimension(n6, n5);
    }
    
    public void setSpanCount(final int mSpanCount) {
        if (mSpanCount == this.mSpanCount) {
            return;
        }
        this.mPendingSpanCountChange = true;
        if (mSpanCount < 1) {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + mSpanCount);
        }
        this.mSpanCount = mSpanCount;
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    public void setSpanSizeLookup(final SpanSizeLookup mSpanSizeLookup) {
        this.mSpanSizeLookup = mSpanSizeLookup;
    }
    
    @Override
    public void setStackFromEnd(final boolean b) {
        if (b) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.setStackFromEnd(false);
    }
    
    @Override
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && !this.mPendingSpanCountChange;
    }
    
    public static final class DefaultSpanSizeLookup extends SpanSizeLookup
    {
        @Override
        public int getSpanIndex(final int n, final int n2) {
            return n % n2;
        }
        
        @Override
        public int getSpanSize(final int n) {
            return 1;
        }
    }
    
    public static class LayoutParams extends RecyclerView.LayoutParams
    {
        public static final int INVALID_SPAN_ID = -1;
        private int mSpanIndex;
        private int mSpanSize;
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public LayoutParams(final RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            super(viewGroup$MarginLayoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public int getSpanIndex() {
            return this.mSpanIndex;
        }
        
        public int getSpanSize() {
            return this.mSpanSize;
        }
    }
    
    public abstract static class SpanSizeLookup
    {
        private boolean mCacheSpanIndices;
        final SparseIntArray mSpanIndexCache;
        
        public SpanSizeLookup() {
            this.mSpanIndexCache = new SparseIntArray();
            this.mCacheSpanIndices = false;
        }
        
        int findReferenceIndexFromCache(final int n) {
            int i = 0;
            int n2 = -1 + this.mSpanIndexCache.size();
            while (i <= n2) {
                final int n3 = i + n2 >>> 1;
                if (this.mSpanIndexCache.keyAt(n3) < n) {
                    i = n3 + 1;
                }
                else {
                    n2 = n3 - 1;
                }
            }
            final int n4 = i - 1;
            if (n4 >= 0 && n4 < this.mSpanIndexCache.size()) {
                return this.mSpanIndexCache.keyAt(n4);
            }
            return -1;
        }
        
        int getCachedSpanIndex(final int n, final int n2) {
            int n3;
            if (!this.mCacheSpanIndices) {
                n3 = this.getSpanIndex(n, n2);
            }
            else {
                n3 = this.mSpanIndexCache.get(n, -1);
                if (n3 == -1) {
                    final int spanIndex = this.getSpanIndex(n, n2);
                    this.mSpanIndexCache.put(n, spanIndex);
                    return spanIndex;
                }
            }
            return n3;
        }
        
        public int getSpanGroupIndex(final int n, final int n2) {
            int n3 = 0;
            int n4 = 0;
            final int spanSize = this.getSpanSize(n);
            for (int i = 0; i < n; ++i) {
                final int spanSize2 = this.getSpanSize(i);
                n3 += spanSize2;
                if (n3 == n2) {
                    n3 = 0;
                    ++n4;
                }
                else if (n3 > n2) {
                    n3 = spanSize2;
                    ++n4;
                }
            }
            if (n3 + spanSize > n2) {
                ++n4;
            }
            return n4;
        }
        
        public int getSpanIndex(final int n, final int n2) {
            final int spanSize = this.getSpanSize(n);
            int n3;
            if (spanSize == n2) {
                n3 = 0;
            }
            else {
                final boolean mCacheSpanIndices = this.mCacheSpanIndices;
                n3 = 0;
                int n4 = 0;
                if (mCacheSpanIndices) {
                    final int size = this.mSpanIndexCache.size();
                    n3 = 0;
                    n4 = 0;
                    if (size > 0) {
                        final int referenceIndexFromCache = this.findReferenceIndexFromCache(n);
                        n3 = 0;
                        n4 = 0;
                        if (referenceIndexFromCache >= 0) {
                            n3 = this.mSpanIndexCache.get(referenceIndexFromCache) + this.getSpanSize(referenceIndexFromCache);
                            n4 = referenceIndexFromCache + 1;
                        }
                    }
                }
                for (int i = n4; i < n; ++i) {
                    final int spanSize2 = this.getSpanSize(i);
                    n3 += spanSize2;
                    if (n3 == n2) {
                        n3 = 0;
                    }
                    else if (n3 > n2) {
                        n3 = spanSize2;
                    }
                }
                if (n3 + spanSize > n2) {
                    return 0;
                }
            }
            return n3;
        }
        
        public abstract int getSpanSize(final int p0);
        
        public void invalidateSpanIndexCache() {
            this.mSpanIndexCache.clear();
        }
        
        public boolean isSpanIndexCacheEnabled() {
            return this.mCacheSpanIndices;
        }
        
        public void setSpanIndexCacheEnabled(final boolean mCacheSpanIndices) {
            this.mCacheSpanIndices = mCacheSpanIndices;
        }
    }
}
