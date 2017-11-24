package com.google.android.gms.tagmanager;

import android.util.*;

public class zzy implements zzbh
{
    private int zzRB;
    
    public zzy() {
        this.zzRB = 5;
    }
    
    @Override
    public void e(final String s) {
        if (this.zzRB <= 6) {
            Log.e("GoogleTagManager", s);
        }
    }
    
    @Override
    public void setLogLevel(final int zzRB) {
        this.zzRB = zzRB;
    }
    
    @Override
    public void v(final String s) {
        if (this.zzRB <= 2) {
            Log.v("GoogleTagManager", s);
        }
    }
    
    @Override
    public void zzaI(final String s) {
        if (this.zzRB <= 3) {
            Log.d("GoogleTagManager", s);
        }
    }
    
    @Override
    public void zzaJ(final String s) {
        if (this.zzRB <= 4) {
            Log.i("GoogleTagManager", s);
        }
    }
    
    @Override
    public void zzaK(final String s) {
        if (this.zzRB <= 5) {
            Log.w("GoogleTagManager", s);
        }
    }
    
    @Override
    public void zzb(final String s, final Throwable t) {
        if (this.zzRB <= 6) {
            Log.e("GoogleTagManager", s, t);
        }
    }
    
    @Override
    public void zzd(final String s, final Throwable t) {
        if (this.zzRB <= 5) {
            Log.w("GoogleTagManager", s, t);
        }
    }
}
