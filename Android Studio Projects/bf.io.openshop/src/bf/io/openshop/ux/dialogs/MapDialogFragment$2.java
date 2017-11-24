package bf.io.openshop.ux.dialogs;

import bf.io.openshop.listeners.*;
import android.view.*;
import timber.log.*;

class MapDialogFragment$2 extends OnSingleClickListener {
    @Override
    public void onSingleClick(final View view) {
        if (MapDialogFragment.this.shippingDialogFragment != null && MapDialogFragment.access$000(MapDialogFragment.this) != null) {
            MapDialogFragment.this.shippingDialogFragment.onShippingSelected(MapDialogFragment.access$000(MapDialogFragment.this));
            MapDialogFragment.this.dismiss();
            return;
        }
        Timber.e("Something is null", new Object[0]);
    }
}