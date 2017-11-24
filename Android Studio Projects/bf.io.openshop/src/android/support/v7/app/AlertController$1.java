package android.support.v7.app;

import android.view.*;
import android.os.*;

class AlertController$1 implements View$OnClickListener {
    public void onClick(final View view) {
        Message message;
        if (view == AlertController.access$000(AlertController.this) && AlertController.access$100(AlertController.this) != null) {
            message = Message.obtain(AlertController.access$100(AlertController.this));
        }
        else if (view == AlertController.access$200(AlertController.this) && AlertController.access$300(AlertController.this) != null) {
            message = Message.obtain(AlertController.access$300(AlertController.this));
        }
        else if (view == AlertController.access$400(AlertController.this) && AlertController.access$500(AlertController.this) != null) {
            message = Message.obtain(AlertController.access$500(AlertController.this));
        }
        else {
            message = null;
        }
        if (message != null) {
            message.sendToTarget();
        }
        AlertController.access$700(AlertController.this).obtainMessage(1, (Object)AlertController.access$600(AlertController.this)).sendToTarget();
    }
}