package com.google.android.gms.internal;

import android.graphics.drawable.*;
import android.content.res.*;
import android.graphics.*;

public final class zzmb
{
    private static Bitmap zza(final Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }
        final Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap$Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
    
    public static Drawable zza(final Resources resources, final Drawable drawable) {
        return (Drawable)new BitmapDrawable(resources, zzb(zza(drawable)));
    }
    
    public static Bitmap zzb(final Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        final int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int n;
        int n2;
        if (width >= height) {
            n = (height - width) / 2;
            n2 = 0;
        }
        else {
            n2 = (width - height) / 2;
            height = width;
            n = 0;
        }
        final Bitmap bitmap2 = Bitmap.createBitmap(height, height, Bitmap$Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap2);
        final Paint paint = new Paint(1);
        paint.setColor(-16777216);
        canvas.drawCircle((float)(height / 2), (float)(height / 2), (float)(height / 2), paint);
        paint.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff$Mode.SRC_IN));
        canvas.drawBitmap(bitmap, (float)n, (float)n2, paint);
        return bitmap2;
    }
}
