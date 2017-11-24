package com.android.volley;

import java.util.concurrent.*;
import android.net.*;
import android.annotation.*;
import android.os.*;

public class NetworkDispatcher extends Thread
{
    private final Cache mCache;
    private final ResponseDelivery mDelivery;
    private final Network mNetwork;
    private final BlockingQueue<Request<?>> mQueue;
    private volatile boolean mQuit;
    
    public NetworkDispatcher(final BlockingQueue<Request<?>> mQueue, final Network mNetwork, final Cache mCache, final ResponseDelivery mDelivery) {
        this.mQuit = false;
        this.mQueue = mQueue;
        this.mNetwork = mNetwork;
        this.mCache = mCache;
        this.mDelivery = mDelivery;
    }
    
    @TargetApi(14)
    private void addTrafficStatsTag(final Request<?> request) {
        if (Build$VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
        }
    }
    
    private void parseAndDeliverNetworkError(final Request<?> request, final VolleyError volleyError) {
        this.mDelivery.postError(request, request.parseNetworkError(volleyError));
    }
    
    public void quit() {
        this.mQuit = true;
        this.interrupt();
    }
    
    @Override
    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            Request<?> request = null;
            try {
                request = this.mQueue.take();
                try {
                    request.addMarker("network-queue-take");
                    if (!request.isCanceled()) {
                        goto Label_0080;
                    }
                    request.finish("network-discard-cancelled");
                }
                catch (VolleyError volleyError) {
                    volleyError.setNetworkTimeMs(SystemClock.elapsedRealtime() - elapsedRealtime);
                    this.parseAndDeliverNetworkError(request, volleyError);
                }
                catch (Exception ex) {
                    VolleyLog.e(ex, "Unhandled exception %s", ex.toString());
                    final VolleyError volleyError2 = new VolleyError(ex);
                    volleyError2.setNetworkTimeMs(SystemClock.elapsedRealtime() - elapsedRealtime);
                    this.mDelivery.postError(request, volleyError2);
                }
                continue;
            }
            catch (InterruptedException ex2) {}
            final NetworkResponse networkResponse2;
            final Response<?> networkResponse = request.parseNetworkResponse(networkResponse2);
            request.addMarker("network-parse-complete");
            if (request.shouldCache() && networkResponse.cacheEntry != null) {
                this.mCache.put(request.getCacheKey(), networkResponse.cacheEntry);
                request.addMarker("network-cache-written");
            }
            request.markDelivered();
            this.mDelivery.postResponse(request, networkResponse);
        }
    }
}
