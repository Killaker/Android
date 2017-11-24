package okhttp3;

import okhttp3.internal.*;
import java.util.concurrent.*;
import java.util.*;

public final class Dispatcher
{
    private ExecutorService executorService;
    private int maxRequests;
    private int maxRequestsPerHost;
    private final Deque<RealCall.AsyncCall> readyAsyncCalls;
    private final Deque<RealCall.AsyncCall> runningAsyncCalls;
    private final Deque<RealCall> runningSyncCalls;
    
    public Dispatcher() {
        this.maxRequests = 64;
        this.maxRequestsPerHost = 5;
        this.readyAsyncCalls = new ArrayDeque<RealCall.AsyncCall>();
        this.runningAsyncCalls = new ArrayDeque<RealCall.AsyncCall>();
        this.runningSyncCalls = new ArrayDeque<RealCall>();
    }
    
    public Dispatcher(final ExecutorService executorService) {
        this.maxRequests = 64;
        this.maxRequestsPerHost = 5;
        this.readyAsyncCalls = new ArrayDeque<RealCall.AsyncCall>();
        this.runningAsyncCalls = new ArrayDeque<RealCall.AsyncCall>();
        this.runningSyncCalls = new ArrayDeque<RealCall>();
        this.executorService = executorService;
    }
    
    private void promoteCalls() {
        if (this.runningAsyncCalls.size() < this.maxRequests && !this.readyAsyncCalls.isEmpty()) {
            final Iterator<RealCall.AsyncCall> iterator = this.readyAsyncCalls.iterator();
            while (iterator.hasNext()) {
                final RealCall.AsyncCall asyncCall = iterator.next();
                if (this.runningCallsForHost(asyncCall) < this.maxRequestsPerHost) {
                    iterator.remove();
                    this.runningAsyncCalls.add(asyncCall);
                    this.executorService().execute(asyncCall);
                }
                if (this.runningAsyncCalls.size() >= this.maxRequests) {
                    return;
                }
            }
        }
    }
    
    private int runningCallsForHost(final RealCall.AsyncCall asyncCall) {
        int n = 0;
        final Iterator<RealCall.AsyncCall> iterator = this.runningAsyncCalls.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().host().equals(asyncCall.host())) {
                ++n;
            }
        }
        return n;
    }
    
    public void cancelAll() {
        synchronized (this) {
            final Iterator<RealCall.AsyncCall> iterator = this.readyAsyncCalls.iterator();
            while (iterator.hasNext()) {
                iterator.next().cancel();
            }
        }
        final Iterator<RealCall.AsyncCall> iterator2 = this.runningAsyncCalls.iterator();
        while (iterator2.hasNext()) {
            iterator2.next().cancel();
        }
        final Iterator<RealCall> iterator3 = this.runningSyncCalls.iterator();
        while (iterator3.hasNext()) {
            iterator3.next().cancel();
        }
    }
    // monitorexit(this)
    
    void enqueue(final RealCall.AsyncCall asyncCall) {
        synchronized (this) {
            if (this.runningAsyncCalls.size() < this.maxRequests && this.runningCallsForHost(asyncCall) < this.maxRequestsPerHost) {
                this.runningAsyncCalls.add(asyncCall);
                this.executorService().execute(asyncCall);
            }
            else {
                this.readyAsyncCalls.add(asyncCall);
            }
        }
    }
    
    void executed(final RealCall realCall) {
        synchronized (this) {
            this.runningSyncCalls.add(realCall);
        }
    }
    
    public ExecutorService executorService() {
        synchronized (this) {
            if (this.executorService == null) {
                this.executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), Util.threadFactory("OkHttp Dispatcher", false));
            }
            return this.executorService;
        }
    }
    
    void finished(final Call call) {
        synchronized (this) {
            if (!this.runningSyncCalls.remove(call)) {
                throw new AssertionError((Object)"Call wasn't in-flight!");
            }
        }
    }
    // monitorexit(this)
    
    void finished(final RealCall.AsyncCall asyncCall) {
        synchronized (this) {
            if (!this.runningAsyncCalls.remove(asyncCall)) {
                throw new AssertionError((Object)"AsyncCall wasn't running!");
            }
        }
        this.promoteCalls();
    }
    // monitorexit(this)
    
    public int getMaxRequests() {
        synchronized (this) {
            return this.maxRequests;
        }
    }
    
    public int getMaxRequestsPerHost() {
        synchronized (this) {
            return this.maxRequestsPerHost;
        }
    }
    
    public List<Call> queuedCalls() {
        final ArrayList<RealCall> list;
        synchronized (this) {
            list = new ArrayList<RealCall>();
            final Iterator<RealCall.AsyncCall> iterator = this.readyAsyncCalls.iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next().get());
            }
        }
        final List<Object> unmodifiableList = Collections.unmodifiableList((List<?>)list);
        // monitorexit(this)
        return (List<Call>)unmodifiableList;
    }
    
    public int queuedCallsCount() {
        synchronized (this) {
            return this.readyAsyncCalls.size();
        }
    }
    
    public List<Call> runningCalls() {
        final ArrayList<Call> list;
        synchronized (this) {
            list = new ArrayList<Call>();
            list.addAll((Collection<?>)this.runningSyncCalls);
            final Iterator<RealCall.AsyncCall> iterator = this.runningAsyncCalls.iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next().get());
            }
        }
        final List<Object> unmodifiableList = Collections.unmodifiableList((List<?>)list);
        // monitorexit(this)
        return (List<Call>)unmodifiableList;
    }
    
    public int runningCallsCount() {
        synchronized (this) {
            return this.runningAsyncCalls.size() + this.runningSyncCalls.size();
        }
    }
    
    public void setMaxRequests(final int maxRequests) {
        // monitorenter(this)
        if (maxRequests < 1) {
            try {
                throw new IllegalArgumentException("max < 1: " + maxRequests);
            }
            finally {
            }
            // monitorexit(this)
        }
        this.maxRequests = maxRequests;
        this.promoteCalls();
    }
    // monitorexit(this)
    
    public void setMaxRequestsPerHost(final int maxRequestsPerHost) {
        // monitorenter(this)
        if (maxRequestsPerHost < 1) {
            try {
                throw new IllegalArgumentException("max < 1: " + maxRequestsPerHost);
            }
            finally {
            }
            // monitorexit(this)
        }
        this.maxRequestsPerHost = maxRequestsPerHost;
        this.promoteCalls();
    }
    // monitorexit(this)
}
