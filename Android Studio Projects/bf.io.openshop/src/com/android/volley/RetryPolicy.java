package com.android.volley;

public interface RetryPolicy
{
    int getCurrentRetryCount();
    
    int getCurrentTimeout();
    
    void retry(final VolleyError p0) throws VolleyError;
}
