package android.support.design.widget;

import android.os.*;

class SnackbarManager$1 implements Handler$Callback {
    public boolean handleMessage(final Message message) {
        switch (message.what) {
            default: {
                return false;
            }
            case 0: {
                SnackbarManager.access$000(SnackbarManager.this, (SnackbarRecord)message.obj);
                return true;
            }
        }
    }
}