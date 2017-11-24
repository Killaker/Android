package bf.io.openshop.ux.dialogs;

import android.view.*;

class FilterDialogFragment$1 implements View$OnClickListener {
    public void onClick(final View view) {
        FilterDialogFragment.access$000(FilterDialogFragment.this).onFilterSelected(FilterDialogFragment.this.buildFilterUrl());
        FilterDialogFragment.this.dismiss();
    }
}