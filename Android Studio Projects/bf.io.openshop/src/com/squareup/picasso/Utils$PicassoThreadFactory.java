package com.squareup.picasso;

import java.util.concurrent.*;

static class PicassoThreadFactory implements ThreadFactory
{
    @Override
    public Thread newThread(final Runnable runnable) {
        return new PicassoThread(runnable);
    }
}
