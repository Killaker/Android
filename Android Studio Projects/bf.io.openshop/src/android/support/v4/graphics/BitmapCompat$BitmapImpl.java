package android.support.v4.graphics;

import android.graphics.*;

interface BitmapImpl
{
    int getAllocationByteCount(final Bitmap p0);
    
    boolean hasMipMap(final Bitmap p0);
    
    void setHasMipMap(final Bitmap p0, final boolean p1);
}
