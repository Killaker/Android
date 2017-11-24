package bf.io.openshop.views;

import android.widget.*;
import android.content.*;
import android.util.*;
import android.graphics.*;
import android.graphics.drawable.*;

public class RoundedImageView extends ImageView
{
    public RoundedImageView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public static Bitmap getRoundedCroppedBitmap(final Bitmap bitmap, final int n) {
        Bitmap scaledBitmap;
        if (bitmap.getWidth() != n || bitmap.getHeight() != n) {
            scaledBitmap = Bitmap.createScaledBitmap(bitmap, n, n, false);
        }
        else {
            scaledBitmap = bitmap;
        }
        final Bitmap bitmap2 = Bitmap.createBitmap(scaledBitmap.getWidth(), scaledBitmap.getHeight(), Bitmap$Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap2);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight());
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(0.7f + scaledBitmap.getWidth() / 2, 0.7f + scaledBitmap.getHeight() / 2, 0.1f + scaledBitmap.getWidth() / 2, paint);
        paint.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff$Mode.SRC_IN));
        canvas.drawBitmap(scaledBitmap, rect, rect, paint);
        return bitmap2;
    }
    
    protected void onDraw(final Canvas canvas) {
        final Drawable drawable = this.getDrawable();
        if (drawable != null && this.getWidth() != 0 && this.getHeight() != 0) {
            final Bitmap copy = ((BitmapDrawable)drawable).getBitmap().copy(Bitmap$Config.ARGB_8888, true);
            final int width = this.getWidth();
            this.getHeight();
            canvas.drawBitmap(getRoundedCroppedBitmap(copy, width), 0.0f, 0.0f, (Paint)null);
        }
    }
}
