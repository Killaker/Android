package com.facebook;

import android.widget.*;
import com.facebook.internal.*;
import android.util.*;
import com.facebook.appevents.*;
import android.os.*;
import android.support.v4.content.*;
import android.content.res.*;
import android.annotation.*;
import android.view.*;
import android.app.*;
import android.content.*;
import android.support.v4.app.*;
import android.graphics.*;

public abstract class FacebookButtonBase extends Button
{
    private String analyticsButtonCreatedEventName;
    private String analyticsButtonTappedEventName;
    private View$OnClickListener externalOnClickListener;
    private View$OnClickListener internalOnClickListener;
    private boolean overrideCompoundPadding;
    private int overrideCompoundPaddingLeft;
    private int overrideCompoundPaddingRight;
    private FragmentWrapper parentFragment;
    
    protected FacebookButtonBase(final Context context, final AttributeSet set, final int n, int n2, final String analyticsButtonCreatedEventName, final String analyticsButtonTappedEventName) {
        super(context, set, 0);
        if (n2 == 0) {
            n2 = this.getDefaultStyleResource();
        }
        if (n2 == 0) {
            n2 = R.style.com_facebook_button;
        }
        this.configureButton(context, set, n, n2);
        this.analyticsButtonCreatedEventName = analyticsButtonCreatedEventName;
        this.analyticsButtonTappedEventName = analyticsButtonTappedEventName;
        this.setClickable(true);
        this.setFocusable(true);
    }
    
    private void logButtonCreated(final Context context) {
        AppEventsLogger.newLogger(context).logSdkEvent(this.analyticsButtonCreatedEventName, null, null);
    }
    
    private void logButtonTapped(final Context context) {
        AppEventsLogger.newLogger(context).logSdkEvent(this.analyticsButtonTappedEventName, null, null);
    }
    
    private void parseBackgroundAttributes(final Context context, final AttributeSet set, final int n, final int n2) {
        if (this.isInEditMode()) {
            return;
        }
        while (true) {
            final TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(set, new int[] { 16842964 }, n, n2);
            try {
                if (obtainStyledAttributes.hasValue(0)) {
                    final int resourceId = obtainStyledAttributes.getResourceId(0, 0);
                    if (resourceId != 0) {
                        this.setBackgroundResource(resourceId);
                    }
                    else {
                        this.setBackgroundColor(obtainStyledAttributes.getColor(0, 0));
                    }
                    return;
                }
            }
            finally {
                obtainStyledAttributes.recycle();
            }
            this.setBackgroundColor(ContextCompat.getColor(context, R.color.com_facebook_blue));
        }
    }
    
    @SuppressLint({ "ResourceType" })
    private void parseCompoundDrawableAttributes(final Context context, final AttributeSet set, final int n, final int n2) {
        final TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(set, new int[] { 16843119, 16843117, 16843120, 16843118, 16843121 }, n, n2);
        try {
            this.setCompoundDrawablesWithIntrinsicBounds(obtainStyledAttributes.getResourceId(0, 0), obtainStyledAttributes.getResourceId(1, 0), obtainStyledAttributes.getResourceId(2, 0), obtainStyledAttributes.getResourceId(3, 0));
            this.setCompoundDrawablePadding(obtainStyledAttributes.getDimensionPixelSize(4, 0));
        }
        finally {
            obtainStyledAttributes.recycle();
        }
    }
    
    private void parseContentAttributes(final Context context, final AttributeSet set, final int n, final int n2) {
        final TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(set, new int[] { 16842966, 16842967, 16842968, 16842969 }, n, n2);
        try {
            this.setPadding(obtainStyledAttributes.getDimensionPixelSize(0, 0), obtainStyledAttributes.getDimensionPixelSize(1, 0), obtainStyledAttributes.getDimensionPixelSize(2, 0), obtainStyledAttributes.getDimensionPixelSize(3, 0));
        }
        finally {
            obtainStyledAttributes.recycle();
        }
    }
    
