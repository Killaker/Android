package android.support.v7.app;

import android.widget.*;
import android.content.*;
import android.view.*;

class AlertController$AlertParams$1 extends ArrayAdapter<CharSequence> {
    final /* synthetic */ ListView val$listView;
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        final View view2 = super.getView(n, view, viewGroup);
        if (AlertParams.this.mCheckedItems != null && AlertParams.this.mCheckedItems[n]) {
            this.val$listView.setItemChecked(n, true);
        }
        return view2;
    }
}