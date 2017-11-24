package android.support.design.widget;

import android.widget.*;
import android.content.*;
import android.util.*;
import android.support.design.*;
import android.support.v4.view.*;
import android.content.res.*;
import android.view.*;

public static class SnackbarLayout extends LinearLayout
{
    private Button mActionView;
    private int mMaxInlineActionWidth;
    private int mMaxWidth;
    private TextView mMessageView;
    private OnAttachStateChangeListener mOnAttachStateChangeListener;
    private OnLayoutChangeListener mOnLayoutChangeListener;
    
    public SnackbarLayout(final Context context) {
        this(context, null);
    }
    
    public SnackbarLayout(final Context context, final AttributeSet set) {
        super(context, set);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.SnackbarLayout);
        this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_android_maxWidth, -1);
        this.mMaxInlineActionWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
        if (obtainStyledAttributes.hasValue(R.styleable.SnackbarLayout_elevation)) {
            ViewCompat.setElevation((View)this, obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_elevation, 0));
        }
        obtainStyledAttributes.recycle();
        this.setClickable(true);
        LayoutInflater.from(context).inflate(R.layout.design_layout_snackbar_include, (ViewGroup)this);
        ViewCompat.setAccessibilityLiveRegion((View)this, 1);
        ViewCompat.setImportantForAccessibility((View)this, 1);
    }
    
    private static void updateTopBottomPadding(final View view, final int n, final int n2) {
        if (ViewCompat.isPaddingRelative(view)) {
            ViewCompat.setPaddingRelative(view, ViewCompat.getPaddingStart(view), n, ViewCompat.getPaddingEnd(view), n2);
            return;
        }
        view.setPadding(view.getPaddingLeft(), n, view.getPaddingRight(), n2);
    }
    
    private boolean updateViewsWithinLayout(final int orientation, final int n, final int n2) {
        final int orientation2 = this.getOrientation();
        boolean b = false;
        if (orientation != orientation2) {
            this.setOrientation(orientation);
            b = true;
        }
        if (this.mMessageView.getPaddingTop() != n || this.mMessageView.getPaddingBottom() != n2) {
            updateTopBottomPadding((View)this.mMessageView, n, n2);
            b = true;
        }
        return b;
    }
    
    void animateChildrenIn(final int n, final int n2) {
        ViewCompat.setAlpha((View)this.mMessageView, 0.0f);
        ViewCompat.animate((View)this.mMessageView).alpha(1.0f).setDuration(n2).setStartDelay(n).start();
        if (this.mActionView.getVisibility() == 0) {
            ViewCompat.setAlpha((View)this.mActionView, 0.0f);
            ViewCompat.animate((View)this.mActionView).alpha(1.0f).setDuration(n2).setStartDelay(n).start();
        }
    }
    
    void animateChildrenOut(final int n, final int n2) {
        ViewCompat.setAlpha((View)this.mMessageView, 1.0f);
        ViewCompat.animate((View)this.mMessageView).alpha(0.0f).setDuration(n2).setStartDelay(n).start();
        if (this.mActionView.getVisibility() == 0) {
            ViewCompat.setAlpha((View)this.mActionView, 1.0f);
            ViewCompat.animate((View)this.mActionView).alpha(0.0f).setDuration(n2).setStartDelay(n).start();
        }
    }
    
    Button getActionView() {
        return this.mActionView;
    }
    
    TextView getMessageView() {
        return this.mMessageView;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mOnAttachStateChangeListener != null) {
            this.mOnAttachStateChangeListener.onViewAttachedToWindow((View)this);
        }
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mOnAttachStateChangeListener != null) {
            this.mOnAttachStateChangeListener.onViewDetachedFromWindow((View)this);
        }
    }
    
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mMessageView = (TextView)this.findViewById(R.id.snackbar_text);
        this.mActionView = (Button)this.findViewById(R.id.snackbar_action);
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        if (this.mOnLayoutChangeListener != null) {
            this.mOnLayoutChangeListener.onLayoutChange((View)this, n, n2, n3, n4);
        }
    }
    
    protected void onMeasure(int measureSpec, final int n) {
        super.onMeasure(measureSpec, n);
        if (this.mMaxWidth > 0 && this.getMeasuredWidth() > this.mMaxWidth) {
            measureSpec = View$MeasureSpec.makeMeasureSpec(this.mMaxWidth, 1073741824);
            super.onMeasure(measureSpec, n);
        }
        final int dimensionPixelSize = this.getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical_2lines);
        final int dimensionPixelSize2 = this.getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical);
        boolean b;
        if (this.mMessageView.getLayout().getLineCount() > 1) {
            b = true;
        }
        else {
            b = false;
        }
        int n2;
        if (b && this.mMaxInlineActionWidth > 0 && this.mActionView.getMeasuredWidth() > this.mMaxInlineActionWidth) {
            final boolean updateViewsWithinLayout = this.updateViewsWithinLayout(1, dimensionPixelSize, dimensionPixelSize - dimensionPixelSize2);
            n2 = 0;
            if (updateViewsWithinLayout) {
                n2 = 1;
            }
        }
        else {
            int n3;
            if (b) {
                n3 = dimensionPixelSize;
            }
            else {
                n3 = dimensionPixelSize2;
            }
            final boolean updateViewsWithinLayout2 = this.updateViewsWithinLayout(0, n3, n3);
            n2 = 0;
            if (updateViewsWithinLayout2) {
                n2 = 1;
            }
        }
        if (n2 != 0) {
            super.onMeasure(measureSpec, n);
        }
    }
    
    void setOnAttachStateChangeListener(final OnAttachStateChangeListener mOnAttachStateChangeListener) {
        this.mOnAttachStateChangeListener = mOnAttachStateChangeListener;
    }
    
    void setOnLayoutChangeListener(final OnLayoutChangeListener mOnLayoutChangeListener) {
        this.mOnLayoutChangeListener = mOnLayoutChangeListener;
    }
    
    interface OnAttachStateChangeListener
    {
        void onViewAttachedToWindow(final View p0);
        
        void onViewDetachedFromWindow(final View p0);
    }
    
    interface OnLayoutChangeListener
    {
        void onLayoutChange(final View p0, final int p1, final int p2, final int p3, final int p4);
    }
}
