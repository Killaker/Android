package android.support.v4.view;

import android.view.*;

static class EclairMr1ViewCompatImpl extends BaseViewCompatImpl
{
    @Override
    public boolean isOpaque(final View view) {
        return ViewCompatEclairMr1.isOpaque(view);
    }
    
    @Override
    public void setChildrenDrawingOrderEnabled(final ViewGroup viewGroup, final boolean b) {
        ViewCompatEclairMr1.setChildrenDrawingOrderEnabled(viewGroup, b);
    }
}
