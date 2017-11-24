package com.google.android.gms.analytics;

import android.content.*;
import java.util.*;
import com.google.android.gms.analytics.internal.*;

public class ExceptionReporter implements UncaughtExceptionHandler
{
    private final Context mContext;
    private final UncaughtExceptionHandler zzPa;
    private final Tracker zzPb;
    private ExceptionParser zzPc;
    private GoogleAnalytics zzPd;
    
    public ExceptionReporter(final Tracker zzPb, final UncaughtExceptionHandler zzPa, final Context context) {
        if (zzPb == null) {
            throw new NullPointerException("tracker cannot be null");
        }
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.zzPa = zzPa;
        this.zzPb = zzPb;
        this.zzPc = new StandardExceptionParser(context, new ArrayList<String>());
        this.mContext = context.getApplicationContext();
        final StringBuilder append = new StringBuilder().append("ExceptionReporter created, original handler is ");
        String name;
        if (zzPa == null) {
            name = "null";
        }
        else {
            name = zzPa.getClass().getName();
        }
        zzae.v(append.append(name).toString());
    }
    
    public ExceptionParser getExceptionParser() {
        return this.zzPc;
    }
    
    public void setExceptionParser(final ExceptionParser zzPc) {
        this.zzPc = zzPc;
    }
    
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        String description = "UncaughtException";
        if (this.zzPc != null) {
            String name;
            if (thread != null) {
                name = thread.getName();
            }
            else {
                name = null;
            }
            description = this.zzPc.getDescription(name, t);
        }
        zzae.v("Reporting uncaught exception: " + description);
        this.zzPb.send(new HitBuilders.ExceptionBuilder().setDescription(description).setFatal(true).build());
        final GoogleAnalytics zziC = this.zziC();
        zziC.dispatchLocalHits();
        zziC.zziG();
        if (this.zzPa != null) {
            zzae.v("Passing exception to the original handler");
            this.zzPa.uncaughtException(thread, t);
        }
    }
    
    GoogleAnalytics zziC() {
        if (this.zzPd == null) {
            this.zzPd = GoogleAnalytics.getInstance(this.mContext);
        }
        return this.zzPd;
    }
    
    UncaughtExceptionHandler zziD() {
        return this.zzPa;
    }
}
