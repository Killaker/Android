package com.squareup.picasso;

import android.content.res.*;
import android.content.*;
import android.net.*;
import java.io.*;

class AssetRequestHandler extends RequestHandler
{
    protected static final String ANDROID_ASSET = "android_asset";
    private static final int ASSET_PREFIX_LENGTH;
    private final AssetManager assetManager;
    
    static {
        ASSET_PREFIX_LENGTH = "file:///android_asset/".length();
    }
    
    public AssetRequestHandler(final Context context) {
        this.assetManager = context.getAssets();
    }
    
    static String getFilePath(final Request request) {
        return request.uri.toString().substring(AssetRequestHandler.ASSET_PREFIX_LENGTH);
    }
    
    @Override
    public boolean canHandleRequest(final Request request) {
        final Uri uri = request.uri;
        final boolean equals = "file".equals(uri.getScheme());
        boolean b = false;
        if (equals) {
            final boolean empty = uri.getPathSegments().isEmpty();
            b = false;
            if (!empty) {
                final boolean equals2 = "android_asset".equals(uri.getPathSegments().get(0));
                b = false;
                if (equals2) {
                    b = true;
                }
            }
        }
        return b;
    }
    
    @Override
    public Result load(final Request request, final int n) throws IOException {
        return new Result(this.assetManager.open(getFilePath(request)), Picasso.LoadedFrom.DISK);
    }
}
