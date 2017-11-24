package android.support.v4.graphics;

import android.graphics.*;

static class HcMr1BitmapCompatImpl extends BaseBitmapImpl
{
    @Override
    public int getAllocationByteCount(final Bitmap bitmap) {
        return BitmapCompatHoneycombMr1.getAllocationByteCount(bitmap);
    }
}
