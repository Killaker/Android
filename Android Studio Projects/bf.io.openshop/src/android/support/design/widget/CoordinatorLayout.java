package android.support.design.widget;

import java.lang.reflect.*;
import android.content.*;
import android.support.design.*;
import android.content.res.*;
import android.text.*;
import java.util.*;
import android.support.v4.view.*;
import android.graphics.*;
import java.io.*;
import android.util.*;
import android.support.v4.graphics.drawable.*;
import android.graphics.drawable.*;
import android.support.annotation.*;
import android.support.v4.content.*;
import java.lang.annotation.*;
import android.view.*;
import android.os.*;
import android.support.v4.os.*;

public class CoordinatorLayout extends ViewGroup implements NestedScrollingParent
{
    static final Class<?>[] CONSTRUCTOR_PARAMS;
    static final CoordinatorLayoutInsetsHelper INSETS_HELPER;
    static final String TAG = "CoordinatorLayout";
    static final Comparator<View> TOP_SORTED_CHILDREN_COMPARATOR;
    private static final int TYPE_ON_INTERCEPT = 0;
    private static final int TYPE_ON_TOUCH = 1;
    static final String WIDGET_PACKAGE_NAME;
    static final ThreadLocal<Map<String, Constructor<Behavior>>> sConstructors;
    private View mBehaviorTouchView;
    private final List<View> mDependencySortedChildren;
    private boolean mDrawStatusBarBackground;
    private boolean mIsAttachedToWindow;
    private int[] mKeylines;
    private WindowInsetsCompat mLastInsets;
    final Comparator<View> mLayoutDependencyComparator;
    private boolean mNeedsPreDrawListener;
    private View mNestedScrollingDirectChild;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private View mNestedScrollingTarget;
    private ViewGroup$OnHierarchyChangeListener mOnHierarchyChangeListener;
    private OnPreDrawListener mOnPreDrawListener;
    private Paint mScrimPaint;
    private Drawable mStatusBarBackground;
    private final List<View> mTempDependenciesList;
    private final int[] mTempIntPair;
    private final List<View> mTempList1;
    private final Rect mTempRect1;
    private final Rect mTempRect2;
    private final Rect mTempRect3;
    
    static {
        final Package package1 = CoordinatorLayout.class.getPackage();
        String name;
        if (package1 != null) {
            name = package1.getName();
        }
        else {
            name = null;
        }
        WIDGET_PACKAGE_NAME = name;
        if (Build$VERSION.SDK_INT >= 21) {
            TOP_SORTED_CHILDREN_COMPARATOR = new ViewElevationComparator();
            INSETS_HELPER = new CoordinatorLayoutInsetsHelperLollipop();
        }
        else {
            TOP_SORTED_CHILDREN_COMPARATOR = null;
            INSETS_HELPER = null;
        }
        CONSTRUCTOR_PARAMS = new Class[] { Context.class, AttributeSet.class };
        sConstructors = new ThreadLocal<Map<String, Constructor<Behavior>>>();
    }
    
    public CoordinatorLayout(final Context context) {
        this(context, null);
    }
    
