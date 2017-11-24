package com.squareup.picasso;

import java.io.*;

static final class BitmapHunter$2 extends RequestHandler {
    @Override
    public boolean canHandleRequest(final Request request) {
        return true;
    }
    
    @Override
    public Result load(final Request request, final int n) throws IOException {
        throw new IllegalStateException("Unrecognized type of request: " + request);
    }
}