package android.support.design.widget;

import android.content.*;
import android.view.animation.*;
import android.support.design.*;
import android.content.res.*;
import android.support.v4.widget.*;
import android.widget.*;
import android.graphics.drawable.*;
import android.util.*;
import android.text.*;
import android.support.v7.widget.*;
import android.graphics.*;
import android.support.v4.content.*;
import android.support.v4.view.*;
import android.support.annotation.*;
import android.view.*;
import android.os.*;
import android.view.accessibility.*;
import android.support.v4.view.accessibility.*;

public class TextInputLayout extends LinearLayout
{
    private static final int ANIMATION_DURATION = 200;
    private static final int INVALID_MAX_LENGTH = -1;
    private static final String LOG_TAG = "TextInputLayout";
    private ValueAnimatorCompat mAnimator;
    private final CollapsingTextHelper mCollapsingTextHelper;
    private boolean mCounterEnabled;
    private int mCounterMaxLength;
    private int mCounterOverflowTextAppearance;
    private boolean mCounterOverflowed;
    private int mCounterTextAppearance;
    private TextView mCounterView;
    private ColorStateList mDefaultTextColor;
    private EditText mEditText;
    private CharSequence mError;
    private boolean mErrorEnabled;
    private boolean mErrorShown;
    private int mErrorTextAppearance;
    private TextView mErrorView;
    private ColorStateList mFocusedTextColor;
    private boolean mHasReconstructedEditTextBackground;
    private CharSequence mHint;
    private boolean mHintAnimationEnabled;
    private boolean mHintEnabled;
    private LinearLayout mIndicatorArea;
    private int mIndicatorsAdded;
    private Paint mTmpPaint;
    
    public TextInputLayout(final Context context) {
        this(context, null);
    }
    
