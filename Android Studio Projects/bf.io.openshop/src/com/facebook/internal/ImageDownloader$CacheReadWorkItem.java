package com.facebook.internal;

import android.content.*;

private static class CacheReadWorkItem implements Runnable
{
    private boolean allowCachedRedirects;
    private Context context;
    private RequestKey key;
    
    CacheReadWorkItem(final Context context, final RequestKey key, final boolean allowCachedRedirects) {
        this.context = context;
        this.key = key;
        this.allowCachedRedirects = allowCachedRedirects;
    }
    
    @Override
    public void run() {
        ImageDownloader.access$100(this.key, this.context, this.allowCachedRedirects);
    }
}
