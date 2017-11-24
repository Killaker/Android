package android.support.design.widget;

import java.lang.ref.*;

private static class SnackbarRecord
{
    private final WeakReference<Callback> callback;
    private int duration;
    
    SnackbarRecord(final int duration, final Callback callback) {
        this.callback = new WeakReference<Callback>(callback);
        this.duration = duration;
    }
    
    boolean isSnackbar(final Callback callback) {
        return callback != null && this.callback.get() == callback;
    }
}
