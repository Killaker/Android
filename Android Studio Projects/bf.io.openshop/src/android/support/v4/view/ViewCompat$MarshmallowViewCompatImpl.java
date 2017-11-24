package android.support.v4.view;

import android.view.*;

static class MarshmallowViewCompatImpl extends LollipopViewCompatImpl
{
    @Override
    public int getScrollIndicators(final View view) {
        return ViewCompatMarshmallow.getScrollIndicators(view);
    }
    
    @Override
    public void offsetLeftAndRight(final View view, final int n) {
        ViewCompatMarshmallow.offsetLeftAndRight(view, n);
    }
    
    @Override
    public void offsetTopAndBottom(final View view, final int n) {
        ViewCompatMarshmallow.offsetTopAndBottom(view, n);
    }
    
    @Override
    public void setScrollIndicators(final View view, final int n) {
        ViewCompatMarshmallow.setScrollIndicators(view, n);
    }
    
    @Override
    public void setScrollIndicators(final View view, final int n, final int n2) {
        ViewCompatMarshmallow.setScrollIndicators(view, n, n2);
    }
}
