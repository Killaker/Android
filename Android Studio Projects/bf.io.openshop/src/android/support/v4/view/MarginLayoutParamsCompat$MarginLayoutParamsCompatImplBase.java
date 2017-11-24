package android.support.v4.view;

import android.view.*;

static class MarginLayoutParamsCompatImplBase implements MarginLayoutParamsCompatImpl
{
    @Override
    public int getLayoutDirection(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        return 0;
    }
    
    @Override
    public int getMarginEnd(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        return viewGroup$MarginLayoutParams.rightMargin;
    }
    
    @Override
    public int getMarginStart(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        return viewGroup$MarginLayoutParams.leftMargin;
    }
    
    @Override
    public boolean isMarginRelative(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        return false;
    }
    
    @Override
    public void resolveLayoutDirection(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int n) {
    }
    
    @Override
    public void setLayoutDirection(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int n) {
    }
    
    @Override
    public void setMarginEnd(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int rightMargin) {
        viewGroup$MarginLayoutParams.rightMargin = rightMargin;
    }
    
    @Override
    public void setMarginStart(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int leftMargin) {
        viewGroup$MarginLayoutParams.leftMargin = leftMargin;
    }
}
