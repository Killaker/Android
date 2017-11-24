package com.google.android.gms.analytics;

import android.content.*;
import java.util.*;
import com.google.android.gms.analytics.internal.*;
import android.content.pm.*;

public class StandardExceptionParser implements ExceptionParser
{
    private final TreeSet<String> zzPr;
    
    public StandardExceptionParser(final Context context, final Collection<String> collection) {
        this.zzPr = new TreeSet<String>();
        this.setIncludedPackages(context, collection);
    }
    
    protected StackTraceElement getBestStackTraceElement(final Throwable t) {
        final StackTraceElement[] stackTrace = t.getStackTrace();
        if (stackTrace == null || stackTrace.length == 0) {
            return null;
        }
        for (final StackTraceElement stackTraceElement : stackTrace) {
            final String className = stackTraceElement.getClassName();
            final Iterator<String> iterator = this.zzPr.iterator();
            while (iterator.hasNext()) {
                if (className.startsWith(iterator.next())) {
                    return stackTraceElement;
                }
            }
        }
        return stackTrace[0];
    }
    
    protected Throwable getCause(Throwable cause) {
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        return cause;
    }
    
    @Override
    public String getDescription(final String s, final Throwable t) {
        return this.getDescription(this.getCause(t), this.getBestStackTraceElement(this.getCause(t)), s);
    }
    
    protected String getDescription(final Throwable t, final StackTraceElement stackTraceElement, final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append(t.getClass().getSimpleName());
        if (stackTraceElement != null) {
            final String[] split = stackTraceElement.getClassName().split("\\.");
            String s2 = "unknown";
            if (split != null && split.length > 0) {
                s2 = split[-1 + split.length];
            }
            sb.append(String.format(" (@%s:%s:%s)", s2, stackTraceElement.getMethodName(), stackTraceElement.getLineNumber()));
        }
        if (s != null) {
            sb.append(String.format(" {%s}", s));
        }
        return sb.toString();
    }
    
    public void setIncludedPackages(final Context context, final Collection<String> collection) {
        this.zzPr.clear();
        final HashSet<String> set = new HashSet<String>();
        if (collection != null) {
            set.addAll((Collection<?>)collection);
        }
        if (context != null) {
            try {
                final String packageName = context.getApplicationContext().getPackageName();
                this.zzPr.add(packageName);
                final ActivityInfo[] activities = context.getApplicationContext().getPackageManager().getPackageInfo(packageName, 1).activities;
                if (activities != null) {
                    for (int length = activities.length, i = 0; i < length; ++i) {
                        set.add(activities[i].packageName);
                    }
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                zzae.zzaJ("No package found");
            }
        }
        for (final String s : set) {
            final Iterator<String> iterator2 = this.zzPr.iterator();
            int n = 1;
            while (iterator2.hasNext()) {
                final String s2 = iterator2.next();
                if (!s.startsWith(s2)) {
                    if (s2.startsWith(s)) {
                        this.zzPr.remove(s2);
                        break;
                    }
                    break;
                }
                else {
                    n = 0;
                }
            }
            if (n != 0) {
                this.zzPr.add(s);
            }
        }
    }
}
