package com.squareup.picasso;

import android.content.*;

private static class OkHttpLoaderCreator
{
    static Downloader create(final Context context) {
        return new OkHttpDownloader(context);
    }
}
