package android.support.v7.app;

import java.lang.ref.*;
import android.os.*;
import android.content.*;

private static final class ButtonHandler extends Handler
{
    private static final int MSG_DISMISS_DIALOG = 1;
    private WeakReference<DialogInterface> mDialog;
    
    public ButtonHandler(final DialogInterface dialogInterface) {
        this.mDialog = new WeakReference<DialogInterface>(dialogInterface);
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {}
            case -3:
            case -2:
            case -1: {
                ((DialogInterface$OnClickListener)message.obj).onClick((DialogInterface)this.mDialog.get(), message.what);
            }
            case 1: {
                ((DialogInterface)message.obj).dismiss();
            }
        }
    }
}
