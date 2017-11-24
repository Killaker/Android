package android.support.v4.graphics;

import android.graphics.*;

static class JbMr2BitmapCompatImpl extends HcMr1BitmapCompatImpl
{
    @Override
    public boolean hasMipMap(final Bitmap bitmap) {
        return BitmapCompatJellybeanMR2.hasMipMap(bitmap);
    }
    
    @Override
    public void setHasMipMap(final Bitmap bitmap, final boolean b) {
        BitmapCompatJellybeanMR2.setHasMipMap(bitmap, b);
    }
}
