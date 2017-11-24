package com.facebook.login.widget;

import android.view.*;

class ToolTipPopup$1 implements ViewTreeObserver$OnScrollChangedListener {
    public void onScrollChanged() {
        if (ToolTipPopup.access$000(ToolTipPopup.this).get() != null && ToolTipPopup.access$100(ToolTipPopup.this) != null && ToolTipPopup.access$100(ToolTipPopup.this).isShowing()) {
            if (!ToolTipPopup.access$100(ToolTipPopup.this).isAboveAnchor()) {
                ToolTipPopup.access$200(ToolTipPopup.this).showTopArrow();
                return;
            }
            ToolTipPopup.access$200(ToolTipPopup.this).showBottomArrow();
        }
    }
}