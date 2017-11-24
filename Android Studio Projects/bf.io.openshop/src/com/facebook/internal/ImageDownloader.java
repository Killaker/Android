package com.facebook.internal;

import java.util.*;
import android.content.*;
import java.net.*;
import android.net.*;
import com.facebook.*;
import java.io.*;
import android.graphics.*;
import android.os.*;

public class ImageDownloader
{
    private static final int CACHE_READ_QUEUE_MAX_CONCURRENT = 2;
    private static final int DOWNLOAD_QUEUE_MAX_CONCURRENT = 8;
    private static WorkQueue cacheReadQueue;
    private static WorkQueue downloadQueue;
    private static Handler handler;
    private static final Map<RequestKey, DownloaderContext> pendingRequests;
    
    static {
        ImageDownloader.downloadQueue = new WorkQueue(8);
        ImageDownloader.cacheReadQueue = new WorkQueue(2);
        pendingRequests = new HashMap<RequestKey, DownloaderContext>();
    }
    
    public static boolean cancelRequest(final ImageRequest imageRequest) {
        final RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        synchronized (ImageDownloader.pendingRequests) {
            final DownloaderContext downloaderContext = ImageDownloader.pendingRequests.get(requestKey);
            boolean b = false;
            if (downloaderContext != null) {
                b = true;
                if (downloaderContext.workItem.cancel()) {
                    ImageDownloader.pendingRequests.remove(requestKey);
                }
                else {
                    downloaderContext.isCancelled = true;
                }
            }
            return b;
        }
    }
    
    public static void clearCache(final Context context) {
        ImageResponseCache.clearCache(context);
        UrlRedirectCache.clearCache();
    }
    
    private static void download(final RequestKey requestKey, final Context context) {
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        Bitmap decodeStream = null;
        int n = 1;
        Label_0308: {
            try {
                httpURLConnection = (HttpURLConnection)new URL(requestKey.uri.toString()).openConnection();
                httpURLConnection.setInstanceFollowRedirects(false);
                switch (httpURLConnection.getResponseCode()) {
                    default: {
                        inputStream = httpURLConnection.getErrorStream();
                        final StringBuilder sb = new StringBuilder();
                        if (inputStream == null) {
                            break Label_0308;
                        }
                        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        final char[] array = new char[128];
                        while (true) {
                            final int read = inputStreamReader.read(array, 0, array.length);
                            if (read <= 0) {
                                break Label_0308;
                            }
                            sb.append(array, 0, read);
                        }
                        break;
                    }
                    case 301:
                    case 302: {
                        break Label_0308;
                    }
                    case 200: {
                        break Label_0308;
                    }
                }
            }
            catch (IOException ex2) {
                Exception ex = ex2;
                Utility.closeQuietly(inputStream);
                Utility.disconnectQuietly(httpURLConnection);
                // iftrue(Label_0297:, isCancelled)
                // iftrue(Label_0297:, removePendingRequest == null)
                // iftrue(Label_0297:, nullOrEmpty)
                while (true) {
                    final StringBuilder sb;
                Label_0297:
                    while (true) {
                        DownloaderContext removePendingRequest;
                        boolean isCancelled;
                        String headerField;
                        Uri parse;
                        boolean nullOrEmpty;
                        Block_8_Outer:Block_7_Outer:
                        while (true) {
                            if (n != 0) {
                                issueResponse(requestKey, ex, decodeStream, false);
                            }
                            return;
                            Block_9: {
                                while (true) {
                                    while (true) {
                                        isCancelled = removePendingRequest.isCancelled;
                                        decodeStream = null;
                                        ex = null;
                                        n = 0;
                                        inputStream = null;
                                        break Block_9;
                                        parse = Uri.parse(headerField);
                                        UrlRedirectCache.cacheUriRedirect(requestKey.uri, parse);
                                        removePendingRequest = removePendingRequest(requestKey);
                                        decodeStream = null;
                                        ex = null;
                                        n = 0;
                                        inputStream = null;
                                        continue Block_7_Outer;
                                    }
                                    inputStream = ImageResponseCache.interceptAndCacheImageStream(context, httpURLConnection);
                                    decodeStream = BitmapFactory.decodeStream(inputStream);
                                    ex = null;
                                    break Label_0297;
                                    n = 0;
                                    headerField = httpURLConnection.getHeaderField("location");
                                    nullOrEmpty = Utility.isNullOrEmpty(headerField);
                                    decodeStream = null;
                                    ex = null;
                                    n = 0;
                                    inputStream = null;
                                    continue;
                                }
                            }
                            enqueueCacheRead(removePendingRequest.request, new RequestKey(parse, requestKey.tag), false);
                            Utility.closeQuietly(inputStream);
                            Utility.disconnectQuietly(httpURLConnection);
                            continue Block_8_Outer;
                        }
                        ex = new FacebookException(sb.toString());
                        decodeStream = null;
                        continue Label_0297;
                    }
                    sb.append(context.getString(R.string.com_facebook_image_download_unknown_error));
                    continue;
                    final InputStreamReader inputStreamReader;
                    Utility.closeQuietly(inputStreamReader);
                    continue;
                }
            }
            finally {
                Utility.closeQuietly(inputStream);
                Utility.disconnectQuietly(httpURLConnection);
            }
        }
    }
    
