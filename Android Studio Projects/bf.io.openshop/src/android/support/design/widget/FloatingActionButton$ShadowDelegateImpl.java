package android.support.design.widget;

import android.graphics.drawable.*;

private class ShadowDelegateImpl implements ShadowViewDelegate
{
    @Override
    public float getRadius() {
        return FloatingActionButton.this.getSizeDimension() / 2.0f;
    }
    
    @Override
    public boolean isCompatPaddingEnabled() {
        return FloatingActionButton.access$600(FloatingActionButton.this);
    }
    
    @Override
    public void setBackgroundDrawable(final Drawable drawable) {
        FloatingActionButton.access$501(FloatingActionButton.this, drawable);
    }
    
    @Override
    public void setShadowPadding(final int n, final int n2, final int n3, final int n4) {
        FloatingActionButton.access$200(FloatingActionButton.this).set(n, n2, n3, n4);
        FloatingActionButton.this.setPadding(n + FloatingActionButton.access$400(FloatingActionButton.this), n2 + FloatingActionButton.access$400(FloatingActionButton.this), n3 + FloatingActionButton.access$400(FloatingActionButton.this), n4 + FloatingActionButton.access$400(FloatingActionButton.this));
    }
}
