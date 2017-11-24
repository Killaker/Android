package com.google.android.gms.tagmanager;

import android.content.*;
import com.google.android.gms.analytics.*;

public class zzdc
{
    private Context mContext;
    private Tracker zzPb;
    private GoogleAnalytics zzPd;
    
    public zzdc(final Context mContext) {
        this.mContext = mContext;
    }
    
    private void zzgr(final String s) {
        synchronized (this) {
            if (this.zzPd == null) {
                (this.zzPd = GoogleAnalytics.getInstance(this.mContext)).setLogger(new zza());
                this.zzPb = this.zzPd.newTracker(s);
            }
        }
    }
    
    public Tracker zzgq(final String s) {
        this.zzgr(s);
        return this.zzPb;
    }
    
    static class zza implements Logger
    {
        private static int zzkn(final int n) {
            switch (n) {
                default: {
                    return 3;
                }
                case 5: {
                    return 2;
                }
                case 3:
                case 4: {
                    return 1;
                }
                case 2: {
                    return 0;
                }
            }
        }
        
        @Override
        public void error(final Exception ex) {
            zzbg.zzb("", ex);
        }
        
        @Override
        public void error(final String s) {
            zzbg.e(s);
        }
        
        @Override
        public int getLogLevel() {
            return zzkn(zzbg.getLogLevel());
        }
        
        @Override
        public void info(final String s) {
            zzbg.zzaJ(s);
        }
        
        @Override
        public void setLogLevel(final int n) {
            zzbg.zzaK("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
        }
        
        @Override
        public void verbose(final String s) {
            zzbg.v(s);
        }
        
        @Override
        public void warn(final String s) {
            zzbg.zzaK(s);
        }
    }
}
