package android.support.v7.widget;

import android.widget.*;
import android.view.*;

class AppCompatSpinner$DropdownPopup$1 implements AdapterView$OnItemClickListener {
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int selection, final long n) {
        DropdownPopup.this.this$0.setSelection(selection);
        if (DropdownPopup.this.this$0.getOnItemClickListener() != null) {
            DropdownPopup.this.this$0.performItemClick(view, selection, DropdownPopup.access$200(DropdownPopup.this).getItemId(selection));
        }
        DropdownPopup.this.dismiss();
    }
}