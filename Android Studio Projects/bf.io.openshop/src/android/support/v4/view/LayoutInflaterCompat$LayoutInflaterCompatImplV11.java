package android.support.v4.view;

import android.view.*;

static class LayoutInflaterCompatImplV11 extends LayoutInflaterCompatImplBase
{
    @Override
    public void setFactory(final LayoutInflater layoutInflater, final LayoutInflaterFactory layoutInflaterFactory) {
        LayoutInflaterCompatHC.setFactory(layoutInflater, layoutInflaterFactory);
    }
}
