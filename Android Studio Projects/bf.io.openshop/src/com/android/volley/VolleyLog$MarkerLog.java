package com.android.volley;

import android.os.*;
import java.util.*;

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
