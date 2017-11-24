package bf.io.openshop.ux.adapters;

import bf.io.openshop.entities.delivery.*;
import android.view.*;
import android.os.*;
import bf.io.openshop.ux.dialogs.*;
import android.support.v4.app.*;

class ShippingSpinnerAdapter$1 implements View$OnClickListener {
    private long mLastClickTime = 0L;
    final /* synthetic */ Shipping val$item;
    
    public void onClick(final View view) {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1000L) {
            return;
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        final FragmentManager fragmentManager = ShippingSpinnerAdapter.this.fragment.getFragmentManager();
        final MapDialogFragment instance = MapDialogFragment.newInstance(ShippingSpinnerAdapter.this.fragment, this.val$item, this.val$item.getBranch());
        instance.setRetainInstance(true);
        instance.show(fragmentManager, MapDialogFragment.class.getSimpleName());
    }
}