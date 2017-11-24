package android.support.design.widget;

import android.support.v4.widget.*;
import java.lang.ref.*;
import android.content.*;
import android.util.*;
import android.support.design.*;
import android.content.res.*;
import android.support.v4.view.*;
import android.support.annotation.*;
import android.view.*;
import android.os.*;
import java.lang.annotation.*;

public class BottomSheetBehavior<V extends View> extends Behavior<V>
{
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;
    private int mActivePointerId;
    private BottomSheetCallback mCallback;
    private final ViewDragHelper.Callback mDragCallback;
    private boolean mHideable;
    private boolean mIgnoreEvents;
    private int mInitialY;
    private int mLastNestedScrollDy;
    private int mMaxOffset;
    private float mMaximumVelocity;
    private int mMinOffset;
    private boolean mNestedScrolled;
    private WeakReference<View> mNestedScrollingChildRef;
    private int mParentHeight;
    private int mPeekHeight;
    private int mState;
    private boolean mTouchingScrollingChild;
    private VelocityTracker mVelocityTracker;
    private ViewDragHelper mViewDragHelper;
    private WeakReference<V> mViewRef;
    
    public BottomSheetBehavior() {
        this.mState = 4;
        this.mDragCallback = new ViewDragHelper.Callback() {
            @Override
            public int clampViewPositionHorizontal(final View view, final int n, final int n2) {
                return view.getLeft();
            }
            
            @Override
            public int clampViewPositionVertical(final View view, final int n, final int n2) {
                final int access$700 = BottomSheetBehavior.this.mMinOffset;
                int n3;
                if (BottomSheetBehavior.this.mHideable) {
                    n3 = BottomSheetBehavior.this.mParentHeight;
                }
                else {
                    n3 = BottomSheetBehavior.this.mMaxOffset;
                }
                return MathUtils.constrain(n, access$700, n3);
            }
            
            @Override
            public int getViewVerticalDragRange(final View view) {
                if (BottomSheetBehavior.this.mHideable) {
                    return BottomSheetBehavior.this.mParentHeight - BottomSheetBehavior.this.mMinOffset;
                }
                return BottomSheetBehavior.this.mMaxOffset - BottomSheetBehavior.this.mMinOffset;
            }
            
            @Override
            public void onViewDragStateChanged(final int n) {
                if (n == 1) {
                    BottomSheetBehavior.this.setStateInternal(1);
                }
            }
            
            @Override
            public void onViewPositionChanged(final View view, final int n, final int n2, final int n3, final int n4) {
                BottomSheetBehavior.this.dispatchOnSlide(n2);
            }
            
            @Override
            public void onViewReleased(final View view, final float n, final float n2) {
                int n3;
                int n4;
                if (n2 < 0.0f) {
                    n3 = BottomSheetBehavior.this.mMinOffset;
                    n4 = 3;
                }
                else if (BottomSheetBehavior.this.mHideable && BottomSheetBehavior.this.shouldHide(view, n2)) {
                    n3 = BottomSheetBehavior.this.mParentHeight;
                    n4 = 5;
                }
                else if (n2 == 0.0f) {
                    final int top = view.getTop();
                    if (Math.abs(top - BottomSheetBehavior.this.mMinOffset) < Math.abs(top - BottomSheetBehavior.this.mMaxOffset)) {
                        n3 = BottomSheetBehavior.this.mMinOffset;
                        n4 = 3;
                    }
                    else {
                        n3 = BottomSheetBehavior.this.mMaxOffset;
                        n4 = 4;
                    }
                }
                else {
                    n3 = BottomSheetBehavior.this.mMaxOffset;
                    n4 = 4;
                }
                if (BottomSheetBehavior.this.mViewDragHelper.settleCapturedViewAt(view.getLeft(), n3)) {
                    BottomSheetBehavior.this.setStateInternal(2);
                    ViewCompat.postOnAnimation(view, new SettleRunnable(view, n4));
                    return;
                }
                BottomSheetBehavior.this.setStateInternal(n4);
            }
            
            @Override
            public boolean tryCaptureView(final View view, final int n) {
                boolean b = true;
                if (BottomSheetBehavior.this.mState != (b ? 1 : 0) && !BottomSheetBehavior.this.mTouchingScrollingChild) {
                    if (BottomSheetBehavior.this.mState == 3 && BottomSheetBehavior.this.mActivePointerId == n) {
                        final View view2 = (View)BottomSheetBehavior.this.mNestedScrollingChildRef.get();
                        if (view2 != null && ViewCompat.canScrollVertically(view2, -1)) {
                            return false;
                        }
                    }
                    if (BottomSheetBehavior.this.mViewRef == null || BottomSheetBehavior.this.mViewRef.get() != view) {
                        b = false;
                    }
                    return b;
                }
                return false;
            }
        };
    }
    
