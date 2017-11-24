package com.facebook.internal;

import android.content.*;

private static class DownloadImageWorkItem implements Runnable
{
    private Context context;
    private RequestKey key;
    
    DownloadImageWorkItem(final Context context, final RequestKey key) {
        this.context = context;
        this.key = key;
    }
    
    @Override
    public void run() {
        ImageDownloader.access$200(this.key, this.context);
    }
}
