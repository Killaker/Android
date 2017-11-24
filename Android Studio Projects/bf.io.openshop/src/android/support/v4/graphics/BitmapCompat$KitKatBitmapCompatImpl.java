package android.support.v4.graphics;

import android.graphics.*;

static class KitKatBitmapCompatImpl extends JbMr2BitmapCompatImpl
{
    @Override
    public int getAllocationByteCount(final Bitmap bitmap) {
        return BitmapCompatKitKat.getAllocationByteCount(bitmap);
    }
}