    private void parseTextAttributes(final Context p0, final AttributeSet p1, final int p2, final int p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_1       
        //     1: newarray        I
        //     3: dup            
        //     4: iconst_0       
        //     5: ldc             16842904
        //     7: iastore        
        //     8: astore          5
        //    10: aload_1        
        //    11: invokevirtual   android/content/Context.getTheme:()Landroid/content/res/Resources$Theme;
        //    14: aload_2        
        //    15: aload           5
        //    17: iload_3        
        //    18: iload           4
        //    20: invokevirtual   android/content/res/Resources$Theme.obtainStyledAttributes:(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
        //    23: astore          6
        //    25: aload_0        
        //    26: aload           6
        //    28: iconst_0       
        //    29: invokevirtual   android/content/res/TypedArray.getColorStateList:(I)Landroid/content/res/ColorStateList;
        //    32: invokevirtual   com/facebook/FacebookButtonBase.setTextColor:(Landroid/content/res/ColorStateList;)V
        //    35: aload           6
        //    37: invokevirtual   android/content/res/TypedArray.recycle:()V
        //    40: iconst_1       
        //    41: newarray        I
        //    43: dup            
        //    44: iconst_0       
        //    45: ldc             16842927
        //    47: iastore        
        //    48: astore          8
        //    50: aload_1        
        //    51: invokevirtual   android/content/Context.getTheme:()Landroid/content/res/Resources$Theme;
        //    54: aload_2        
        //    55: aload           8
        //    57: iload_3        
        //    58: iload           4
        //    60: invokevirtual   android/content/res/Resources$Theme.obtainStyledAttributes:(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
        //    63: astore          9
        //    65: aload_0        
        //    66: aload           9
        //    68: iconst_0       
        //    69: bipush          17
        //    71: invokevirtual   android/content/res/TypedArray.getInt:(II)I
        //    74: invokevirtual   com/facebook/FacebookButtonBase.setGravity:(I)V
        //    77: aload           9
        //    79: invokevirtual   android/content/res/TypedArray.recycle:()V
        //    82: iconst_3       
        //    83: newarray        I
        //    85: dup            
        //    86: iconst_0       
        //    87: ldc             16842901
        //    89: iastore        
        //    90: dup            
        //    91: iconst_1       
        //    92: ldc             16842903
        //    94: iastore        
        //    95: dup            
        //    96: iconst_2       
        //    97: ldc             16843087
        //    99: iastore        
        //   100: astore          11
        //   102: aload_1        
        //   103: invokevirtual   android/content/Context.getTheme:()Landroid/content/res/Resources$Theme;
        //   106: aload_2        
        //   107: aload           11
        //   109: iload_3        
        //   110: iload           4
        //   112: invokevirtual   android/content/res/Resources$Theme.obtainStyledAttributes:(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
        //   115: astore          12
        //   117: aload_0        
        //   118: iconst_0       
        //   119: aload           12
        //   121: iconst_0       
        //   122: iconst_0       
        //   123: invokevirtual   android/content/res/TypedArray.getDimensionPixelSize:(II)I
        //   126: i2f            
        //   127: invokevirtual   com/facebook/FacebookButtonBase.setTextSize:(IF)V
        //   130: aload_0        
        //   131: aload           12
        //   133: iconst_1       
        //   134: iconst_1       
        //   135: invokevirtual   android/content/res/TypedArray.getInt:(II)I
        //   138: invokestatic    android/graphics/Typeface.defaultFromStyle:(I)Landroid/graphics/Typeface;
        //   141: invokevirtual   com/facebook/FacebookButtonBase.setTypeface:(Landroid/graphics/Typeface;)V
        //   144: aload_0        
        //   145: aload           12
        //   147: iconst_2       
        //   148: invokevirtual   android/content/res/TypedArray.getString:(I)Ljava/lang/String;
        //   151: invokevirtual   com/facebook/FacebookButtonBase.setText:(Ljava/lang/CharSequence;)V
        //   154: aload           12
        //   156: invokevirtual   android/content/res/TypedArray.recycle:()V
        //   159: return         
        //   160: astore          7
        //   162: aload           6
        //   164: invokevirtual   android/content/res/TypedArray.recycle:()V
        //   167: aload           7
        //   169: athrow         
        //   170: astore          10
        //   172: aload           9
        //   174: invokevirtual   android/content/res/TypedArray.recycle:()V
        //   177: aload           10
        //   179: athrow         
        //   180: astore          13
        //   182: aload           12
        //   184: invokevirtual   android/content/res/TypedArray.recycle:()V
        //   187: aload           13
        //   189: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  25     35     160    170    Any
        //  65     77     170    180    Any
        //  117    154    180    190    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void setupOnClickListener() {
        super.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                FacebookButtonBase.this.logButtonTapped(FacebookButtonBase.this.getContext());
                if (FacebookButtonBase.this.internalOnClickListener != null) {
                    FacebookButtonBase.this.internalOnClickListener.onClick(view);
                }
                else if (FacebookButtonBase.this.externalOnClickListener != null) {
                    FacebookButtonBase.this.externalOnClickListener.onClick(view);
                }
            }
        });
    }
    
    protected void callExternalOnClickListener(final View view) {
        if (this.externalOnClickListener != null) {
            this.externalOnClickListener.onClick(view);
        }
    }
    
    protected void configureButton(final Context context, final AttributeSet set, final int n, final int n2) {
        this.parseBackgroundAttributes(context, set, n, n2);
        this.parseCompoundDrawableAttributes(context, set, n, n2);
        this.parseContentAttributes(context, set, n, n2);
        this.parseTextAttributes(context, set, n, n2);
        this.setupOnClickListener();
    }
    
    protected Activity getActivity() {
        Context context;
        for (context = this.getContext(); !(context instanceof Activity) && context instanceof ContextWrapper; context = ((ContextWrapper)context).getBaseContext()) {}
        if (context instanceof Activity) {
            return (Activity)context;
        }
        throw new FacebookException("Unable to get Activity.");
    }
    
    public int getCompoundPaddingLeft() {
        if (this.overrideCompoundPadding) {
            return this.overrideCompoundPaddingLeft;
        }
        return super.getCompoundPaddingLeft();
    }
    
    public int getCompoundPaddingRight() {
        if (this.overrideCompoundPadding) {
            return this.overrideCompoundPaddingRight;
        }
        return super.getCompoundPaddingRight();
    }
    
    protected abstract int getDefaultRequestCode();
    
    protected int getDefaultStyleResource() {
        return 0;
    }
    
    public Fragment getFragment() {
        if (this.parentFragment != null) {
            return this.parentFragment.getSupportFragment();
        }
        return null;
    }
    
    public android.app.Fragment getNativeFragment() {
        if (this.parentFragment != null) {
            return this.parentFragment.getNativeFragment();
        }
        return null;
    }
    
    public int getRequestCode() {
        return this.getDefaultRequestCode();
    }
    
    protected int measureTextWidth(final String s) {
        return (int)Math.ceil(this.getPaint().measureText(s));
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.isInEditMode()) {
            this.logButtonCreated(this.getContext());
        }
    }
    
    protected void onDraw(final Canvas canvas) {
        int n;
        if ((0x1 & this.getGravity()) != 0x0) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            final int compoundPaddingLeft = this.getCompoundPaddingLeft();
            final int compoundPaddingRight = this.getCompoundPaddingRight();
            final int min = Math.min((this.getWidth() - (compoundPaddingLeft + this.getCompoundDrawablePadding()) - compoundPaddingRight - this.measureTextWidth(this.getText().toString())) / 2, (compoundPaddingLeft - this.getPaddingLeft()) / 2);
            this.overrideCompoundPaddingLeft = compoundPaddingLeft - min;
            this.overrideCompoundPaddingRight = compoundPaddingRight + min;
            this.overrideCompoundPadding = true;
        }
        super.onDraw(canvas);
        this.overrideCompoundPadding = false;
    }
    
    public void setFragment(final android.app.Fragment fragment) {
        this.parentFragment = new FragmentWrapper(fragment);
    }
    
    public void setFragment(final Fragment fragment) {
        this.parentFragment = new FragmentWrapper(fragment);
    }
    
    protected void setInternalOnClickListener(final View$OnClickListener internalOnClickListener) {
        this.internalOnClickListener = internalOnClickListener;
    }
    
    public void setOnClickListener(final View$OnClickListener externalOnClickListener) {
        this.externalOnClickListener = externalOnClickListener;
    }
}
