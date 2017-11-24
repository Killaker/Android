package android.support.design.widget;

import android.content.*;
import android.util.*;
import android.support.design.*;
import java.util.*;
import android.support.v4.view.*;
import android.content.res.*;
import android.widget.*;
import java.lang.ref.*;
import android.view.animation.*;
import android.support.annotation.*;
import android.view.*;
import android.os.*;
import android.support.v4.os.*;
import java.lang.annotation.*;

@CoordinatorLayout.DefaultBehavior("Landroid/support/design/widget/AppBarLayout$Behavior;")
public class AppBarLayout extends LinearLayout
{
    private static final int INVALID_SCROLL_RANGE = -1;
    private static final int PENDING_ACTION_ANIMATE_ENABLED = 4;
    private static final int PENDING_ACTION_COLLAPSED = 2;
    private static final int PENDING_ACTION_EXPANDED = 1;
    private static final int PENDING_ACTION_NONE;
    private int mDownPreScrollRange;
    private int mDownScrollRange;
    boolean mHaveChildWithInterpolator;
    private WindowInsetsCompat mLastInsets;
    private final List<OnOffsetChangedListener> mListeners;
    private int mPendingAction;
    private float mTargetElevation;
    private int mTotalScrollRange;
    
    public AppBarLayout(final Context context) {
        this(context, null);
    }
    
