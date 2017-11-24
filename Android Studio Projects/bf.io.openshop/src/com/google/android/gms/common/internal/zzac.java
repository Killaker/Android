package com.google.android.gms.common.internal;

import android.widget.*;
import android.content.*;
import android.util.*;
import android.graphics.*;
import com.google.android.gms.*;
import com.google.android.gms.common.api.*;
import android.content.res.*;
import android.text.method.*;

public final class zzac extends Button
{
    public zzac(final Context context) {
        this(context, null);
    }
    
    public zzac(final Context context, final AttributeSet set) {
        super(context, set, 16842824);
    }
    
    private void zza(final Resources resources) {
        this.setTypeface(Typeface.DEFAULT_BOLD);
        this.setTextSize(14.0f);
        final float density = resources.getDisplayMetrics().density;
        this.setMinHeight((int)(0.5f + density * 48.0f));
        this.setMinWidth((int)(0.5f + density * 48.0f));
    }
    
    private void zza(final Resources resources, final int n, final int n2, final boolean b) {
        int n3;
        if (b) {
            n3 = this.zzd(n, this.zzf(n2, R.drawable.common_plus_signin_btn_icon_dark, R.drawable.common_plus_signin_btn_icon_light, R.drawable.common_plus_signin_btn_icon_dark), this.zzf(n2, R.drawable.common_plus_signin_btn_text_dark, R.drawable.common_plus_signin_btn_text_light, R.drawable.common_plus_signin_btn_text_dark));
        }
        else {
            n3 = this.zzd(n, this.zzf(n2, R.drawable.common_google_signin_btn_icon_dark, R.drawable.common_google_signin_btn_icon_light, R.drawable.common_google_signin_btn_icon_light), this.zzf(n2, R.drawable.common_google_signin_btn_text_dark, R.drawable.common_google_signin_btn_text_light, R.drawable.common_google_signin_btn_text_light));
        }
        this.setBackgroundDrawable(resources.getDrawable(n3));
    }
    
    private boolean zza(final Scope[] array) {
        if (array != null) {
            for (int length = array.length, i = 0; i < length; ++i) {
                final String zzpb = array[i].zzpb();
                if (zzpb.contains("/plus.") && !zzpb.equals("https://www.googleapis.com/auth/plus.me")) {
                    return true;
                }
                if (zzpb.equals("https://www.googleapis.com/auth/games")) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void zzb(final Resources resources, final int n, final int n2, final boolean b) {
        int n3;
        if (b) {
            n3 = this.zzf(n2, R.color.common_plus_signin_btn_text_dark, R.color.common_plus_signin_btn_text_light, R.color.common_plus_signin_btn_text_dark);
        }
        else {
            n3 = this.zzf(n2, R.color.common_google_signin_btn_text_dark, R.color.common_google_signin_btn_text_light, R.color.common_google_signin_btn_text_light);
        }
        this.setTextColor((ColorStateList)zzx.zzz(resources.getColorStateList(n3)));
        switch (n) {
            default: {
                throw new IllegalStateException("Unknown button size: " + n);
            }
            case 0: {
                this.setText((CharSequence)resources.getString(R.string.common_signin_button_text));
                break;
            }
            case 1: {
                this.setText((CharSequence)resources.getString(R.string.common_signin_button_text_long));
                break;
            }
            case 2: {
                this.setText((CharSequence)null);
                break;
            }
        }
        this.setTransformationMethod((TransformationMethod)null);
    }
    
    private int zzd(final int n, final int n2, int n3) {
        switch (n) {
            default: {
                throw new IllegalStateException("Unknown button size: " + n);
            }
            case 2: {
                n3 = n2;
            }
            case 0:
            case 1: {
                return n3;
            }
        }
    }
    
    private int zzf(final int n, int n2, final int n3, final int n4) {
        switch (n) {
            default: {
                throw new IllegalStateException("Unknown color scheme: " + n);
            }
            case 1: {
                n2 = n3;
            }
            case 0: {
                return n2;
            }
            case 2: {
                return n4;
            }
        }
    }
    
    public void zza(final Resources resources, final int n, final int n2, final Scope[] array) {
        final boolean zza = this.zza(array);
        this.zza(resources);
        this.zza(resources, n, n2, zza);
        this.zzb(resources, n, n2, zza);
    }
}
