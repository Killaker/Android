package com.squareup.picasso;

import android.graphics.*;

public interface Transformation
{
    String key();
    
    Bitmap transform(final Bitmap p0);
}
