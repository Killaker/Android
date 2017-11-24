package android.support.v4.view;

import android.view.*;

static class LayoutInflaterCompatImplV21 extends LayoutInflaterCompatImplV11
{
    @Override
    public void setFactory(final LayoutInflater layoutInflater, final LayoutInflaterFactory layoutInflaterFactory) {
        LayoutInflaterCompatLollipop.setFactory(layoutInflater, layoutInflaterFactory);
    }
}
