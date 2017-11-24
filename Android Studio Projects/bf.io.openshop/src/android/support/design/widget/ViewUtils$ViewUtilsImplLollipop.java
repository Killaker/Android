package android.support.design.widget;

import android.view.*;

private static class ViewUtilsImplLollipop implements ViewUtilsImpl
{
    @Override
    public void setBoundsViewOutlineProvider(final View boundsViewOutlineProvider) {
        ViewUtilsLollipop.setBoundsViewOutlineProvider(boundsViewOutlineProvider);
    }
}
