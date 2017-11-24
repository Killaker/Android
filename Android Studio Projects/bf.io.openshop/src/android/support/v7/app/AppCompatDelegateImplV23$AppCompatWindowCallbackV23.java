package android.support.v7.app;

import android.view.*;

class AppCompatWindowCallbackV23 extends AppCompatWindowCallbackV14
{
    AppCompatWindowCallbackV23(final Window$Callback window$Callback) {
        super(window$Callback);
    }
    
    @Override
    public ActionMode onWindowStartingActionMode(final ActionMode$Callback actionMode$Callback) {
        return null;
    }
    
    @Override
    public ActionMode onWindowStartingActionMode(final ActionMode$Callback actionMode$Callback, final int n) {
        if (AppCompatDelegateImplV23.this.isHandleNativeActionModesEnabled()) {
            switch (n) {
                case 0: {
                    return ((AppCompatWindowCallbackV14)this).startAsSupportActionMode(actionMode$Callback);
                }
            }
        }
        return super.onWindowStartingActionMode(actionMode$Callback, n);
    }
}
