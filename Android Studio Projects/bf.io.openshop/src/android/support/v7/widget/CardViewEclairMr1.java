package android.support.v7.widget;

import android.content.*;
import android.graphics.drawable.*;
import android.graphics.*;

class CardViewEclairMr1 implements CardViewImpl
{
    final RectF sCornerRect;
    
    CardViewEclairMr1() {
        this.sCornerRect = new RectF();
    }
    
    private RoundRectDrawableWithShadow getShadowBackground(final CardViewDelegate cardViewDelegate) {
        return (RoundRectDrawableWithShadow)cardViewDelegate.getBackground();
    }
    
    RoundRectDrawableWithShadow createBackground(final Context context, final int n, final float n2, final float n3, final float n4) {
        return new RoundRectDrawableWithShadow(context.getResources(), n, n2, n3, n4);
    }
    
    @Override
    public float getElevation(final CardViewDelegate cardViewDelegate) {
        return this.getShadowBackground(cardViewDelegate).getShadowSize();
    }
    
    @Override
    public float getMaxElevation(final CardViewDelegate cardViewDelegate) {
        return this.getShadowBackground(cardViewDelegate).getMaxShadowSize();
    }
    
    @Override
    public float getMinHeight(final CardViewDelegate cardViewDelegate) {
        return this.getShadowBackground(cardViewDelegate).getMinHeight();
    }
    
    @Override
    public float getMinWidth(final CardViewDelegate cardViewDelegate) {
        return this.getShadowBackground(cardViewDelegate).getMinWidth();
    }
    
    @Override
    public float getRadius(final CardViewDelegate cardViewDelegate) {
        return this.getShadowBackground(cardViewDelegate).getCornerRadius();
    }
    
    @Override
    public void initStatic() {
        RoundRectDrawableWithShadow.sRoundRectHelper = (RoundRectDrawableWithShadow.RoundRectHelper)new RoundRectDrawableWithShadow.RoundRectHelper() {
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
        };
    }
    
    @Override
    public void initialize(final CardViewDelegate cardViewDelegate, final Context context, final int n, final float n2, final float n3, final float n4) {
        final RoundRectDrawableWithShadow background = this.createBackground(context, n, n2, n3, n4);
        background.setAddPaddingForCorners(cardViewDelegate.getPreventCornerOverlap());
        cardViewDelegate.setBackgroundDrawable(background);
        this.updatePadding(cardViewDelegate);
    }
    
    @Override
    public void onCompatPaddingChanged(final CardViewDelegate cardViewDelegate) {
    }
    
    @Override
    public void onPreventCornerOverlapChanged(final CardViewDelegate cardViewDelegate) {
        this.getShadowBackground(cardViewDelegate).setAddPaddingForCorners(cardViewDelegate.getPreventCornerOverlap());
        this.updatePadding(cardViewDelegate);
    }
    
    @Override
    public void setBackgroundColor(final CardViewDelegate cardViewDelegate, final int color) {
        this.getShadowBackground(cardViewDelegate).setColor(color);
    }
    
    @Override
    public void setElevation(final CardViewDelegate cardViewDelegate, final float shadowSize) {
        this.getShadowBackground(cardViewDelegate).setShadowSize(shadowSize);
    }
    
    @Override
    public void setMaxElevation(final CardViewDelegate cardViewDelegate, final float maxShadowSize) {
        this.getShadowBackground(cardViewDelegate).setMaxShadowSize(maxShadowSize);
        this.updatePadding(cardViewDelegate);
    }
    
    @Override
    public void setRadius(final CardViewDelegate cardViewDelegate, final float cornerRadius) {
        this.getShadowBackground(cardViewDelegate).setCornerRadius(cornerRadius);
        this.updatePadding(cardViewDelegate);
    }
    
    @Override
    public void updatePadding(final CardViewDelegate cardViewDelegate) {
        final Rect rect = new Rect();
        this.getShadowBackground(cardViewDelegate).getMaxShadowAndCornerPadding(rect);
        cardViewDelegate.setMinWidthHeightInternal((int)Math.ceil(this.getMinWidth(cardViewDelegate)), (int)Math.ceil(this.getMinHeight(cardViewDelegate)));
        cardViewDelegate.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }
}
