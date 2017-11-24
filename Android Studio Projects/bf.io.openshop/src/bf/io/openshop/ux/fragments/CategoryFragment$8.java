package bf.io.openshop.ux.fragments;

import android.widget.*;
import android.view.*;
import timber.log.*;

class CategoryFragment$8 implements AdapterView$OnItemSelectedListener {
    private int lastSortSpinnerPosition = -1;
    
    public void onItemSelected(final AdapterView<?> adapterView, final View view, final int lastSortSpinnerPosition, final long n) {
        if (CategoryFragment.this.firstTimeSort) {
            CategoryFragment.this.firstTimeSort = false;
            return;
        }
        Timber.d("Selected pos: " + lastSortSpinnerPosition, new Object[0]);
        if (lastSortSpinnerPosition != this.lastSortSpinnerPosition) {
            Timber.d("OnItemSelected change", new Object[0]);
            this.lastSortSpinnerPosition = lastSortSpinnerPosition;
            CategoryFragment.access$400(CategoryFragment.this, null);
            return;
        }
        Timber.d("OnItemSelected no change", new Object[0]);
    }
    
    public void onNothingSelected(final AdapterView<?> adapterView) {
        Timber.d("OnNothingSelected - no change", new Object[0]);
    }
}