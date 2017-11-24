package com.facebook.messenger;

import android.net.*;
import java.util.*;

public class ShareToMessengerParams
{
    public static final Set<String> VALID_EXTERNAL_URI_SCHEMES;
    public static final Set<String> VALID_MIME_TYPES;
    public static final Set<String> VALID_URI_SCHEMES;
    public final Uri externalUri;
    public final String metaData;
    public final String mimeType;
    public final Uri uri;
    
    static {
        final HashSet<String> set = new HashSet<String>();
        set.add("image/*");
        set.add("image/jpeg");
        set.add("image/png");
        set.add("image/gif");
        set.add("image/webp");
        set.add("video/*");
        set.add("video/mp4");
        set.add("audio/*");
        set.add("audio/mpeg");
        VALID_MIME_TYPES = Collections.unmodifiableSet((Set<?>)set);
        final HashSet<String> set2 = new HashSet<String>();
        set2.add("content");
        set2.add("android.resource");
        set2.add("file");
        VALID_URI_SCHEMES = Collections.unmodifiableSet((Set<?>)set2);
        final HashSet<String> set3 = new HashSet<String>();
        set3.add("http");
        set3.add("https");
        VALID_EXTERNAL_URI_SCHEMES = Collections.unmodifiableSet((Set<?>)set3);
    }
    
    ShareToMessengerParams(final ShareToMessengerParamsBuilder shareToMessengerParamsBuilder) {
        this.uri = shareToMessengerParamsBuilder.getUri();
        this.mimeType = shareToMessengerParamsBuilder.getMimeType();
        this.metaData = shareToMessengerParamsBuilder.getMetaData();
        this.externalUri = shareToMessengerParamsBuilder.getExternalUri();
        if (this.uri == null) {
            throw new NullPointerException("Must provide non-null uri");
        }
        if (this.mimeType == null) {
            throw new NullPointerException("Must provide mimeType");
        }
        if (!ShareToMessengerParams.VALID_URI_SCHEMES.contains(this.uri.getScheme())) {
            throw new IllegalArgumentException("Unsupported URI scheme: " + this.uri.getScheme());
        }
        if (!ShareToMessengerParams.VALID_MIME_TYPES.contains(this.mimeType)) {
            throw new IllegalArgumentException("Unsupported mime-type: " + this.mimeType);
        }
        if (this.externalUri != null && !ShareToMessengerParams.VALID_EXTERNAL_URI_SCHEMES.contains(this.externalUri.getScheme())) {
            throw new IllegalArgumentException("Unsupported external uri scheme: " + this.externalUri.getScheme());
        }
    }
    
    public static ShareToMessengerParamsBuilder newBuilder(final Uri uri, final String s) {
        return new ShareToMessengerParamsBuilder(uri, s);
    }
}