    public AppBarLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.mTotalScrollRange = -1;
        this.mDownPreScrollRange = -1;
        this.mDownScrollRange = -1;
        this.mPendingAction = 0;
        this.setOrientation(1);
        ThemeUtils.checkAppCompatTheme(context);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.AppBarLayout, 0, R.style.Widget_Design_AppBarLayout);
        this.mTargetElevation = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AppBarLayout_elevation, 0);
        this.setBackgroundDrawable(obtainStyledAttributes.getDrawable(R.styleable.AppBarLayout_android_background));
        if (obtainStyledAttributes.hasValue(R.styleable.AppBarLayout_expanded)) {
            this.setExpanded(obtainStyledAttributes.getBoolean(R.styleable.AppBarLayout_expanded, false));
        }
        obtainStyledAttributes.recycle();
        ViewUtils.setBoundsViewOutlineProvider((View)this);
        this.mListeners = new ArrayList<OnOffsetChangedListener>();
        ViewCompat.setElevation((View)this, this.mTargetElevation);
        ViewCompat.setOnApplyWindowInsetsListener((View)this, new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
                return AppBarLayout.this.onWindowInsetChanged(windowInsetsCompat);
            }
        });
    }
    
    private int getDownNestedPreScrollRange() {
        if (this.mDownPreScrollRange != -1) {
            return this.mDownPreScrollRange;
        }
        int n = 0;
        for (int i = -1 + this.getChildCount(); i >= 0; --i) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            final int measuredHeight = child.getMeasuredHeight();
            final int mScrollFlags = layoutParams.mScrollFlags;
            if ((mScrollFlags & 0x5) == 0x5) {
                final int n2 = n + (layoutParams.topMargin + layoutParams.bottomMargin);
                if ((mScrollFlags & 0x8) != 0x0) {
                    n = n2 + ViewCompat.getMinimumHeight(child);
                }
                else if ((mScrollFlags & 0x2) != 0x0) {
                    n = n2 + (measuredHeight - ViewCompat.getMinimumHeight(child));
                }
                else {
                    n = n2 + measuredHeight;
                }
            }
            else if (n > 0) {
                break;
            }
        }
        return this.mDownPreScrollRange = Math.max(0, n - this.getTopInset());
    }
    
    private int getDownNestedScrollRange() {
        if (this.mDownScrollRange != -1) {
            return this.mDownScrollRange;
        }
        int n = 0;
        for (int i = 0; i < this.getChildCount(); ++i) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            final int n2 = child.getMeasuredHeight() + (layoutParams.topMargin + layoutParams.bottomMargin);
            final int mScrollFlags = layoutParams.mScrollFlags;
            if ((mScrollFlags & 0x1) == 0x0) {
                break;
            }
            n += n2;
            if ((mScrollFlags & 0x2) != 0x0) {
                n -= ViewCompat.getMinimumHeight(child) + this.getTopInset();
                break;
            }
        }
        return this.mDownScrollRange = Math.max(0, n);
    }
    
    private int getPendingAction() {
        return this.mPendingAction;
    }
    
    private int getTopInset() {
        if (this.mLastInsets != null) {
            return this.mLastInsets.getSystemWindowInsetTop();
        }
        return 0;
    }
    
    private int getUpNestedPreScrollRange() {
        return this.getTotalScrollRange();
    }
    
    private boolean hasChildWithInterpolator() {
        return this.mHaveChildWithInterpolator;
    }
    
    private boolean hasScrollableChildren() {
        return this.getTotalScrollRange() != 0;
    }
    
    private void invalidateScrollRanges() {
        this.mTotalScrollRange = -1;
        this.mDownPreScrollRange = -1;
        this.mDownScrollRange = -1;
    }
    
    private WindowInsetsCompat onWindowInsetChanged(final WindowInsetsCompat windowInsetsCompat) {
        final boolean fitsSystemWindows = ViewCompat.getFitsSystemWindows((View)this);
        WindowInsetsCompat mLastInsets = null;
        if (fitsSystemWindows) {
            mLastInsets = windowInsetsCompat;
        }
        if (mLastInsets != this.mLastInsets) {
            this.mLastInsets = mLastInsets;
            this.invalidateScrollRanges();
        }
        return windowInsetsCompat;
    }
    
    private void resetPendingAction() {
        this.mPendingAction = 0;
    }
    
    public void addOnOffsetChangedListener(final OnOffsetChangedListener onOffsetChangedListener) {
        if (onOffsetChangedListener != null && !this.mListeners.contains(onOffsetChangedListener)) {
            this.mListeners.add(onOffsetChangedListener);
        }
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof LayoutParams;
    }
    
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -2);
    }
    
    public LayoutParams generateLayoutParams(final AttributeSet set) {
        return new LayoutParams(this.getContext(), set);
    }
    
    protected LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams instanceof LinearLayout$LayoutParams) {
            return new LayoutParams((LinearLayout$LayoutParams)viewGroup$LayoutParams);
        }
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            return new LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        return new LayoutParams(viewGroup$LayoutParams);
    }
    
    final int getMinimumHeightForVisibleOverlappingContent() {
        final int topInset = this.getTopInset();
        final int minimumHeight = ViewCompat.getMinimumHeight((View)this);
        if (minimumHeight != 0) {
            return topInset + minimumHeight * 2;
        }
        final int childCount = this.getChildCount();
        if (childCount >= 1) {
            return topInset + 2 * ViewCompat.getMinimumHeight(this.getChildAt(childCount - 1));
        }
        return 0;
    }
    
    public float getTargetElevation() {
        return this.mTargetElevation;
    }
    
    public final int getTotalScrollRange() {
        if (this.mTotalScrollRange != -1) {
            return this.mTotalScrollRange;
        }
        int n = 0;
        for (int i = 0; i < this.getChildCount(); ++i) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            final int measuredHeight = child.getMeasuredHeight();
            final int mScrollFlags = layoutParams.mScrollFlags;
            if ((mScrollFlags & 0x1) == 0x0) {
                break;
            }
            n += measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin;
            if ((mScrollFlags & 0x2) != 0x0) {
                n -= ViewCompat.getMinimumHeight(child);
                break;
            }
        }
        return this.mTotalScrollRange = Math.max(0, n - this.getTopInset());
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        this.invalidateScrollRanges();
        this.mHaveChildWithInterpolator = false;
        for (int i = 0; i < this.getChildCount(); ++i) {
            if (((LayoutParams)this.getChildAt(i).getLayoutParams()).getScrollInterpolator() != null) {
                this.mHaveChildWithInterpolator = true;
                break;
            }
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        this.invalidateScrollRanges();
    }
    
    public void removeOnOffsetChangedListener(final OnOffsetChangedListener onOffsetChangedListener) {
        if (onOffsetChangedListener != null) {
            this.mListeners.remove(onOffsetChangedListener);
        }
    }
    
    public void setExpanded(final boolean b) {
        this.setExpanded(b, ViewCompat.isLaidOut((View)this));
    }
    
    public void setExpanded(final boolean b, final boolean b2) {
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 2;
        }
        int n2;
        if (b2) {
            n2 = 4;
        }
        else {
            n2 = 0;
        }
        this.mPendingAction = (n2 | n);
        this.requestLayout();
    }
    
    public void setOrientation(final int orientation) {
        if (orientation != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(orientation);
    }
    
    public void setTargetElevation(final float mTargetElevation) {
        this.mTargetElevation = mTargetElevation;
    }
    
    public static class Behavior extends HeaderBehavior<AppBarLayout>
    {
        private static final int ANIMATE_OFFSET_DIPS_PER_SECOND = 300;
        private static final int INVALID_POSITION = -1;
        private ValueAnimatorCompat mAnimator;
        private WeakReference<View> mLastNestedScrollingChildRef;
        private int mOffsetDelta;
        private int mOffsetToChildIndexOnLayout;
        private boolean mOffsetToChildIndexOnLayoutIsMinHeight;
        private float mOffsetToChildIndexOnLayoutPerc;
        private DragCallback mOnDragCallback;
        private boolean mSkipNestedPreScroll;
        private boolean mWasNestedFlung;
        
        public Behavior() {
            this.mOffsetToChildIndexOnLayout = -1;
        }
        
        public Behavior(final Context context, final AttributeSet set) {
            super(context, set);
            this.mOffsetToChildIndexOnLayout = -1;
        }
        
        private void animateOffsetTo(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final int n) {
            final int topBottomOffsetForScrollingSibling = this.getTopBottomOffsetForScrollingSibling();
            if (topBottomOffsetForScrollingSibling == n) {
                if (this.mAnimator != null && this.mAnimator.isRunning()) {
                    this.mAnimator.cancel();
                }
                return;
            }
            if (this.mAnimator == null) {
                (this.mAnimator = ViewUtils.createAnimator()).setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
                this.mAnimator.setUpdateListener((ValueAnimatorCompat.AnimatorUpdateListener)new ValueAnimatorCompat.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
                        Behavior.this.setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, valueAnimatorCompat.getAnimatedIntValue());
                    }
                });
            }
            else {
                this.mAnimator.cancel();
            }
            this.mAnimator.setDuration(Math.round(1000.0f * (Math.abs(topBottomOffsetForScrollingSibling - n) / coordinatorLayout.getResources().getDisplayMetrics().density) / 300.0f));
            this.mAnimator.setIntValues(topBottomOffsetForScrollingSibling, n);
            this.mAnimator.start();
        }
        
        private void dispatchOffsetUpdates(final AppBarLayout appBarLayout) {
            final List access$800 = appBarLayout.mListeners;
            for (int i = 0; i < access$800.size(); ++i) {
                final OnOffsetChangedListener onOffsetChangedListener = access$800.get(i);
                if (onOffsetChangedListener != null) {
                    onOffsetChangedListener.onOffsetChanged(appBarLayout, this.getTopAndBottomOffset());
                }
            }
        }
        
        private View getChildOnOffset(final AppBarLayout appBarLayout, final int n) {
            for (int i = 0; i < appBarLayout.getChildCount(); ++i) {
                final View child = appBarLayout.getChildAt(i);
                if (child.getTop() <= -n && child.getBottom() >= -n) {
                    return child;
                }
            }
            return null;
        }
        
        private int interpolateOffset(final AppBarLayout appBarLayout, int n) {
            final int abs = Math.abs(n);
            int i = 0;
            while (i < appBarLayout.getChildCount()) {
                final View child = appBarLayout.getChildAt(i);
                final AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams)child.getLayoutParams();
                final Interpolator scrollInterpolator = layoutParams.getScrollInterpolator();
                if (abs >= child.getTop() && abs <= child.getBottom()) {
                    if (scrollInterpolator == null) {
                        break;
                    }
                    final int scrollFlags = layoutParams.getScrollFlags();
                    final int n2 = scrollFlags & 0x1;
                    int n3 = 0;
                    if (n2 != 0) {
                        n3 = 0 + (child.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin);
                        if ((scrollFlags & 0x2) != 0x0) {
                            n3 -= ViewCompat.getMinimumHeight(child);
                        }
                    }
                    if (ViewCompat.getFitsSystemWindows(child)) {
                        n3 -= appBarLayout.getTopInset();
                    }
                    if (n3 > 0) {
                        n = Integer.signum(n) * (Math.round(n3 * scrollInterpolator.getInterpolation((abs - child.getTop()) / n3)) + child.getTop());
                        break;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
            return n;
        }
        
        private void snapToChildIfNeeded(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout) {
            final int topBottomOffsetForScrollingSibling = this.getTopBottomOffsetForScrollingSibling();
            final View childOnOffset = this.getChildOnOffset(appBarLayout, topBottomOffsetForScrollingSibling);
            if (childOnOffset != null) {
                final AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams)childOnOffset.getLayoutParams();
                if ((0x11 & layoutParams.getScrollFlags()) == 0x11) {
                    final int n = -childOnOffset.getTop();
                    int n2 = -childOnOffset.getBottom();
                    if ((0x2 & layoutParams.getScrollFlags()) == 0x2) {
                        n2 += ViewCompat.getMinimumHeight(childOnOffset);
                    }
                    int n3;
                    if (topBottomOffsetForScrollingSibling < (n2 + n) / 2) {
                        n3 = n2;
                    }
                    else {
                        n3 = n;
                    }
                    this.animateOffsetTo(coordinatorLayout, appBarLayout, MathUtils.constrain(n3, -appBarLayout.getTotalScrollRange(), 0));
                }
            }
        }
        
        @Override
        boolean canDragView(final AppBarLayout appBarLayout) {
            boolean canDrag = true;
            if (this.mOnDragCallback != null) {
                canDrag = this.mOnDragCallback.canDrag(appBarLayout);
            }
            else if (this.mLastNestedScrollingChildRef != null) {
                final View view = this.mLastNestedScrollingChildRef.get();
                if (view == null || !view.isShown() || ViewCompat.canScrollVertically(view, -1)) {
                    return false;
                }
            }
            return canDrag;
        }
        
        @Override
        int getMaxDragOffset(final AppBarLayout appBarLayout) {
            return -appBarLayout.getDownNestedScrollRange();
        }
        
        @Override
        int getScrollRangeForDragFling(final AppBarLayout appBarLayout) {
            return appBarLayout.getTotalScrollRange();
        }
        
        @Override
        int getTopBottomOffsetForScrollingSibling() {
            return this.getTopAndBottomOffset() + this.mOffsetDelta;
        }
        
        @Override
        void onFlingFinished(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout) {
            this.snapToChildIfNeeded(coordinatorLayout, appBarLayout);
        }
        
        @Override
        public boolean onLayoutChild(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final int n) {
            final boolean onLayoutChild = super.onLayoutChild(coordinatorLayout, appBarLayout, n);
            final int access$500 = appBarLayout.getPendingAction();
            if (access$500 != 0) {
                boolean b;
                if ((access$500 & 0x4) != 0x0) {
                    b = true;
                }
                else {
                    b = false;
                }
                if ((access$500 & 0x2) != 0x0) {
                    final int n2 = -appBarLayout.getUpNestedPreScrollRange();
                    if (b) {
                        this.animateOffsetTo(coordinatorLayout, appBarLayout, n2);
                    }
                    else {
                        this.setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, n2);
                    }
                }
                else if ((access$500 & 0x1) != 0x0) {
                    if (b) {
                        this.animateOffsetTo(coordinatorLayout, appBarLayout, 0);
                    }
                    else {
                        this.setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, 0);
                    }
                }
            }
            else if (this.mOffsetToChildIndexOnLayout >= 0) {
                final View child = appBarLayout.getChildAt(this.mOffsetToChildIndexOnLayout);
                final int n3 = -child.getBottom();
                int topAndBottomOffset;
                if (this.mOffsetToChildIndexOnLayoutIsMinHeight) {
                    topAndBottomOffset = n3 + ViewCompat.getMinimumHeight(child);
                }
                else {
                    topAndBottomOffset = n3 + Math.round(child.getHeight() * this.mOffsetToChildIndexOnLayoutPerc);
                }
                this.setTopAndBottomOffset(topAndBottomOffset);
            }
            appBarLayout.resetPendingAction();
            this.mOffsetToChildIndexOnLayout = -1;
            this.setTopAndBottomOffset(MathUtils.constrain(this.getTopAndBottomOffset(), -appBarLayout.getTotalScrollRange(), 0));
            this.dispatchOffsetUpdates(appBarLayout);
            return onLayoutChild;
        }
        
        public boolean onNestedFling(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final View view, final float n, final float n2, final boolean b) {
            boolean fling;
            if (!b) {
                fling = this.fling(coordinatorLayout, appBarLayout, -appBarLayout.getTotalScrollRange(), 0, -n2);
            }
            else if (n2 < 0.0f) {
                final int n3 = -appBarLayout.getTotalScrollRange() + appBarLayout.getDownNestedPreScrollRange();
                final int topBottomOffsetForScrollingSibling = this.getTopBottomOffsetForScrollingSibling();
                fling = false;
                if (topBottomOffsetForScrollingSibling < n3) {
                    this.animateOffsetTo(coordinatorLayout, appBarLayout, n3);
                    fling = true;
                }
            }
            else {
                final int n4 = -appBarLayout.getUpNestedPreScrollRange();
                final int topBottomOffsetForScrollingSibling2 = this.getTopBottomOffsetForScrollingSibling();
                fling = false;
                if (topBottomOffsetForScrollingSibling2 > n4) {
                    this.animateOffsetTo(coordinatorLayout, appBarLayout, n4);
                    fling = true;
                }
            }
            return this.mWasNestedFlung = fling;
        }
        
        public void onNestedPreScroll(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final View view, final int n, final int n2, final int[] array) {
            if (n2 != 0 && !this.mSkipNestedPreScroll) {
                int n3;
                int n4;
                if (n2 < 0) {
                    n3 = -appBarLayout.getTotalScrollRange();
                    n4 = n3 + appBarLayout.getDownNestedPreScrollRange();
                }
                else {
                    n3 = -appBarLayout.getUpNestedPreScrollRange();
                    n4 = 0;
                }
                array[1] = this.scroll(coordinatorLayout, appBarLayout, n2, n3, n4);
            }
        }
        
        public void onNestedScroll(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final View view, final int n, final int n2, final int n3, final int n4) {
            if (n4 < 0) {
                this.scroll(coordinatorLayout, appBarLayout, n4, -appBarLayout.getDownNestedScrollRange(), 0);
                this.mSkipNestedPreScroll = true;
                return;
            }
            this.mSkipNestedPreScroll = false;
        }
        
        public void onRestoreInstanceState(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final Parcelable parcelable) {
            if (parcelable instanceof SavedState) {
                final SavedState savedState = (SavedState)parcelable;
                super.onRestoreInstanceState(coordinatorLayout, appBarLayout, savedState.getSuperState());
                this.mOffsetToChildIndexOnLayout = savedState.firstVisibleChildIndex;
                this.mOffsetToChildIndexOnLayoutPerc = savedState.firstVisibileChildPercentageShown;
                this.mOffsetToChildIndexOnLayoutIsMinHeight = savedState.firstVisibileChildAtMinimumHeight;
                return;
            }
            super.onRestoreInstanceState(coordinatorLayout, appBarLayout, parcelable);
            this.mOffsetToChildIndexOnLayout = -1;
        }
        
        public Parcelable onSaveInstanceState(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout) {
            final Parcelable onSaveInstanceState = super.onSaveInstanceState(coordinatorLayout, appBarLayout);
            final int topAndBottomOffset = this.getTopAndBottomOffset();
            for (int i = 0; i < appBarLayout.getChildCount(); ++i) {
                final View child = appBarLayout.getChildAt(i);
                final int n = topAndBottomOffset + child.getBottom();
                if (topAndBottomOffset + child.getTop() <= 0 && n >= 0) {
                    final SavedState savedState = new SavedState(onSaveInstanceState);
                    savedState.firstVisibleChildIndex = i;
                    savedState.firstVisibileChildAtMinimumHeight = (n == ViewCompat.getMinimumHeight(child));
                    savedState.firstVisibileChildPercentageShown = n / child.getHeight();
                    return (Parcelable)savedState;
                }
            }
            return onSaveInstanceState;
        }
        
        public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final View view, final View view2, final int n) {
            final boolean b = (n & 0x2) != 0x0 && appBarLayout.hasScrollableChildren() && coordinatorLayout.getHeight() - view.getHeight() <= appBarLayout.getHeight();
            if (b && this.mAnimator != null) {
                this.mAnimator.cancel();
            }
            this.mLastNestedScrollingChildRef = null;
            return b;
        }
        
        public void onStopNestedScroll(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final View view) {
            if (!this.mWasNestedFlung) {
                this.snapToChildIfNeeded(coordinatorLayout, appBarLayout);
            }
            this.mSkipNestedPreScroll = false;
            this.mWasNestedFlung = false;
            this.mLastNestedScrollingChildRef = new WeakReference<View>(view);
        }
        
        public void setDragCallback(@Nullable final DragCallback mOnDragCallback) {
            this.mOnDragCallback = mOnDragCallback;
        }
        
        @Override
        int setHeaderTopBottomOffset(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final int n, final int n2, final int n3) {
            final int topBottomOffsetForScrollingSibling = this.getTopBottomOffsetForScrollingSibling();
            if (n2 != 0 && topBottomOffsetForScrollingSibling >= n2 && topBottomOffsetForScrollingSibling <= n3) {
                final int constrain = MathUtils.constrain(n, n2, n3);
                int n4 = 0;
                if (topBottomOffsetForScrollingSibling != constrain) {
                    int interpolateOffset;
                    if (appBarLayout.hasChildWithInterpolator()) {
                        interpolateOffset = this.interpolateOffset(appBarLayout, constrain);
                    }
                    else {
                        interpolateOffset = constrain;
                    }
                    final boolean setTopAndBottomOffset = this.setTopAndBottomOffset(interpolateOffset);
                    n4 = topBottomOffsetForScrollingSibling - constrain;
                    this.mOffsetDelta = constrain - interpolateOffset;
                    if (!setTopAndBottomOffset && appBarLayout.hasChildWithInterpolator()) {
                        coordinatorLayout.dispatchDependentViewsChanged((View)appBarLayout);
                    }
                    this.dispatchOffsetUpdates(appBarLayout);
                }
                return n4;
            }
            return this.mOffsetDelta = 0;
        }
        
        public abstract static class DragCallback
        {
            public abstract boolean canDrag(@NonNull final AppBarLayout p0);
        }
        
        protected static class SavedState extends View$BaseSavedState
        {
            public static final Parcelable$Creator<SavedState> CREATOR;
            boolean firstVisibileChildAtMinimumHeight;
            float firstVisibileChildPercentageShown;
            int firstVisibleChildIndex;
            
            static {
                CREATOR = ParcelableCompat.newCreator((ParcelableCompatCreatorCallbacks<SavedState>)new ParcelableCompatCreatorCallbacks<SavedState>() {
                    @Override
                    public SavedState createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
                        return new SavedState(parcel, classLoader);
                    }
                    
                    @Override
                    public SavedState[] newArray(final int n) {
                        return new SavedState[n];
                    }
                });
            }
            
            public SavedState(final Parcel parcel, final ClassLoader classLoader) {
                super(parcel);
                this.firstVisibleChildIndex = parcel.readInt();
                this.firstVisibileChildPercentageShown = parcel.readFloat();
                this.firstVisibileChildAtMinimumHeight = (parcel.readByte() != 0);
            }
            
            public SavedState(final Parcelable parcelable) {
                super(parcelable);
            }
            
            public void writeToParcel(final Parcel parcel, final int n) {
                super.writeToParcel(parcel, n);
                parcel.writeInt(this.firstVisibleChildIndex);
                parcel.writeFloat(this.firstVisibileChildPercentageShown);
                boolean b;
                if (this.firstVisibileChildAtMinimumHeight) {
                    b = true;
                }
                else {
                    b = false;
                }
                parcel.writeByte((byte)(byte)(b ? 1 : 0));
            }
        }
    }
    
    public static class LayoutParams extends LinearLayout$LayoutParams
    {
        static final int FLAG_QUICK_RETURN = 5;
        static final int FLAG_SNAP = 17;
        public static final int SCROLL_FLAG_ENTER_ALWAYS = 4;
        public static final int SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED = 8;
        public static final int SCROLL_FLAG_EXIT_UNTIL_COLLAPSED = 2;
        public static final int SCROLL_FLAG_SCROLL = 1;
        public static final int SCROLL_FLAG_SNAP = 16;
        int mScrollFlags;
        Interpolator mScrollInterpolator;
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
            this.mScrollFlags = 1;
        }
        
        public LayoutParams(final int n, final int n2, final float n3) {
            super(n, n2, n3);
            this.mScrollFlags = 1;
        }
        
        public LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
            this.mScrollFlags = 1;
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.AppBarLayout_LayoutParams);
            this.mScrollFlags = obtainStyledAttributes.getInt(R.styleable.AppBarLayout_LayoutParams_layout_scrollFlags, 0);
            if (obtainStyledAttributes.hasValue(R.styleable.AppBarLayout_LayoutParams_layout_scrollInterpolator)) {
                this.mScrollInterpolator = android.view.animation.AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(R.styleable.AppBarLayout_LayoutParams_layout_scrollInterpolator, 0));
            }
            obtainStyledAttributes.recycle();
        }
        
        public LayoutParams(final LayoutParams layoutParams) {
            super((LinearLayout$LayoutParams)layoutParams);
            this.mScrollFlags = 1;
            this.mScrollFlags = layoutParams.mScrollFlags;
            this.mScrollInterpolator = layoutParams.mScrollInterpolator;
        }
        
        public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
            this.mScrollFlags = 1;
        }
        
        public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            super(viewGroup$MarginLayoutParams);
            this.mScrollFlags = 1;
        }
        
        public LayoutParams(final LinearLayout$LayoutParams linearLayout$LayoutParams) {
            super(linearLayout$LayoutParams);
            this.mScrollFlags = 1;
        }
        
        public int getScrollFlags() {
            return this.mScrollFlags;
        }
        
        public Interpolator getScrollInterpolator() {
            return this.mScrollInterpolator;
        }
        
        public void setScrollFlags(final int mScrollFlags) {
            this.mScrollFlags = mScrollFlags;
        }
        
        public void setScrollInterpolator(final Interpolator mScrollInterpolator) {
            this.mScrollInterpolator = mScrollInterpolator;
        }
        
        @Retention(RetentionPolicy.SOURCE)
        public @interface ScrollFlags {
        }
    }
    
    public interface OnOffsetChangedListener
    {
        void onOffsetChanged(final AppBarLayout p0, final int p1);
    }
    
    public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior
    {
        public ScrollingViewBehavior() {
        }
        
        public ScrollingViewBehavior(final Context context, final AttributeSet set) {
            super(context, set);
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.ScrollingViewBehavior_Params);
            this.setOverlayTop(obtainStyledAttributes.getDimensionPixelSize(R.styleable.ScrollingViewBehavior_Params_behavior_overlapTop, 0));
            obtainStyledAttributes.recycle();
        }
        
        private static int getAppBarLayoutOffset(final AppBarLayout appBarLayout) {
            final CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams)appBarLayout.getLayoutParams()).getBehavior();
            if (behavior instanceof AppBarLayout.Behavior) {
                return ((AppBarLayout.Behavior)behavior).getTopBottomOffsetForScrollingSibling();
            }
            return 0;
        }
        
        private void offsetChildAsNeeded(final CoordinatorLayout coordinatorLayout, final View view, final View view2) {
            final CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams)view2.getLayoutParams()).getBehavior();
            if (behavior instanceof AppBarLayout.Behavior) {
                final AppBarLayout.Behavior behavior2 = (AppBarLayout.Behavior)behavior;
                behavior2.getTopBottomOffsetForScrollingSibling();
                view.offsetTopAndBottom(view2.getBottom() - view.getTop() + behavior2.mOffsetDelta + this.getVerticalLayoutGap() - this.getOverlapPixelsForOffset(view2));
            }
        }
        
        @Override
        View findFirstDependency(final List<View> list) {
            for (int i = 0; i < list.size(); ++i) {
                final View view = list.get(i);
                if (view instanceof AppBarLayout) {
                    return view;
                }
            }
            return null;
        }
        
        @Override
        float getOverlapRatioForOffset(final View view) {
            if (view instanceof AppBarLayout) {
                final AppBarLayout appBarLayout = (AppBarLayout)view;
                final int totalScrollRange = appBarLayout.getTotalScrollRange();
                final int access$200 = appBarLayout.getDownNestedPreScrollRange();
                final int appBarLayoutOffset = getAppBarLayoutOffset(appBarLayout);
                if (access$200 == 0 || totalScrollRange + appBarLayoutOffset > access$200) {
                    final int n = totalScrollRange - access$200;
                    if (n != 0) {
                        return 1.0f + appBarLayoutOffset / n;
                    }
                }
            }
            return 0.0f;
        }
        
        @Override
        int getScrollRange(final View view) {
            if (view instanceof AppBarLayout) {
                return ((AppBarLayout)view).getTotalScrollRange();
            }
            return super.getScrollRange(view);
        }
        
        @Override
        public boolean layoutDependsOn(final CoordinatorLayout coordinatorLayout, final View view, final View view2) {
            return view2 instanceof AppBarLayout;
        }
        
        @Override
        public boolean onDependentViewChanged(final CoordinatorLayout coordinatorLayout, final View view, final View view2) {
            this.offsetChildAsNeeded(coordinatorLayout, view, view2);
            return false;
        }
    }
}
