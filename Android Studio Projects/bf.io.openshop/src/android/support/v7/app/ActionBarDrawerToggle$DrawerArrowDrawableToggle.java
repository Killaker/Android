package android.support.v7.app;

import android.support.v7.graphics.drawable.*;
import android.app.*;
import android.content.*;

static class DrawerArrowDrawableToggle extends DrawerArrowDrawable implements DrawerToggle
{
    private final Activity mActivity;
    
    public DrawerArrowDrawableToggle(final Activity mActivity, final Context context) {
        super(context);
        this.mActivity = mActivity;
    }
    
    @Override
    public float getPosition() {
        return this.getProgress();
    }
    
    @Override
    public void setPosition(final float progress) {
        if (progress == 1.0f) {
            this.setVerticalMirror(true);
        }
        else if (progress == 0.0f) {
            this.setVerticalMirror(false);
        }
        this.setProgress(progress);
    }
}
