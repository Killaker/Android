package android.support.v7.widget;

import java.util.*;
import android.view.*;

class Span
{
    static final int INVALID_LINE = Integer.MIN_VALUE;
    int mCachedEnd;
    int mCachedStart;
    int mDeletedSize;
    final int mIndex;
    private ArrayList<View> mViews;
    
    private Span(final int mIndex) {
        this.mViews = new ArrayList<View>();
        this.mCachedStart = Integer.MIN_VALUE;
        this.mCachedEnd = Integer.MIN_VALUE;
        this.mDeletedSize = 0;
        this.mIndex = mIndex;
    }
    
    void appendToSpan(final View view) {
        final LayoutParams layoutParams = this.getLayoutParams(view);
        layoutParams.mSpan = this;
        this.mViews.add(view);
        this.mCachedEnd = Integer.MIN_VALUE;
        if (this.mViews.size() == 1) {
            this.mCachedStart = Integer.MIN_VALUE;
        }
        if (((RecyclerView.LayoutParams)layoutParams).isItemRemoved() || ((RecyclerView.LayoutParams)layoutParams).isItemChanged()) {
            this.mDeletedSize += StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view);
        }
    }
    
    void cacheReferenceLineAndClear(final boolean b, final int n) {
        int n2;
        if (b) {
            n2 = this.getEndLine(Integer.MIN_VALUE);
        }
        else {
            n2 = this.getStartLine(Integer.MIN_VALUE);
        }
        this.clear();
        if (n2 != Integer.MIN_VALUE && (!b || n2 >= StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding()) && (b || n2 <= StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding())) {
            if (n != Integer.MIN_VALUE) {
                n2 += n;
            }
            this.mCachedEnd = n2;
            this.mCachedStart = n2;
        }
    }
    
    void calculateCachedEnd() {
        final View view = this.mViews.get(-1 + this.mViews.size());
        final LayoutParams layoutParams = this.getLayoutParams(view);
        this.mCachedEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view);
        if (layoutParams.mFullSpan) {
            final LazySpanLookup.FullSpanItem fullSpanItem = StaggeredGridLayoutManager.this.mLazySpanLookup.getFullSpanItem(((RecyclerView.LayoutParams)layoutParams).getViewLayoutPosition());
            if (fullSpanItem != null && fullSpanItem.mGapDir == 1) {
                this.mCachedEnd += fullSpanItem.getGapForSpan(this.mIndex);
            }
        }
    }
    
    void calculateCachedStart() {
        final View view = this.mViews.get(0);
        final LayoutParams layoutParams = this.getLayoutParams(view);
        this.mCachedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view);
        if (layoutParams.mFullSpan) {
            final LazySpanLookup.FullSpanItem fullSpanItem = StaggeredGridLayoutManager.this.mLazySpanLookup.getFullSpanItem(((RecyclerView.LayoutParams)layoutParams).getViewLayoutPosition());
            if (fullSpanItem != null && fullSpanItem.mGapDir == -1) {
                this.mCachedStart -= fullSpanItem.getGapForSpan(this.mIndex);
            }
        }
    }
    
    void clear() {
        this.mViews.clear();
        this.invalidateCache();
        this.mDeletedSize = 0;
    }
    
    public int findFirstCompletelyVisibleItemPosition() {
        if (StaggeredGridLayoutManager.access$600(StaggeredGridLayoutManager.this)) {
            return this.findOneVisibleChild(-1 + this.mViews.size(), -1, true);
        }
        return this.findOneVisibleChild(0, this.mViews.size(), true);
    }
    
    public int findFirstVisibleItemPosition() {
        if (StaggeredGridLayoutManager.access$600(StaggeredGridLayoutManager.this)) {
            return this.findOneVisibleChild(-1 + this.mViews.size(), -1, false);
        }
        return this.findOneVisibleChild(0, this.mViews.size(), false);
    }
    
    public int findLastCompletelyVisibleItemPosition() {
        if (StaggeredGridLayoutManager.access$600(StaggeredGridLayoutManager.this)) {
            return this.findOneVisibleChild(0, this.mViews.size(), true);
        }
        return this.findOneVisibleChild(-1 + this.mViews.size(), -1, true);
    }
    
    public int findLastVisibleItemPosition() {
        if (StaggeredGridLayoutManager.access$600(StaggeredGridLayoutManager.this)) {
            return this.findOneVisibleChild(0, this.mViews.size(), false);
        }
        return this.findOneVisibleChild(-1 + this.mViews.size(), -1, false);
    }
    
    int findOneVisibleChild(final int n, final int n2, final boolean b) {
        int position = -1;
        final int startAfterPadding = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
        final int endAfterPadding = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding();
        int n3;
        if (n2 > n) {
            n3 = 1;
        }
        else {
            n3 = position;
        }
        for (int i = n; i != n2; i += n3) {
            final View view = this.mViews.get(i);
            final int decoratedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view);
            final int decoratedEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view);
            if (decoratedStart < endAfterPadding && decoratedEnd > startAfterPadding) {
                if (!b) {
                    return ((RecyclerView.LayoutManager)StaggeredGridLayoutManager.this).getPosition(view);
                }
                if (decoratedStart >= startAfterPadding && decoratedEnd <= endAfterPadding) {
                    position = ((RecyclerView.LayoutManager)StaggeredGridLayoutManager.this).getPosition(view);
                    break;
                }
            }
        }
        return position;
    }
    
    public int getDeletedSize() {
        return this.mDeletedSize;
    }
    
    int getEndLine() {
        if (this.mCachedEnd != Integer.MIN_VALUE) {
            return this.mCachedEnd;
        }
        this.calculateCachedEnd();
        return this.mCachedEnd;
    }
    
    int getEndLine(int mCachedEnd) {
        if (this.mCachedEnd != Integer.MIN_VALUE) {
            mCachedEnd = this.mCachedEnd;
        }
        else if (this.mViews.size() != 0) {
            this.calculateCachedEnd();
            return this.mCachedEnd;
        }
        return mCachedEnd;
    }
    
    public View getFocusableViewAfter(final int n, final int n2) {
        View view = null;
        if (n2 == -1) {
            for (int size = this.mViews.size(), i = 0; i < size; ++i) {
                final View view2 = this.mViews.get(i);
                if (!view2.isFocusable() || ((RecyclerView.LayoutManager)StaggeredGridLayoutManager.this).getPosition(view2) > n != StaggeredGridLayoutManager.access$600(StaggeredGridLayoutManager.this)) {
                    break;
                }
                view = view2;
            }
        }
        else {
            for (int j = -1 + this.mViews.size(); j >= 0; --j) {
                final View view3 = this.mViews.get(j);
                if (!view3.isFocusable()) {
                    break;
                }
                int n3;
                if (((RecyclerView.LayoutManager)StaggeredGridLayoutManager.this).getPosition(view3) > n) {
                    n3 = 1;
                }
                else {
                    n3 = 0;
                }
                int n4;
                if (!StaggeredGridLayoutManager.access$600(StaggeredGridLayoutManager.this)) {
                    n4 = 1;
                }
                else {
                    n4 = 0;
                }
                if (n3 != n4) {
                    break;
                }
                view = view3;
            }
        }
        return view;
    }
    
    LayoutParams getLayoutParams(final View view) {
        return (LayoutParams)view.getLayoutParams();
    }
    
    int getStartLine() {
        if (this.mCachedStart != Integer.MIN_VALUE) {
            return this.mCachedStart;
        }
        this.calculateCachedStart();
        return this.mCachedStart;
    }
    
    int getStartLine(int mCachedStart) {
        if (this.mCachedStart != Integer.MIN_VALUE) {
            mCachedStart = this.mCachedStart;
        }
        else if (this.mViews.size() != 0) {
            this.calculateCachedStart();
            return this.mCachedStart;
        }
        return mCachedStart;
    }
    
    void invalidateCache() {
        this.mCachedStart = Integer.MIN_VALUE;
        this.mCachedEnd = Integer.MIN_VALUE;
    }
    
    void onOffset(final int n) {
        if (this.mCachedStart != Integer.MIN_VALUE) {
            this.mCachedStart += n;
        }
        if (this.mCachedEnd != Integer.MIN_VALUE) {
            this.mCachedEnd += n;
        }
    }
    
    void popEnd() {
        final int size = this.mViews.size();
        final View view = this.mViews.remove(size - 1);
        final LayoutParams layoutParams = this.getLayoutParams(view);
        layoutParams.mSpan = null;
        if (((RecyclerView.LayoutParams)layoutParams).isItemRemoved() || ((RecyclerView.LayoutParams)layoutParams).isItemChanged()) {
            this.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view);
        }
        if (size == 1) {
            this.mCachedStart = Integer.MIN_VALUE;
        }
        this.mCachedEnd = Integer.MIN_VALUE;
    }
    
    void popStart() {
        final View view = this.mViews.remove(0);
        final LayoutParams layoutParams = this.getLayoutParams(view);
        layoutParams.mSpan = null;
        if (this.mViews.size() == 0) {
            this.mCachedEnd = Integer.MIN_VALUE;
        }
        if (((RecyclerView.LayoutParams)layoutParams).isItemRemoved() || ((RecyclerView.LayoutParams)layoutParams).isItemChanged()) {
            this.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view);
        }
        this.mCachedStart = Integer.MIN_VALUE;
    }
    
    void prependToSpan(final View view) {
        final LayoutParams layoutParams = this.getLayoutParams(view);
        layoutParams.mSpan = this;
        this.mViews.add(0, view);
        this.mCachedStart = Integer.MIN_VALUE;
        if (this.mViews.size() == 1) {
            this.mCachedEnd = Integer.MIN_VALUE;
        }
        if (((RecyclerView.LayoutParams)layoutParams).isItemRemoved() || ((RecyclerView.LayoutParams)layoutParams).isItemChanged()) {
            this.mDeletedSize += StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view);
        }
    }
    
    void setLine(final int n) {
        this.mCachedStart = n;
        this.mCachedEnd = n;
    }
}
