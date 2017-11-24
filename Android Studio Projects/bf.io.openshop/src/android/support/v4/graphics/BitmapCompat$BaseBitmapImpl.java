package android.support.v4.graphics;

import android.graphics.*;

static class BaseBitmapImpl implements BitmapImpl
{
    @Override
    public int getAllocationByteCount(final Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
    
    @Override
    public boolean hasMipMap(final Bitmap bitmap) {
        return false;
    }
    
    @Override
    public void setHasMipMap(final Bitmap bitmap, final boolean b) {
    }
}