    public CoordinatorLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public CoordinatorLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mLayoutDependencyComparator = new Comparator<View>() {
            @Override
            public int compare(final View view, final View view2) {
                if (view == view2) {
                    return 0;
                }
                if (((LayoutParams)view.getLayoutParams()).dependsOn(CoordinatorLayout.this, view, view2)) {
                    return 1;
                }
                if (((LayoutParams)view2.getLayoutParams()).dependsOn(CoordinatorLayout.this, view2, view)) {
                    return -1;
                }
                return 0;
            }
        };
        this.mDependencySortedChildren = new ArrayList<View>();
        this.mTempList1 = new ArrayList<View>();
        this.mTempDependenciesList = new ArrayList<View>();
        this.mTempRect1 = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRect3 = new Rect();
        this.mTempIntPair = new int[2];
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        ThemeUtils.checkAppCompatTheme(context);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.CoordinatorLayout, n, R.style.Widget_Design_CoordinatorLayout);
        final int resourceId = obtainStyledAttributes.getResourceId(R.styleable.CoordinatorLayout_keylines, 0);
        if (resourceId != 0) {
            final Resources resources = context.getResources();
            this.mKeylines = resources.getIntArray(resourceId);
            final float density = resources.getDisplayMetrics().density;
            for (int length = this.mKeylines.length, i = 0; i < length; ++i) {
                final int[] mKeylines = this.mKeylines;
                mKeylines[i] *= (int)density;
            }
        }
        this.mStatusBarBackground = obtainStyledAttributes.getDrawable(R.styleable.CoordinatorLayout_statusBarBackground);
        obtainStyledAttributes.recycle();
        if (CoordinatorLayout.INSETS_HELPER != null) {
            CoordinatorLayout.INSETS_HELPER.setupForWindowInsets((View)this, new ApplyInsetsListener());
        }
        super.setOnHierarchyChangeListener((ViewGroup$OnHierarchyChangeListener)new HierarchyChangeListener());
    }
    
    private WindowInsetsCompat dispatchApplyWindowInsetsToBehaviors(WindowInsetsCompat onApplyWindowInsets) {
        if (onApplyWindowInsets.isConsumed()) {
            return onApplyWindowInsets;
        }
        for (int i = 0; i < this.getChildCount(); ++i) {
            final View child = this.getChildAt(i);
            if (ViewCompat.getFitsSystemWindows(child)) {
                final Behavior behavior = ((LayoutParams)child.getLayoutParams()).getBehavior();
                if (behavior != null) {
                    onApplyWindowInsets = behavior.onApplyWindowInsets(this, child, onApplyWindowInsets);
                    if (onApplyWindowInsets.isConsumed()) {
                        break;
                    }
                }
            }
        }
        return onApplyWindowInsets;
    }
    
    private int getKeyline(final int n) {
        if (this.mKeylines == null) {
            Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + n);
            return 0;
        }
        if (n < 0 || n >= this.mKeylines.length) {
            Log.e("CoordinatorLayout", "Keyline index " + n + " out of range for " + this);
            return 0;
        }
        return this.mKeylines[n];
    }
    
    private void getTopSortedChildren(final List<View> list) {
        list.clear();
        final boolean childrenDrawingOrderEnabled = this.isChildrenDrawingOrderEnabled();
        final int childCount = this.getChildCount();
        for (int i = childCount - 1; i >= 0; --i) {
            int childDrawingOrder;
            if (childrenDrawingOrderEnabled) {
                childDrawingOrder = this.getChildDrawingOrder(childCount, i);
            }
            else {
                childDrawingOrder = i;
            }
            list.add(this.getChildAt(childDrawingOrder));
        }
        if (CoordinatorLayout.TOP_SORTED_CHILDREN_COMPARATOR != null) {
            Collections.sort((List<Object>)list, (Comparator<? super Object>)CoordinatorLayout.TOP_SORTED_CHILDREN_COMPARATOR);
        }
    }
    
    private void layoutChild(final View view, final int n) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final Rect mTempRect1 = this.mTempRect1;
        mTempRect1.set(this.getPaddingLeft() + layoutParams.leftMargin, this.getPaddingTop() + layoutParams.topMargin, this.getWidth() - this.getPaddingRight() - layoutParams.rightMargin, this.getHeight() - this.getPaddingBottom() - layoutParams.bottomMargin);
        if (this.mLastInsets != null && ViewCompat.getFitsSystemWindows((View)this) && !ViewCompat.getFitsSystemWindows(view)) {
            mTempRect1.left += this.mLastInsets.getSystemWindowInsetLeft();
            mTempRect1.top += this.mLastInsets.getSystemWindowInsetTop();
            mTempRect1.right -= this.mLastInsets.getSystemWindowInsetRight();
            mTempRect1.bottom -= this.mLastInsets.getSystemWindowInsetBottom();
        }
        final Rect mTempRect2 = this.mTempRect2;
        GravityCompat.apply(resolveGravity(layoutParams.gravity), view.getMeasuredWidth(), view.getMeasuredHeight(), mTempRect1, mTempRect2, n);
        view.layout(mTempRect2.left, mTempRect2.top, mTempRect2.right, mTempRect2.bottom);
    }
    
    private void layoutChildWithAnchor(final View view, final View view2, final int n) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final Rect mTempRect1 = this.mTempRect1;
        final Rect mTempRect2 = this.mTempRect2;
        this.getDescendantRect(view2, mTempRect1);
        this.getDesiredAnchoredChildRect(view, n, mTempRect1, mTempRect2);
        view.layout(mTempRect2.left, mTempRect2.top, mTempRect2.right, mTempRect2.bottom);
    }
    
    private void layoutChildWithKeyline(final View view, int n, final int n2) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final int absoluteGravity = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(layoutParams.gravity), n2);
        final int n3 = absoluteGravity & 0x7;
        final int n4 = absoluteGravity & 0x70;
        final int width = this.getWidth();
        final int height = this.getHeight();
        final int measuredWidth = view.getMeasuredWidth();
        final int measuredHeight = view.getMeasuredHeight();
        if (n2 == 1) {
            n = width - n;
        }
        int n5 = this.getKeyline(n) - measuredWidth;
        switch (n3) {
            case 5: {
                n5 += measuredWidth;
                break;
            }
            case 1: {
                n5 += measuredWidth / 2;
                break;
            }
        }
        int n6 = 0;
        switch (n4) {
            case 80: {
                n6 = 0 + measuredHeight;
                break;
            }
            case 16: {
                n6 = 0 + measuredHeight / 2;
                break;
            }
        }
        final int max = Math.max(this.getPaddingLeft() + layoutParams.leftMargin, Math.min(n5, width - this.getPaddingRight() - measuredWidth - layoutParams.rightMargin));
        final int max2 = Math.max(this.getPaddingTop() + layoutParams.topMargin, Math.min(n6, height - this.getPaddingBottom() - measuredHeight - layoutParams.bottomMargin));
        view.layout(max, max2, max + measuredWidth, max2 + measuredHeight);
    }
    
    static Behavior parseBehavior(final Context context, final AttributeSet set, final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        Label_0151: {
            if (!s.startsWith(".")) {
                break Label_0151;
            }
            String s2 = context.getPackageName() + s;
            try {
                // iftrue(Label_0165:, s.indexOf(46) < 0)
            Label_0200_Outer:
                while (true) {
                Label_0200:
                    while (true) {
                    Block_6_Outer:
                        while (true) {
                            Map<String, Constructor<Behavior>> map = CoordinatorLayout.sConstructors.get();
                            if (map == null) {
                                map = new HashMap<String, Constructor<Behavior>>();
                                CoordinatorLayout.sConstructors.set(map);
                            }
                            Constructor<?> constructor = map.get(s2);
                            if (constructor == null) {
                                constructor = Class.forName(s2, true, context.getClassLoader()).getConstructor(CoordinatorLayout.CONSTRUCTOR_PARAMS);
                                constructor.setAccessible(true);
                                map.put(s2, (Constructor<Behavior>)constructor);
                            }
                            return constructor.newInstance(context, set);
                            while (true) {
                                s2 = s;
                                continue Block_6_Outer;
                                s2 = CoordinatorLayout.WIDGET_PACKAGE_NAME + '.' + s;
                                break Label_0200;
                                continue Label_0200_Outer;
                            }
                            continue Block_6_Outer;
                        }
                        Label_0203: {
                            s2 = s;
                        }
                        continue Label_0200;
                    }
                    Label_0165: {
                        continue Label_0200_Outer;
                    }
                }
            }
            // iftrue(Label_0203:, TextUtils.isEmpty((CharSequence)CoordinatorLayout.WIDGET_PACKAGE_NAME))
            catch (Exception ex) {
                throw new RuntimeException("Could not inflate Behavior subclass " + s2, ex);
            }
        }
    }
    
    private boolean performIntercept(final MotionEvent motionEvent, final int n) {
        boolean b = false;
        int n2 = 0;
        MotionEvent obtain = null;
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        final List<View> mTempList1 = this.mTempList1;
        this.getTopSortedChildren(mTempList1);
        for (int size = mTempList1.size(), i = 0; i < size; ++i) {
            final View mBehaviorTouchView = mTempList1.get(i);
            final LayoutParams layoutParams = (LayoutParams)mBehaviorTouchView.getLayoutParams();
            final Behavior behavior = layoutParams.getBehavior();
            if ((b || n2 != 0) && actionMasked != 0) {
                if (behavior != null) {
                    if (obtain == null) {
                        final long uptimeMillis = SystemClock.uptimeMillis();
                        obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                    }
                    switch (n) {
                        case 0: {
                            behavior.onInterceptTouchEvent(this, mBehaviorTouchView, obtain);
                            break;
                        }
                        case 1: {
                            behavior.onTouchEvent(this, mBehaviorTouchView, obtain);
                            break;
                        }
                    }
                }
            }
            else {
                if (!b && behavior != null) {
                    switch (n) {
                        case 0: {
                            b = behavior.onInterceptTouchEvent(this, mBehaviorTouchView, motionEvent);
                            break;
                        }
                        case 1: {
                            b = behavior.onTouchEvent(this, mBehaviorTouchView, motionEvent);
                            break;
                        }
                    }
                    if (b) {
                        this.mBehaviorTouchView = mBehaviorTouchView;
                    }
                }
                final boolean didBlockInteraction = layoutParams.didBlockInteraction();
                final boolean blockingInteractionBelow = layoutParams.isBlockingInteractionBelow(this, mBehaviorTouchView);
                if (blockingInteractionBelow && !didBlockInteraction) {
                    n2 = 1;
                }
                else {
                    n2 = 0;
                }
                if (blockingInteractionBelow && n2 == 0) {
                    break;
                }
            }
        }
        mTempList1.clear();
        return b;
    }
    
    private void prepareChildren() {
        this.mDependencySortedChildren.clear();
        for (int i = 0; i < this.getChildCount(); ++i) {
            final View child = this.getChildAt(i);
            this.getResolvedLayoutParams(child).findAnchorView(this, child);
            this.mDependencySortedChildren.add(child);
        }
        selectionSort(this.mDependencySortedChildren, this.mLayoutDependencyComparator);
    }
    
    private void resetTouchBehaviors() {
        if (this.mBehaviorTouchView != null) {
            final Behavior behavior = ((LayoutParams)this.mBehaviorTouchView.getLayoutParams()).getBehavior();
            if (behavior != null) {
                final long uptimeMillis = SystemClock.uptimeMillis();
                final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                behavior.onTouchEvent(this, this.mBehaviorTouchView, obtain);
                obtain.recycle();
            }
            this.mBehaviorTouchView = null;
        }
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            ((LayoutParams)this.getChildAt(i).getLayoutParams()).resetTouchBehaviorTracking();
        }
    }
    
    private static int resolveAnchoredChildGravity(int n) {
        if (n == 0) {
            n = 17;
        }
        return n;
    }
    
    private static int resolveGravity(int n) {
        if (n == 0) {
            n = 8388659;
        }
        return n;
    }
    
    private static int resolveKeylineGravity(int n) {
        if (n == 0) {
            n = 8388661;
        }
        return n;
    }
    
    private static void selectionSort(final List<View> list, final Comparator<View> comparator) {
        if (list != null && list.size() >= 2) {
            final View[] array = new View[list.size()];
            list.toArray(array);
            final int length = array.length;
            for (int i = 0; i < length; ++i) {
                int n = i;
                for (int j = i + 1; j < length; ++j) {
                    if (comparator.compare(array[j], array[n]) < 0) {
                        n = j;
                    }
                }
                if (i != n) {
                    final View view = array[n];
                    array[n] = array[i];
                    array[i] = view;
                }
            }
            list.clear();
            for (int k = 0; k < length; ++k) {
                list.add(array[k]);
            }
        }
    }
    
    private WindowInsetsCompat setWindowInsets(WindowInsetsCompat dispatchApplyWindowInsetsToBehaviors) {
        boolean willNotDraw = true;
        if (this.mLastInsets != dispatchApplyWindowInsetsToBehaviors) {
            this.mLastInsets = dispatchApplyWindowInsetsToBehaviors;
            this.mDrawStatusBarBackground = (dispatchApplyWindowInsetsToBehaviors != null && dispatchApplyWindowInsetsToBehaviors.getSystemWindowInsetTop() > 0 && willNotDraw);
            if (this.mDrawStatusBarBackground || this.getBackground() != null) {
                willNotDraw = false;
            }
            this.setWillNotDraw(willNotDraw);
            dispatchApplyWindowInsetsToBehaviors = this.dispatchApplyWindowInsetsToBehaviors(dispatchApplyWindowInsetsToBehaviors);
            this.requestLayout();
        }
        return dispatchApplyWindowInsetsToBehaviors;
    }
    
    void addPreDrawListener() {
        if (this.mIsAttachedToWindow) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new OnPreDrawListener();
            }
            this.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = true;
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof LayoutParams && super.checkLayoutParams(viewGroup$LayoutParams);
    }
    
    void dispatchDependentViewRemoved(final View view) {
        final int size = this.mDependencySortedChildren.size();
        boolean b = false;
        for (int i = 0; i < size; ++i) {
            final View view2 = this.mDependencySortedChildren.get(i);
            if (view2 == view) {
                b = true;
            }
            else if (b) {
                final LayoutParams layoutParams = (LayoutParams)view2.getLayoutParams();
                final Behavior behavior = layoutParams.getBehavior();
                if (behavior != null && layoutParams.dependsOn(this, view2, view)) {
                    behavior.onDependentViewRemoved(this, view2, view);
                }
            }
        }
    }
    
    public void dispatchDependentViewsChanged(final View view) {
        final int size = this.mDependencySortedChildren.size();
        boolean b = false;
        for (int i = 0; i < size; ++i) {
            final View view2 = this.mDependencySortedChildren.get(i);
            if (view2 == view) {
                b = true;
            }
            else if (b) {
                final LayoutParams layoutParams = (LayoutParams)view2.getLayoutParams();
                final Behavior behavior = layoutParams.getBehavior();
                if (behavior != null && layoutParams.dependsOn(this, view2, view)) {
                    behavior.onDependentViewChanged(this, view2, view);
                }
            }
        }
    }
    
    void dispatchOnDependentViewChanged(final boolean b) {
        final int layoutDirection = ViewCompat.getLayoutDirection((View)this);
        for (int size = this.mDependencySortedChildren.size(), i = 0; i < size; ++i) {
            final View view = this.mDependencySortedChildren.get(i);
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            for (int j = 0; j < i; ++j) {
                if (layoutParams.mAnchorDirectChild == this.mDependencySortedChildren.get(j)) {
                    this.offsetChildToAnchor(view, layoutDirection);
                }
            }
            final Rect mTempRect1 = this.mTempRect1;
            final Rect mTempRect2 = this.mTempRect2;
            this.getLastChildRect(view, mTempRect1);
            this.getChildRect(view, true, mTempRect2);
            if (!mTempRect1.equals((Object)mTempRect2)) {
                this.recordLastChildRect(view, mTempRect2);
                for (int k = i + 1; k < size; ++k) {
                    final View view2 = this.mDependencySortedChildren.get(k);
                    final LayoutParams layoutParams2 = (LayoutParams)view2.getLayoutParams();
                    final Behavior behavior = layoutParams2.getBehavior();
                    if (behavior != null && behavior.layoutDependsOn(this, view2, view)) {
                        if (!b && layoutParams2.getChangedAfterNestedScroll()) {
                            layoutParams2.resetChangedAfterNestedScroll();
                        }
                        else {
                            final boolean onDependentViewChanged = behavior.onDependentViewChanged(this, view2, view);
                            if (b) {
                                layoutParams2.setChangedAfterNestedScroll(onDependentViewChanged);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public boolean doViewsOverlap(final View view, final View view2) {
        if (view.getVisibility() == 0 && view2.getVisibility() == 0) {
            final Rect mTempRect1 = this.mTempRect1;
            this.getChildRect(view, view.getParent() != this, mTempRect1);
            final Rect mTempRect2 = this.mTempRect2;
            this.getChildRect(view2, view2.getParent() != this, mTempRect2);
            return mTempRect1.left <= mTempRect2.right && mTempRect1.top <= mTempRect2.bottom && mTempRect1.right >= mTempRect2.left && mTempRect1.bottom >= mTempRect2.top;
        }
        return false;
    }
    
    protected boolean drawChild(final Canvas canvas, final View view, final long n) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.mBehavior != null && layoutParams.mBehavior.getScrimOpacity(this, view) > 0.0f) {
            if (this.mScrimPaint == null) {
                this.mScrimPaint = new Paint();
            }
            this.mScrimPaint.setColor(layoutParams.mBehavior.getScrimColor(this, view));
            canvas.drawRect((float)this.getPaddingLeft(), (float)this.getPaddingTop(), (float)(this.getWidth() - this.getPaddingRight()), (float)(this.getHeight() - this.getPaddingBottom()), this.mScrimPaint);
        }
        return super.drawChild(canvas, view, n);
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        final int[] drawableState = this.getDrawableState();
        final Drawable mStatusBarBackground = this.mStatusBarBackground;
        boolean b = false;
        if (mStatusBarBackground != null) {
            final boolean stateful = mStatusBarBackground.isStateful();
            b = false;
            if (stateful) {
                b = (false | mStatusBarBackground.setState(drawableState));
            }
        }
        if (b) {
            this.invalidate();
        }
    }
    
    void ensurePreDrawListener() {
        final int childCount = this.getChildCount();
        int n = 0;
        boolean b;
        while (true) {
            b = false;
            if (n >= childCount) {
                break;
            }
            if (this.hasDependencies(this.getChildAt(n))) {
                b = true;
                break;
            }
            ++n;
        }
        if (b != this.mNeedsPreDrawListener) {
            if (!b) {
                this.removePreDrawListener();
                return;
            }
            this.addPreDrawListener();
        }
    }
    
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }
    
    public LayoutParams generateLayoutParams(final AttributeSet set) {
        return new LayoutParams(this.getContext(), set);
    }
    
    protected LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams)viewGroup$LayoutParams);
        }
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            return new LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        return new LayoutParams(viewGroup$LayoutParams);
    }
    
    void getChildRect(final View view, final boolean b, final Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.set(0, 0, 0, 0);
            return;
        }
        if (b) {
            this.getDescendantRect(view, rect);
            return;
        }
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }
    
    public List<View> getDependencies(final View view) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final List<View> mTempDependenciesList = this.mTempDependenciesList;
        mTempDependenciesList.clear();
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (child != view && layoutParams.dependsOn(this, view, child)) {
                mTempDependenciesList.add(child);
            }
        }
        return mTempDependenciesList;
    }
    
    void getDescendantRect(final View view, final Rect rect) {
        ViewGroupUtils.getDescendantRect(this, view, rect);
    }
    
    void getDesiredAnchoredChildRect(final View view, final int n, final Rect rect, final Rect rect2) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final int absoluteGravity = GravityCompat.getAbsoluteGravity(resolveAnchoredChildGravity(layoutParams.gravity), n);
        final int absoluteGravity2 = GravityCompat.getAbsoluteGravity(resolveGravity(layoutParams.anchorGravity), n);
        final int n2 = absoluteGravity & 0x7;
        final int n3 = absoluteGravity & 0x70;
        final int n4 = absoluteGravity2 & 0x7;
        final int n5 = absoluteGravity2 & 0x70;
        final int measuredWidth = view.getMeasuredWidth();
        final int measuredHeight = view.getMeasuredHeight();
        int n6 = 0;
        switch (n4) {
            default: {
                n6 = rect.left;
                break;
            }
            case 5: {
                n6 = rect.right;
                break;
            }
            case 1: {
                n6 = rect.left + rect.width() / 2;
                break;
            }
        }
        int n7 = 0;
        switch (n5) {
            default: {
                n7 = rect.top;
                break;
            }
            case 80: {
                n7 = rect.bottom;
                break;
            }
            case 16: {
                n7 = rect.top + rect.height() / 2;
                break;
            }
        }
        switch (n2) {
            case 1: {
                n6 -= measuredWidth / 2;
            }
            default: {
                n6 -= measuredWidth;
            }
            case 5: {
                switch (n3) {
                    case 16: {
                        n7 -= measuredHeight / 2;
                    }
                    default: {
                        n7 -= measuredHeight;
                    }
                    case 80: {
                        final int width = this.getWidth();
                        final int height = this.getHeight();
                        final int max = Math.max(this.getPaddingLeft() + layoutParams.leftMargin, Math.min(n6, width - this.getPaddingRight() - measuredWidth - layoutParams.rightMargin));
                        final int max2 = Math.max(this.getPaddingTop() + layoutParams.topMargin, Math.min(n7, height - this.getPaddingBottom() - measuredHeight - layoutParams.bottomMargin));
                        rect2.set(max, max2, max + measuredWidth, max2 + measuredHeight);
                        return;
                    }
                }
                break;
            }
        }
    }
    
    void getLastChildRect(final View view, final Rect rect) {
        rect.set(((LayoutParams)view.getLayoutParams()).getLastChildRect());
    }
    
    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }
    
    LayoutParams getResolvedLayoutParams(final View view) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.mBehaviorResolved) {
            return layoutParams;
        }
        Serializable s = view.getClass();
        DefaultBehavior defaultBehavior = null;
        while (s != null) {
            defaultBehavior = ((Class<? extends View>)s).getAnnotation(DefaultBehavior.class);
            if (defaultBehavior != null) {
                break;
            }
            s = ((Class<? extends View>)s).getSuperclass();
        }
        while (true) {
            if (defaultBehavior == null) {
                break Label_0073;
            }
            try {
                layoutParams.setBehavior((Behavior)defaultBehavior.value().newInstance());
                layoutParams.mBehaviorResolved = true;
                return layoutParams;
            }
            catch (Exception ex) {
                Log.e("CoordinatorLayout", "Default behavior class " + defaultBehavior.value().getName() + " could not be instantiated. Did you forget a default constructor?", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    @Nullable
    public Drawable getStatusBarBackground() {
        return this.mStatusBarBackground;
    }
    
    protected int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), this.getPaddingTop() + this.getPaddingBottom());
    }
    
    protected int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), this.getPaddingLeft() + this.getPaddingRight());
    }
    
    boolean hasDependencies(final View view) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.mAnchorView != null) {
            return true;
        }
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (child != view && layoutParams.dependsOn(this, view, child)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isPointInChildBounds(final View view, final int n, final int n2) {
        final Rect mTempRect1 = this.mTempRect1;
        this.getDescendantRect(view, mTempRect1);
        return mTempRect1.contains(n, n2);
    }
    
    void offsetChildToAnchor(final View view, final int n) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.mAnchorView != null) {
            final Rect mTempRect1 = this.mTempRect1;
            final Rect mTempRect2 = this.mTempRect2;
            final Rect mTempRect3 = this.mTempRect3;
            this.getDescendantRect(layoutParams.mAnchorView, mTempRect1);
            this.getChildRect(view, false, mTempRect2);
            this.getDesiredAnchoredChildRect(view, n, mTempRect1, mTempRect3);
            final int n2 = mTempRect3.left - mTempRect2.left;
            final int n3 = mTempRect3.top - mTempRect2.top;
            if (n2 != 0) {
                view.offsetLeftAndRight(n2);
            }
            if (n3 != 0) {
                view.offsetTopAndBottom(n3);
            }
            if (n2 != 0 || n3 != 0) {
                final Behavior behavior = layoutParams.getBehavior();
                if (behavior != null) {
                    behavior.onDependentViewChanged(this, view, layoutParams.mAnchorView);
                }
            }
        }
    }
    
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.resetTouchBehaviors();
        if (this.mNeedsPreDrawListener) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new OnPreDrawListener();
            }
            this.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this.mOnPreDrawListener);
        }
        if (this.mLastInsets == null && ViewCompat.getFitsSystemWindows((View)this)) {
            ViewCompat.requestApplyInsets((View)this);
        }
        this.mIsAttachedToWindow = true;
    }
    
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.resetTouchBehaviors();
        if (this.mNeedsPreDrawListener && this.mOnPreDrawListener != null) {
            this.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this.mOnPreDrawListener);
        }
        if (this.mNestedScrollingTarget != null) {
            this.onStopNestedScroll(this.mNestedScrollingTarget);
        }
        this.mIsAttachedToWindow = false;
    }
    
    public void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.mDrawStatusBarBackground && this.mStatusBarBackground != null) {
            int systemWindowInsetTop;
            if (this.mLastInsets != null) {
                systemWindowInsetTop = this.mLastInsets.getSystemWindowInsetTop();
            }
            else {
                systemWindowInsetTop = 0;
            }
            if (systemWindowInsetTop > 0) {
                this.mStatusBarBackground.setBounds(0, 0, this.getWidth(), systemWindowInsetTop);
                this.mStatusBarBackground.draw(canvas);
            }
        }
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0) {
            this.resetTouchBehaviors();
        }
        final boolean performIntercept = this.performIntercept(motionEvent, 0);
        if (false) {
            null.recycle();
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.resetTouchBehaviors();
        }
        return performIntercept;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        final int layoutDirection = ViewCompat.getLayoutDirection((View)this);
        for (int size = this.mDependencySortedChildren.size(), i = 0; i < size; ++i) {
            final View view = this.mDependencySortedChildren.get(i);
            final Behavior behavior = ((LayoutParams)view.getLayoutParams()).getBehavior();
            if (behavior == null || !behavior.onLayoutChild(this, view, layoutDirection)) {
                this.onLayoutChild(view, layoutDirection);
            }
        }
    }
    
    public void onLayoutChild(final View view, final int n) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.checkAnchorChanged()) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
        if (layoutParams.mAnchorView != null) {
            this.layoutChildWithAnchor(view, layoutParams.mAnchorView, n);
            return;
        }
        if (layoutParams.keyline >= 0) {
            this.layoutChildWithKeyline(view, layoutParams.keyline, n);
            return;
        }
        this.layoutChild(view, n);
    }
    
    protected void onMeasure(final int n, final int n2) {
        this.prepareChildren();
        this.ensurePreDrawListener();
        final int paddingLeft = this.getPaddingLeft();
        final int paddingTop = this.getPaddingTop();
        final int paddingRight = this.getPaddingRight();
        final int paddingBottom = this.getPaddingBottom();
        final int layoutDirection = ViewCompat.getLayoutDirection((View)this);
        boolean b;
        if (layoutDirection == 1) {
            b = true;
        }
        else {
            b = false;
        }
        final int mode = View$MeasureSpec.getMode(n);
        final int size = View$MeasureSpec.getSize(n);
        final int mode2 = View$MeasureSpec.getMode(n2);
        final int size2 = View$MeasureSpec.getSize(n2);
        final int n3 = paddingLeft + paddingRight;
        final int n4 = paddingTop + paddingBottom;
        int n5 = this.getSuggestedMinimumWidth();
        int n6 = this.getSuggestedMinimumHeight();
        int combineMeasuredStates = 0;
        boolean b2;
        if (this.mLastInsets != null && ViewCompat.getFitsSystemWindows((View)this)) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        for (int size3 = this.mDependencySortedChildren.size(), i = 0; i < size3; ++i) {
            final View view = this.mDependencySortedChildren.get(i);
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            final int keyline = layoutParams.keyline;
            int n7 = 0;
            Label_0250: {
                if (keyline >= 0) {
                    n7 = 0;
                    if (mode != 0) {
                        final int keyline2 = this.getKeyline(layoutParams.keyline);
                        final int n8 = 0x7 & GravityCompat.getAbsoluteGravity(resolveKeylineGravity(layoutParams.gravity), layoutDirection);
                        if ((n8 == 3 && !b) || (n8 == 5 && b)) {
                            n7 = Math.max(0, size - paddingRight - keyline2);
                        }
                        else {
                            if (n8 != 5 || b) {
                                n7 = 0;
                                if (n8 != 3) {
                                    break Label_0250;
                                }
                                n7 = 0;
                                if (!b) {
                                    break Label_0250;
                                }
                            }
                            n7 = Math.max(0, keyline2 - paddingLeft);
                        }
                    }
                }
            }
            int measureSpec = n;
            int measureSpec2 = n2;
            if (b2 && !ViewCompat.getFitsSystemWindows(view)) {
                final int n9 = this.mLastInsets.getSystemWindowInsetLeft() + this.mLastInsets.getSystemWindowInsetRight();
                final int n10 = this.mLastInsets.getSystemWindowInsetTop() + this.mLastInsets.getSystemWindowInsetBottom();
                measureSpec = View$MeasureSpec.makeMeasureSpec(size - n9, mode);
                measureSpec2 = View$MeasureSpec.makeMeasureSpec(size2 - n10, mode2);
            }
            final Behavior behavior = layoutParams.getBehavior();
            if (behavior == null || !behavior.onMeasureChild(this, view, measureSpec, n7, measureSpec2, 0)) {
                this.onMeasureChild(view, measureSpec, n7, measureSpec2, 0);
            }
            n5 = Math.max(n5, n3 + view.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
            n6 = Math.max(n6, n4 + view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin);
            combineMeasuredStates = ViewCompat.combineMeasuredStates(combineMeasuredStates, ViewCompat.getMeasuredState(view));
        }
        this.setMeasuredDimension(ViewCompat.resolveSizeAndState(n5, n, 0xFF000000 & combineMeasuredStates), ViewCompat.resolveSizeAndState(n6, n2, combineMeasuredStates << 16));
    }
    
    public void onMeasureChild(final View view, final int n, final int n2, final int n3, final int n4) {
        this.measureChildWithMargins(view, n, n2, n3, n4);
    }
    
    public boolean onNestedFling(final View view, final float n, final float n2, final boolean b) {
        boolean b2 = false;
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (layoutParams.isNestedScrollAccepted()) {
                final Behavior behavior = layoutParams.getBehavior();
                if (behavior != null) {
                    b2 |= behavior.onNestedFling(this, child, view, n, n2, b);
                }
            }
        }
        if (b2) {
            this.dispatchOnDependentViewChanged(true);
        }
        return b2;
    }
    
    public boolean onNestedPreFling(final View view, final float n, final float n2) {
        boolean b = false;
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (layoutParams.isNestedScrollAccepted()) {
                final Behavior behavior = layoutParams.getBehavior();
                if (behavior != null) {
                    b |= behavior.onNestedPreFling(this, child, view, n, n2);
                }
            }
        }
        return b;
    }
    
    public void onNestedPreScroll(final View view, final int n, final int n2, final int[] array) {
        int n3 = 0;
        int n4 = 0;
        boolean b = false;
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (layoutParams.isNestedScrollAccepted()) {
                final Behavior behavior = layoutParams.getBehavior();
                if (behavior != null) {
                    this.mTempIntPair[this.mTempIntPair[1] = 0] = 0;
                    behavior.onNestedPreScroll(this, child, view, n, n2, this.mTempIntPair);
                    if (n > 0) {
                        n3 = Math.max(n3, this.mTempIntPair[0]);
                    }
                    else {
                        n3 = Math.min(n3, this.mTempIntPair[0]);
                    }
                    if (n2 > 0) {
                        n4 = Math.max(n4, this.mTempIntPair[1]);
                    }
                    else {
                        n4 = Math.min(n4, this.mTempIntPair[1]);
                    }
                    b = true;
                }
            }
        }
        array[0] = n3;
        array[1] = n4;
        if (b) {
            this.dispatchOnDependentViewChanged(true);
        }
    }
    
    public void onNestedScroll(final View view, final int n, final int n2, final int n3, final int n4) {
        final int childCount = this.getChildCount();
        boolean b = false;
        for (int i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (layoutParams.isNestedScrollAccepted()) {
                final Behavior behavior = layoutParams.getBehavior();
                if (behavior != null) {
                    behavior.onNestedScroll(this, child, view, n, n2, n3, n4);
                    b = true;
                }
            }
        }
        if (b) {
            this.dispatchOnDependentViewChanged(true);
        }
    }
    
    public void onNestedScrollAccepted(final View mNestedScrollingDirectChild, final View mNestedScrollingTarget, final int n) {
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(mNestedScrollingDirectChild, mNestedScrollingTarget, n);
        this.mNestedScrollingDirectChild = mNestedScrollingDirectChild;
        this.mNestedScrollingTarget = mNestedScrollingTarget;
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (layoutParams.isNestedScrollAccepted()) {
                final Behavior behavior = layoutParams.getBehavior();
                if (behavior != null) {
                    behavior.onNestedScrollAccepted(this, child, mNestedScrollingDirectChild, mNestedScrollingTarget, n);
                }
            }
        }
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
        }
        else {
            final SavedState savedState = (SavedState)parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            final SparseArray<Parcelable> behaviorStates = savedState.behaviorStates;
            for (int i = 0; i < this.getChildCount(); ++i) {
                final View child = this.getChildAt(i);
                final int id = child.getId();
                final Behavior behavior = this.getResolvedLayoutParams(child).getBehavior();
                if (id != -1 && behavior != null) {
                    final Parcelable parcelable2 = (Parcelable)behaviorStates.get(id);
                    if (parcelable2 != null) {
                        behavior.onRestoreInstanceState(this, child, parcelable2);
                    }
                }
            }
        }
    }
    
    protected Parcelable onSaveInstanceState() {
        final SavedState savedState = new SavedState(super.onSaveInstanceState());
        final SparseArray behaviorStates = new SparseArray();
        for (int i = 0; i < this.getChildCount(); ++i) {
            final View child = this.getChildAt(i);
            final int id = child.getId();
            final Behavior behavior = ((LayoutParams)child.getLayoutParams()).getBehavior();
            if (id != -1 && behavior != null) {
                final Parcelable onSaveInstanceState = behavior.onSaveInstanceState(this, child);
                if (onSaveInstanceState != null) {
                    behaviorStates.append(id, (Object)onSaveInstanceState);
                }
            }
        }
        savedState.behaviorStates = (SparseArray<Parcelable>)behaviorStates;
        return (Parcelable)savedState;
    }
    
    public boolean onStartNestedScroll(final View view, final View view2, final int n) {
        boolean b = false;
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            final Behavior behavior = layoutParams.getBehavior();
            if (behavior != null) {
                final boolean onStartNestedScroll = behavior.onStartNestedScroll(this, child, view, view2, n);
                b |= onStartNestedScroll;
                layoutParams.acceptNestedScroll(onStartNestedScroll);
            }
            else {
                layoutParams.acceptNestedScroll(false);
            }
        }
        return b;
    }
    
    public void onStopNestedScroll(final View view) {
        this.mNestedScrollingParentHelper.onStopNestedScroll(view);
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (layoutParams.isNestedScrollAccepted()) {
                final Behavior behavior = layoutParams.getBehavior();
                if (behavior != null) {
                    behavior.onStopNestedScroll(this, child, view);
                }
                layoutParams.resetNestedScroll();
                layoutParams.resetChangedAfterNestedScroll();
            }
        }
        this.mNestedScrollingDirectChild = null;
        this.mNestedScrollingTarget = null;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        MotionEvent obtain = null;
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        final View mBehaviorTouchView = this.mBehaviorTouchView;
        boolean performIntercept = false;
        boolean onTouchEvent = false;
        Label_0073: {
            if (mBehaviorTouchView == null) {
                performIntercept = this.performIntercept(motionEvent, 1);
                onTouchEvent = false;
                if (!performIntercept) {
                    break Label_0073;
                }
            }
            final Behavior behavior = ((LayoutParams)this.mBehaviorTouchView.getLayoutParams()).getBehavior();
            onTouchEvent = false;
            if (behavior != null) {
                onTouchEvent = behavior.onTouchEvent(this, this.mBehaviorTouchView, motionEvent);
            }
        }
        if (this.mBehaviorTouchView == null) {
            onTouchEvent |= super.onTouchEvent(motionEvent);
        }
        else {
            obtain = null;
            if (performIntercept) {
                obtain = null;
                if (!false) {
                    final long uptimeMillis = SystemClock.uptimeMillis();
                    obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                }
                super.onTouchEvent(obtain);
            }
        }
        if (onTouchEvent || actionMasked == 0) {}
        if (obtain != null) {
            obtain.recycle();
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.resetTouchBehaviors();
        }
        return onTouchEvent;
    }
    
    void recordLastChildRect(final View view, final Rect lastChildRect) {
        ((LayoutParams)view.getLayoutParams()).setLastChildRect(lastChildRect);
    }
    
    void removePreDrawListener() {
        if (this.mIsAttachedToWindow && this.mOnPreDrawListener != null) {
            this.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = false;
    }
    
    public void requestDisallowInterceptTouchEvent(final boolean b) {
        super.requestDisallowInterceptTouchEvent(b);
        if (b) {
            this.resetTouchBehaviors();
        }
    }
    
    public void setOnHierarchyChangeListener(final ViewGroup$OnHierarchyChangeListener mOnHierarchyChangeListener) {
        this.mOnHierarchyChangeListener = mOnHierarchyChangeListener;
    }
    
    public void setStatusBarBackground(@Nullable final Drawable drawable) {
        if (this.mStatusBarBackground != drawable) {
            if (this.mStatusBarBackground != null) {
                this.mStatusBarBackground.setCallback((Drawable$Callback)null);
            }
            Drawable mutate = null;
            if (drawable != null) {
                mutate = drawable.mutate();
            }
            this.mStatusBarBackground = mutate;
            if (this.mStatusBarBackground != null) {
                if (this.mStatusBarBackground.isStateful()) {
                    this.mStatusBarBackground.setState(this.getDrawableState());
                }
                DrawableCompat.setLayoutDirection(this.mStatusBarBackground, ViewCompat.getLayoutDirection((View)this));
                this.mStatusBarBackground.setVisible(this.getVisibility() == 0, false);
                this.mStatusBarBackground.setCallback((Drawable$Callback)this);
            }
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    public void setStatusBarBackgroundColor(@ColorInt final int n) {
        this.setStatusBarBackground((Drawable)new ColorDrawable(n));
    }
    
    public void setStatusBarBackgroundResource(@DrawableRes final int n) {
        Drawable drawable;
        if (n != 0) {
            drawable = ContextCompat.getDrawable(this.getContext(), n);
        }
        else {
            drawable = null;
        }
        this.setStatusBarBackground(drawable);
    }
    
    public void setVisibility(final int visibility) {
        super.setVisibility(visibility);
        final boolean b = visibility == 0;
        if (this.mStatusBarBackground != null && this.mStatusBarBackground.isVisible() != b) {
            this.mStatusBarBackground.setVisible(b, false);
        }
    }
    
    protected boolean verifyDrawable(final Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mStatusBarBackground;
    }
    
    private class ApplyInsetsListener implements OnApplyWindowInsetsListener
    {
        @Override
        public WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
            return CoordinatorLayout.this.setWindowInsets(windowInsetsCompat);
        }
    }
    
    public abstract static class Behavior<V extends View>
    {
        public Behavior() {
        }
        
        public Behavior(final Context context, final AttributeSet set) {
        }
        
        public static Object getTag(final View view) {
            return ((LayoutParams)view.getLayoutParams()).mBehaviorTag;
        }
        
        public static void setTag(final View view, final Object mBehaviorTag) {
            ((LayoutParams)view.getLayoutParams()).mBehaviorTag = mBehaviorTag;
        }
        
        public boolean blocksInteractionBelow(final CoordinatorLayout coordinatorLayout, final V v) {
            return this.getScrimOpacity(coordinatorLayout, v) > 0.0f;
        }
        
        public int getScrimColor(final CoordinatorLayout coordinatorLayout, final V v) {
            return -16777216;
        }
        
        public float getScrimOpacity(final CoordinatorLayout coordinatorLayout, final V v) {
            return 0.0f;
        }
        
        public boolean isDirty(final CoordinatorLayout coordinatorLayout, final V v) {
            return false;
        }
        
        public boolean layoutDependsOn(final CoordinatorLayout coordinatorLayout, final V v, final View view) {
            return false;
        }
        
        public WindowInsetsCompat onApplyWindowInsets(final CoordinatorLayout coordinatorLayout, final V v, final WindowInsetsCompat windowInsetsCompat) {
            return windowInsetsCompat;
        }
        
        public boolean onDependentViewChanged(final CoordinatorLayout coordinatorLayout, final V v, final View view) {
            return false;
        }
        
        public void onDependentViewRemoved(final CoordinatorLayout coordinatorLayout, final V v, final View view) {
        }
        
        public boolean onInterceptTouchEvent(final CoordinatorLayout coordinatorLayout, final V v, final MotionEvent motionEvent) {
            return false;
        }
        
        public boolean onLayoutChild(final CoordinatorLayout coordinatorLayout, final V v, final int n) {
            return false;
        }
        
        public boolean onMeasureChild(final CoordinatorLayout coordinatorLayout, final V v, final int n, final int n2, final int n3, final int n4) {
            return false;
        }
        
        public boolean onNestedFling(final CoordinatorLayout coordinatorLayout, final V v, final View view, final float n, final float n2, final boolean b) {
            return false;
        }
        
        public boolean onNestedPreFling(final CoordinatorLayout coordinatorLayout, final V v, final View view, final float n, final float n2) {
            return false;
        }
        
        public void onNestedPreScroll(final CoordinatorLayout coordinatorLayout, final V v, final View view, final int n, final int n2, final int[] array) {
        }
        
        public void onNestedScroll(final CoordinatorLayout coordinatorLayout, final V v, final View view, final int n, final int n2, final int n3, final int n4) {
        }
        
        public void onNestedScrollAccepted(final CoordinatorLayout coordinatorLayout, final V v, final View view, final View view2, final int n) {
        }
        
        public void onRestoreInstanceState(final CoordinatorLayout coordinatorLayout, final V v, final Parcelable parcelable) {
        }
        
        public Parcelable onSaveInstanceState(final CoordinatorLayout coordinatorLayout, final V v) {
            return (Parcelable)View$BaseSavedState.EMPTY_STATE;
        }
        
        public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final V v, final View view, final View view2, final int n) {
            return false;
        }
        
        public void onStopNestedScroll(final CoordinatorLayout coordinatorLayout, final V v, final View view) {
        }
        
        public boolean onTouchEvent(final CoordinatorLayout coordinatorLayout, final V v, final MotionEvent motionEvent) {
            return false;
        }
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DefaultBehavior {
        Class<? extends Behavior> value();
    }
    
    private class HierarchyChangeListener implements ViewGroup$OnHierarchyChangeListener
    {
        public void onChildViewAdded(final View view, final View view2) {
            if (CoordinatorLayout.this.mOnHierarchyChangeListener != null) {
                CoordinatorLayout.this.mOnHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }
        
        public void onChildViewRemoved(final View view, final View view2) {
            CoordinatorLayout.this.dispatchDependentViewRemoved(view2);
            if (CoordinatorLayout.this.mOnHierarchyChangeListener != null) {
                CoordinatorLayout.this.mOnHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }
    
    public static class LayoutParams extends ViewGroup$MarginLayoutParams
    {
        public int anchorGravity;
        public int gravity;
        public int keyline;
        View mAnchorDirectChild;
        int mAnchorId;
        View mAnchorView;
        Behavior mBehavior;
        boolean mBehaviorResolved;
        Object mBehaviorTag;
        private boolean mDidAcceptNestedScroll;
        private boolean mDidBlockInteraction;
        private boolean mDidChangeAfterNestedScroll;
        final Rect mLastChildRect;
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.mLastChildRect = new Rect();
        }
        
        LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.mLastChildRect = new Rect();
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.CoordinatorLayout_LayoutParams);
            this.gravity = obtainStyledAttributes.getInteger(R.styleable.CoordinatorLayout_LayoutParams_android_layout_gravity, 0);
            this.mAnchorId = obtainStyledAttributes.getResourceId(R.styleable.CoordinatorLayout_LayoutParams_layout_anchor, -1);
            this.anchorGravity = obtainStyledAttributes.getInteger(R.styleable.CoordinatorLayout_LayoutParams_layout_anchorGravity, 0);
            this.keyline = obtainStyledAttributes.getInteger(R.styleable.CoordinatorLayout_LayoutParams_layout_keyline, -1);
            this.mBehaviorResolved = obtainStyledAttributes.hasValue(R.styleable.CoordinatorLayout_LayoutParams_layout_behavior);
            if (this.mBehaviorResolved) {
                this.mBehavior = CoordinatorLayout.parseBehavior(context, set, obtainStyledAttributes.getString(R.styleable.CoordinatorLayout_LayoutParams_layout_behavior));
            }
            obtainStyledAttributes.recycle();
        }
        
        public LayoutParams(final LayoutParams layoutParams) {
            super((ViewGroup$MarginLayoutParams)layoutParams);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.mLastChildRect = new Rect();
        }
        
        public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.mLastChildRect = new Rect();
        }
        
        public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            super(viewGroup$MarginLayoutParams);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.mLastChildRect = new Rect();
        }
        
        private void resolveAnchorView(final View view, final CoordinatorLayout coordinatorLayout) {
            this.mAnchorView = coordinatorLayout.findViewById(this.mAnchorId);
            if (this.mAnchorView != null) {
                if (this.mAnchorView != coordinatorLayout) {
                    View mAnchorView = this.mAnchorView;
                    ViewParent viewParent = this.mAnchorView.getParent();
                    while (viewParent != coordinatorLayout && viewParent != null) {
                        if (viewParent == view) {
                            if (coordinatorLayout.isInEditMode()) {
                                this.mAnchorDirectChild = null;
                                this.mAnchorView = null;
                                return;
                            }
                            throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                        }
                        else {
                            if (viewParent instanceof View) {
                                mAnchorView = (View)viewParent;
                            }
                            viewParent = viewParent.getParent();
                        }
                    }
                    this.mAnchorDirectChild = mAnchorView;
                    return;
                }
                if (coordinatorLayout.isInEditMode()) {
                    this.mAnchorDirectChild = null;
                    this.mAnchorView = null;
                    return;
                }
                throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
            }
            else {
                if (coordinatorLayout.isInEditMode()) {
                    this.mAnchorDirectChild = null;
                    this.mAnchorView = null;
                    return;
                }
                throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + coordinatorLayout.getResources().getResourceName(this.mAnchorId) + " to anchor view " + view);
            }
        }
        
        private boolean verifyAnchorView(final View view, final CoordinatorLayout coordinatorLayout) {
            if (this.mAnchorView.getId() != this.mAnchorId) {
                return false;
            }
            View mAnchorView = this.mAnchorView;
            for (ViewParent viewParent = this.mAnchorView.getParent(); viewParent != coordinatorLayout; viewParent = viewParent.getParent()) {
                if (viewParent == null || viewParent == view) {
                    this.mAnchorDirectChild = null;
                    this.mAnchorView = null;
                    return false;
                }
                if (viewParent instanceof View) {
                    mAnchorView = (View)viewParent;
                }
            }
            this.mAnchorDirectChild = mAnchorView;
            return true;
        }
        
        void acceptNestedScroll(final boolean mDidAcceptNestedScroll) {
            this.mDidAcceptNestedScroll = mDidAcceptNestedScroll;
        }
        
        boolean checkAnchorChanged() {
            return this.mAnchorView == null && this.mAnchorId != -1;
        }
        
        boolean dependsOn(final CoordinatorLayout coordinatorLayout, final View view, final View view2) {
            return view2 == this.mAnchorDirectChild || (this.mBehavior != null && this.mBehavior.layoutDependsOn(coordinatorLayout, view, view2));
        }
        
        boolean didBlockInteraction() {
            if (this.mBehavior == null) {
                this.mDidBlockInteraction = false;
            }
            return this.mDidBlockInteraction;
        }
        
        View findAnchorView(final CoordinatorLayout coordinatorLayout, final View view) {
            if (this.mAnchorId == -1) {
                this.mAnchorDirectChild = null;
                return this.mAnchorView = null;
            }
            if (this.mAnchorView == null || !this.verifyAnchorView(view, coordinatorLayout)) {
                this.resolveAnchorView(view, coordinatorLayout);
            }
            return this.mAnchorView;
        }
        
        public int getAnchorId() {
            return this.mAnchorId;
        }
        
        public Behavior getBehavior() {
            return this.mBehavior;
        }
        
        boolean getChangedAfterNestedScroll() {
            return this.mDidChangeAfterNestedScroll;
        }
        
        Rect getLastChildRect() {
            return this.mLastChildRect;
        }
        
        void invalidateAnchor() {
            this.mAnchorDirectChild = null;
            this.mAnchorView = null;
        }
        
        boolean isBlockingInteractionBelow(final CoordinatorLayout coordinatorLayout, final View view) {
            return this.mDidBlockInteraction || (this.mDidBlockInteraction |= (this.mBehavior != null && this.mBehavior.blocksInteractionBelow(coordinatorLayout, view)));
        }
        
        boolean isDirty(final CoordinatorLayout coordinatorLayout, final View view) {
            return this.mBehavior != null && this.mBehavior.isDirty(coordinatorLayout, view);
        }
        
        boolean isNestedScrollAccepted() {
            return this.mDidAcceptNestedScroll;
        }
        
        void resetChangedAfterNestedScroll() {
            this.mDidChangeAfterNestedScroll = false;
        }
        
        void resetNestedScroll() {
            this.mDidAcceptNestedScroll = false;
        }
        
        void resetTouchBehaviorTracking() {
            this.mDidBlockInteraction = false;
        }
        
        public void setAnchorId(final int mAnchorId) {
            this.invalidateAnchor();
            this.mAnchorId = mAnchorId;
        }
        
        public void setBehavior(final Behavior mBehavior) {
            if (this.mBehavior != mBehavior) {
                this.mBehavior = mBehavior;
                this.mBehaviorTag = null;
                this.mBehaviorResolved = true;
            }
        }
        
        void setChangedAfterNestedScroll(final boolean mDidChangeAfterNestedScroll) {
            this.mDidChangeAfterNestedScroll = mDidChangeAfterNestedScroll;
        }
        
        void setLastChildRect(final Rect rect) {
            this.mLastChildRect.set(rect);
        }
    }
    
    class OnPreDrawListener implements ViewTreeObserver$OnPreDrawListener
    {
        public boolean onPreDraw() {
            CoordinatorLayout.this.dispatchOnDependentViewChanged(false);
            return true;
        }
    }
    
    protected static class SavedState extends View$BaseSavedState
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        SparseArray<Parcelable> behaviorStates;
        
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
            final int int1 = parcel.readInt();
            final int[] array = new int[int1];
            parcel.readIntArray(array);
            final Parcelable[] parcelableArray = parcel.readParcelableArray(classLoader);
            this.behaviorStates = (SparseArray<Parcelable>)new SparseArray(int1);
            for (int i = 0; i < int1; ++i) {
                this.behaviorStates.append(array[i], (Object)parcelableArray[i]);
            }
        }
        
        public SavedState(final Parcelable parcelable) {
            super(parcelable);
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            super.writeToParcel(parcel, n);
            int size;
            if (this.behaviorStates != null) {
                size = this.behaviorStates.size();
            }
            else {
                size = 0;
            }
            parcel.writeInt(size);
            final int[] array = new int[size];
            final Parcelable[] array2 = new Parcelable[size];
            for (int i = 0; i < size; ++i) {
                array[i] = this.behaviorStates.keyAt(i);
                array2[i] = (Parcelable)this.behaviorStates.valueAt(i);
            }
            parcel.writeIntArray(array);
            parcel.writeParcelableArray(array2, n);
        }
    }
    
    static class ViewElevationComparator implements Comparator<View>
    {
        @Override
        public int compare(final View view, final View view2) {
            final float z = ViewCompat.getZ(view);
            final float z2 = ViewCompat.getZ(view2);
            if (z > z2) {
                return -1;
            }
            if (z < z2) {
                return 1;
            }
            return 0;
        }
    }
}
