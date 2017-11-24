package com.squareup.picasso;

import android.content.*;
import java.io.*;

class ContentStreamRequestHandler extends RequestHandler
{
    final Context context;
    
    ContentStreamRequestHandler(final Context context) {
        this.context = context;
    }
    
    @Override
    public boolean canHandleRequest(final Request request) {
        return "content".equals(request.uri.getScheme());
    }
    
    InputStream getInputStream(final Request request) throws FileNotFoundException {
        return this.context.getContentResolver().openInputStream(request.uri);
    }
    
    @Override
    public Result load(final Request request, final int n) throws IOException {
        return new Result(this.getInputStream(request), Picasso.LoadedFrom.DISK);
    }
}
