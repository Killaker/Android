package android.support.v7.app;

import android.widget.*;
import android.view.*;
import android.content.*;

class AlertController$AlertParams$4 implements AdapterView$OnItemClickListener {
    final /* synthetic */ AlertController val$dialog;
    final /* synthetic */ ListView val$listView;
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        if (AlertParams.this.mCheckedItems != null) {
            AlertParams.this.mCheckedItems[n] = this.val$listView.isItemChecked(n);
        }
        AlertParams.this.mOnCheckboxClickListener.onClick((DialogInterface)AlertController.access$600(this.val$dialog), n, this.val$listView.isItemChecked(n));
    }
}