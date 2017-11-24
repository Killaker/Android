package android.support.v7.widget;

import android.content.*;
import android.util.*;
import android.view.*;
import android.support.annotation.*;
import android.view.accessibility.*;
import android.support.v4.view.accessibility.*;
import android.graphics.*;
import java.util.*;
import android.os.*;

public class StaggeredGridLayoutManager extends LayoutManager
{
    private static final boolean DEBUG = false;
    @Deprecated
    public static final int GAP_HANDLING_LAZY = 1;
    public static final int GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS = 2;
    public static final int GAP_HANDLING_NONE = 0;
    public static final int HORIZONTAL = 0;
    private static final int INVALID_OFFSET = Integer.MIN_VALUE;
    private static final float MAX_SCROLL_FACTOR = 0.33333334f;
    public static final String TAG = "StaggeredGridLayoutManager";
    public static final int VERTICAL = 1;
    private final AnchorInfo mAnchorInfo;
    private final Runnable mCheckForGapsRunnable;
    private int mFullSizeSpec;
    private int mGapStrategy;
    private boolean mLaidOutInvalidFullSpan;
    private boolean mLastLayoutFromEnd;
    private boolean mLastLayoutRTL;
    private LayoutState mLayoutState;
    LazySpanLookup mLazySpanLookup;
    private int mOrientation;
    private SavedState mPendingSavedState;
    int mPendingScrollPosition;
    int mPendingScrollPositionOffset;
    OrientationHelper mPrimaryOrientation;
    private BitSet mRemainingSpans;
    private boolean mReverseLayout;
    OrientationHelper mSecondaryOrientation;
    boolean mShouldReverseLayout;
    private int mSizePerSpan;
    private boolean mSmoothScrollbarEnabled;
    private int mSpanCount;
    private Span[] mSpans;
    private final Rect mTmpRect;
    
    public StaggeredGridLayoutManager(final int spanCount, final int mOrientation) {
        boolean b = true;
        this.mSpanCount = -1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mLazySpanLookup = new LazySpanLookup();
        this.mGapStrategy = 2;
        this.mTmpRect = new Rect();
        this.mAnchorInfo = new AnchorInfo();
        this.mLaidOutInvalidFullSpan = false;
        this.mSmoothScrollbarEnabled = b;
        this.mCheckForGapsRunnable = new Runnable() {
            @Override
            public void run() {
                StaggeredGridLayoutManager.this.checkForGaps();
            }
        };
        this.mOrientation = mOrientation;
        this.setSpanCount(spanCount);
        if (this.mGapStrategy == 0) {
            b = false;
        }
        ((RecyclerView.LayoutManager)this).setAutoMeasureEnabled(b);
    }
    
    public StaggeredGridLayoutManager(final Context context, final AttributeSet set, final int n, final int n2) {
        boolean b = true;
        this.mSpanCount = -1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mLazySpanLookup = new LazySpanLookup();
        this.mGapStrategy = 2;
        this.mTmpRect = new Rect();
        this.mAnchorInfo = new AnchorInfo();
        this.mLaidOutInvalidFullSpan = false;
        this.mSmoothScrollbarEnabled = b;
        this.mCheckForGapsRunnable = new Runnable() {
            @Override
            public void run() {
                StaggeredGridLayoutManager.this.checkForGaps();
            }
        };
        final Properties properties = RecyclerView.LayoutManager.getProperties(context, set, n, n2);
        this.setOrientation(properties.orientation);
        this.setSpanCount(properties.spanCount);
        this.setReverseLayout(properties.reverseLayout);
        if (this.mGapStrategy == 0) {
            b = false;
        }
        ((RecyclerView.LayoutManager)this).setAutoMeasureEnabled(b);
    }
    
    private void appendViewToAllSpans(final View view) {
        for (int i = -1 + this.mSpanCount; i >= 0; --i) {
            this.mSpans[i].appendToSpan(view);
        }
    }
    
    private void applyPendingSavedState(final AnchorInfo anchorInfo) {
        if (this.mPendingSavedState.mSpanOffsetsSize > 0) {
            if (this.mPendingSavedState.mSpanOffsetsSize == this.mSpanCount) {
                for (int i = 0; i < this.mSpanCount; ++i) {
                    this.mSpans[i].clear();
                    int line = this.mPendingSavedState.mSpanOffsets[i];
                    if (line != Integer.MIN_VALUE) {
                        if (this.mPendingSavedState.mAnchorLayoutFromEnd) {
                            line += this.mPrimaryOrientation.getEndAfterPadding();
                        }
                        else {
                            line += this.mPrimaryOrientation.getStartAfterPadding();
                        }
                    }
                    this.mSpans[i].setLine(line);
                }
            }
            else {
                this.mPendingSavedState.invalidateSpanInfo();
                this.mPendingSavedState.mAnchorPosition = this.mPendingSavedState.mVisibleAnchorPosition;
            }
        }
        this.mLastLayoutRTL = this.mPendingSavedState.mLastLayoutRTL;
        this.setReverseLayout(this.mPendingSavedState.mReverseLayout);
        this.resolveShouldLayoutReverse();
        if (this.mPendingSavedState.mAnchorPosition != -1) {
            this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
            anchorInfo.mLayoutFromEnd = this.mPendingSavedState.mAnchorLayoutFromEnd;
        }
        else {
            anchorInfo.mLayoutFromEnd = this.mShouldReverseLayout;
        }
        if (this.mPendingSavedState.mSpanLookupSize > 1) {
            this.mLazySpanLookup.mData = this.mPendingSavedState.mSpanLookup;
            this.mLazySpanLookup.mFullSpanItems = this.mPendingSavedState.mFullSpanItems;
        }
    }
    
    private void attachViewToSpans(final View view, final LayoutParams layoutParams, final LayoutState layoutState) {
        if (layoutState.mLayoutDirection == 1) {
            if (layoutParams.mFullSpan) {
                this.appendViewToAllSpans(view);
                return;
            }
            layoutParams.mSpan.appendToSpan(view);
        }
        else {
            if (layoutParams.mFullSpan) {
                this.prependViewToAllSpans(view);
                return;
            }
            layoutParams.mSpan.prependToSpan(view);
        }
    }
    
    private int calculateScrollDirectionForPosition(final int n) {
        int n2 = -1;
        if (((RecyclerView.LayoutManager)this).getChildCount() != 0) {
            if (n < this.getFirstChildPosition() == this.mShouldReverseLayout) {
                n2 = 1;
            }
            return n2;
        }
        if (this.mShouldReverseLayout) {
            return 1;
        }
        return n2;
    }
    
    private boolean checkForGaps() {
        if (((RecyclerView.LayoutManager)this).getChildCount() == 0 || this.mGapStrategy == 0 || !((RecyclerView.LayoutManager)this).isAttachedToWindow()) {
            return false;
        }
        int n;
        int n2;
        if (this.mShouldReverseLayout) {
            n = this.getLastChildPosition();
            n2 = this.getFirstChildPosition();
        }
        else {
            n = this.getFirstChildPosition();
            n2 = this.getLastChildPosition();
        }
        if (n == 0 && this.hasGapsToFix() != null) {
            this.mLazySpanLookup.clear();
            ((RecyclerView.LayoutManager)this).requestSimpleAnimationsInNextLayout();
            ((RecyclerView.LayoutManager)this).requestLayout();
            return true;
        }
        if (!this.mLaidOutInvalidFullSpan) {
            return false;
        }
        int n3;
        if (this.mShouldReverseLayout) {
            n3 = -1;
        }
        else {
            n3 = 1;
        }
        final FullSpanItem firstFullSpanItemInRange = this.mLazySpanLookup.getFirstFullSpanItemInRange(n, n2 + 1, n3, true);
        if (firstFullSpanItemInRange == null) {
            this.mLaidOutInvalidFullSpan = false;
            this.mLazySpanLookup.forceInvalidateAfter(n2 + 1);
            return false;
        }
        final FullSpanItem firstFullSpanItemInRange2 = this.mLazySpanLookup.getFirstFullSpanItemInRange(n, firstFullSpanItemInRange.mPosition, n3 * -1, true);
        if (firstFullSpanItemInRange2 == null) {
            this.mLazySpanLookup.forceInvalidateAfter(firstFullSpanItemInRange.mPosition);
        }
        else {
            this.mLazySpanLookup.forceInvalidateAfter(1 + firstFullSpanItemInRange2.mPosition);
        }
        ((RecyclerView.LayoutManager)this).requestSimpleAnimationsInNextLayout();
        ((RecyclerView.LayoutManager)this).requestLayout();
        return true;
    }
    
    private boolean checkSpanForGap(final Span span) {
        if (this.mShouldReverseLayout) {
            if (span.getEndLine() >= this.mPrimaryOrientation.getEndAfterPadding()) {
                return false;
            }
            if (span.getLayoutParams(span.mViews.get(-1 + span.mViews.size())).mFullSpan) {
                return false;
            }
        }
        else {
            if (span.getStartLine() <= this.mPrimaryOrientation.getStartAfterPadding()) {
                return false;
            }
            if (span.getLayoutParams(span.mViews.get(0)).mFullSpan) {
                return false;
            }
        }
        return true;
    }
    
