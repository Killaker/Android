package com.android.volley.toolbox;

import android.os.*;
import com.android.volley.*;

public class ClearCacheRequest extends Request<Object>
{
    private final Cache mCache;
    private final Runnable mCallback;
    
    public ClearCacheRequest(final Cache mCache, final Runnable mCallback) {
        super(0, null, null);
        this.mCache = mCache;
        this.mCallback = mCallback;
    }
    
    @Override
    protected void deliverResponse(final Object o) {
    }
    
    @Override
    public Priority getPriority() {
        return Priority.IMMEDIATE;
    }
    
    @Override
    public boolean isCanceled() {
        this.mCache.clear();
        if (this.mCallback != null) {
            new Handler(Looper.getMainLooper()).postAtFrontOfQueue(this.mCallback);
        }
        return true;
    }
    
    @Override
    protected Response<Object> parseNetworkResponse(final NetworkResponse networkResponse) {
        return null;
    }
}
