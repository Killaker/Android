package android.support.design.widget;

import android.graphics.*;

class CircularBorderDrawableLollipop extends CircularBorderDrawable
{
    public void getOutline(final Outline outline) {
        this.copyBounds(this.mRect);
        outline.setOval(this.mRect);
    }
}