    public BottomSheetBehavior(final Context context, final AttributeSet set) {
        super(context, set);
        this.mState = 4;
        this.mDragCallback = new ViewDragHelper.Callback() {
            @Override
            public int clampViewPositionHorizontal(final View view, final int n, final int n2) {
                return view.getLeft();
            }
            
            @Override
            public int clampViewPositionVertical(final View view, final int n, final int n2) {
                final int access$700 = BottomSheetBehavior.this.mMinOffset;
                int n3;
                if (BottomSheetBehavior.this.mHideable) {
                    n3 = BottomSheetBehavior.this.mParentHeight;
                }
                else {
                    n3 = BottomSheetBehavior.this.mMaxOffset;
                }
                return MathUtils.constrain(n, access$700, n3);
            }
            
            @Override
            public int getViewVerticalDragRange(final View view) {
                if (BottomSheetBehavior.this.mHideable) {
                    return BottomSheetBehavior.this.mParentHeight - BottomSheetBehavior.this.mMinOffset;
                }
                return BottomSheetBehavior.this.mMaxOffset - BottomSheetBehavior.this.mMinOffset;
            }
            
            @Override
            public void onViewDragStateChanged(final int n) {
                if (n == 1) {
                    BottomSheetBehavior.this.setStateInternal(1);
                }
            }
            
            @Override
            public void onViewPositionChanged(final View view, final int n, final int n2, final int n3, final int n4) {
                BottomSheetBehavior.this.dispatchOnSlide(n2);
            }
            
            @Override
            public void onViewReleased(final View view, final float n, final float n2) {
                int n3;
                int n4;
                if (n2 < 0.0f) {
                    n3 = BottomSheetBehavior.this.mMinOffset;
                    n4 = 3;
                }
                else if (BottomSheetBehavior.this.mHideable && BottomSheetBehavior.this.shouldHide(view, n2)) {
                    n3 = BottomSheetBehavior.this.mParentHeight;
                    n4 = 5;
                }
                else if (n2 == 0.0f) {
                    final int top = view.getTop();
                    if (Math.abs(top - BottomSheetBehavior.this.mMinOffset) < Math.abs(top - BottomSheetBehavior.this.mMaxOffset)) {
                        n3 = BottomSheetBehavior.this.mMinOffset;
                        n4 = 3;
                    }
                    else {
                        n3 = BottomSheetBehavior.this.mMaxOffset;
                        n4 = 4;
                    }
                }
                else {
                    n3 = BottomSheetBehavior.this.mMaxOffset;
                    n4 = 4;
                }
                if (BottomSheetBehavior.this.mViewDragHelper.settleCapturedViewAt(view.getLeft(), n3)) {
                    BottomSheetBehavior.this.setStateInternal(2);
                    ViewCompat.postOnAnimation(view, new SettleRunnable(view, n4));
                    return;
                }
                BottomSheetBehavior.this.setStateInternal(n4);
            }
            
            @Override
            public boolean tryCaptureView(final View view, final int n) {
                boolean b = true;
                if (BottomSheetBehavior.this.mState != (b ? 1 : 0) && !BottomSheetBehavior.this.mTouchingScrollingChild) {
                    if (BottomSheetBehavior.this.mState == 3 && BottomSheetBehavior.this.mActivePointerId == n) {
                        final View view2 = (View)BottomSheetBehavior.this.mNestedScrollingChildRef.get();
                        if (view2 != null && ViewCompat.canScrollVertically(view2, -1)) {
                            return false;
                        }
                    }
                    if (BottomSheetBehavior.this.mViewRef == null || BottomSheetBehavior.this.mViewRef.get() != view) {
                        b = false;
                    }
                    return b;
                }
                return false;
            }
        };
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.BottomSheetBehavior_Params);
        this.setPeekHeight(obtainStyledAttributes.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Params_behavior_peekHeight, 0));
        this.setHideable(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Params_behavior_hideable, false));
        obtainStyledAttributes.recycle();
        this.mMaximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
    
    private void dispatchOnSlide(final int n) {
        final View view = this.mViewRef.get();
        if (view != null && this.mCallback != null) {
            if (n <= this.mMaxOffset) {
                this.mCallback.onSlide(view, (this.mMaxOffset - n) / (this.mMaxOffset - this.mMinOffset));
                return;
            }
            this.mCallback.onSlide(view, (this.mMaxOffset - n) / this.mPeekHeight);
        }
    }
    
    private View findScrollingChild(final View view) {
        if (view instanceof NestedScrollingChild) {
            return view;
        }
        if (view instanceof ViewGroup) {
            final ViewGroup viewGroup = (ViewGroup)view;
            for (int i = 0; i < viewGroup.getChildCount(); ++i) {
                final View scrollingChild = this.findScrollingChild(viewGroup.getChildAt(i));
                if (scrollingChild != null) {
                    return scrollingChild;
                }
            }
        }
        return null;
    }
    
    public static <V extends View> BottomSheetBehavior<V> from(final V v) {
        final ViewGroup$LayoutParams layoutParams = v.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        final Behavior behavior = ((LayoutParams)layoutParams).getBehavior();
        if (!(behavior instanceof BottomSheetBehavior)) {
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        }
        return (BottomSheetBehavior<V>)behavior;
    }
    
    private float getYVelocity() {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
        return VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mActivePointerId);
    }
    
    private void reset() {
        this.mActivePointerId = -1;
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }
    
    private void setStateInternal(final int mState) {
        if (this.mState != mState) {
            this.mState = mState;
            final View view = this.mViewRef.get();
            if (view != null && this.mCallback != null) {
                this.mCallback.onStateChanged(view, mState);
            }
        }
    }
    
    private boolean shouldHide(final View view, final float n) {
        return view.getTop() >= this.mMaxOffset && Math.abs(view.getTop() + 0.1f * n - this.mMaxOffset) / this.mPeekHeight > 0.5f;
    }
    
    public final int getPeekHeight() {
        return this.mPeekHeight;
    }
    
    public final int getState() {
        return this.mState;
    }
    
    public boolean isHideable() {
        return this.mHideable;
    }
    
    @Override
    public boolean onInterceptTouchEvent(final CoordinatorLayout coordinatorLayout, final V v, final MotionEvent motionEvent) {
        boolean mTouchingScrollingChild = true;
        if (!v.isShown()) {
            return false;
        }
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0) {
            this.reset();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        switch (actionMasked) {
            case 1:
            case 3: {
                this.mTouchingScrollingChild = false;
                this.mActivePointerId = -1;
                if (this.mIgnoreEvents) {
                    return this.mIgnoreEvents = false;
                }
                break;
            }
            case 0: {
                final int n = (int)motionEvent.getX();
                this.mInitialY = (int)motionEvent.getY();
                final View view = this.mNestedScrollingChildRef.get();
                if (view != null && coordinatorLayout.isPointInChildBounds(view, n, this.mInitialY)) {
                    this.mActivePointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.mTouchingScrollingChild = mTouchingScrollingChild;
                }
                this.mIgnoreEvents = (this.mActivePointerId == -1 && !coordinatorLayout.isPointInChildBounds(v, n, this.mInitialY) && mTouchingScrollingChild);
                break;
            }
        }
        if (!this.mIgnoreEvents && this.mViewDragHelper.shouldInterceptTouchEvent(motionEvent)) {
            return mTouchingScrollingChild;
        }
        final View view2 = this.mNestedScrollingChildRef.get();
        if (actionMasked != 2 || view2 == null || this.mIgnoreEvents || this.mState == (mTouchingScrollingChild ? 1 : 0) || coordinatorLayout.isPointInChildBounds(view2, (int)motionEvent.getX(), (int)motionEvent.getY()) || Math.abs(this.mInitialY - motionEvent.getY()) <= this.mViewDragHelper.getTouchSlop()) {
            mTouchingScrollingChild = false;
        }
        return mTouchingScrollingChild;
    }
    
    @Override
    public boolean onLayoutChild(final CoordinatorLayout coordinatorLayout, final V v, final int n) {
        if (this.mState != 1 && this.mState != 2) {
            coordinatorLayout.onLayoutChild(v, n);
        }
        this.mParentHeight = coordinatorLayout.getHeight();
        this.mMinOffset = Math.max(0, this.mParentHeight - v.getHeight());
        this.mMaxOffset = Math.max(this.mParentHeight - this.mPeekHeight, this.mMinOffset);
        if (this.mState == 3) {
            ViewCompat.offsetTopAndBottom(v, this.mMinOffset);
        }
        else if (this.mHideable && this.mState == 5) {
            ViewCompat.offsetTopAndBottom(v, this.mParentHeight);
        }
        else if (this.mState == 4) {
            ViewCompat.offsetTopAndBottom(v, this.mMaxOffset);
        }
        if (this.mViewDragHelper == null) {
            this.mViewDragHelper = ViewDragHelper.create(coordinatorLayout, this.mDragCallback);
        }
        this.mViewRef = new WeakReference<V>(v);
        this.mNestedScrollingChildRef = new WeakReference<View>(this.findScrollingChild(v));
        return true;
    }
    
    @Override
    public boolean onNestedPreFling(final CoordinatorLayout coordinatorLayout, final V v, final View view, final float n, final float n2) {
        return view == this.mNestedScrollingChildRef.get() && (this.mState != 3 || super.onNestedPreFling(coordinatorLayout, v, view, n, n2));
    }
    
    @Override
    public void onNestedPreScroll(final CoordinatorLayout coordinatorLayout, final V v, final View view, final int n, final int mLastNestedScrollDy, final int[] array) {
        if (view != this.mNestedScrollingChildRef.get()) {
            return;
        }
        final int top = v.getTop();
        final int n2 = top - mLastNestedScrollDy;
        if (mLastNestedScrollDy > 0) {
            if (n2 < this.mMinOffset) {
                array[1] = top - this.mMinOffset;
                ViewCompat.offsetTopAndBottom(v, -array[1]);
                this.setStateInternal(3);
            }
            else {
                ViewCompat.offsetTopAndBottom(v, -(array[1] = mLastNestedScrollDy));
                this.setStateInternal(1);
            }
        }
        else if (mLastNestedScrollDy < 0 && !ViewCompat.canScrollVertically(view, -1)) {
            if (n2 <= this.mMaxOffset || this.mHideable) {
                ViewCompat.offsetTopAndBottom(v, -(array[1] = mLastNestedScrollDy));
                this.setStateInternal(1);
            }
            else {
                array[1] = top - this.mMaxOffset;
                ViewCompat.offsetTopAndBottom(v, -array[1]);
                this.setStateInternal(4);
            }
        }
        this.dispatchOnSlide(v.getTop());
        this.mLastNestedScrollDy = mLastNestedScrollDy;
        this.mNestedScrolled = true;
    }
    
    @Override
    public void onRestoreInstanceState(final CoordinatorLayout coordinatorLayout, final V v, final Parcelable parcelable) {
        final SavedState savedState = (SavedState)parcelable;
        super.onRestoreInstanceState(coordinatorLayout, v, savedState.getSuperState());
        if (savedState.state == 1 || savedState.state == 2) {
            this.mState = 4;
            return;
        }
        this.mState = savedState.state;
    }
    
    @Override
    public Parcelable onSaveInstanceState(final CoordinatorLayout coordinatorLayout, final V v) {
        return (Parcelable)new SavedState(super.onSaveInstanceState(coordinatorLayout, v), this.mState);
    }
    
    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final V v, final View view, final View view2, final int n) {
        this.mLastNestedScrollDy = 0;
        this.mNestedScrolled = false;
        final int n2 = n & 0x2;
        boolean b = false;
        if (n2 != 0) {
            b = true;
        }
        return b;
    }
    
    @Override
    public void onStopNestedScroll(final CoordinatorLayout coordinatorLayout, final V v, final View view) {
        if (v.getTop() == this.mMinOffset) {
            this.setStateInternal(3);
        }
        else if (view == this.mNestedScrollingChildRef.get() && this.mNestedScrolled) {
            int n;
            int stateInternal;
            if (this.mLastNestedScrollDy > 0) {
                n = this.mMinOffset;
                stateInternal = 3;
            }
            else if (this.mHideable && this.shouldHide(v, this.getYVelocity())) {
                n = this.mParentHeight;
                stateInternal = 5;
            }
            else if (this.mLastNestedScrollDy == 0) {
                final int top = v.getTop();
                if (Math.abs(top - this.mMinOffset) < Math.abs(top - this.mMaxOffset)) {
                    n = this.mMinOffset;
                    stateInternal = 3;
                }
                else {
                    n = this.mMaxOffset;
                    stateInternal = 4;
                }
            }
            else {
                n = this.mMaxOffset;
                stateInternal = 4;
            }
            if (this.mViewDragHelper.smoothSlideViewTo(v, v.getLeft(), n)) {
                this.setStateInternal(2);
                ViewCompat.postOnAnimation(v, new SettleRunnable(v, stateInternal));
            }
            else {
                this.setStateInternal(stateInternal);
            }
            this.mNestedScrolled = false;
        }
    }
    
    @Override
    public boolean onTouchEvent(final CoordinatorLayout coordinatorLayout, final V v, final MotionEvent motionEvent) {
        boolean b = true;
        if (!v.isShown()) {
            b = false;
        }
        else {
            final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
            if (this.mState != (b ? 1 : 0) || actionMasked != 0) {
                this.mViewDragHelper.processTouchEvent(motionEvent);
                if (actionMasked == 0) {
                    this.reset();
                }
                if (this.mVelocityTracker == null) {
                    this.mVelocityTracker = VelocityTracker.obtain();
                }
                this.mVelocityTracker.addMovement(motionEvent);
                if (actionMasked == 2 && Math.abs(this.mInitialY - motionEvent.getY()) > this.mViewDragHelper.getTouchSlop()) {
                    this.mViewDragHelper.captureChildView(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
                    return b;
                }
            }
        }
        return b;
    }
    
    public void setBottomSheetCallback(final BottomSheetCallback mCallback) {
        this.mCallback = mCallback;
    }
    
    public void setHideable(final boolean mHideable) {
        this.mHideable = mHideable;
    }
    
    public final void setPeekHeight(final int n) {
        this.mPeekHeight = Math.max(0, n);
        this.mMaxOffset = this.mParentHeight - n;
    }
    
    public final void setState(final int n) {
        final View view = this.mViewRef.get();
        if (view != null) {
            int n2;
            if (n == 4) {
                n2 = this.mMaxOffset;
            }
            else if (n == 3) {
                n2 = this.mMinOffset;
            }
            else {
                if (!this.mHideable || n != 5) {
                    throw new IllegalArgumentException("Illegal state argument: " + n);
                }
                n2 = this.mParentHeight;
            }
            this.setStateInternal(2);
            if (this.mViewDragHelper.smoothSlideViewTo(view, view.getLeft(), n2)) {
                ViewCompat.postOnAnimation(view, new SettleRunnable(view, n));
            }
        }
    }
    
    public abstract static class BottomSheetCallback
    {
        public abstract void onSlide(@NonNull final View p0, final float p1);
        
        public abstract void onStateChanged(@NonNull final View p0, final int p1);
    }
    
    protected static class SavedState extends View$BaseSavedState
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        final int state;
        
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
        
        public SavedState(final Parcel parcel) {
            super(parcel);
            this.state = parcel.readInt();
        }
        
        public SavedState(final Parcelable parcelable, final int state) {
            super(parcelable);
            this.state = state;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            super.writeToParcel(parcel, n);
            parcel.writeInt(this.state);
        }
    }
    
    private class SettleRunnable implements Runnable
    {
        private final int mTargetState;
        private final View mView;
        
        SettleRunnable(final View mView, final int mTargetState) {
            this.mView = mView;
            this.mTargetState = mTargetState;
        }
        
        @Override
        public void run() {
            if (BottomSheetBehavior.this.mViewDragHelper != null && BottomSheetBehavior.this.mViewDragHelper.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.mView, this);
                return;
            }
            BottomSheetBehavior.this.setStateInternal(this.mTargetState);
        }
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }
}
