package android.support.v4.view;

import android.view.*;
import android.graphics.*;

static class JbMr2ViewCompatImpl extends JbMr1ViewCompatImpl
{
    @Override
    public Rect getClipBounds(final View view) {
        return ViewCompatJellybeanMr2.getClipBounds(view);
    }
    
    @Override
    public void setClipBounds(final View view, final Rect rect) {
        ViewCompatJellybeanMr2.setClipBounds(view, rect);
    }
}
