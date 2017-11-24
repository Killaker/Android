package com.squareup.picasso;

import android.content.*;
import android.graphics.*;
import java.util.concurrent.*;
import java.util.*;

public static class Builder
{
    private Cache cache;
    private final Context context;
    private Bitmap$Config defaultBitmapConfig;
    private Downloader downloader;
    private boolean indicatorsEnabled;
    private Listener listener;
    private boolean loggingEnabled;
    private List<RequestHandler> requestHandlers;
    private ExecutorService service;
    private RequestTransformer transformer;
    
    public Builder(final Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        this.context = context.getApplicationContext();
    }
    
    public Builder addRequestHandler(final RequestHandler requestHandler) {
        if (requestHandler == null) {
            throw new IllegalArgumentException("RequestHandler must not be null.");
        }
        if (this.requestHandlers == null) {
            this.requestHandlers = new ArrayList<RequestHandler>();
        }
        if (this.requestHandlers.contains(requestHandler)) {
            throw new IllegalStateException("RequestHandler already registered.");
        }
        this.requestHandlers.add(requestHandler);
        return this;
    }
    
    public Picasso build() {
        final Context context = this.context;
        if (this.downloader == null) {
            this.downloader = Utils.createDefaultDownloader(context);
        }
        if (this.cache == null) {
            this.cache = new LruCache(context);
        }
        if (this.service == null) {
            this.service = new PicassoExecutorService();
        }
        if (this.transformer == null) {
            this.transformer = RequestTransformer.IDENTITY;
        }
        final Stats stats = new Stats(this.cache);
        return new Picasso(context, new Dispatcher(context, this.service, Picasso.HANDLER, this.downloader, this.cache, stats), this.cache, this.listener, this.transformer, this.requestHandlers, stats, this.defaultBitmapConfig, this.indicatorsEnabled, this.loggingEnabled);
    }
    
    @Deprecated
    public Builder debugging(final boolean b) {
        return this.indicatorsEnabled(b);
    }
    
    public Builder defaultBitmapConfig(final Bitmap$Config defaultBitmapConfig) {
        if (defaultBitmapConfig == null) {
            throw new IllegalArgumentException("Bitmap config must not be null.");
        }
        this.defaultBitmapConfig = defaultBitmapConfig;
        return this;
    }
    
    public Builder downloader(final Downloader downloader) {
        if (downloader == null) {
            throw new IllegalArgumentException("Downloader must not be null.");
        }
        if (this.downloader != null) {
            throw new IllegalStateException("Downloader already set.");
        }
        this.downloader = downloader;
        return this;
    }
    
    public Builder executor(final ExecutorService service) {
        if (service == null) {
            throw new IllegalArgumentException("Executor service must not be null.");
        }
        if (this.service != null) {
            throw new IllegalStateException("Executor service already set.");
        }
        this.service = service;
        return this;
    }
    
    public Builder indicatorsEnabled(final boolean indicatorsEnabled) {
        this.indicatorsEnabled = indicatorsEnabled;
        return this;
    }
    
    public Builder listener(final Listener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener must not be null.");
        }
        if (this.listener != null) {
            throw new IllegalStateException("Listener already set.");
        }
        this.listener = listener;
        return this;
    }
    
    public Builder loggingEnabled(final boolean loggingEnabled) {
        this.loggingEnabled = loggingEnabled;
        return this;
    }
    
    public Builder memoryCache(final Cache cache) {
        if (cache == null) {
            throw new IllegalArgumentException("Memory cache must not be null.");
        }
        if (this.cache != null) {
            throw new IllegalStateException("Memory cache already set.");
        }
        this.cache = cache;
        return this;
    }
    
    public Builder requestTransformer(final RequestTransformer transformer) {
        if (transformer == null) {
            throw new IllegalArgumentException("Transformer must not be null.");
        }
        if (this.transformer != null) {
            throw new IllegalStateException("Transformer already set.");
        }
        this.transformer = transformer;
        return this;
    }
}
