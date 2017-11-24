package com.squareup.picasso;

import android.graphics.drawable.*;
import android.graphics.*;

public interface Target
{
    void onBitmapFailed(final Drawable p0);
    
    void onBitmapLoaded(final Bitmap p0, final Picasso.LoadedFrom p1);
    
    void onPrepareLoad(final Drawable p0);
}
