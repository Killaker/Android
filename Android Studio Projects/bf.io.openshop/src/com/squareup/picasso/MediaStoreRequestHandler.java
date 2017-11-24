package com.squareup.picasso;

import android.net.*;
import android.database.*;
import android.content.*;
import android.provider.*;
import android.graphics.*;
import java.io.*;

class MediaStoreRequestHandler extends ContentStreamRequestHandler
{
    private static final String[] CONTENT_ORIENTATION;
    
    static {
        CONTENT_ORIENTATION = new String[] { "orientation" };
    }
    
    MediaStoreRequestHandler(final Context context) {
        super(context);
    }
    
    static int getExifOrientation(final ContentResolver contentResolver, final Uri uri) {
        Cursor query = null;
        try {
            query = contentResolver.query(uri, MediaStoreRequestHandler.CONTENT_ORIENTATION, (String)null, (String[])null, (String)null);
            if (query == null || !query.moveToFirst()) {
                if (query != null) {
                    query.close();
                }
                return 0;
            }
            return query.getInt(0);
        }
        catch (RuntimeException ex) {
            return 0;
        }
        finally {
            if (query != null) {
                query.close();
            }
        }
    }
    
    static PicassoKind getPicassoKind(final int n, final int n2) {
        if (n <= PicassoKind.MICRO.width && n2 <= PicassoKind.MICRO.height) {
            return PicassoKind.MICRO;
        }
        if (n <= PicassoKind.MINI.width && n2 <= PicassoKind.MINI.height) {
            return PicassoKind.MINI;
        }
        return PicassoKind.FULL;
    }
    
    @Override
    public boolean canHandleRequest(final Request request) {
        final Uri uri = request.uri;
        return "content".equals(uri.getScheme()) && "media".equals(uri.getAuthority());
    }
    
    @Override
    public Result load(final Request request, final int n) throws IOException {
        final ContentResolver contentResolver = this.context.getContentResolver();
        final int exifOrientation = getExifOrientation(contentResolver, request.uri);
        final String type = contentResolver.getType(request.uri);
        boolean b;
        if (type != null && type.startsWith("video/")) {
            b = true;
        }
        else {
            b = false;
        }
        if (request.hasSize()) {
            final PicassoKind picassoKind = getPicassoKind(request.targetWidth, request.targetHeight);
            if (!b && picassoKind == PicassoKind.FULL) {
                return new Result(null, this.getInputStream(request), Picasso.LoadedFrom.DISK, exifOrientation);
            }
            final long id = ContentUris.parseId(request.uri);
            final BitmapFactory$Options bitmapOptions = RequestHandler.createBitmapOptions(request);
            bitmapOptions.inJustDecodeBounds = true;
            RequestHandler.calculateInSampleSize(request.targetWidth, request.targetHeight, picassoKind.width, picassoKind.height, bitmapOptions, request);
            Bitmap bitmap;
            if (b) {
                int androidKind;
                if (picassoKind == PicassoKind.FULL) {
                    androidKind = 1;
                }
                else {
                    androidKind = picassoKind.androidKind;
                }
                bitmap = MediaStore$Video$Thumbnails.getThumbnail(contentResolver, id, androidKind, bitmapOptions);
            }
            else {
                bitmap = MediaStore$Images$Thumbnails.getThumbnail(contentResolver, id, picassoKind.androidKind, bitmapOptions);
            }
            if (bitmap != null) {
                return new Result(bitmap, null, Picasso.LoadedFrom.DISK, exifOrientation);
            }
        }
        return new Result(null, this.getInputStream(request), Picasso.LoadedFrom.DISK, exifOrientation);
    }
    
    enum PicassoKind
    {
        FULL(2, -1, -1), 
        MICRO(3, 96, 96), 
        MINI(1, 512, 384);
        
        final int androidKind;
        final int height;
        final int width;
        
        private PicassoKind(final int androidKind, final int width, final int height) {
            this.androidKind = androidKind;
            this.width = width;
            this.height = height;
        }
    }
}
