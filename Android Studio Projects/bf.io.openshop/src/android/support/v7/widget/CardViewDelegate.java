package android.support.v7.widget;

import android.graphics.drawable.*;

interface CardViewDelegate
{
    Drawable getBackground();
    
    boolean getPreventCornerOverlap();
    
    float getRadius();
    
    boolean getUseCompatPadding();
    
    void setBackgroundDrawable(final Drawable p0);
    
    void setMinWidthHeightInternal(final int p0, final int p1);
    
    void setShadowPadding(final int p0, final int p1, final int p2, final int p3);
}
