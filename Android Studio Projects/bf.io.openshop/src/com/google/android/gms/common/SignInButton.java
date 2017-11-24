package com.google.android.gms.common;

import com.google.android.gms.common.api.*;
import android.view.*;
import android.content.*;
import android.widget.*;
import com.google.android.gms.*;
import android.content.res.*;
import com.google.android.gms.common.internal.*;
import android.util.*;
import com.google.android.gms.dynamic.*;
import java.lang.annotation.*;

public final class SignInButton extends FrameLayout implements View$OnClickListener
{
    public static final int COLOR_AUTO = 2;
    public static final int COLOR_DARK = 0;
    public static final int COLOR_LIGHT = 1;
    public static final int SIZE_ICON_ONLY = 2;
    public static final int SIZE_STANDARD = 0;
    public static final int SIZE_WIDE = 1;
    private int mColor;
    private int mSize;
    private Scope[] zzafT;
    private View zzafU;
    private View$OnClickListener zzafV;
    
    public SignInButton(final Context context) {
        this(context, null);
    }
    
    public SignInButton(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public SignInButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.zzafV = null;
        this.zza(context, set);
        this.setStyle(this.mSize, this.mColor, this.zzafT);
    }
    
    private static Button zza(final Context context, final int n, final int n2, final Scope[] array) {
        final zzac zzac = new zzac(context);
        zzac.zza(context.getResources(), n, n2, array);
        return zzac;
    }
    
    private void zza(final Context context, final AttributeSet set) {
        int i = 0;
        final TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(set, R.styleable.SignInButton, 0, 0);
        try {
            this.mSize = obtainStyledAttributes.getInt(R.styleable.SignInButton_buttonSize, 0);
            this.mColor = obtainStyledAttributes.getInt(R.styleable.SignInButton_colorScheme, 2);
            final String string = obtainStyledAttributes.getString(R.styleable.SignInButton_scopeUris);
            if (string == null) {
                this.zzafT = null;
            }
            else {
                final String[] split = string.trim().split("\\s+");
                this.zzafT = new Scope[split.length];
                while (i < split.length) {
                    this.zzafT[i] = new Scope(split[i].toString());
                    ++i;
                }
            }
        }
        finally {
            obtainStyledAttributes.recycle();
        }
    }
    
    private void zzar(final Context context) {
        if (this.zzafU != null) {
            this.removeView(this.zzafU);
        }
        while (true) {
            try {
                this.zzafU = zzab.zzb(context, this.mSize, this.mColor, this.zzafT);
                this.addView(this.zzafU);
                this.zzafU.setEnabled(this.isEnabled());
                this.zzafU.setOnClickListener((View$OnClickListener)this);
            }
            catch (zzg.zza zza) {
                Log.w("SignInButton", "Sign in button not found, using placeholder instead");
                this.zzafU = (View)zza(context, this.mSize, this.mColor, this.zzafT);
                continue;
            }
            break;
        }
    }
    
    public void onClick(final View view) {
        if (this.zzafV != null && view == this.zzafU) {
            this.zzafV.onClick((View)this);
        }
    }
    
    public void setColorScheme(final int n) {
        this.setStyle(this.mSize, n, this.zzafT);
    }
    
    public void setEnabled(final boolean b) {
        super.setEnabled(b);
        this.zzafU.setEnabled(b);
    }
    
    public void setOnClickListener(final View$OnClickListener zzafV) {
        this.zzafV = zzafV;
        if (this.zzafU != null) {
            this.zzafU.setOnClickListener((View$OnClickListener)this);
        }
    }
    
    public void setScopes(final Scope[] array) {
        this.setStyle(this.mSize, this.mColor, array);
    }
    
    public void setSize(final int n) {
        this.setStyle(n, this.mColor, this.zzafT);
    }
    
    public void setStyle(final int n, final int n2) {
        this.setStyle(n, n2, this.zzafT);
    }
    
    public void setStyle(final int mSize, final int mColor, final Scope[] zzafT) {
        this.mSize = mSize;
        this.mColor = mColor;
        this.zzafT = zzafT;
        this.zzar(this.getContext());
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface ButtonSize {
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorScheme {
    }
}
