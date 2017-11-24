package com.android.volley;

import java.util.concurrent.atomic.*;
import android.os.*;
import java.util.*;
import java.util.concurrent.*;

public class RequestQueue
{
    private static final int DEFAULT_NETWORK_THREAD_POOL_SIZE = 4;
    private final Cache mCache;
    private CacheDispatcher mCacheDispatcher;
    private final PriorityBlockingQueue<Request<?>> mCacheQueue;
    private final Set<Request<?>> mCurrentRequests;
    private final ResponseDelivery mDelivery;
    private NetworkDispatcher[] mDispatchers;
    private List<RequestFinishedListener> mFinishedListeners;
    private final Network mNetwork;
    private final PriorityBlockingQueue<Request<?>> mNetworkQueue;
    private AtomicInteger mSequenceGenerator;
    private final Map<String, Queue<Request<?>>> mWaitingRequests;
    
    public RequestQueue(final Cache cache, final Network network) {
        this(cache, network, 4);
    }
    
    public RequestQueue(final Cache cache, final Network network, final int n) {
        this(cache, network, n, new ExecutorDelivery(new Handler(Looper.getMainLooper())));
    }
    
    public RequestQueue(final Cache mCache, final Network mNetwork, final int n, final ResponseDelivery mDelivery) {
        this.mSequenceGenerator = new AtomicInteger();
        this.mWaitingRequests = new HashMap<String, Queue<Request<?>>>();
        this.mCurrentRequests = new HashSet<Request<?>>();
        this.mCacheQueue = new PriorityBlockingQueue<Request<?>>();
        this.mNetworkQueue = new PriorityBlockingQueue<Request<?>>();
        this.mFinishedListeners = new ArrayList<RequestFinishedListener>();
        this.mCache = mCache;
        this.mNetwork = mNetwork;
        this.mDispatchers = new NetworkDispatcher[n];
        this.mDelivery = mDelivery;
    }
    
    public <T> Request<T> add(final Request<T> request) {
        request.setRequestQueue(this);
        synchronized (this.mCurrentRequests) {
            this.mCurrentRequests.add(request);
            // monitorexit(this.mCurrentRequests)
            request.setSequence(this.getSequenceNumber());
            request.addMarker("add-to-queue");
            if (!request.shouldCache()) {
                this.mNetworkQueue.add(request);
                return request;
            }
        }
        while (true) {
            final String cacheKey;
            synchronized (this.mWaitingRequests) {
                cacheKey = request.getCacheKey();
                if (this.mWaitingRequests.containsKey(cacheKey)) {
                    Queue<Request<?>> queue = this.mWaitingRequests.get(cacheKey);
                    if (queue == null) {
                        queue = new LinkedList<Request<?>>();
                    }
                    queue.add(request);
                    this.mWaitingRequests.put(cacheKey, queue);
                    if (VolleyLog.DEBUG) {
                        VolleyLog.v("Request for cacheKey=%s is in flight, putting on hold.", cacheKey);
                    }
                    return request;
                }
            }
            this.mWaitingRequests.put(cacheKey, null);
            this.mCacheQueue.add(request);
            return request;
        }
    }
    
    public <T> void addRequestFinishedListener(final RequestFinishedListener<T> requestFinishedListener) {
        synchronized (this.mFinishedListeners) {
            this.mFinishedListeners.add(requestFinishedListener);
        }
    }
    
    public void cancelAll(final RequestFilter requestFilter) {
        synchronized (this.mCurrentRequests) {
            for (final Request<?> request : this.mCurrentRequests) {
                if (requestFilter.apply(request)) {
                    request.cancel();
                }
            }
        }
    }
    // monitorexit(set)
    
    public void cancelAll(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Cannot cancelAll with a null tag");
        }
        this.cancelAll((RequestFilter)new RequestFilter() {
            @Override
            public boolean apply(final Request<?> request) {
                return request.getTag() == o;
            }
        });
    }
    
     <T> void finish(final Request<T> request) {
        synchronized (this.mCurrentRequests) {
            this.mCurrentRequests.remove(request);
            // monitorexit(this.mCurrentRequests)
            synchronized (this.mFinishedListeners) {
                final Iterator<RequestFinishedListener<T>> iterator = this.mFinishedListeners.iterator();
                while (iterator.hasNext()) {
                    iterator.next().onRequestFinished(request);
                }
            }
        }
        // monitorexit(list)
        if (request.shouldCache()) {
            synchronized (this.mWaitingRequests) {
                final String cacheKey = request.getCacheKey();
                final Queue<Request<?>> queue = this.mWaitingRequests.remove(cacheKey);
                if (queue != null) {
                    if (VolleyLog.DEBUG) {
                        VolleyLog.v("Releasing %d waiting requests for cacheKey=%s.", queue.size(), cacheKey);
                    }
                    this.mCacheQueue.addAll((Collection<?>)queue);
                }
            }
        }
    }
    
    public Cache getCache() {
        return this.mCache;
    }
    
    public int getSequenceNumber() {
        return this.mSequenceGenerator.incrementAndGet();
    }
    
    public <T> void removeRequestFinishedListener(final RequestFinishedListener<T> requestFinishedListener) {
        synchronized (this.mFinishedListeners) {
            this.mFinishedListeners.remove(requestFinishedListener);
        }
    }
    
    public void start() {
        this.stop();
        (this.mCacheDispatcher = new CacheDispatcher(this.mCacheQueue, this.mNetworkQueue, this.mCache, this.mDelivery)).start();
        for (int i = 0; i < this.mDispatchers.length; ++i) {
            (this.mDispatchers[i] = new NetworkDispatcher(this.mNetworkQueue, this.mNetwork, this.mCache, this.mDelivery)).start();
        }
    }
    
    public void stop() {
        if (this.mCacheDispatcher != null) {
            this.mCacheDispatcher.quit();
        }
        for (int i = 0; i < this.mDispatchers.length; ++i) {
            if (this.mDispatchers[i] != null) {
                this.mDispatchers[i].quit();
            }
        }
    }
    
    public interface RequestFilter
    {
        boolean apply(final Request<?> p0);
    }
    
    public interface RequestFinishedListener<T>
    {
        void onRequestFinished(final Request<T> p0);
    }
}
