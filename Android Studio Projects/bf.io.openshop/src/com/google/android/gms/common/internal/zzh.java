package com.google.android.gms.common.internal;

import android.app.*;
import android.support.v4.app.*;
import android.util.*;
import android.content.*;

public class zzh implements DialogInterface$OnClickListener
{
    private final Activity mActivity;
    private final Intent mIntent;
    private final int zzagz;
    private final Fragment zzalg;
    
    public zzh(final Activity mActivity, final Intent mIntent, final int zzagz) {
        this.mActivity = mActivity;
        this.zzalg = null;
        this.mIntent = mIntent;
        this.zzagz = zzagz;
    }
    
    public zzh(final Fragment zzalg, final Intent mIntent, final int zzagz) {
        this.mActivity = null;
        this.zzalg = zzalg;
        this.mIntent = mIntent;
        this.zzagz = zzagz;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        try {
            if (this.mIntent != null && this.zzalg != null) {
                this.zzalg.startActivityForResult(this.mIntent, this.zzagz);
            }
            else if (this.mIntent != null) {
                this.mActivity.startActivityForResult(this.mIntent, this.zzagz);
            }
            dialogInterface.dismiss();
        }
        catch (ActivityNotFoundException ex) {
            Log.e("SettingsRedirect", "Can't redirect to app settings for Google Play services");
        }
    }
}
