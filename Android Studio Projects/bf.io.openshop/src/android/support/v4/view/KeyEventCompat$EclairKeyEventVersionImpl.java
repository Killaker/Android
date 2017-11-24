package android.support.v4.view;

import android.view.*;

static class EclairKeyEventVersionImpl extends BaseKeyEventVersionImpl
{
    @Override
    public boolean dispatch(final KeyEvent keyEvent, final KeyEvent$Callback keyEvent$Callback, final Object o, final Object o2) {
        return KeyEventCompatEclair.dispatch(keyEvent, keyEvent$Callback, o, o2);
    }
    
    @Override
    public Object getKeyDispatcherState(final View view) {
        return KeyEventCompatEclair.getKeyDispatcherState(view);
    }
    
    @Override
    public boolean isTracking(final KeyEvent keyEvent) {
        return KeyEventCompatEclair.isTracking(keyEvent);
    }
    
    @Override
    public void startTracking(final KeyEvent keyEvent) {
        KeyEventCompatEclair.startTracking(keyEvent);
    }
}
