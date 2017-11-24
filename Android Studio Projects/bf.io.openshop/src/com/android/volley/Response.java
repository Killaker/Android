package com.android.volley;

public class Response<T>
{
    public final Cache.Entry cacheEntry;
    public final VolleyError error;
    public boolean intermediate;
    public final T result;
    
    private Response(final VolleyError error) {
        this.intermediate = false;
        this.result = null;
        this.cacheEntry = null;
        this.error = error;
    }
    
    private Response(final T result, final Cache.Entry cacheEntry) {
        this.intermediate = false;
        this.result = result;
        this.cacheEntry = cacheEntry;
        this.error = null;
    }
    
    public static <T> Response<T> error(final VolleyError volleyError) {
        return new Response<T>(volleyError);
    }
    
    public static <T> Response<T> success(final T t, final Cache.Entry entry) {
        return new Response<T>(t, entry);
    }
    
    public boolean isSuccess() {
        return this.error == null;
    }
    
    public interface ErrorListener
    {
        void onErrorResponse(final VolleyError p0);
    }
    
    public interface Listener<T>
    {
        void onResponse(final T p0);
    }
}