    public TextInputLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public TextInputLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set);
        this.mCollapsingTextHelper = new CollapsingTextHelper((View)this);
        ThemeUtils.checkAppCompatTheme(context);
        this.setOrientation(1);
        this.setWillNotDraw(false);
        this.setAddStatesFromChildren(true);
        this.mCollapsingTextHelper.setTextSizeInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        this.mCollapsingTextHelper.setPositionInterpolator((Interpolator)new AccelerateInterpolator());
        this.mCollapsingTextHelper.setCollapsedTextGravity(8388659);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.TextInputLayout, n, R.style.Widget_Design_TextInputLayout);
        this.mHintEnabled = obtainStyledAttributes.getBoolean(R.styleable.TextInputLayout_hintEnabled, true);
        this.setHint(obtainStyledAttributes.getText(R.styleable.TextInputLayout_android_hint));
        this.mHintAnimationEnabled = obtainStyledAttributes.getBoolean(R.styleable.TextInputLayout_hintAnimationEnabled, true);
        if (obtainStyledAttributes.hasValue(R.styleable.TextInputLayout_android_textColorHint)) {
            final ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.TextInputLayout_android_textColorHint);
            this.mFocusedTextColor = colorStateList;
            this.mDefaultTextColor = colorStateList;
        }
        if (obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, -1) != -1) {
            this.setHintTextAppearance(obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, 0));
        }
        this.mErrorTextAppearance = obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_errorTextAppearance, 0);
        final boolean boolean1 = obtainStyledAttributes.getBoolean(R.styleable.TextInputLayout_errorEnabled, false);
        final boolean boolean2 = obtainStyledAttributes.getBoolean(R.styleable.TextInputLayout_counterEnabled, false);
        this.setCounterMaxLength(obtainStyledAttributes.getInt(R.styleable.TextInputLayout_counterMaxLength, -1));
        this.mCounterTextAppearance = obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_counterTextAppearance, 0);
        this.mCounterOverflowTextAppearance = obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_counterOverflowTextAppearance, 0);
        obtainStyledAttributes.recycle();
        this.setErrorEnabled(boolean1);
        this.setCounterEnabled(boolean2);
        if (ViewCompat.getImportantForAccessibility((View)this) == 0) {
            ViewCompat.setImportantForAccessibility((View)this, 1);
        }
        ViewCompat.setAccessibilityDelegate((View)this, new TextInputAccessibilityDelegate());
    }
    
    private void addIndicator(final TextView textView, final int n) {
        if (this.mIndicatorArea == null) {
            (this.mIndicatorArea = new LinearLayout(this.getContext())).setOrientation(0);
            this.addView((View)this.mIndicatorArea, -1, -2);
            this.mIndicatorArea.addView((View)new Space(this.getContext()), (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(0, 0, 1.0f));
            if (this.mEditText != null) {
                this.adjustIndicatorPadding();
            }
        }
        this.mIndicatorArea.setVisibility(0);
        this.mIndicatorArea.addView((View)textView, n);
        ++this.mIndicatorsAdded;
    }
    
    private void adjustIndicatorPadding() {
        ViewCompat.setPaddingRelative((View)this.mIndicatorArea, ViewCompat.getPaddingStart((View)this.mEditText), 0, ViewCompat.getPaddingEnd((View)this.mEditText), this.mEditText.getPaddingBottom());
    }
    
    private void animateToExpansionFraction(final float n) {
        if (this.mCollapsingTextHelper.getExpansionFraction() == n) {
            return;
        }
        if (this.mAnimator == null) {
            (this.mAnimator = ViewUtils.createAnimator()).setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
            this.mAnimator.setDuration(200);
            this.mAnimator.setUpdateListener((ValueAnimatorCompat.AnimatorUpdateListener)new ValueAnimatorCompat.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
                    TextInputLayout.this.mCollapsingTextHelper.setExpansionFraction(valueAnimatorCompat.getAnimatedFloatValue());
                }
            });
        }
        this.mAnimator.setFloatValues(this.mCollapsingTextHelper.getExpansionFraction(), n);
        this.mAnimator.start();
    }
    
    private static boolean arrayContains(final int[] array, final int n) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (array[i] == n) {
                return true;
            }
        }
        return false;
    }
    
    private void collapseHint(final boolean b) {
        if (this.mAnimator != null && this.mAnimator.isRunning()) {
            this.mAnimator.cancel();
        }
        if (b && this.mHintAnimationEnabled) {
            this.animateToExpansionFraction(1.0f);
            return;
        }
        this.mCollapsingTextHelper.setExpansionFraction(1.0f);
    }
    
    private void ensureBackgroundDrawableStateWorkaround() {
        final Drawable background = this.mEditText.getBackground();
        if (background != null && !this.mHasReconstructedEditTextBackground) {
            final Drawable drawable = background.getConstantState().newDrawable();
            if (background instanceof DrawableContainer) {
                this.mHasReconstructedEditTextBackground = DrawableUtils.setContainerConstantState((DrawableContainer)background, drawable.getConstantState());
            }
            if (!this.mHasReconstructedEditTextBackground) {
                this.mEditText.setBackgroundDrawable(drawable);
                this.mHasReconstructedEditTextBackground = true;
            }
        }
    }
    
    private void expandHint(final boolean b) {
        if (this.mAnimator != null && this.mAnimator.isRunning()) {
            this.mAnimator.cancel();
        }
        if (b && this.mHintAnimationEnabled) {
            this.animateToExpansionFraction(0.0f);
            return;
        }
        this.mCollapsingTextHelper.setExpansionFraction(0.0f);
    }
    
    private void removeIndicator(final TextView textView) {
        if (this.mIndicatorArea != null) {
            this.mIndicatorArea.removeView((View)textView);
            if (--this.mIndicatorsAdded == 0) {
                this.mIndicatorArea.setVisibility(8);
            }
        }
    }
    
    private void setEditText(final EditText mEditText) {
        if (this.mEditText != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (!(mEditText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.mEditText = mEditText;
        this.mCollapsingTextHelper.setTypefaces(this.mEditText.getTypeface());
        this.mCollapsingTextHelper.setExpandedTextSize(this.mEditText.getTextSize());
        this.mCollapsingTextHelper.setExpandedTextGravity(this.mEditText.getGravity());
        this.mEditText.addTextChangedListener((TextWatcher)new TextWatcher() {
            public void afterTextChanged(final Editable editable) {
                TextInputLayout.this.updateLabelState(true);
                if (TextInputLayout.this.mCounterEnabled) {
                    TextInputLayout.this.updateCounter(editable.length());
                }
            }
            
            public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
            }
            
            public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
            }
        });
        if (this.mDefaultTextColor == null) {
            this.mDefaultTextColor = this.mEditText.getHintTextColors();
        }
        if (this.mHintEnabled && TextUtils.isEmpty(this.mHint)) {
            this.setHint(this.mEditText.getHint());
            this.mEditText.setHint((CharSequence)null);
        }
        if (this.mCounterView != null) {
            this.updateCounter(this.mEditText.getText().length());
        }
        if (this.mIndicatorArea != null) {
            this.adjustIndicatorPadding();
        }
        this.updateLabelState(false);
    }
    
    private void setHintInternal(final CharSequence charSequence) {
        this.mHint = charSequence;
        this.mCollapsingTextHelper.setText(charSequence);
    }
    
    private void updateCounter(final int n) {
        final boolean mCounterOverflowed = this.mCounterOverflowed;
        if (this.mCounterMaxLength == -1) {
            this.mCounterView.setText((CharSequence)String.valueOf(n));
            this.mCounterOverflowed = false;
        }
        else {
            this.mCounterOverflowed = (n > this.mCounterMaxLength);
            if (mCounterOverflowed != this.mCounterOverflowed) {
                final TextView mCounterView = this.mCounterView;
                final Context context = this.getContext();
                int n2;
                if (this.mCounterOverflowed) {
                    n2 = this.mCounterOverflowTextAppearance;
                }
                else {
                    n2 = this.mCounterTextAppearance;
                }
                mCounterView.setTextAppearance(context, n2);
            }
            this.mCounterView.setText((CharSequence)this.getContext().getString(R.string.character_counter_pattern, new Object[] { n, this.mCounterMaxLength }));
        }
        if (this.mEditText != null && mCounterOverflowed != this.mCounterOverflowed) {
            this.updateLabelState(false);
            this.updateEditTextBackground();
        }
    }
    
    private void updateEditTextBackground() {
        this.ensureBackgroundDrawableStateWorkaround();
        final Drawable background = this.mEditText.getBackground();
        if (background == null) {
            return;
        }
        if (this.mErrorShown && this.mErrorView != null) {
            background.setColorFilter((ColorFilter)AppCompatDrawableManager.getPorterDuffColorFilter(this.mErrorView.getCurrentTextColor(), PorterDuff$Mode.SRC_IN));
            return;
        }
        if (this.mCounterOverflowed && this.mCounterView != null) {
            background.setColorFilter((ColorFilter)AppCompatDrawableManager.getPorterDuffColorFilter(this.mCounterView.getCurrentTextColor(), PorterDuff$Mode.SRC_IN));
            return;
        }
        background.clearColorFilter();
        this.mEditText.refreshDrawableState();
    }
    
    private LinearLayout$LayoutParams updateEditTextMargin(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        LinearLayout$LayoutParams linearLayout$LayoutParams;
        if (viewGroup$LayoutParams instanceof LinearLayout$LayoutParams) {
            linearLayout$LayoutParams = (LinearLayout$LayoutParams)viewGroup$LayoutParams;
        }
        else {
            linearLayout$LayoutParams = new LinearLayout$LayoutParams(viewGroup$LayoutParams);
        }
        if (this.mHintEnabled) {
            if (this.mTmpPaint == null) {
                this.mTmpPaint = new Paint();
            }
            this.mTmpPaint.setTypeface(this.mCollapsingTextHelper.getCollapsedTypeface());
            this.mTmpPaint.setTextSize(this.mCollapsingTextHelper.getCollapsedTextSize());
            linearLayout$LayoutParams.topMargin = (int)(-this.mTmpPaint.ascent());
            return linearLayout$LayoutParams;
        }
        linearLayout$LayoutParams.topMargin = 0;
        return linearLayout$LayoutParams;
    }
    
    private void updateLabelState(final boolean b) {
        boolean b2;
        if (this.mEditText != null && !TextUtils.isEmpty((CharSequence)this.mEditText.getText())) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        final boolean arrayContains = arrayContains(this.getDrawableState(), 16842908);
        boolean b3;
        if (!TextUtils.isEmpty(this.getError())) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        if (this.mDefaultTextColor != null) {
            this.mCollapsingTextHelper.setExpandedTextColor(this.mDefaultTextColor.getDefaultColor());
        }
        if (this.mCounterOverflowed && this.mCounterView != null) {
            this.mCollapsingTextHelper.setCollapsedTextColor(this.mCounterView.getCurrentTextColor());
        }
        else if (arrayContains && this.mFocusedTextColor != null) {
            this.mCollapsingTextHelper.setCollapsedTextColor(this.mFocusedTextColor.getDefaultColor());
        }
        else if (this.mDefaultTextColor != null) {
            this.mCollapsingTextHelper.setCollapsedTextColor(this.mDefaultTextColor.getDefaultColor());
        }
        if (b2 || arrayContains || b3) {
            this.collapseHint(b);
            return;
        }
        this.expandHint(b);
    }
    
    public void addView(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (view instanceof EditText) {
            this.setEditText((EditText)view);
            super.addView(view, 0, (ViewGroup$LayoutParams)this.updateEditTextMargin(viewGroup$LayoutParams));
            return;
        }
        super.addView(view, n, viewGroup$LayoutParams);
    }
    
    public void draw(final Canvas canvas) {
        super.draw(canvas);
        if (this.mHintEnabled) {
            this.mCollapsingTextHelper.draw(canvas);
        }
    }
    
    public int getCounterMaxLength() {
        return this.mCounterMaxLength;
    }
    
    @Nullable
    public EditText getEditText() {
        return this.mEditText;
    }
    
    @Nullable
    public CharSequence getError() {
        if (this.mErrorEnabled) {
            return this.mError;
        }
        return null;
    }
    
    @Nullable
    public CharSequence getHint() {
        if (this.mHintEnabled) {
            return this.mHint;
        }
        return null;
    }
    
    @NonNull
    public Typeface getTypeface() {
        return this.mCollapsingTextHelper.getCollapsedTypeface();
    }
    
    public boolean isCounterEnabled() {
        return this.mCounterEnabled;
    }
    
    public boolean isErrorEnabled() {
        return this.mErrorEnabled;
    }
    
    public boolean isHintAnimationEnabled() {
        return this.mHintAnimationEnabled;
    }
    
    public boolean isHintEnabled() {
        return this.mHintEnabled;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        if (this.mHintEnabled && this.mEditText != null) {
            final int n5 = this.mEditText.getLeft() + this.mEditText.getCompoundPaddingLeft();
            final int n6 = this.mEditText.getRight() - this.mEditText.getCompoundPaddingRight();
            this.mCollapsingTextHelper.setExpandedBounds(n5, this.mEditText.getTop() + this.mEditText.getCompoundPaddingTop(), n6, this.mEditText.getBottom() - this.mEditText.getCompoundPaddingBottom());
            this.mCollapsingTextHelper.setCollapsedBounds(n5, this.getPaddingTop(), n6, n4 - n2 - this.getPaddingBottom());
            this.mCollapsingTextHelper.recalculate();
        }
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        final SavedState savedState = (SavedState)parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.setError(savedState.error);
        this.requestLayout();
    }
    
    public Parcelable onSaveInstanceState() {
        final SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.mErrorShown) {
            savedState.error = this.getError();
        }
        return (Parcelable)savedState;
    }
    
    public void refreshDrawableState() {
        super.refreshDrawableState();
        this.updateLabelState(ViewCompat.isLaidOut((View)this));
    }
    
    public void setCounterEnabled(final boolean mCounterEnabled) {
        if (this.mCounterEnabled == mCounterEnabled) {
            return;
        }
    Label_0071:
        while (true) {
            if (!mCounterEnabled) {
                this.removeIndicator(this.mCounterView);
                this.mCounterView = null;
                break Label_0071;
            }
            while (true) {
                (this.mCounterView = new TextView(this.getContext())).setMaxLines(1);
                while (true) {
                    try {
                        this.mCounterView.setTextAppearance(this.getContext(), this.mCounterTextAppearance);
                        this.addIndicator(this.mCounterView, -1);
                        if (this.mEditText == null) {
                            this.updateCounter(0);
                            this.mCounterEnabled = mCounterEnabled;
                            return;
                        }
                    }
                    catch (Exception ex) {
                        this.mCounterView.setTextAppearance(this.getContext(), R.style.TextAppearance_AppCompat_Caption);
                        this.mCounterView.setTextColor(ContextCompat.getColor(this.getContext(), R.color.design_textinput_error_color_light));
                        continue;
                    }
                    break;
                }
                this.updateCounter(this.mEditText.getText().length());
                continue Label_0071;
            }
            break;
        }
    }
    
    public void setCounterMaxLength(final int mCounterMaxLength) {
        if (this.mCounterMaxLength != mCounterMaxLength) {
            if (mCounterMaxLength > 0) {
                this.mCounterMaxLength = mCounterMaxLength;
            }
            else {
                this.mCounterMaxLength = -1;
            }
            if (this.mCounterEnabled) {
                int length;
                if (this.mEditText == null) {
                    length = 0;
                }
                else {
                    length = this.mEditText.getText().length();
                }
                this.updateCounter(length);
            }
        }
    }
    
    public void setError(@Nullable final CharSequence charSequence) {
        if (!TextUtils.equals(this.mError, charSequence)) {
            this.mError = charSequence;
            if (!this.mErrorEnabled) {
                if (TextUtils.isEmpty(charSequence)) {
                    return;
                }
                this.setErrorEnabled(true);
            }
            final boolean laidOut = ViewCompat.isLaidOut((View)this);
            this.mErrorShown = !TextUtils.isEmpty(charSequence);
            if (this.mErrorShown) {
                this.mErrorView.setText(charSequence);
                this.mErrorView.setVisibility(0);
                if (laidOut) {
                    if (ViewCompat.getAlpha((View)this.mErrorView) == 1.0f) {
                        ViewCompat.setAlpha((View)this.mErrorView, 0.0f);
                    }
                    ViewCompat.animate((View)this.mErrorView).alpha(1.0f).setDuration(200L).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setListener(new ViewPropertyAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(final View view) {
                            view.setVisibility(0);
                        }
                    }).start();
                }
            }
            else if (this.mErrorView.getVisibility() == 0) {
                if (laidOut) {
                    ViewCompat.animate((View)this.mErrorView).alpha(0.0f).setDuration(200L).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setListener(new ViewPropertyAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(final View view) {
                            TextInputLayout.this.mErrorView.setText(charSequence);
                            view.setVisibility(4);
                        }
                    }).start();
                }
                else {
                    this.mErrorView.setVisibility(4);
                }
            }
            this.updateEditTextBackground();
            this.updateLabelState(true);
        }
    }
    
    public void setErrorEnabled(final boolean mErrorEnabled) {
        if (this.mErrorEnabled == mErrorEnabled) {
            return;
        }
        if (this.mErrorView != null) {
            ViewCompat.animate((View)this.mErrorView).cancel();
        }
        while (true) {
            Label_0125: {
                if (!mErrorEnabled) {
                    break Label_0125;
                }
                this.mErrorView = new TextView(this.getContext());
                while (true) {
                    try {
                        this.mErrorView.setTextAppearance(this.getContext(), this.mErrorTextAppearance);
                        this.mErrorView.setVisibility(4);
                        ViewCompat.setAccessibilityLiveRegion((View)this.mErrorView, 1);
                        this.addIndicator(this.mErrorView, 0);
                        this.mErrorEnabled = mErrorEnabled;
                        return;
                    }
                    catch (Exception ex) {
                        this.mErrorView.setTextAppearance(this.getContext(), R.style.TextAppearance_AppCompat_Caption);
                        this.mErrorView.setTextColor(ContextCompat.getColor(this.getContext(), R.color.design_textinput_error_color_light));
                        continue;
                    }
                    break;
                }
            }
            this.mErrorShown = false;
            this.updateEditTextBackground();
            this.removeIndicator(this.mErrorView);
            this.mErrorView = null;
            continue;
        }
    }
    
    public void setHint(@Nullable final CharSequence hintInternal) {
        if (this.mHintEnabled) {
            this.setHintInternal(hintInternal);
            this.sendAccessibilityEvent(2048);
        }
    }
    
    public void setHintAnimationEnabled(final boolean mHintAnimationEnabled) {
        this.mHintAnimationEnabled = mHintAnimationEnabled;
    }
    
    public void setHintEnabled(final boolean mHintEnabled) {
        if (mHintEnabled != this.mHintEnabled) {
            this.mHintEnabled = mHintEnabled;
            final CharSequence hint = this.mEditText.getHint();
            if (!this.mHintEnabled) {
                if (!TextUtils.isEmpty(this.mHint) && TextUtils.isEmpty(hint)) {
                    this.mEditText.setHint(this.mHint);
                }
                this.setHintInternal(null);
            }
            else if (!TextUtils.isEmpty(hint)) {
                if (TextUtils.isEmpty(this.mHint)) {
                    this.setHint(hint);
                }
                this.mEditText.setHint((CharSequence)null);
            }
            if (this.mEditText != null) {
                this.mEditText.setLayoutParams((ViewGroup$LayoutParams)this.updateEditTextMargin(this.mEditText.getLayoutParams()));
            }
        }
    }
    
    public void setHintTextAppearance(@StyleRes final int collapsedTextAppearance) {
        this.mCollapsingTextHelper.setCollapsedTextAppearance(collapsedTextAppearance);
        this.mFocusedTextColor = ColorStateList.valueOf(this.mCollapsingTextHelper.getCollapsedTextColor());
        if (this.mEditText != null) {
            this.updateLabelState(false);
            this.mEditText.setLayoutParams((ViewGroup$LayoutParams)this.updateEditTextMargin(this.mEditText.getLayoutParams()));
            this.mEditText.requestLayout();
        }
    }
    
    public void setTypeface(@Nullable final Typeface typefaces) {
        this.mCollapsingTextHelper.setTypefaces(typefaces);
    }
    
    static class SavedState extends View$BaseSavedState
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        CharSequence error;
        
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
            this.error = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }
        
        SavedState(final Parcelable parcelable) {
            super(parcelable);
        }
        
        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + (Object)this.error + "}";
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            super.writeToParcel(parcel, n);
            TextUtils.writeToParcel(this.error, parcel, n);
        }
    }
    
    private class TextInputAccessibilityDelegate extends AccessibilityDelegateCompat
    {
        @Override
        public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName((CharSequence)TextInputLayout.class.getSimpleName());
        }
        
        @Override
        public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setClassName(TextInputLayout.class.getSimpleName());
            final CharSequence text = TextInputLayout.this.mCollapsingTextHelper.getText();
            if (!TextUtils.isEmpty(text)) {
                accessibilityNodeInfoCompat.setText(text);
            }
            if (TextInputLayout.this.mEditText != null) {
                accessibilityNodeInfoCompat.setLabelFor((View)TextInputLayout.this.mEditText);
            }
            CharSequence text2;
            if (TextInputLayout.this.mErrorView != null) {
                text2 = TextInputLayout.this.mErrorView.getText();
            }
            else {
                text2 = null;
            }
            if (!TextUtils.isEmpty(text2)) {
                accessibilityNodeInfoCompat.setContentInvalid(true);
                accessibilityNodeInfoCompat.setError(text2);
            }
        }
        
        @Override
        public void onPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            final CharSequence text = TextInputLayout.this.mCollapsingTextHelper.getText();
            if (!TextUtils.isEmpty(text)) {
                accessibilityEvent.getText().add(text);
            }
        }
    }
}
