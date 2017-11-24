package bf.io.openshop.ux.dialogs;

import com.android.volley.*;
import android.support.annotation.*;
import timber.log.*;
import bf.io.openshop.ux.adapters.*;
import android.content.*;
import android.widget.*;
import android.view.*;
import android.os.*;
import bf.io.openshop.entities.delivery.*;
import android.support.v4.app.*;

class ShippingDialogFragment$3 implements Listener<BranchesRequest> {
    public void onResponse(@NonNull final BranchesRequest branchesRequest) {
        Timber.d("GetBranches response: " + branchesRequest.toString(), new Object[0]);
        ShippingDialogFragment.access$000(ShippingDialogFragment.this, true);
        if (branchesRequest.getBranches() != null && branchesRequest.getBranches().size() >= 0) {
            ShippingDialogFragment.access$100(ShippingDialogFragment.this).setVisibility(8);
            ShippingDialogFragment.access$200(ShippingDialogFragment.this).setVisibility(0);
            ShippingDialogFragment.access$200(ShippingDialogFragment.this).setAdapter((ListAdapter)new BranchesAdapter((Context)ShippingDialogFragment.this.getActivity(), branchesRequest.getBranches()));
            ShippingDialogFragment.access$200(ShippingDialogFragment.this).setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
                private long mLastClickTime = 0L;
                
                public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                    if (SystemClock.elapsedRealtime() - this.mLastClickTime >= 1000L) {
                        this.mLastClickTime = SystemClock.elapsedRealtime();
                        final Branch branch = (Branch)ShippingDialogFragment.access$200(ShippingDialogFragment.this).getItemAtPosition(n);
                        if (branch != null) {
                            final FragmentManager fragmentManager = ShippingDialogFragment.access$300(ShippingDialogFragment.this).getFragmentManager();
                            final MapDialogFragment instance = MapDialogFragment.newInstance(branch);
                            instance.setRetainInstance(true);
                            instance.show(fragmentManager, MapDialogFragment.class.getSimpleName());
                        }
                    }
                }
            });
            return;
        }
        ShippingDialogFragment.access$100(ShippingDialogFragment.this).setVisibility(0);
        ShippingDialogFragment.access$200(ShippingDialogFragment.this).setVisibility(8);
    }
}