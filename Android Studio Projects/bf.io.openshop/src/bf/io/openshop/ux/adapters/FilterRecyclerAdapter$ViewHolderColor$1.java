package bf.io.openshop.ux.adapters;

import android.widget.*;
import android.view.*;
import bf.io.openshop.entities.filtr.*;

class FilterRecyclerAdapter$ViewHolderColor$1 implements AdapterView$OnItemSelectedListener {
    public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        if (ViewHolderColor.access$100(ViewHolderColor.this).getValues().get(n).getId() != -131L) {
            ViewHolderColor.access$100(ViewHolderColor.this).setSelectedValue(ViewHolderColor.access$100(ViewHolderColor.this).getValues().get(n));
            return;
        }
        ViewHolderColor.access$100(ViewHolderColor.this).setSelectedValue(null);
    }
    
    public void onNothingSelected(final AdapterView<?> adapterView) {
    }
}