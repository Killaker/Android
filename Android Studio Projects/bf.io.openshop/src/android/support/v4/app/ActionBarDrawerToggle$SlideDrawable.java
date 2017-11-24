package android.support.v4.app;

import android.graphics.drawable.*;
import android.os.*;
import android.graphics.*;
import android.support.v4.view.*;

private class SlideDrawable extends InsetDrawable implements Drawable$Callback
{
    private final boolean mHasMirroring;
    private float mOffset;
    private float mPosition;
    private final Rect mTmpRect;
    
    private SlideDrawable(final Drawable drawable) {
        super(drawable, 0);
        final int sdk_INT = Build$VERSION.SDK_INT;
        boolean mHasMirroring = false;
        if (sdk_INT > 18) {
            mHasMirroring = true;
        }
        this.mHasMirroring = mHasMirroring;
        this.mTmpRect = new Rect();
    }
    
    public void draw(final Canvas canvas) {
        int n = 1;
        this.copyBounds(this.mTmpRect);
        canvas.save();
        int n2;
        if (ViewCompat.getLayoutDirection(ActionBarDrawerToggle.access$400(ActionBarDrawerToggle.this).getWindow().getDecorView()) == n) {
            n2 = n;
        }
        else {
            n2 = 0;
        }
        if (n2 != 0) {
            n = -1;
        }
        final int width = this.mTmpRect.width();
        canvas.translate(-this.mOffset * width * this.mPosition * n, 0.0f);
        if (n2 != 0 && !this.mHasMirroring) {
            canvas.translate((float)width, 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        super.draw(canvas);
        canvas.restore();
    }
    
    public float getPosition() {
        return this.mPosition;
    }
    
    public void setOffset(final float mOffset) {
        this.mOffset = mOffset;
        this.invalidateSelf();
    }
    
    public void setPosition(final float mPosition) {
        this.mPosition = mPosition;
        this.invalidateSelf();
    }
}