    public static void downloadAsync(final ImageRequest request) {
        if (request == null) {
            return;
        }
        while (true) {
            final RequestKey requestKey = new RequestKey(request.getImageUri(), request.getCallerTag());
            synchronized (ImageDownloader.pendingRequests) {
                final DownloaderContext downloaderContext = ImageDownloader.pendingRequests.get(requestKey);
                if (downloaderContext != null) {
                    downloaderContext.request = request;
                    downloaderContext.isCancelled = false;
                    downloaderContext.workItem.moveToFront();
                    return;
                }
            }
            enqueueCacheRead(request, requestKey, request.isCachedRedirectAllowed());
        }
    }
    
    private static void enqueueCacheRead(final ImageRequest imageRequest, final RequestKey requestKey, final boolean b) {
        enqueueRequest(imageRequest, requestKey, ImageDownloader.cacheReadQueue, new CacheReadWorkItem(imageRequest.getContext(), requestKey, b));
    }
    
    private static void enqueueDownload(final ImageRequest imageRequest, final RequestKey requestKey) {
        enqueueRequest(imageRequest, requestKey, ImageDownloader.downloadQueue, new DownloadImageWorkItem(imageRequest.getContext(), requestKey));
    }
    
    private static void enqueueRequest(final ImageRequest request, final RequestKey requestKey, final WorkQueue workQueue, final Runnable runnable) {
        synchronized (ImageDownloader.pendingRequests) {
            final DownloaderContext downloaderContext = new DownloaderContext();
            downloaderContext.request = request;
            ImageDownloader.pendingRequests.put(requestKey, downloaderContext);
            downloaderContext.workItem = workQueue.addActiveWorkItem(runnable);
        }
    }
    
    private static Handler getHandler() {
        synchronized (ImageDownloader.class) {
            if (ImageDownloader.handler == null) {
                ImageDownloader.handler = new Handler(Looper.getMainLooper());
            }
            return ImageDownloader.handler;
        }
    }
    
    private static void issueResponse(final RequestKey requestKey, final Exception ex, final Bitmap bitmap, final boolean b) {
        final DownloaderContext removePendingRequest = removePendingRequest(requestKey);
        if (removePendingRequest != null && !removePendingRequest.isCancelled) {
            final ImageRequest request = removePendingRequest.request;
            final ImageRequest.Callback callback = request.getCallback();
            if (callback != null) {
                getHandler().post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        callback.onCompleted(new ImageResponse(request, ex, b, bitmap));
                    }
                });
            }
        }
    }
    
    public static void prioritizeRequest(final ImageRequest imageRequest) {
        final RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        synchronized (ImageDownloader.pendingRequests) {
            final DownloaderContext downloaderContext = ImageDownloader.pendingRequests.get(requestKey);
            if (downloaderContext != null) {
                downloaderContext.workItem.moveToFront();
            }
        }
    }
    
    private static void readFromCache(final RequestKey requestKey, final Context context, final boolean b) {
        InputStream inputStream = null;
        boolean b2 = false;
        if (b) {
            final Uri redirectedUri = UrlRedirectCache.getRedirectedUri(requestKey.uri);
            inputStream = null;
            b2 = false;
            if (redirectedUri != null) {
                inputStream = ImageResponseCache.getCachedImageStream(redirectedUri, context);
                b2 = (inputStream != null);
            }
        }
        if (!b2) {
            inputStream = ImageResponseCache.getCachedImageStream(requestKey.uri, context);
        }
        if (inputStream != null) {
            final Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
            Utility.closeQuietly(inputStream);
            issueResponse(requestKey, null, decodeStream, b2);
        }
        else {
            final DownloaderContext removePendingRequest = removePendingRequest(requestKey);
            if (removePendingRequest != null && !removePendingRequest.isCancelled) {
                enqueueDownload(removePendingRequest.request, requestKey);
            }
        }
    }
    
    private static DownloaderContext removePendingRequest(final RequestKey requestKey) {
        synchronized (ImageDownloader.pendingRequests) {
            return ImageDownloader.pendingRequests.remove(requestKey);
        }
    }
    
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
            readFromCache(this.key, this.context, this.allowCachedRedirects);
        }
    }
    
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
            download(this.key, this.context);
        }
    }
    
    private static class DownloaderContext
    {
        boolean isCancelled;
        ImageRequest request;
        WorkQueue.WorkItem workItem;
    }
    
    private static class RequestKey
    {
        private static final int HASH_MULTIPLIER = 37;
        private static final int HASH_SEED = 29;
        Object tag;
        Uri uri;
        
        RequestKey(final Uri uri, final Object tag) {
            this.uri = uri;
            this.tag = tag;
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b = false;
            if (o != null) {
                final boolean b2 = o instanceof RequestKey;
                b = false;
                if (b2) {
                    final RequestKey requestKey = (RequestKey)o;
                    if (requestKey.uri != this.uri || requestKey.tag != this.tag) {
                        return false;
                    }
                    b = true;
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            return 37 * (1073 + this.uri.hashCode()) + this.tag.hashCode();
        }
    }
}
