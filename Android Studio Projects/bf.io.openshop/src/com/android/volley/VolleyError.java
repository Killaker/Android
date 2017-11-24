package com.android.volley;

public class VolleyError extends Exception
{
    public final NetworkResponse networkResponse;
    private long networkTimeMs;
    
    public VolleyError() {
        this.networkResponse = null;
    }
    
    public VolleyError(final NetworkResponse networkResponse) {
        this.networkResponse = networkResponse;
    }
    
    public VolleyError(final String s) {
        super(s);
        this.networkResponse = null;
    }
    
    public VolleyError(final String s, final Throwable t) {
        super(s, t);
        this.networkResponse = null;
    }
    
    public VolleyError(final Throwable t) {
        super(t);
        this.networkResponse = null;
    }
    
    public long getNetworkTimeMs() {
        return this.networkTimeMs;
    }
    
    void setNetworkTimeMs(final long networkTimeMs) {
        this.networkTimeMs = networkTimeMs;
    }
}
