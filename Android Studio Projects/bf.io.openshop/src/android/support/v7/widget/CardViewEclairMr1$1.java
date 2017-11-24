package android.support.v7.widget;

import android.graphics.*;

class CardViewEclairMr1$1 implements RoundRectHelper {
    @Override
    public void drawRoundRect(final Canvas canvas, final RectF rectF, final float n, final Paint paint) {
        final float n2 = n * 2.0f;
        final float n3 = rectF.width() - n2 - 1.0f;
        final float n4 = rectF.height() - n2 - 1.0f;
        if (n >= 1.0f) {
            final float n5 = n + 0.5f;
            CardViewEclairMr1.this.sCornerRect.set(-n5, -n5, n5, n5);
            final int save = canvas.save();
            canvas.translate(n5 + rectF.left, n5 + rectF.top);
            canvas.drawArc(CardViewEclairMr1.this.sCornerRect, 180.0f, 90.0f, true, paint);
            canvas.translate(n3, 0.0f);
            canvas.rotate(90.0f);
            canvas.drawArc(CardViewEclairMr1.this.sCornerRect, 180.0f, 90.0f, true, paint);
            canvas.translate(n4, 0.0f);
            canvas.rotate(90.0f);
            canvas.drawArc(CardViewEclairMr1.this.sCornerRect, 180.0f, 90.0f, true, paint);
            canvas.translate(n3, 0.0f);
            canvas.rotate(90.0f);
            canvas.drawArc(CardViewEclairMr1.this.sCornerRect, 180.0f, 90.0f, true, paint);
            canvas.restoreToCount(save);
            canvas.drawRect(n5 + rectF.left - 1.0f, rectF.top, 1.0f + (rectF.right - n5), n5 + rectF.top, paint);
            canvas.drawRect(n5 + rectF.left - 1.0f, 1.0f + (rectF.bottom - n5), 1.0f + (rectF.right - n5), rectF.bottom, paint);
        }
        canvas.drawRect(rectF.left, rectF.top + Math.max(0.0f, n - 1.0f), rectF.right, 1.0f + (rectF.bottom - n), paint);
    }
}