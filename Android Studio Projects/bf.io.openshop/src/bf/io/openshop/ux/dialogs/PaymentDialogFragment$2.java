package bf.io.openshop.ux.dialogs;

import bf.io.openshop.ux.adapters.*;
import android.widget.*;
import android.view.*;
import timber.log.*;
import bf.io.openshop.entities.delivery.*;

class PaymentDialogFragment$2 implements AdapterView$OnItemClickListener {
    final /* synthetic */ PaymentSpinnerAdapter val$paymentSpinnerAdapter;
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        final Payment item = this.val$paymentSpinnerAdapter.getItem(n);
        if (PaymentDialogFragment.access$000(PaymentDialogFragment.this) != null) {
            PaymentDialogFragment.access$000(PaymentDialogFragment.this).onPaymentSelected(item);
        }
        Timber.e("Payment click: " + item.toString(), new Object[0]);
        PaymentDialogFragment.this.dismiss();
    }
}