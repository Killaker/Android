package bf.io.openshop.ux.dialogs;

import android.widget.*;
import android.view.*;
import android.os.*;
import bf.io.openshop.entities.delivery.*;
import android.support.v4.app.*;

class ShippingDialogFragment$3$1 implements AdapterView$OnItemClickListener {
    private long mLastClickTime = 0L;
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime >= 1000L) {
            this.mLastClickTime = SystemClock.elapsedRealtime();
            final Branch branch = (Branch)ShippingDialogFragment.access$200(Listener.this.this$0).getItemAtPosition(n);
            if (branch != null) {
                final FragmentManager fragmentManager = ShippingDialogFragment.access$300(Listener.this.this$0).getFragmentManager();
                final MapDialogFragment instance = MapDialogFragment.newInstance(branch);
                instance.setRetainInstance(true);
                instance.show(fragmentManager, MapDialogFragment.class.getSimpleName());
            }
        }
    }
}