    private int computeScrollExtent(final State state) {
        if (((RecyclerView.LayoutManager)this).getChildCount() == 0) {
            return 0;
        }
        this.ensureOrientationHelper();
        final OrientationHelper mPrimaryOrientation = this.mPrimaryOrientation;
        final View firstVisibleItemClosestToStart = this.findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled, true);
        final boolean mSmoothScrollbarEnabled = this.mSmoothScrollbarEnabled;
        boolean b = false;
        if (!mSmoothScrollbarEnabled) {
            b = true;
        }
        return ScrollbarHelper.computeScrollExtent(state, mPrimaryOrientation, firstVisibleItemClosestToStart, this.findFirstVisibleItemClosestToEnd(b, true), this, this.mSmoothScrollbarEnabled);
    }
    
    private int computeScrollOffset(final State state) {
        if (((RecyclerView.LayoutManager)this).getChildCount() == 0) {
            return 0;
        }
        this.ensureOrientationHelper();
        final OrientationHelper mPrimaryOrientation = this.mPrimaryOrientation;
        final View firstVisibleItemClosestToStart = this.findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled, true);
        final boolean mSmoothScrollbarEnabled = this.mSmoothScrollbarEnabled;
        boolean b = false;
        if (!mSmoothScrollbarEnabled) {
            b = true;
        }
        return ScrollbarHelper.computeScrollOffset(state, mPrimaryOrientation, firstVisibleItemClosestToStart, this.findFirstVisibleItemClosestToEnd(b, true), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }
    
    private int computeScrollRange(final State state) {
        if (((RecyclerView.LayoutManager)this).getChildCount() == 0) {
            return 0;
        }
        this.ensureOrientationHelper();
        final OrientationHelper mPrimaryOrientation = this.mPrimaryOrientation;
        final View firstVisibleItemClosestToStart = this.findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled, true);
        final boolean mSmoothScrollbarEnabled = this.mSmoothScrollbarEnabled;
        boolean b = false;
        if (!mSmoothScrollbarEnabled) {
            b = true;
        }
        return ScrollbarHelper.computeScrollRange(state, mPrimaryOrientation, firstVisibleItemClosestToStart, this.findFirstVisibleItemClosestToEnd(b, true), this, this.mSmoothScrollbarEnabled);
    }
    
    private int convertFocusDirectionToLayoutDirection(final int n) {
        int n2 = -1;
        int n3 = 1;
        int n4 = Integer.MIN_VALUE;
        switch (n) {
            default: {
                n2 = n4;
                return n2;
            }
            case 1: {
                return n2;
            }
            case 2: {
                return n3;
            }
            case 33: {
                if (this.mOrientation != n3) {
                    return n4;
                }
                return n2;
            }
            case 130: {
                if (this.mOrientation == n3) {
                    n4 = n3;
                }
                return n4;
            }
            case 17: {
                if (this.mOrientation != 0) {
                    return n4;
                }
                return n2;
            }
            case 66: {
                if (this.mOrientation != 0) {
                    n3 = n4;
                }
                return n3;
            }
        }
    }
    
    private FullSpanItem createFullSpanItemFromEnd(final int n) {
        final FullSpanItem fullSpanItem = new FullSpanItem();
        fullSpanItem.mGapPerSpan = new int[this.mSpanCount];
        for (int i = 0; i < this.mSpanCount; ++i) {
            fullSpanItem.mGapPerSpan[i] = n - this.mSpans[i].getEndLine(n);
        }
        return fullSpanItem;
    }
    
    private FullSpanItem createFullSpanItemFromStart(final int n) {
        final FullSpanItem fullSpanItem = new FullSpanItem();
        fullSpanItem.mGapPerSpan = new int[this.mSpanCount];
        for (int i = 0; i < this.mSpanCount; ++i) {
            fullSpanItem.mGapPerSpan[i] = this.mSpans[i].getStartLine(n) - n;
        }
        return fullSpanItem;
    }
    
    private void ensureOrientationHelper() {
        if (this.mPrimaryOrientation == null) {
            this.mPrimaryOrientation = OrientationHelper.createOrientationHelper(this, this.mOrientation);
            this.mSecondaryOrientation = OrientationHelper.createOrientationHelper(this, 1 - this.mOrientation);
            this.mLayoutState = new LayoutState();
        }
    }
    
    private int fill(final Recycler recycler, final LayoutState layoutState, final State state) {
        this.mRemainingSpans.set(0, this.mSpanCount, true);
        int n;
        if (this.mLayoutState.mInfinite) {
            if (layoutState.mLayoutDirection == 1) {
                n = Integer.MAX_VALUE;
            }
            else {
                n = Integer.MIN_VALUE;
            }
        }
        else if (layoutState.mLayoutDirection == 1) {
            n = layoutState.mEndLine + layoutState.mAvailable;
        }
        else {
            n = layoutState.mStartLine - layoutState.mAvailable;
        }
        this.updateAllRemainingSpans(layoutState.mLayoutDirection, n);
        int n2;
        if (this.mShouldReverseLayout) {
            n2 = this.mPrimaryOrientation.getEndAfterPadding();
        }
        else {
            n2 = this.mPrimaryOrientation.getStartAfterPadding();
        }
        boolean b = false;
        while (layoutState.hasMore(state) && (this.mLayoutState.mInfinite || !this.mRemainingSpans.isEmpty())) {
            final View next = layoutState.next(recycler);
            final LayoutParams layoutParams = (LayoutParams)next.getLayoutParams();
            final int viewLayoutPosition = ((RecyclerView.LayoutParams)layoutParams).getViewLayoutPosition();
            final int span = this.mLazySpanLookup.getSpan(viewLayoutPosition);
            int n3;
            if (span == -1) {
                n3 = 1;
            }
            else {
                n3 = 0;
            }
            Span nextSpan;
            if (n3 != 0) {
                if (layoutParams.mFullSpan) {
                    nextSpan = this.mSpans[0];
                }
                else {
                    nextSpan = this.getNextSpan(layoutState);
                }
                this.mLazySpanLookup.setSpan(viewLayoutPosition, nextSpan);
            }
            else {
                nextSpan = this.mSpans[span];
            }
            layoutParams.mSpan = nextSpan;
            if (layoutState.mLayoutDirection == 1) {
                ((RecyclerView.LayoutManager)this).addView(next);
            }
            else {
                ((RecyclerView.LayoutManager)this).addView(next, 0);
            }
            this.measureChildWithDecorationsAndMargin(next, layoutParams, false);
            int n4;
            int n5;
            if (layoutState.mLayoutDirection == 1) {
                if (layoutParams.mFullSpan) {
                    n4 = this.getMaxEnd(n2);
                }
                else {
                    n4 = nextSpan.getEndLine(n2);
                }
                n5 = n4 + this.mPrimaryOrientation.getDecoratedMeasurement(next);
                if (n3 != 0 && layoutParams.mFullSpan) {
                    final FullSpanItem fullSpanItemFromEnd = this.createFullSpanItemFromEnd(n4);
                    fullSpanItemFromEnd.mGapDir = -1;
                    fullSpanItemFromEnd.mPosition = viewLayoutPosition;
                    this.mLazySpanLookup.addFullSpanItem(fullSpanItemFromEnd);
                }
            }
            else {
                if (layoutParams.mFullSpan) {
                    n5 = this.getMinStart(n2);
                }
                else {
                    n5 = nextSpan.getStartLine(n2);
                }
                n4 = n5 - this.mPrimaryOrientation.getDecoratedMeasurement(next);
                if (n3 != 0 && layoutParams.mFullSpan) {
                    final FullSpanItem fullSpanItemFromStart = this.createFullSpanItemFromStart(n5);
                    fullSpanItemFromStart.mGapDir = 1;
                    fullSpanItemFromStart.mPosition = viewLayoutPosition;
                    this.mLazySpanLookup.addFullSpanItem(fullSpanItemFromStart);
                }
            }
            if (layoutParams.mFullSpan && layoutState.mItemDirection == -1) {
                if (n3 != 0) {
                    this.mLaidOutInvalidFullSpan = true;
                }
                else {
                    int n6;
                    if (layoutState.mLayoutDirection == 1) {
                        if (!this.areAllEndsEqual()) {
                            n6 = 1;
                        }
                        else {
                            n6 = 0;
                        }
                    }
                    else if (!this.areAllStartsEqual()) {
                        n6 = 1;
                    }
                    else {
                        n6 = 0;
                    }
                    if (n6 != 0) {
                        final FullSpanItem fullSpanItem = this.mLazySpanLookup.getFullSpanItem(viewLayoutPosition);
                        if (fullSpanItem != null) {
                            fullSpanItem.mHasUnwantedGapAfter = true;
                        }
                        this.mLaidOutInvalidFullSpan = true;
                    }
                }
            }
            this.attachViewToSpans(next, layoutParams, layoutState);
            int endAfterPadding;
            int startAfterPadding;
            if (this.isLayoutRTL() && this.mOrientation == 1) {
                if (layoutParams.mFullSpan) {
                    endAfterPadding = this.mSecondaryOrientation.getEndAfterPadding();
                }
                else {
                    endAfterPadding = this.mSecondaryOrientation.getEndAfterPadding() - (-1 + this.mSpanCount - nextSpan.mIndex) * this.mSizePerSpan;
                }
                startAfterPadding = endAfterPadding - this.mSecondaryOrientation.getDecoratedMeasurement(next);
            }
            else {
                if (layoutParams.mFullSpan) {
                    startAfterPadding = this.mSecondaryOrientation.getStartAfterPadding();
                }
                else {
                    startAfterPadding = nextSpan.mIndex * this.mSizePerSpan + this.mSecondaryOrientation.getStartAfterPadding();
                }
                endAfterPadding = startAfterPadding + this.mSecondaryOrientation.getDecoratedMeasurement(next);
            }
            if (this.mOrientation == 1) {
                this.layoutDecoratedWithMargins(next, startAfterPadding, n4, endAfterPadding, n5);
            }
            else {
                this.layoutDecoratedWithMargins(next, n4, startAfterPadding, n5, endAfterPadding);
            }
            if (layoutParams.mFullSpan) {
                this.updateAllRemainingSpans(this.mLayoutState.mLayoutDirection, n);
            }
            else {
                this.updateRemainingSpans(nextSpan, this.mLayoutState.mLayoutDirection, n);
            }
            this.recycle(recycler, this.mLayoutState);
            if (this.mLayoutState.mStopInFocusable && next.isFocusable()) {
                if (layoutParams.mFullSpan) {
                    this.mRemainingSpans.clear();
                }
                else {
                    this.mRemainingSpans.set(nextSpan.mIndex, false);
                }
            }
            b = true;
        }
        if (!b) {
            this.recycle(recycler, this.mLayoutState);
        }
        int n7;
        if (this.mLayoutState.mLayoutDirection == -1) {
            n7 = this.mPrimaryOrientation.getStartAfterPadding() - this.getMinStart(this.mPrimaryOrientation.getStartAfterPadding());
        }
        else {
            n7 = this.getMaxEnd(this.mPrimaryOrientation.getEndAfterPadding()) - this.mPrimaryOrientation.getEndAfterPadding();
        }
        if (n7 > 0) {
            return Math.min(layoutState.mAvailable, n7);
        }
        return 0;
    }
    
    private int findFirstReferenceChildPosition(final int n) {
        for (int childCount = ((RecyclerView.LayoutManager)this).getChildCount(), i = 0; i < childCount; ++i) {
            final int position = ((RecyclerView.LayoutManager)this).getPosition(((RecyclerView.LayoutManager)this).getChildAt(i));
            if (position >= 0 && position < n) {
                return position;
            }
        }
        return 0;
    }
    
    private int findLastReferenceChildPosition(final int n) {
        for (int i = -1 + ((RecyclerView.LayoutManager)this).getChildCount(); i >= 0; --i) {
            final int position = ((RecyclerView.LayoutManager)this).getPosition(((RecyclerView.LayoutManager)this).getChildAt(i));
            if (position >= 0 && position < n) {
                return position;
            }
        }
        return 0;
    }
    
    private void fixEndGap(final Recycler recycler, final State state, final boolean b) {
        final int maxEnd = this.getMaxEnd(Integer.MIN_VALUE);
        if (maxEnd != Integer.MIN_VALUE) {
            final int n = this.mPrimaryOrientation.getEndAfterPadding() - maxEnd;
            if (n > 0) {
                final int n2 = n - -this.scrollBy(-n, recycler, state);
                if (b && n2 > 0) {
                    this.mPrimaryOrientation.offsetChildren(n2);
                }
            }
        }
    }
    
    private void fixStartGap(final Recycler recycler, final State state, final boolean b) {
        final int minStart = this.getMinStart(Integer.MAX_VALUE);
        if (minStart != Integer.MAX_VALUE) {
            final int n = minStart - this.mPrimaryOrientation.getStartAfterPadding();
            if (n > 0) {
                final int n2 = n - this.scrollBy(n, recycler, state);
                if (b && n2 > 0) {
                    this.mPrimaryOrientation.offsetChildren(-n2);
                }
            }
        }
    }
    
    private int getFirstChildPosition() {
        if (((RecyclerView.LayoutManager)this).getChildCount() == 0) {
            return 0;
        }
        return ((RecyclerView.LayoutManager)this).getPosition(((RecyclerView.LayoutManager)this).getChildAt(0));
    }
    
    private int getLastChildPosition() {
        final int childCount = ((RecyclerView.LayoutManager)this).getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return ((RecyclerView.LayoutManager)this).getPosition(((RecyclerView.LayoutManager)this).getChildAt(childCount - 1));
    }
    
    private int getMaxEnd(final int n) {
        int endLine = this.mSpans[0].getEndLine(n);
        for (int i = 1; i < this.mSpanCount; ++i) {
            final int endLine2 = this.mSpans[i].getEndLine(n);
            if (endLine2 > endLine) {
                endLine = endLine2;
            }
        }
        return endLine;
    }
    
    private int getMaxStart(final int n) {
        int startLine = this.mSpans[0].getStartLine(n);
        for (int i = 1; i < this.mSpanCount; ++i) {
            final int startLine2 = this.mSpans[i].getStartLine(n);
            if (startLine2 > startLine) {
                startLine = startLine2;
            }
        }
        return startLine;
    }
    
    private int getMinEnd(final int n) {
        int endLine = this.mSpans[0].getEndLine(n);
        for (int i = 1; i < this.mSpanCount; ++i) {
            final int endLine2 = this.mSpans[i].getEndLine(n);
            if (endLine2 < endLine) {
                endLine = endLine2;
            }
        }
        return endLine;
    }
    
    private int getMinStart(final int n) {
        int startLine = this.mSpans[0].getStartLine(n);
        for (int i = 1; i < this.mSpanCount; ++i) {
            final int startLine2 = this.mSpans[i].getStartLine(n);
            if (startLine2 < startLine) {
                startLine = startLine2;
            }
        }
        return startLine;
    }
    
    private Span getNextSpan(final LayoutState layoutState) {
        int n;
        int mSpanCount;
        int n2;
        if (this.preferLastSpan(layoutState.mLayoutDirection)) {
            n = -1 + this.mSpanCount;
            mSpanCount = -1;
            n2 = -1;
        }
        else {
            mSpanCount = this.mSpanCount;
            n2 = 1;
            n = 0;
        }
        Span span;
        if (layoutState.mLayoutDirection == 1) {
            span = null;
            int n3 = Integer.MAX_VALUE;
            final int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
            for (int i = n; i != mSpanCount; i += n2) {
                final Span span2 = this.mSpans[i];
                final int endLine = span2.getEndLine(startAfterPadding);
                if (endLine < n3) {
                    span = span2;
                    n3 = endLine;
                }
            }
        }
        else {
            Span span3 = null;
            int n4 = Integer.MIN_VALUE;
            final int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
            for (int j = n; j != mSpanCount; j += n2) {
                final Span span4 = this.mSpans[j];
                final int startLine = span4.getStartLine(endAfterPadding);
                if (startLine > n4) {
                    span3 = span4;
                    n4 = startLine;
                }
            }
            span = span3;
        }
        return span;
    }
    
    private void handleUpdate(final int n, final int n2, final int n3) {
        int n4;
        if (this.mShouldReverseLayout) {
            n4 = this.getLastChildPosition();
        }
        else {
            n4 = this.getFirstChildPosition();
        }
        int n5;
        int n6;
        if (n3 == 8) {
            if (n < n2) {
                n5 = n2 + 1;
                n6 = n;
            }
            else {
                n5 = n + 1;
                n6 = n2;
            }
        }
        else {
            n6 = n;
            n5 = n + n2;
        }
        this.mLazySpanLookup.invalidateAfter(n6);
        switch (n3) {
            case 1: {
                this.mLazySpanLookup.offsetForAddition(n, n2);
                break;
            }
            case 2: {
                this.mLazySpanLookup.offsetForRemoval(n, n2);
                break;
            }
            case 8: {
                this.mLazySpanLookup.offsetForRemoval(n, 1);
                this.mLazySpanLookup.offsetForAddition(n2, 1);
                break;
            }
        }
        if (n5 > n4) {
            int n7;
            if (this.mShouldReverseLayout) {
                n7 = this.getFirstChildPosition();
            }
            else {
                n7 = this.getLastChildPosition();
            }
            if (n6 <= n7) {
                ((RecyclerView.LayoutManager)this).requestLayout();
            }
        }
    }
    
    private void layoutDecoratedWithMargins(final View view, final int n, final int n2, final int n3, final int n4) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        ((RecyclerView.LayoutManager)this).layoutDecorated(view, n + layoutParams.leftMargin, n2 + layoutParams.topMargin, n3 - layoutParams.rightMargin, n4 - layoutParams.bottomMargin);
    }
    
    private void measureChildWithDecorationsAndMargin(final View view, final int n, final int n2, final boolean b) {
        ((RecyclerView.LayoutManager)this).calculateItemDecorationsForChild(view, this.mTmpRect);
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final int updateSpecWithExtra = this.updateSpecWithExtra(n, layoutParams.leftMargin + this.mTmpRect.left, layoutParams.rightMargin + this.mTmpRect.right);
        final int updateSpecWithExtra2 = this.updateSpecWithExtra(n2, layoutParams.topMargin + this.mTmpRect.top, layoutParams.bottomMargin + this.mTmpRect.bottom);
        boolean b2;
        if (b) {
            b2 = ((RecyclerView.LayoutManager)this).shouldReMeasureChild(view, updateSpecWithExtra, updateSpecWithExtra2, layoutParams);
        }
        else {
            b2 = ((RecyclerView.LayoutManager)this).shouldMeasureChild(view, updateSpecWithExtra, updateSpecWithExtra2, layoutParams);
        }
        if (b2) {
            view.measure(updateSpecWithExtra, updateSpecWithExtra2);
        }
    }
    
    private void measureChildWithDecorationsAndMargin(final View view, final LayoutParams layoutParams, final boolean b) {
        if (layoutParams.mFullSpan) {
            if (this.mOrientation == 1) {
                this.measureChildWithDecorationsAndMargin(view, this.mFullSizeSpec, RecyclerView.LayoutManager.getChildMeasureSpec(((RecyclerView.LayoutManager)this).getHeight(), ((RecyclerView.LayoutManager)this).getHeightMode(), 0, layoutParams.height, true), b);
                return;
            }
            this.measureChildWithDecorationsAndMargin(view, RecyclerView.LayoutManager.getChildMeasureSpec(((RecyclerView.LayoutManager)this).getWidth(), ((RecyclerView.LayoutManager)this).getWidthMode(), 0, layoutParams.width, true), this.mFullSizeSpec, b);
        }
        else {
            if (this.mOrientation == 1) {
                this.measureChildWithDecorationsAndMargin(view, RecyclerView.LayoutManager.getChildMeasureSpec(this.mSizePerSpan, ((RecyclerView.LayoutManager)this).getWidthMode(), 0, layoutParams.width, false), RecyclerView.LayoutManager.getChildMeasureSpec(((RecyclerView.LayoutManager)this).getHeight(), ((RecyclerView.LayoutManager)this).getHeightMode(), 0, layoutParams.height, true), b);
                return;
            }
            this.measureChildWithDecorationsAndMargin(view, RecyclerView.LayoutManager.getChildMeasureSpec(((RecyclerView.LayoutManager)this).getWidth(), ((RecyclerView.LayoutManager)this).getWidthMode(), 0, layoutParams.width, true), RecyclerView.LayoutManager.getChildMeasureSpec(this.mSizePerSpan, ((RecyclerView.LayoutManager)this).getHeightMode(), 0, layoutParams.height, false), b);
        }
    }
    
    private void onLayoutChildren(final Recycler recycler, final State state, final boolean b) {
        int layoutStateDirection = 1;
        this.ensureOrientationHelper();
        final AnchorInfo mAnchorInfo = this.mAnchorInfo;
        mAnchorInfo.reset();
        if ((this.mPendingSavedState != null || this.mPendingScrollPosition != -1) && state.getItemCount() == 0) {
            ((RecyclerView.LayoutManager)this).removeAndRecycleAllViews(recycler);
        }
        else {
            if (this.mPendingSavedState != null) {
                this.applyPendingSavedState(mAnchorInfo);
            }
            else {
                this.resolveShouldLayoutReverse();
                mAnchorInfo.mLayoutFromEnd = this.mShouldReverseLayout;
            }
            this.updateAnchorInfoForLayout(state, mAnchorInfo);
            if (this.mPendingSavedState == null && (mAnchorInfo.mLayoutFromEnd != this.mLastLayoutFromEnd || this.isLayoutRTL() != this.mLastLayoutRTL)) {
                this.mLazySpanLookup.clear();
                mAnchorInfo.mInvalidateOffsets = (layoutStateDirection != 0);
            }
            if (((RecyclerView.LayoutManager)this).getChildCount() > 0 && (this.mPendingSavedState == null || this.mPendingSavedState.mSpanOffsetsSize < layoutStateDirection)) {
                if (mAnchorInfo.mInvalidateOffsets) {
                    for (int i = 0; i < this.mSpanCount; ++i) {
                        this.mSpans[i].clear();
                        if (mAnchorInfo.mOffset != Integer.MIN_VALUE) {
                            this.mSpans[i].setLine(mAnchorInfo.mOffset);
                        }
                    }
                }
                else {
                    for (int j = 0; j < this.mSpanCount; ++j) {
                        this.mSpans[j].cacheReferenceLineAndClear(this.mShouldReverseLayout, mAnchorInfo.mOffset);
                    }
                }
            }
            ((RecyclerView.LayoutManager)this).detachAndScrapAttachedViews(recycler);
            this.mLayoutState.mRecycle = false;
            this.mLaidOutInvalidFullSpan = false;
            this.updateMeasureSpecs(this.mSecondaryOrientation.getTotalSpace());
            this.updateLayoutState(mAnchorInfo.mPosition, state);
            if (mAnchorInfo.mLayoutFromEnd) {
                this.setLayoutStateDirection(-1);
                this.fill(recycler, this.mLayoutState, state);
                this.setLayoutStateDirection(layoutStateDirection);
                this.mLayoutState.mCurrentPosition = mAnchorInfo.mPosition + this.mLayoutState.mItemDirection;
                this.fill(recycler, this.mLayoutState, state);
            }
            else {
                this.setLayoutStateDirection(layoutStateDirection);
                this.fill(recycler, this.mLayoutState, state);
                this.setLayoutStateDirection(-1);
                this.mLayoutState.mCurrentPosition = mAnchorInfo.mPosition + this.mLayoutState.mItemDirection;
                this.fill(recycler, this.mLayoutState, state);
            }
            this.repositionToWrapContentIfNecessary();
            if (((RecyclerView.LayoutManager)this).getChildCount() > 0) {
                if (this.mShouldReverseLayout) {
                    this.fixEndGap(recycler, state, layoutStateDirection != 0);
                    this.fixStartGap(recycler, state, false);
                }
                else {
                    this.fixStartGap(recycler, state, layoutStateDirection != 0);
                    this.fixEndGap(recycler, state, false);
                }
            }
            boolean b2 = false;
            if (b) {
                final boolean preLayout = state.isPreLayout();
                b2 = false;
                if (!preLayout) {
                    if (this.mGapStrategy == 0 || ((RecyclerView.LayoutManager)this).getChildCount() <= 0 || (!this.mLaidOutInvalidFullSpan && this.hasGapsToFix() == null)) {
                        layoutStateDirection = 0;
                    }
                    b2 = false;
                    if (layoutStateDirection != 0) {
                        ((RecyclerView.LayoutManager)this).removeCallbacks(this.mCheckForGapsRunnable);
                        final boolean checkForGaps = this.checkForGaps();
                        b2 = false;
                        if (checkForGaps) {
                            b2 = true;
                        }
                    }
                    this.mPendingScrollPosition = -1;
                    this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
                }
            }
            this.mLastLayoutFromEnd = mAnchorInfo.mLayoutFromEnd;
            this.mLastLayoutRTL = this.isLayoutRTL();
            this.mPendingSavedState = null;
            if (b2) {
                this.onLayoutChildren(recycler, state, false);
            }
        }
    }
    
    private boolean preferLastSpan(final int n) {
        if (this.mOrientation == 0) {
            if (n == -1 == this.mShouldReverseLayout) {
                return false;
            }
        }
        else if (n == -1 == this.mShouldReverseLayout != this.isLayoutRTL()) {
            return false;
        }
        return true;
    }
    
    private void prependViewToAllSpans(final View view) {
        for (int i = -1 + this.mSpanCount; i >= 0; --i) {
            this.mSpans[i].prependToSpan(view);
        }
    }
    
    private void recycle(final Recycler recycler, final LayoutState layoutState) {
        if (!layoutState.mRecycle || layoutState.mInfinite) {
            return;
        }
        if (layoutState.mAvailable == 0) {
            if (layoutState.mLayoutDirection == -1) {
                this.recycleFromEnd(recycler, layoutState.mEndLine);
                return;
            }
            this.recycleFromStart(recycler, layoutState.mStartLine);
        }
        else {
            if (layoutState.mLayoutDirection == -1) {
                final int n = layoutState.mStartLine - this.getMaxStart(layoutState.mStartLine);
                int mEndLine;
                if (n < 0) {
                    mEndLine = layoutState.mEndLine;
                }
                else {
                    mEndLine = layoutState.mEndLine - Math.min(n, layoutState.mAvailable);
                }
                this.recycleFromEnd(recycler, mEndLine);
                return;
            }
            final int n2 = this.getMinEnd(layoutState.mEndLine) - layoutState.mEndLine;
            int mStartLine;
            if (n2 < 0) {
                mStartLine = layoutState.mStartLine;
            }
            else {
                mStartLine = layoutState.mStartLine + Math.min(n2, layoutState.mAvailable);
            }
            this.recycleFromStart(recycler, mStartLine);
        }
    }
    
    private void recycleFromEnd(final Recycler recycler, final int n) {
    Label_0078:
        for (int i = -1 + ((RecyclerView.LayoutManager)this).getChildCount(); i >= 0; --i) {
            final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
            if (this.mPrimaryOrientation.getDecoratedStart(child) < n) {
                break;
            }
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (layoutParams.mFullSpan) {
                for (int j = 0; j < this.mSpanCount; ++j) {
                    if (this.mSpans[j].mViews.size() == 1) {
                        break Label_0078;
                    }
                }
                for (int k = 0; k < this.mSpanCount; ++k) {
                    this.mSpans[k].popEnd();
                }
            }
            else {
                if (layoutParams.mSpan.mViews.size() == 1) {
                    break;
                }
                layoutParams.mSpan.popEnd();
            }
            ((RecyclerView.LayoutManager)this).removeAndRecycleView(child, recycler);
        }
    }
    
    private void recycleFromStart(final Recycler recycler, final int n) {
    Label_0071:
        while (((RecyclerView.LayoutManager)this).getChildCount() > 0) {
            final View child = ((RecyclerView.LayoutManager)this).getChildAt(0);
            if (this.mPrimaryOrientation.getDecoratedEnd(child) > n) {
                break;
            }
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (layoutParams.mFullSpan) {
                for (int i = 0; i < this.mSpanCount; ++i) {
                    if (this.mSpans[i].mViews.size() == 1) {
                        break Label_0071;
                    }
                }
                for (int j = 0; j < this.mSpanCount; ++j) {
                    this.mSpans[j].popStart();
                }
            }
            else {
                if (layoutParams.mSpan.mViews.size() == 1) {
                    break;
                }
                layoutParams.mSpan.popStart();
            }
            ((RecyclerView.LayoutManager)this).removeAndRecycleView(child, recycler);
        }
    }
    
    private void repositionToWrapContentIfNecessary() {
        if (this.mSecondaryOrientation.getMode() != 1073741824) {
            float max = 0.0f;
            final int childCount = ((RecyclerView.LayoutManager)this).getChildCount();
            for (int i = 0; i < childCount; ++i) {
                final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
                float n = this.mSecondaryOrientation.getDecoratedMeasurement(child);
                if (n >= max) {
                    if (((LayoutParams)child.getLayoutParams()).isFullSpan()) {
                        n = 1.0f * n / this.mSpanCount;
                    }
                    max = Math.max(max, n);
                }
            }
            final int mSizePerSpan = this.mSizePerSpan;
            int n2 = Math.round(max * this.mSpanCount);
            if (this.mSecondaryOrientation.getMode() == Integer.MIN_VALUE) {
                n2 = Math.min(n2, this.mSecondaryOrientation.getTotalSpace());
            }
            this.updateMeasureSpecs(n2);
            if (this.mSizePerSpan != mSizePerSpan) {
                for (int j = 0; j < childCount; ++j) {
                    final View child2 = ((RecyclerView.LayoutManager)this).getChildAt(j);
                    final LayoutParams layoutParams = (LayoutParams)child2.getLayoutParams();
                    if (!layoutParams.mFullSpan) {
                        if (this.isLayoutRTL() && this.mOrientation == 1) {
                            child2.offsetLeftAndRight(-(-1 + this.mSpanCount - layoutParams.mSpan.mIndex) * this.mSizePerSpan - mSizePerSpan * -(-1 + this.mSpanCount - layoutParams.mSpan.mIndex));
                        }
                        else {
                            final int n3 = layoutParams.mSpan.mIndex * this.mSizePerSpan;
                            final int n4 = mSizePerSpan * layoutParams.mSpan.mIndex;
                            if (this.mOrientation == 1) {
                                child2.offsetLeftAndRight(n3 - n4);
                            }
                            else {
                                child2.offsetTopAndBottom(n3 - n4);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void resolveShouldLayoutReverse() {
        boolean mShouldReverseLayout = true;
        if (this.mOrientation == (mShouldReverseLayout ? 1 : 0) || !this.isLayoutRTL()) {
            this.mShouldReverseLayout = this.mReverseLayout;
            return;
        }
        if (this.mReverseLayout) {
            mShouldReverseLayout = false;
        }
        this.mShouldReverseLayout = mShouldReverseLayout;
    }
    
    private void setLayoutStateDirection(final int mLayoutDirection) {
        int mItemDirection = 1;
        this.mLayoutState.mLayoutDirection = mLayoutDirection;
        final LayoutState mLayoutState = this.mLayoutState;
        if (this.mShouldReverseLayout != (mLayoutDirection == -1 && mItemDirection)) {
            mItemDirection = -1;
        }
        mLayoutState.mItemDirection = mItemDirection;
    }
    
    private void updateAllRemainingSpans(final int n, final int n2) {
        for (int i = 0; i < this.mSpanCount; ++i) {
            if (!this.mSpans[i].mViews.isEmpty()) {
                this.updateRemainingSpans(this.mSpans[i], n, n2);
            }
        }
    }
    
    private boolean updateAnchorFromChildren(final State state, final AnchorInfo anchorInfo) {
        int mPosition;
        if (this.mLastLayoutFromEnd) {
            mPosition = this.findLastReferenceChildPosition(state.getItemCount());
        }
        else {
            mPosition = this.findFirstReferenceChildPosition(state.getItemCount());
        }
        anchorInfo.mPosition = mPosition;
        anchorInfo.mOffset = Integer.MIN_VALUE;
        return true;
    }
    
    private void updateLayoutState(final int mCurrentPosition, final State state) {
        boolean b = true;
        this.mLayoutState.mAvailable = 0;
        this.mLayoutState.mCurrentPosition = mCurrentPosition;
        final boolean smoothScrolling = ((RecyclerView.LayoutManager)this).isSmoothScrolling();
        int totalSpace = 0;
        int totalSpace2 = 0;
        if (smoothScrolling) {
            final int targetScrollPosition = state.getTargetScrollPosition();
            totalSpace = 0;
            totalSpace2 = 0;
            if (targetScrollPosition != -1) {
                if (this.mShouldReverseLayout == (targetScrollPosition < mCurrentPosition && b)) {
                    totalSpace = this.mPrimaryOrientation.getTotalSpace();
                }
                else {
                    totalSpace2 = this.mPrimaryOrientation.getTotalSpace();
                    totalSpace = 0;
                }
            }
        }
        if (((RecyclerView.LayoutManager)this).getClipToPadding()) {
            this.mLayoutState.mStartLine = this.mPrimaryOrientation.getStartAfterPadding() - totalSpace2;
            this.mLayoutState.mEndLine = totalSpace + this.mPrimaryOrientation.getEndAfterPadding();
        }
        else {
            this.mLayoutState.mEndLine = totalSpace + this.mPrimaryOrientation.getEnd();
            this.mLayoutState.mStartLine = -totalSpace2;
        }
        this.mLayoutState.mStopInFocusable = false;
        this.mLayoutState.mRecycle = b;
        final LayoutState mLayoutState = this.mLayoutState;
        if (this.mPrimaryOrientation.getMode() != 0) {
            b = false;
        }
        mLayoutState.mInfinite = b;
    }
    
    private void updateRemainingSpans(final Span span, final int n, final int n2) {
        final int deletedSize = span.getDeletedSize();
        if (n == -1) {
            if (deletedSize + span.getStartLine() <= n2) {
                this.mRemainingSpans.set(span.mIndex, false);
            }
        }
        else if (span.getEndLine() - deletedSize >= n2) {
            this.mRemainingSpans.set(span.mIndex, false);
        }
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
    
    boolean areAllEndsEqual() {
        final int endLine = this.mSpans[0].getEndLine(Integer.MIN_VALUE);
        for (int i = 1; i < this.mSpanCount; ++i) {
            if (this.mSpans[i].getEndLine(Integer.MIN_VALUE) != endLine) {
                return false;
            }
        }
        return true;
    }
    
    boolean areAllStartsEqual() {
        final int startLine = this.mSpans[0].getStartLine(Integer.MIN_VALUE);
        for (int i = 1; i < this.mSpanCount; ++i) {
            if (this.mSpans[i].getStartLine(Integer.MIN_VALUE) != startLine) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public void assertNotInLayoutOrScroll(final String s) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(s);
        }
    }
    
    @Override
    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }
    
    @Override
    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }
    
    @Override
    public boolean checkLayoutParams(final RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }
    
    @Override
    public int computeHorizontalScrollExtent(final State state) {
        return this.computeScrollExtent(state);
    }
    
    @Override
    public int computeHorizontalScrollOffset(final State state) {
        return this.computeScrollOffset(state);
    }
    
    @Override
    public int computeHorizontalScrollRange(final State state) {
        return this.computeScrollRange(state);
    }
    
    @Override
    public int computeVerticalScrollExtent(final State state) {
        return this.computeScrollExtent(state);
    }
    
    @Override
    public int computeVerticalScrollOffset(final State state) {
        return this.computeScrollOffset(state);
    }
    
    @Override
    public int computeVerticalScrollRange(final State state) {
        return this.computeScrollRange(state);
    }
    
    public int[] findFirstCompletelyVisibleItemPositions(int[] array) {
        if (array == null) {
            array = new int[this.mSpanCount];
        }
        else if (array.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + array.length);
        }
        for (int i = 0; i < this.mSpanCount; ++i) {
            array[i] = this.mSpans[i].findFirstCompletelyVisibleItemPosition();
        }
        return array;
    }
    
    View findFirstVisibleItemClosestToEnd(final boolean b, final boolean b2) {
        this.ensureOrientationHelper();
        final int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        final int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        View view = null;
        for (int i = -1 + ((RecyclerView.LayoutManager)this).getChildCount(); i >= 0; --i) {
            final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
            final int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(child);
            final int decoratedEnd = this.mPrimaryOrientation.getDecoratedEnd(child);
            if (decoratedEnd > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedEnd <= endAfterPadding || !b) {
                    return child;
                }
                if (b2 && view == null) {
                    view = child;
                }
            }
        }
        return view;
    }
    
    View findFirstVisibleItemClosestToStart(final boolean b, final boolean b2) {
        this.ensureOrientationHelper();
        final int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        final int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        final int childCount = ((RecyclerView.LayoutManager)this).getChildCount();
        View view = null;
        for (int i = 0; i < childCount; ++i) {
            final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
            final int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(child);
            if (this.mPrimaryOrientation.getDecoratedEnd(child) > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedStart >= startAfterPadding || !b) {
                    return child;
                }
                if (b2 && view == null) {
                    view = child;
                }
            }
        }
        return view;
    }
    
    int findFirstVisibleItemPositionInt() {
        View view;
        if (this.mShouldReverseLayout) {
            view = this.findFirstVisibleItemClosestToEnd(true, true);
        }
        else {
            view = this.findFirstVisibleItemClosestToStart(true, true);
        }
        if (view == null) {
            return -1;
        }
        return ((RecyclerView.LayoutManager)this).getPosition(view);
    }
    
    public int[] findFirstVisibleItemPositions(int[] array) {
        if (array == null) {
            array = new int[this.mSpanCount];
        }
        else if (array.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + array.length);
        }
        for (int i = 0; i < this.mSpanCount; ++i) {
            array[i] = this.mSpans[i].findFirstVisibleItemPosition();
        }
        return array;
    }
    
    public int[] findLastCompletelyVisibleItemPositions(int[] array) {
        if (array == null) {
            array = new int[this.mSpanCount];
        }
        else if (array.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + array.length);
        }
        for (int i = 0; i < this.mSpanCount; ++i) {
            array[i] = this.mSpans[i].findLastCompletelyVisibleItemPosition();
        }
        return array;
    }
    
    public int[] findLastVisibleItemPositions(int[] array) {
        if (array == null) {
            array = new int[this.mSpanCount];
        }
        else if (array.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + array.length);
        }
        for (int i = 0; i < this.mSpanCount; ++i) {
            array[i] = this.mSpans[i].findLastVisibleItemPosition();
        }
        return array;
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
        return super.getColumnCountForAccessibility(recycler, state);
    }
    
    public int getGapStrategy() {
        return this.mGapStrategy;
    }
    
    public int getOrientation() {
        return this.mOrientation;
    }
    
    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }
    
    @Override
    public int getRowCountForAccessibility(final Recycler recycler, final State state) {
        if (this.mOrientation == 0) {
            return this.mSpanCount;
        }
        return super.getRowCountForAccessibility(recycler, state);
    }
    
    public int getSpanCount() {
        return this.mSpanCount;
    }
    
    View hasGapsToFix() {
        final int n = -1 + ((RecyclerView.LayoutManager)this).getChildCount();
        final BitSet set = new BitSet(this.mSpanCount);
        set.set(0, this.mSpanCount, true);
        int n2;
        if (this.mOrientation == 1 && this.isLayoutRTL()) {
            n2 = 1;
        }
        else {
            n2 = -1;
        }
        int n3;
        int n4;
        if (this.mShouldReverseLayout) {
            n3 = n;
            n4 = 0 - 1;
        }
        else {
            n4 = n + 1;
            n3 = 0;
        }
        int n5;
        if (n3 < n4) {
            n5 = 1;
        }
        else {
            n5 = -1;
        }
        for (int i = n3; i != n4; i += n5) {
            final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (set.get(layoutParams.mSpan.mIndex)) {
                if (this.checkSpanForGap(layoutParams.mSpan)) {
                    return child;
                }
                set.clear(layoutParams.mSpan.mIndex);
            }
            if (!layoutParams.mFullSpan && i + n5 != n4) {
                final View child2 = ((RecyclerView.LayoutManager)this).getChildAt(i + n5);
                int n6;
                if (this.mShouldReverseLayout) {
                    final int decoratedEnd = this.mPrimaryOrientation.getDecoratedEnd(child);
                    final int decoratedEnd2 = this.mPrimaryOrientation.getDecoratedEnd(child2);
                    if (decoratedEnd < decoratedEnd2) {
                        return child;
                    }
                    n6 = 0;
                    if (decoratedEnd == decoratedEnd2) {
                        n6 = 1;
                    }
                }
                else {
                    final int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(child);
                    final int decoratedStart2 = this.mPrimaryOrientation.getDecoratedStart(child2);
                    if (decoratedStart > decoratedStart2) {
                        return child;
                    }
                    n6 = 0;
                    if (decoratedStart == decoratedStart2) {
                        n6 = 1;
                    }
                }
                if (n6 != 0) {
                    int n7;
                    if (layoutParams.mSpan.mIndex - ((LayoutParams)child2.getLayoutParams()).mSpan.mIndex < 0) {
                        n7 = 1;
                    }
                    else {
                        n7 = 0;
                    }
                    int n8;
                    if (n2 < 0) {
                        n8 = 1;
                    }
                    else {
                        n8 = 0;
                    }
                    if (n7 != n8) {
                        return child;
                    }
                }
            }
        }
        return null;
    }
    
    public void invalidateSpanAssignments() {
        this.mLazySpanLookup.clear();
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    boolean isLayoutRTL() {
        return ((RecyclerView.LayoutManager)this).getLayoutDirection() == 1;
    }
    
    @Override
    public void offsetChildrenHorizontal(final int n) {
        super.offsetChildrenHorizontal(n);
        for (int i = 0; i < this.mSpanCount; ++i) {
            this.mSpans[i].onOffset(n);
        }
    }
    
    @Override
    public void offsetChildrenVertical(final int n) {
        super.offsetChildrenVertical(n);
        for (int i = 0; i < this.mSpanCount; ++i) {
            this.mSpans[i].onOffset(n);
        }
    }
    
    @Override
    public void onDetachedFromWindow(final RecyclerView recyclerView, final Recycler recycler) {
        ((RecyclerView.LayoutManager)this).removeCallbacks(this.mCheckForGapsRunnable);
        for (int i = 0; i < this.mSpanCount; ++i) {
            this.mSpans[i].clear();
        }
    }
    
    @Nullable
    @Override
    public View onFocusSearchFailed(final View view, final int n, final Recycler recycler, final State state) {
        View view2;
        if (((RecyclerView.LayoutManager)this).getChildCount() == 0) {
            view2 = null;
        }
        else {
            final View containingItemView = ((RecyclerView.LayoutManager)this).findContainingItemView(view);
            if (containingItemView == null) {
                return null;
            }
            this.ensureOrientationHelper();
            this.resolveShouldLayoutReverse();
            final int convertFocusDirectionToLayoutDirection = this.convertFocusDirectionToLayoutDirection(n);
            if (convertFocusDirectionToLayoutDirection == Integer.MIN_VALUE) {
                return null;
            }
            final LayoutParams layoutParams = (LayoutParams)containingItemView.getLayoutParams();
            final boolean mFullSpan = layoutParams.mFullSpan;
            final Span mSpan = layoutParams.mSpan;
            int n2;
            if (convertFocusDirectionToLayoutDirection == 1) {
                n2 = this.getLastChildPosition();
            }
            else {
                n2 = this.getFirstChildPosition();
            }
            this.updateLayoutState(n2, state);
            this.setLayoutStateDirection(convertFocusDirectionToLayoutDirection);
            this.mLayoutState.mCurrentPosition = n2 + this.mLayoutState.mItemDirection;
            this.mLayoutState.mAvailable = (int)(0.33333334f * this.mPrimaryOrientation.getTotalSpace());
            this.mLayoutState.mStopInFocusable = true;
            this.mLayoutState.mRecycle = false;
            this.fill(recycler, this.mLayoutState, state);
            this.mLastLayoutFromEnd = this.mShouldReverseLayout;
            if (!mFullSpan) {
                view2 = mSpan.getFocusableViewAfter(n2, convertFocusDirectionToLayoutDirection);
                if (view2 != null && view2 != containingItemView) {
                    return view2;
                }
            }
            if (this.preferLastSpan(convertFocusDirectionToLayoutDirection)) {
                for (int i = -1 + this.mSpanCount; i >= 0; --i) {
                    view2 = this.mSpans[i].getFocusableViewAfter(n2, convertFocusDirectionToLayoutDirection);
                    if (view2 != null && view2 != containingItemView) {
                        return view2;
                    }
                }
            }
            else {
                for (int j = 0; j < this.mSpanCount; ++j) {
                    view2 = this.mSpans[j].getFocusableViewAfter(n2, convertFocusDirectionToLayoutDirection);
                    if (view2 != null && view2 != containingItemView) {
                        return view2;
                    }
                }
            }
            return null;
        }
        return view2;
    }
    
    @Override
    public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (((RecyclerView.LayoutManager)this).getChildCount() > 0) {
            final AccessibilityRecordCompat record = AccessibilityEventCompat.asRecord(accessibilityEvent);
            final View firstVisibleItemClosestToStart = this.findFirstVisibleItemClosestToStart(false, true);
            final View firstVisibleItemClosestToEnd = this.findFirstVisibleItemClosestToEnd(false, true);
            if (firstVisibleItemClosestToStart != null && firstVisibleItemClosestToEnd != null) {
                final int position = ((RecyclerView.LayoutManager)this).getPosition(firstVisibleItemClosestToStart);
                final int position2 = ((RecyclerView.LayoutManager)this).getPosition(firstVisibleItemClosestToEnd);
                if (position < position2) {
                    record.setFromIndex(position);
                    record.setToIndex(position2);
                    return;
                }
                record.setFromIndex(position2);
                record.setToIndex(position);
            }
        }
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfoForItem(final Recycler recycler, final State state, final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        final ViewGroup$LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        final LayoutParams layoutParams2 = (LayoutParams)layoutParams;
        if (this.mOrientation == 0) {
            final int spanIndex = layoutParams2.getSpanIndex();
            int mSpanCount;
            if (layoutParams2.mFullSpan) {
                mSpanCount = this.mSpanCount;
            }
            else {
                mSpanCount = 1;
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(spanIndex, mSpanCount, -1, -1, layoutParams2.mFullSpan, false));
            return;
        }
        final int spanIndex2 = layoutParams2.getSpanIndex();
        int mSpanCount2;
        if (layoutParams2.mFullSpan) {
            mSpanCount2 = this.mSpanCount;
        }
        else {
            mSpanCount2 = 1;
        }
        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(-1, -1, spanIndex2, mSpanCount2, layoutParams2.mFullSpan, false));
    }
    
    @Override
    public void onItemsAdded(final RecyclerView recyclerView, final int n, final int n2) {
        this.handleUpdate(n, n2, 1);
    }
    
    @Override
    public void onItemsChanged(final RecyclerView recyclerView) {
        this.mLazySpanLookup.clear();
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    @Override
    public void onItemsMoved(final RecyclerView recyclerView, final int n, final int n2, final int n3) {
        this.handleUpdate(n, n2, 8);
    }
    
    @Override
    public void onItemsRemoved(final RecyclerView recyclerView, final int n, final int n2) {
        this.handleUpdate(n, n2, 2);
    }
    
    @Override
    public void onItemsUpdated(final RecyclerView recyclerView, final int n, final int n2, final Object o) {
        this.handleUpdate(n, n2, 4);
    }
    
    @Override
    public void onLayoutChildren(final Recycler recycler, final State state) {
        this.onLayoutChildren(recycler, state, true);
    }
    
    @Override
    public void onRestoreInstanceState(final Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = (SavedState)parcelable;
            ((RecyclerView.LayoutManager)this).requestLayout();
        }
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        SavedState savedState;
        if (this.mPendingSavedState != null) {
            savedState = new SavedState(this.mPendingSavedState);
        }
        else {
            savedState = new SavedState();
            savedState.mReverseLayout = this.mReverseLayout;
            savedState.mAnchorLayoutFromEnd = this.mLastLayoutFromEnd;
            savedState.mLastLayoutRTL = this.mLastLayoutRTL;
            if (this.mLazySpanLookup != null && this.mLazySpanLookup.mData != null) {
                savedState.mSpanLookup = this.mLazySpanLookup.mData;
                savedState.mSpanLookupSize = savedState.mSpanLookup.length;
                savedState.mFullSpanItems = this.mLazySpanLookup.mFullSpanItems;
            }
            else {
                savedState.mSpanLookupSize = 0;
            }
            if (((RecyclerView.LayoutManager)this).getChildCount() <= 0) {
                savedState.mAnchorPosition = -1;
                savedState.mVisibleAnchorPosition = -1;
                savedState.mSpanOffsetsSize = 0;
                return (Parcelable)savedState;
            }
            this.ensureOrientationHelper();
            int mAnchorPosition;
            if (this.mLastLayoutFromEnd) {
                mAnchorPosition = this.getLastChildPosition();
            }
            else {
                mAnchorPosition = this.getFirstChildPosition();
            }
            savedState.mAnchorPosition = mAnchorPosition;
            savedState.mVisibleAnchorPosition = this.findFirstVisibleItemPositionInt();
            savedState.mSpanOffsetsSize = this.mSpanCount;
            savedState.mSpanOffsets = new int[this.mSpanCount];
            for (int i = 0; i < this.mSpanCount; ++i) {
                int n;
                if (this.mLastLayoutFromEnd) {
                    n = this.mSpans[i].getEndLine(Integer.MIN_VALUE);
                    if (n != Integer.MIN_VALUE) {
                        n -= this.mPrimaryOrientation.getEndAfterPadding();
                    }
                }
                else {
                    n = this.mSpans[i].getStartLine(Integer.MIN_VALUE);
                    if (n != Integer.MIN_VALUE) {
                        n -= this.mPrimaryOrientation.getStartAfterPadding();
                    }
                }
                savedState.mSpanOffsets[i] = n;
            }
        }
        return (Parcelable)savedState;
    }
    
    @Override
    public void onScrollStateChanged(final int n) {
        if (n == 0) {
            this.checkForGaps();
        }
    }
    
    int scrollBy(final int n, final Recycler recycler, final State state) {
        this.ensureOrientationHelper();
        int layoutStateDirection;
        int n2;
        if (n > 0) {
            layoutStateDirection = 1;
            n2 = this.getLastChildPosition();
        }
        else {
            layoutStateDirection = -1;
            n2 = this.getFirstChildPosition();
        }
        this.mLayoutState.mRecycle = true;
        this.updateLayoutState(n2, state);
        this.setLayoutStateDirection(layoutStateDirection);
        this.mLayoutState.mCurrentPosition = n2 + this.mLayoutState.mItemDirection;
        final int abs = Math.abs(n);
        this.mLayoutState.mAvailable = abs;
        final int fill = this.fill(recycler, this.mLayoutState, state);
        int n3;
        if (abs < fill) {
            n3 = n;
        }
        else if (n < 0) {
            n3 = -fill;
        }
        else {
            n3 = fill;
        }
        this.mPrimaryOrientation.offsetChildren(-n3);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        return n3;
    }
    
    @Override
    public int scrollHorizontallyBy(final int n, final Recycler recycler, final State state) {
        return this.scrollBy(n, recycler, state);
    }
    
    @Override
    public void scrollToPosition(final int mPendingScrollPosition) {
        if (this.mPendingSavedState != null && this.mPendingSavedState.mAnchorPosition != mPendingScrollPosition) {
            this.mPendingSavedState.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = mPendingScrollPosition;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    public void scrollToPositionWithOffset(final int mPendingScrollPosition, final int mPendingScrollPositionOffset) {
        if (this.mPendingSavedState != null) {
            this.mPendingSavedState.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = mPendingScrollPosition;
        this.mPendingScrollPositionOffset = mPendingScrollPositionOffset;
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    @Override
    public int scrollVerticallyBy(final int n, final Recycler recycler, final State state) {
        return this.scrollBy(n, recycler, state);
    }
    
    public void setGapStrategy(final int mGapStrategy) {
        this.assertNotInLayoutOrScroll(null);
        if (mGapStrategy == this.mGapStrategy) {
            return;
        }
        if (mGapStrategy != 0 && mGapStrategy != 2) {
            throw new IllegalArgumentException("invalid gap strategy. Must be GAP_HANDLING_NONE or GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS");
        }
        this.mGapStrategy = mGapStrategy;
        ((RecyclerView.LayoutManager)this).setAutoMeasureEnabled(this.mGapStrategy != 0);
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    @Override
    public void setMeasuredDimension(final Rect rect, final int n, final int n2) {
        final int n3 = ((RecyclerView.LayoutManager)this).getPaddingLeft() + ((RecyclerView.LayoutManager)this).getPaddingRight();
        final int n4 = ((RecyclerView.LayoutManager)this).getPaddingTop() + ((RecyclerView.LayoutManager)this).getPaddingBottom();
        int n5;
        int n6;
        if (this.mOrientation == 1) {
            n5 = RecyclerView.LayoutManager.chooseSize(n2, n4 + rect.height(), ((RecyclerView.LayoutManager)this).getMinimumHeight());
            n6 = RecyclerView.LayoutManager.chooseSize(n, n3 + this.mSizePerSpan * this.mSpanCount, ((RecyclerView.LayoutManager)this).getMinimumWidth());
        }
        else {
            n6 = RecyclerView.LayoutManager.chooseSize(n, n3 + rect.width(), ((RecyclerView.LayoutManager)this).getMinimumWidth());
            n5 = RecyclerView.LayoutManager.chooseSize(n2, n4 + this.mSizePerSpan * this.mSpanCount, ((RecyclerView.LayoutManager)this).getMinimumHeight());
        }
        ((RecyclerView.LayoutManager)this).setMeasuredDimension(n6, n5);
    }
    
    public void setOrientation(final int mOrientation) {
        if (mOrientation != 0 && mOrientation != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        this.assertNotInLayoutOrScroll(null);
        if (mOrientation == this.mOrientation) {
            return;
        }
        this.mOrientation = mOrientation;
        if (this.mPrimaryOrientation != null && this.mSecondaryOrientation != null) {
            final OrientationHelper mPrimaryOrientation = this.mPrimaryOrientation;
            this.mPrimaryOrientation = this.mSecondaryOrientation;
            this.mSecondaryOrientation = mPrimaryOrientation;
        }
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    public void setReverseLayout(final boolean b) {
        this.assertNotInLayoutOrScroll(null);
        if (this.mPendingSavedState != null && this.mPendingSavedState.mReverseLayout != b) {
            this.mPendingSavedState.mReverseLayout = b;
        }
        this.mReverseLayout = b;
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    public void setSpanCount(final int mSpanCount) {
        this.assertNotInLayoutOrScroll(null);
        if (mSpanCount != this.mSpanCount) {
            this.invalidateSpanAssignments();
            this.mSpanCount = mSpanCount;
            this.mRemainingSpans = new BitSet(this.mSpanCount);
            this.mSpans = new Span[this.mSpanCount];
            for (int i = 0; i < this.mSpanCount; ++i) {
                this.mSpans[i] = new Span(i);
            }
            ((RecyclerView.LayoutManager)this).requestLayout();
        }
    }
    
    @Override
    public void smoothScrollToPosition(final RecyclerView recyclerView, final State state, final int targetPosition) {
        final LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
            @Override
            public PointF computeScrollVectorForPosition(final int n) {
                final int access$400 = StaggeredGridLayoutManager.this.calculateScrollDirectionForPosition(n);
                if (access$400 == 0) {
                    return null;
                }
                if (StaggeredGridLayoutManager.this.mOrientation == 0) {
                    return new PointF((float)access$400, 0.0f);
                }
                return new PointF(0.0f, (float)access$400);
            }
        };
        ((RecyclerView.SmoothScroller)linearSmoothScroller).setTargetPosition(targetPosition);
        ((RecyclerView.LayoutManager)this).startSmoothScroll(linearSmoothScroller);
    }
    
    @Override
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null;
    }
    
    boolean updateAnchorFromPendingData(final State state, final AnchorInfo anchorInfo) {
        if (state.isPreLayout() || this.mPendingScrollPosition == -1) {
            return false;
        }
        if (this.mPendingScrollPosition < 0 || this.mPendingScrollPosition >= state.getItemCount()) {
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            return false;
        }
        if (this.mPendingSavedState != null && this.mPendingSavedState.mAnchorPosition != -1 && this.mPendingSavedState.mSpanOffsetsSize >= 1) {
            anchorInfo.mOffset = Integer.MIN_VALUE;
            anchorInfo.mPosition = this.mPendingScrollPosition;
            return true;
        }
        final View viewByPosition = ((RecyclerView.LayoutManager)this).findViewByPosition(this.mPendingScrollPosition);
        if (viewByPosition == null) {
            anchorInfo.mPosition = this.mPendingScrollPosition;
            if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                final int calculateScrollDirectionForPosition = this.calculateScrollDirectionForPosition(anchorInfo.mPosition);
                boolean mLayoutFromEnd = false;
                if (calculateScrollDirectionForPosition == 1) {
                    mLayoutFromEnd = true;
                }
                anchorInfo.mLayoutFromEnd = mLayoutFromEnd;
                anchorInfo.assignCoordinateFromPadding();
            }
            else {
                anchorInfo.assignCoordinateFromPadding(this.mPendingScrollPositionOffset);
            }
            return anchorInfo.mInvalidateOffsets = true;
        }
        int mPosition;
        if (this.mShouldReverseLayout) {
            mPosition = this.getLastChildPosition();
        }
        else {
            mPosition = this.getFirstChildPosition();
        }
        anchorInfo.mPosition = mPosition;
        if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
            if (anchorInfo.mLayoutFromEnd) {
                anchorInfo.mOffset = this.mPrimaryOrientation.getEndAfterPadding() - this.mPendingScrollPositionOffset - this.mPrimaryOrientation.getDecoratedEnd(viewByPosition);
                return true;
            }
            anchorInfo.mOffset = this.mPrimaryOrientation.getStartAfterPadding() + this.mPendingScrollPositionOffset - this.mPrimaryOrientation.getDecoratedStart(viewByPosition);
            return true;
        }
        else {
            if (this.mPrimaryOrientation.getDecoratedMeasurement(viewByPosition) > this.mPrimaryOrientation.getTotalSpace()) {
                int mOffset;
                if (anchorInfo.mLayoutFromEnd) {
                    mOffset = this.mPrimaryOrientation.getEndAfterPadding();
                }
                else {
                    mOffset = this.mPrimaryOrientation.getStartAfterPadding();
                }
                anchorInfo.mOffset = mOffset;
                return true;
            }
            final int n = this.mPrimaryOrientation.getDecoratedStart(viewByPosition) - this.mPrimaryOrientation.getStartAfterPadding();
            if (n < 0) {
                anchorInfo.mOffset = -n;
                return true;
            }
            final int mOffset2 = this.mPrimaryOrientation.getEndAfterPadding() - this.mPrimaryOrientation.getDecoratedEnd(viewByPosition);
            if (mOffset2 < 0) {
                anchorInfo.mOffset = mOffset2;
                return true;
            }
            anchorInfo.mOffset = Integer.MIN_VALUE;
            return true;
        }
    }
    
    void updateAnchorInfoForLayout(final State state, final AnchorInfo anchorInfo) {
        if (!this.updateAnchorFromPendingData(state, anchorInfo) && !this.updateAnchorFromChildren(state, anchorInfo)) {
            anchorInfo.assignCoordinateFromPadding();
            anchorInfo.mPosition = 0;
        }
    }
    
    void updateMeasureSpecs(final int n) {
        this.mSizePerSpan = n / this.mSpanCount;
        this.mFullSizeSpec = View$MeasureSpec.makeMeasureSpec(n, this.mSecondaryOrientation.getMode());
    }
    
    private class AnchorInfo
    {
        boolean mInvalidateOffsets;
        boolean mLayoutFromEnd;
        int mOffset;
        int mPosition;
        
        void assignCoordinateFromPadding() {
            int mOffset;
            if (this.mLayoutFromEnd) {
                mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding();
            }
            else {
                mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
            }
            this.mOffset = mOffset;
        }
        
        void assignCoordinateFromPadding(final int n) {
            if (this.mLayoutFromEnd) {
                this.mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding() - n;
                return;
            }
            this.mOffset = n + StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
        }
        
        void reset() {
            this.mPosition = -1;
            this.mOffset = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
            this.mInvalidateOffsets = false;
        }
    }
    
    public static class LayoutParams extends RecyclerView.LayoutParams
    {
        public static final int INVALID_SPAN_ID = -1;
        boolean mFullSpan;
        Span mSpan;
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
        }
        
        public LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
        }
        
        public LayoutParams(final RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
        }
        
        public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
        }
        
        public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            super(viewGroup$MarginLayoutParams);
        }
        
        public final int getSpanIndex() {
            if (this.mSpan == null) {
                return -1;
            }
            return this.mSpan.mIndex;
        }
        
        public boolean isFullSpan() {
            return this.mFullSpan;
        }
        
        public void setFullSpan(final boolean mFullSpan) {
            this.mFullSpan = mFullSpan;
        }
    }
    
    static class LazySpanLookup
    {
        private static final int MIN_SIZE = 10;
        int[] mData;
        List<FullSpanItem> mFullSpanItems;
        
        private int invalidateFullSpansAfter(final int n) {
            if (this.mFullSpanItems != null) {
                final FullSpanItem fullSpanItem = this.getFullSpanItem(n);
                if (fullSpanItem != null) {
                    this.mFullSpanItems.remove(fullSpanItem);
                }
                int n2 = -1;
                for (int size = this.mFullSpanItems.size(), i = 0; i < size; ++i) {
                    if (this.mFullSpanItems.get(i).mPosition >= n) {
                        n2 = i;
                        break;
                    }
                }
                if (n2 != -1) {
                    final FullSpanItem fullSpanItem2 = this.mFullSpanItems.get(n2);
                    this.mFullSpanItems.remove(n2);
                    return fullSpanItem2.mPosition;
                }
            }
            return -1;
        }
        
        private void offsetFullSpansForAddition(final int n, final int n2) {
            if (this.mFullSpanItems != null) {
                for (int i = -1 + this.mFullSpanItems.size(); i >= 0; --i) {
                    final FullSpanItem fullSpanItem = this.mFullSpanItems.get(i);
                    if (fullSpanItem.mPosition >= n) {
                        fullSpanItem.mPosition += n2;
                    }
                }
            }
        }
        
        private void offsetFullSpansForRemoval(final int n, final int n2) {
            if (this.mFullSpanItems != null) {
                final int n3 = n + n2;
                for (int i = -1 + this.mFullSpanItems.size(); i >= 0; --i) {
                    final FullSpanItem fullSpanItem = this.mFullSpanItems.get(i);
                    if (fullSpanItem.mPosition >= n) {
                        if (fullSpanItem.mPosition < n3) {
                            this.mFullSpanItems.remove(i);
                        }
                        else {
                            fullSpanItem.mPosition -= n2;
                        }
                    }
                }
            }
        }
        
        public void addFullSpanItem(final FullSpanItem fullSpanItem) {
            if (this.mFullSpanItems == null) {
                this.mFullSpanItems = new ArrayList<FullSpanItem>();
            }
            for (int size = this.mFullSpanItems.size(), i = 0; i < size; ++i) {
                final FullSpanItem fullSpanItem2 = this.mFullSpanItems.get(i);
                if (fullSpanItem2.mPosition == fullSpanItem.mPosition) {
                    this.mFullSpanItems.remove(i);
                }
                if (fullSpanItem2.mPosition >= fullSpanItem.mPosition) {
                    this.mFullSpanItems.add(i, fullSpanItem);
                    return;
                }
            }
            this.mFullSpanItems.add(fullSpanItem);
        }
        
        void clear() {
            if (this.mData != null) {
                Arrays.fill(this.mData, -1);
            }
            this.mFullSpanItems = null;
        }
        
        void ensureSize(final int n) {
            if (this.mData == null) {
                Arrays.fill(this.mData = new int[1 + Math.max(n, 10)], -1);
            }
            else if (n >= this.mData.length) {
                final int[] mData = this.mData;
                System.arraycopy(mData, 0, this.mData = new int[this.sizeForPosition(n)], 0, mData.length);
                Arrays.fill(this.mData, mData.length, this.mData.length, -1);
            }
        }
        
        int forceInvalidateAfter(final int n) {
            if (this.mFullSpanItems != null) {
                for (int i = -1 + this.mFullSpanItems.size(); i >= 0; --i) {
                    if (this.mFullSpanItems.get(i).mPosition >= n) {
                        this.mFullSpanItems.remove(i);
                    }
                }
            }
            return this.invalidateAfter(n);
        }
        
        public FullSpanItem getFirstFullSpanItemInRange(final int n, final int n2, final int n3, final boolean b) {
            if (this.mFullSpanItems != null) {
                for (int size = this.mFullSpanItems.size(), i = 0; i < size; ++i) {
                    final FullSpanItem fullSpanItem = this.mFullSpanItems.get(i);
                    if (fullSpanItem.mPosition >= n2) {
                        return null;
                    }
                    if (fullSpanItem.mPosition >= n && (n3 == 0 || fullSpanItem.mGapDir == n3 || (b && fullSpanItem.mHasUnwantedGapAfter))) {
                        return fullSpanItem;
                    }
                }
                return null;
            }
            return null;
        }
        
        public FullSpanItem getFullSpanItem(final int n) {
            if (this.mFullSpanItems != null) {
                for (int i = -1 + this.mFullSpanItems.size(); i >= 0; --i) {
                    final FullSpanItem fullSpanItem = this.mFullSpanItems.get(i);
                    if (fullSpanItem.mPosition == n) {
                        return fullSpanItem;
                    }
                }
                return null;
            }
            return null;
        }
        
        int getSpan(final int n) {
            if (this.mData == null || n >= this.mData.length) {
                return -1;
            }
            return this.mData[n];
        }
        
        int invalidateAfter(final int n) {
            if (this.mData == null || n >= this.mData.length) {
                return -1;
            }
            final int invalidateFullSpansAfter = this.invalidateFullSpansAfter(n);
            if (invalidateFullSpansAfter == -1) {
                Arrays.fill(this.mData, n, this.mData.length, -1);
                return this.mData.length;
            }
            Arrays.fill(this.mData, n, invalidateFullSpansAfter + 1, -1);
            return invalidateFullSpansAfter + 1;
        }
        
        void offsetForAddition(final int n, final int n2) {
            if (this.mData == null || n >= this.mData.length) {
                return;
            }
            this.ensureSize(n + n2);
            System.arraycopy(this.mData, n, this.mData, n + n2, this.mData.length - n - n2);
            Arrays.fill(this.mData, n, n + n2, -1);
            this.offsetFullSpansForAddition(n, n2);
        }
        
        void offsetForRemoval(final int n, final int n2) {
            if (this.mData == null || n >= this.mData.length) {
                return;
            }
            this.ensureSize(n + n2);
            System.arraycopy(this.mData, n + n2, this.mData, n, this.mData.length - n - n2);
            Arrays.fill(this.mData, this.mData.length - n2, this.mData.length, -1);
            this.offsetFullSpansForRemoval(n, n2);
        }
        
        void setSpan(final int n, final Span span) {
            this.ensureSize(n);
            this.mData[n] = span.mIndex;
        }
        
        int sizeForPosition(final int n) {
            int i;
            for (i = this.mData.length; i <= n; i *= 2) {}
            return i;
        }
        
        static class FullSpanItem implements Parcelable
        {
            public static final Parcelable$Creator<FullSpanItem> CREATOR;
            int mGapDir;
            int[] mGapPerSpan;
            boolean mHasUnwantedGapAfter;
            int mPosition;
            
            static {
                CREATOR = (Parcelable$Creator)new Parcelable$Creator<FullSpanItem>() {
                    public FullSpanItem createFromParcel(final Parcel parcel) {
                        return new FullSpanItem(parcel);
                    }
                    
                    public FullSpanItem[] newArray(final int n) {
                        return new FullSpanItem[n];
                    }
                };
            }
            
            public FullSpanItem() {
            }
            
            public FullSpanItem(final Parcel parcel) {
                boolean mHasUnwantedGapAfter = true;
                this.mPosition = parcel.readInt();
                this.mGapDir = parcel.readInt();
                if (parcel.readInt() != (mHasUnwantedGapAfter ? 1 : 0)) {
                    mHasUnwantedGapAfter = false;
                }
                this.mHasUnwantedGapAfter = mHasUnwantedGapAfter;
                final int int1 = parcel.readInt();
                if (int1 > 0) {
                    parcel.readIntArray(this.mGapPerSpan = new int[int1]);
                }
            }
            
            public int describeContents() {
                return 0;
            }
            
            int getGapForSpan(final int n) {
                if (this.mGapPerSpan == null) {
                    return 0;
                }
                return this.mGapPerSpan[n];
            }
            
            @Override
            public String toString() {
                return "FullSpanItem{mPosition=" + this.mPosition + ", mGapDir=" + this.mGapDir + ", mHasUnwantedGapAfter=" + this.mHasUnwantedGapAfter + ", mGapPerSpan=" + Arrays.toString(this.mGapPerSpan) + '}';
            }
            
            public void writeToParcel(final Parcel parcel, final int n) {
                parcel.writeInt(this.mPosition);
                parcel.writeInt(this.mGapDir);
                int n2;
                if (this.mHasUnwantedGapAfter) {
                    n2 = 1;
                }
                else {
                    n2 = 0;
                }
                parcel.writeInt(n2);
                if (this.mGapPerSpan != null && this.mGapPerSpan.length > 0) {
                    parcel.writeInt(this.mGapPerSpan.length);
                    parcel.writeIntArray(this.mGapPerSpan);
                    return;
                }
                parcel.writeInt(0);
            }
        }
    }
    
    public static class SavedState implements Parcelable
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        boolean mAnchorLayoutFromEnd;
        int mAnchorPosition;
        List<FullSpanItem> mFullSpanItems;
        boolean mLastLayoutRTL;
        boolean mReverseLayout;
        int[] mSpanLookup;
        int mSpanLookupSize;
        int[] mSpanOffsets;
        int mSpanOffsetsSize;
        int mVisibleAnchorPosition;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<SavedState>() {
                public SavedState createFromParcel(final Parcel parcel) {
                    return new SavedState(parcel);
                }
                
                public SavedState[] newArray(final int n) {
                    return new SavedState[n];
                }
            };
        }
        
        public SavedState() {
        }
        
        SavedState(final Parcel parcel) {
            boolean mLastLayoutRTL = true;
            this.mAnchorPosition = parcel.readInt();
            this.mVisibleAnchorPosition = parcel.readInt();
            this.mSpanOffsetsSize = parcel.readInt();
            if (this.mSpanOffsetsSize > 0) {
                parcel.readIntArray(this.mSpanOffsets = new int[this.mSpanOffsetsSize]);
            }
            this.mSpanLookupSize = parcel.readInt();
            if (this.mSpanLookupSize > 0) {
                parcel.readIntArray(this.mSpanLookup = new int[this.mSpanLookupSize]);
            }
            this.mReverseLayout = (parcel.readInt() == (mLastLayoutRTL ? 1 : 0) && mLastLayoutRTL);
            this.mAnchorLayoutFromEnd = (parcel.readInt() == (mLastLayoutRTL ? 1 : 0) && mLastLayoutRTL);
            if (parcel.readInt() != (mLastLayoutRTL ? 1 : 0)) {
                mLastLayoutRTL = false;
            }
            this.mLastLayoutRTL = mLastLayoutRTL;
            this.mFullSpanItems = (List<FullSpanItem>)parcel.readArrayList(FullSpanItem.class.getClassLoader());
        }
        
        public SavedState(final SavedState savedState) {
            this.mSpanOffsetsSize = savedState.mSpanOffsetsSize;
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mVisibleAnchorPosition = savedState.mVisibleAnchorPosition;
            this.mSpanOffsets = savedState.mSpanOffsets;
            this.mSpanLookupSize = savedState.mSpanLookupSize;
            this.mSpanLookup = savedState.mSpanLookup;
            this.mReverseLayout = savedState.mReverseLayout;
            this.mAnchorLayoutFromEnd = savedState.mAnchorLayoutFromEnd;
            this.mLastLayoutRTL = savedState.mLastLayoutRTL;
            this.mFullSpanItems = savedState.mFullSpanItems;
        }
        
        public int describeContents() {
            return 0;
        }
        
        void invalidateAnchorPositionInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mAnchorPosition = -1;
            this.mVisibleAnchorPosition = -1;
        }
        
        void invalidateSpanInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mSpanLookupSize = 0;
            this.mSpanLookup = null;
            this.mFullSpanItems = null;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            int n2 = 1;
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mVisibleAnchorPosition);
            parcel.writeInt(this.mSpanOffsetsSize);
            if (this.mSpanOffsetsSize > 0) {
                parcel.writeIntArray(this.mSpanOffsets);
            }
            parcel.writeInt(this.mSpanLookupSize);
            if (this.mSpanLookupSize > 0) {
                parcel.writeIntArray(this.mSpanLookup);
            }
            int n3;
            if (this.mReverseLayout) {
                n3 = n2;
            }
            else {
                n3 = 0;
            }
            parcel.writeInt(n3);
            int n4;
            if (this.mAnchorLayoutFromEnd) {
                n4 = n2;
            }
            else {
                n4 = 0;
            }
            parcel.writeInt(n4);
            if (!this.mLastLayoutRTL) {
                n2 = 0;
            }
            parcel.writeInt(n2);
            parcel.writeList((List)this.mFullSpanItems);
        }
    }
    
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
                final FullSpanItem fullSpanItem = StaggeredGridLayoutManager.this.mLazySpanLookup.getFullSpanItem(((RecyclerView.LayoutParams)layoutParams).getViewLayoutPosition());
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
                final FullSpanItem fullSpanItem = StaggeredGridLayoutManager.this.mLazySpanLookup.getFullSpanItem(((RecyclerView.LayoutParams)layoutParams).getViewLayoutPosition());
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
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return this.findOneVisibleChild(-1 + this.mViews.size(), -1, true);
            }
            return this.findOneVisibleChild(0, this.mViews.size(), true);
        }
        
        public int findFirstVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return this.findOneVisibleChild(-1 + this.mViews.size(), -1, false);
            }
            return this.findOneVisibleChild(0, this.mViews.size(), false);
        }
        
        public int findLastCompletelyVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return this.findOneVisibleChild(0, this.mViews.size(), true);
            }
            return this.findOneVisibleChild(-1 + this.mViews.size(), -1, true);
        }
        
        public int findLastVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
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
                    if (!view2.isFocusable() || ((RecyclerView.LayoutManager)StaggeredGridLayoutManager.this).getPosition(view2) > n != StaggeredGridLayoutManager.this.mReverseLayout) {
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
                    if (!StaggeredGridLayoutManager.this.mReverseLayout) {
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
}
