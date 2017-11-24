package com.android.volley;

import android.util.*;
import android.os.*;
import java.util.*;

public class VolleyLog
{
    public static boolean DEBUG;
    public static String TAG;
    
    static {
        VolleyLog.TAG = "Volley";
        VolleyLog.DEBUG = Log.isLoggable(VolleyLog.TAG, 2);
    }
    
    private static String buildMessage(final String s, final Object... array) {
        String format;
        if (array == null) {
            format = s;
        }
        else {
            format = String.format(Locale.US, s, array);
        }
        final StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        String string = "<unknown>";
        for (int i = 2; i < stackTrace.length; ++i) {
            if (!stackTrace[i].getClass().equals(VolleyLog.class)) {
                final String className = stackTrace[i].getClassName();
                final String substring = className.substring(1 + className.lastIndexOf(46));
                string = substring.substring(1 + substring.lastIndexOf(36)) + "." + stackTrace[i].getMethodName();
                break;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), string, format);
    }
    
    public static void d(final String s, final Object... array) {
        Log.d(VolleyLog.TAG, buildMessage(s, array));
    }
    
    public static void e(final String s, final Object... array) {
        Log.e(VolleyLog.TAG, buildMessage(s, array));
    }
    
    public static void e(final Throwable t, final String s, final Object... array) {
        Log.e(VolleyLog.TAG, buildMessage(s, array), t);
    }
    
    public static void setTag(final String tag) {
        d("Changing log tag to %s", tag);
        VolleyLog.TAG = tag;
        VolleyLog.DEBUG = Log.isLoggable(VolleyLog.TAG, 2);
    }
    
    public static void v(final String s, final Object... array) {
        if (VolleyLog.DEBUG) {
            Log.v(VolleyLog.TAG, buildMessage(s, array));
        }
    }
    
    public static void wtf(final String s, final Object... array) {
        Log.wtf(VolleyLog.TAG, buildMessage(s, array));
    }
    
    public static void wtf(final Throwable t, final String s, final Object... array) {
        Log.wtf(VolleyLog.TAG, buildMessage(s, array), t);
    }
    
    static class MarkerLog
    {
        public static final boolean ENABLED;
        private static final long MIN_DURATION_FOR_LOGGING_MS;
        private boolean mFinished;
        private final List<Marker> mMarkers;
        
        static {
            ENABLED = VolleyLog.DEBUG;
        }
        
        MarkerLog() {
            this.mMarkers = new ArrayList<Marker>();
            this.mFinished = false;
        }
        
        private long getTotalDuration() {
            if (this.mMarkers.size() == 0) {
                return 0L;
            }
            return this.mMarkers.get(-1 + this.mMarkers.size()).time - this.mMarkers.get(0).time;
        }
        
        public void add(final String s, final long n) {
            synchronized (this) {
                if (this.mFinished) {
                    throw new IllegalStateException("Marker added to finished log");
                }
            }
            this.mMarkers.add(new Marker(s, n, SystemClock.elapsedRealtime()));
        }
        // monitorexit(this)
        
        @Override
        protected void finalize() throws Throwable {
            if (!this.mFinished) {
                this.finish("Request on the loose");
                VolleyLog.e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }
        
        public void finish(final String s) {
            synchronized (this) {
                this.mFinished = true;
                final long totalDuration = this.getTotalDuration();
                if (totalDuration > 0L) {
                    long time = this.mMarkers.get(0).time;
                    VolleyLog.d("(%-4d ms) %s", totalDuration, s);
                    for (final Marker marker : this.mMarkers) {
                        final long time2 = marker.time;
                        VolleyLog.d("(+%-4d) [%2d] %s", time2 - time, marker.thread, marker.name);
                        time = time2;
                    }
                }
            }
        }
        
        private static class Marker
        {
            public final String name;
            public final long thread;
            public final long time;
            
            public Marker(final String name, final long thread, final long time) {
                this.name = name;
                this.thread = thread;
                this.time = time;
            }
        }
    }
}
