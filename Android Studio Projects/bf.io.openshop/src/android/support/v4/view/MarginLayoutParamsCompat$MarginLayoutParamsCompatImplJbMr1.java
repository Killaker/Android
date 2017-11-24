package android.support.v4.view;

import android.view.*;

static class MarginLayoutParamsCompatImplJbMr1 implements MarginLayoutParamsCompatImpl
{
    @Override
    public int getLayoutDirection(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        return MarginLayoutParamsCompatJellybeanMr1.getLayoutDirection(viewGroup$MarginLayoutParams);
    }
    
    @Override
    public int getMarginEnd(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        return MarginLayoutParamsCompatJellybeanMr1.getMarginEnd(viewGroup$MarginLayoutParams);
    }
    
    @Override
    public int getMarginStart(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        return MarginLayoutParamsCompatJellybeanMr1.getMarginStart(viewGroup$MarginLayoutParams);
    }
    
    @Override
    public boolean isMarginRelative(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        return MarginLayoutParamsCompatJellybeanMr1.isMarginRelative(viewGroup$MarginLayoutParams);
    }
    
    @Override
    public void resolveLayoutDirection(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int n) {
        MarginLayoutParamsCompatJellybeanMr1.resolveLayoutDirection(viewGroup$MarginLayoutParams, n);
    }
    
    @Override
    public void setLayoutDirection(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int n) {
        MarginLayoutParamsCompatJellybeanMr1.setLayoutDirection(viewGroup$MarginLayoutParams, n);
    }
    
    @Override
    public void setMarginEnd(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int n) {
        MarginLayoutParamsCompatJellybeanMr1.setMarginEnd(viewGroup$MarginLayoutParams, n);
    }
    
    @Override
    public void setMarginStart(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int n) {
        MarginLayoutParamsCompatJellybeanMr1.setMarginStart(viewGroup$MarginLayoutParams, n);
    }
}
