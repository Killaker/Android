package com.squareup.picasso;

import android.annotation.*;
import android.graphics.*;

@TargetApi(12)
private static class BitmapHoneycombMR1
{
    static int getByteCount(final Bitmap bitmap) {
        return bitmap.getByteCount();
    }
}
