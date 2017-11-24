package bf.io.openshop.ux.dialogs;

import android.support.v4.app.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.entities.delivery.*;
import android.os.*;
import timber.log.*;
import bf.io.openshop.listeners.*;
import bf.io.openshop.ux.adapters.*;
import android.content.*;
import android.widget.*;
import android.app.*;
import android.view.*;

public class PaymentDialogFragment extends DialogFragment
{
    private PaymentDialogInterface paymentDialogInterface;
    private Payment selectedPaymentType;
    private Shipping selectedShipping;
    
    public static PaymentDialogFragment newInstance(final Shipping selectedShipping, final Payment selectedPaymentType, final PaymentDialogInterface paymentDialogInterface) {
        final PaymentDialogFragment paymentDialogFragment = new PaymentDialogFragment();
        paymentDialogFragment.paymentDialogInterface = paymentDialogInterface;
        paymentDialogFragment.selectedShipping = selectedShipping;
        paymentDialogFragment.selectedPaymentType = selectedPaymentType;
        return paymentDialogFragment;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setStyle(1, 2131427707);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d(PaymentDialogFragment.class.getSimpleName() + " onCreateView", new Object[0]);
        final View inflate = layoutInflater.inflate(2130968678, viewGroup, false);
        final ListView listView = (ListView)inflate.findViewById(2131624382);
        inflate.findViewById(2131624381).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                PaymentDialogFragment.this.dismiss();
            }
        });
        if (this.selectedShipping != null) {
            final PaymentSpinnerAdapter adapter = new PaymentSpinnerAdapter((Context)this.getActivity(), this.selectedShipping.getPayment());
            adapter.preselectPayment(this.selectedPaymentType);
            listView.setAdapter((ListAdapter)adapter);
            listView.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
                public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                    final Payment item = adapter.getItem(n);
                    if (PaymentDialogFragment.this.paymentDialogInterface != null) {
                        PaymentDialogFragment.this.paymentDialogInterface.onPaymentSelected(item);
                    }
                    Timber.e("Payment click: " + item.toString(), new Object[0]);
                    PaymentDialogFragment.this.dismiss();
                }
            });
        }
        return inflate;
    }
    
    @Override
    public void onStart() {
        super.onStart();
        final Dialog dialog = this.getDialog();
        if (dialog != null) {
            final Window window = dialog.getWindow();
            window.setLayout(-1, -1);
            window.setWindowAnimations(2131427694);
        }
    }
}
