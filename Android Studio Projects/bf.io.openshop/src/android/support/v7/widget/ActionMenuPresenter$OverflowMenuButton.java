package android.support.v7.widget;

import android.content.*;
import android.support.v7.appcompat.*;
import android.util.*;
import android.view.*;
import android.support.v4.graphics.drawable.*;
import android.graphics.drawable.*;

private class OverflowMenuButton extends AppCompatImageView implements ActionMenuChildView
{
    private final float[] mTempPts;
    
    public OverflowMenuButton(final Context context) {
        super(context, null, R.attr.actionOverflowButtonStyle);
        this.mTempPts = new float[2];
        this.setClickable(true);
        this.setFocusable(true);
        this.setVisibility(0);
        this.setEnabled(true);
        this.setOnTouchListener((View$OnTouchListener)new ListPopupWindow.ForwardingListener(this) {
            @Override
            public ListPopupWindow getPopup() {
                if (ActionMenuPresenter.access$200(ActionMenuPresenter.this) == null) {
                    return null;
                }
                return ActionMenuPresenter.access$200(ActionMenuPresenter.this).getPopup();
            }
            
            public boolean onForwardingStarted() {
                ActionMenuPresenter.this.showOverflowMenu();
                return true;
            }
            
            public boolean onForwardingStopped() {
                if (ActionMenuPresenter.access$300(ActionMenuPresenter.this) != null) {
                    return false;
                }
                ActionMenuPresenter.this.hideOverflowMenu();
                return true;
            }
        });
    }
    
    @Override
    public boolean needsDividerAfter() {
        return false;
    }
    
    @Override
    public boolean needsDividerBefore() {
        return false;
    }
    
    public boolean performClick() {
        if (super.performClick()) {
            return true;
        }
        this.playSoundEffect(0);
        ActionMenuPresenter.this.showOverflowMenu();
        return true;
    }
    
    protected boolean setFrame(final int n, final int n2, final int n3, final int n4) {
        final boolean setFrame = super.setFrame(n, n2, n3, n4);
        final Drawable drawable = this.getDrawable();
        final Drawable background = this.getBackground();
        if (drawable != null && background != null) {
            final int width = this.getWidth();
            final int height = this.getHeight();
            final int n5 = Math.max(width, height) / 2;
            final int n6 = this.getPaddingLeft() - this.getPaddingRight();
            final int n7 = this.getPaddingTop() - this.getPaddingBottom();
            final int n8 = (width + n6) / 2;
            final int n9 = (height + n7) / 2;
            DrawableCompat.setHotspotBounds(background, n8 - n5, n9 - n5, n8 + n5, n9 + n5);
        }
        return setFrame;
    }
}
