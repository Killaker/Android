package android.support.v4.widget;

import android.graphics.drawable.shapes.*;
import android.graphics.*;

private class OvalShadow extends OvalShape
{
    private int mCircleDiameter;
    private RadialGradient mRadialGradient;
    private Paint mShadowPaint;
    
    public OvalShadow(final int n, final int mCircleDiameter) {
        this.mShadowPaint = new Paint();
        CircleImageView.access$002(CircleImageView.this, n);
        this.mCircleDiameter = mCircleDiameter;
        this.mRadialGradient = new RadialGradient((float)(this.mCircleDiameter / 2), (float)(this.mCircleDiameter / 2), (float)CircleImageView.access$000(CircleImageView.this), new int[] { 1023410176, 0 }, (float[])null, Shader$TileMode.CLAMP);
        this.mShadowPaint.setShader((Shader)this.mRadialGradient);
    }
    
    public void draw(final Canvas canvas, final Paint paint) {
        final int width = CircleImageView.this.getWidth();
        final int height = CircleImageView.this.getHeight();
        canvas.drawCircle((float)(width / 2), (float)(height / 2), (float)(this.mCircleDiameter / 2 + CircleImageView.access$000(CircleImageView.this)), this.mShadowPaint);
        canvas.drawCircle((float)(width / 2), (float)(height / 2), (float)(this.mCircleDiameter / 2), paint);
    }
}
