package android.support.v7.app;

import android.view.*;
import android.support.v7.view.*;

class AppCompatWindowCallbackV14 extends AppCompatWindowCallbackBase
{
    AppCompatWindowCallbackV14(final Window$Callback window$Callback) {
        super(window$Callback);
    }
    
    @Override
    public ActionMode onWindowStartingActionMode(final ActionMode$Callback actionMode$Callback) {
        if (AppCompatDelegateImplV14.this.isHandleNativeActionModesEnabled()) {
            return this.startAsSupportActionMode(actionMode$Callback);
        }
        return super.onWindowStartingActionMode(actionMode$Callback);
    }
    
    final ActionMode startAsSupportActionMode(final ActionMode$Callback actionMode$Callback) {
        final SupportActionModeWrapper.CallbackWrapper callbackWrapper = new SupportActionModeWrapper.CallbackWrapper(AppCompatDelegateImplV14.this.mContext, actionMode$Callback);
        final android.support.v7.view.ActionMode startSupportActionMode = AppCompatDelegateImplV14.this.startSupportActionMode(callbackWrapper);
        if (startSupportActionMode != null) {
            return callbackWrapper.getActionModeWrapper(startSupportActionMode);
        }
        return null;
    }
}
