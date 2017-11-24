package bf.io.openshop.ux.adapters;

import android.widget.*;
import android.view.*;
import bf.io.openshop.entities.filtr.*;

class FilterRecyclerAdapter$ViewHolderSelect$1 implements AdapterView$OnItemSelectedListener {
    public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        if (ViewHolderSelect.access$200(ViewHolderSelect.this).getValues().get(n).getId() != -131L) {
            ViewHolderSelect.access$200(ViewHolderSelect.this).setSelectedValue(ViewHolderSelect.access$200(ViewHolderSelect.this).getValues().get(n));
            return;
        }
        ViewHolderSelect.access$200(ViewHolderSelect.this).setSelectedValue(null);
    }
    
    public void onNothingSelected(final AdapterView<?> adapterView) {
    }
}