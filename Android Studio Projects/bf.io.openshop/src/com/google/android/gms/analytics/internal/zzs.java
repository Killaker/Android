package com.google.android.gms.analytics.internal;

import com.google.android.gms.analytics.*;
import android.util.*;

class zzs implements Logger
{
    private boolean zzPk;
    private int zzRB;
    
    zzs() {
        this.zzRB = 2;
    }
    
    @Override
    public void error(final Exception ex) {
    }
    
    @Override
    public void error(final String s) {
    }
    
    @Override
    public int getLogLevel() {
        return this.zzRB;
    }
    
    @Override
    public void info(final String s) {
    }
    
    @Override
    public void setLogLevel(final int zzRB) {
        this.zzRB = zzRB;
        if (!this.zzPk) {
            Log.i((String)zzy.zzRL.get(), "Logger is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag." + zzy.zzRL.get() + " DEBUG");
            this.zzPk = true;
        }
    }
    
    @Override
    public void verbose(final String s) {
    }
    
    @Override
    public void warn(final String s) {
    }
}
