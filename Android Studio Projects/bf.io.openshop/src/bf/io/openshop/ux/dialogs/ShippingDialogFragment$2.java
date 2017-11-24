package bf.io.openshop.ux.dialogs;

import bf.io.openshop.ux.adapters.*;
import android.widget.*;
import android.view.*;

class ShippingDialogFragment$2 implements AdapterView$OnItemClickListener {
    final /* synthetic */ ShippingSpinnerAdapter val$deliverySpinnerAdapter;
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        ShippingDialogFragment.this.onShippingSelected(this.val$deliverySpinnerAdapter.getItem(n));
    }
}