package android.support.design.widget;

import android.view.*;

class Snackbar$4 implements OnDismissListener {
    @Override
    public void onDismiss(final View view) {
        view.setVisibility(8);
        Snackbar.access$000(Snackbar.this, 0);
    }
    
    @Override
    public void onDragStateChanged(final int n) {
        switch (n) {
            default: {}
            case 1:
            case 2: {
                SnackbarManager.getInstance().cancelTimeout(Snackbar.access$200(Snackbar.this));
            }
            case 0: {
                SnackbarManager.getInstance().restoreTimeout(Snackbar.access$200(Snackbar.this));
            }
        }
    }
}