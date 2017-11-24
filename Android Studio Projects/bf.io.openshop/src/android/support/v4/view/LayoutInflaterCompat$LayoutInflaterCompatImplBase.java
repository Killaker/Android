package android.support.v4.view;

import android.view.*;

static class LayoutInflaterCompatImplBase implements LayoutInflaterCompatImpl
{
    @Override
    public LayoutInflaterFactory getFactory(final LayoutInflater layoutInflater) {
        return LayoutInflaterCompatBase.getFactory(layoutInflater);
    }
    
    @Override
    public void setFactory(final LayoutInflater layoutInflater, final LayoutInflaterFactory layoutInflaterFactory) {
        LayoutInflaterCompatBase.setFactory(layoutInflater, layoutInflaterFactory);
    }
}
