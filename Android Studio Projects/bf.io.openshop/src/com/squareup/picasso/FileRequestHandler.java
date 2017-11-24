package com.squareup.picasso;

import android.content.*;
import android.net.*;
import android.media.*;
import java.io.*;
import android.graphics.*;

class FileRequestHandler extends ContentStreamRequestHandler
{
    FileRequestHandler(final Context context) {
        super(context);
    }
    
    static int getFileExifRotation(final Uri uri) throws IOException {
        switch (new ExifInterface(uri.getPath()).getAttributeInt("Orientation", 1)) {
            default: {
                return 0;
            }
            case 6: {
                return 90;
            }
            case 3: {
                return 180;
            }
            case 8: {
                return 270;
            }
        }
    }
    
    @Override
    public boolean canHandleRequest(final Request request) {
        return "file".equals(request.uri.getScheme());
    }
    
    @Override
    public Result load(final Request request, final int n) throws IOException {
        return new Result(null, this.getInputStream(request), Picasso.LoadedFrom.DISK, getFileExifRotation(request.uri));
    }
}
