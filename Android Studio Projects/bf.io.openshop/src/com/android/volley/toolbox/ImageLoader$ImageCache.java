package com.android.volley.toolbox;

import android.graphics.*;

public interface ImageCache
{
    Bitmap getBitmap(final String p0);
    
    void putBitmap(final String p0, final Bitmap p1);
}
