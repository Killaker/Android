package android.support.v7.app;

import android.widget.*;
import android.view.*;
import android.content.*;

class AlertController$AlertParams$3 implements AdapterView$OnItemClickListener {
    final /* synthetic */ AlertController val$dialog;
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        AlertParams.this.mOnClickListener.onClick((DialogInterface)AlertController.access$600(this.val$dialog), n);
        if (!AlertParams.this.mIsSingleChoice) {
            AlertController.access$600(this.val$dialog).dismiss();
        }
    }
}