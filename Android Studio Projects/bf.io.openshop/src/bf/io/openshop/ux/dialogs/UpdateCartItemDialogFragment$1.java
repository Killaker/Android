package bf.io.openshop.ux.dialogs;

import android.view.*;
import timber.log.*;
import bf.io.openshop.utils.*;
import android.app.*;
import bf.io.openshop.entities.product.*;

class UpdateCartItemDialogFragment$1 implements View$OnClickListener {
    public void onClick(final View view) {
        if (UpdateCartItemDialogFragment.access$000(UpdateCartItemDialogFragment.this) == null || UpdateCartItemDialogFragment.access$100(UpdateCartItemDialogFragment.this) == null) {
            Timber.e(new NullPointerException(), "Null spinners in editing item in cart", new Object[0]);
            MsgUtils.showToast(UpdateCartItemDialogFragment.this.getActivity(), 1, UpdateCartItemDialogFragment.this.getString(2131230834), MsgUtils.ToastLength.SHORT);
            UpdateCartItemDialogFragment.this.dismiss();
            return;
        }
        final ProductVariant productVariant = (ProductVariant)UpdateCartItemDialogFragment.access$100(UpdateCartItemDialogFragment.this).getSelectedItem();
        final ProductQuantity productQuantity = (ProductQuantity)UpdateCartItemDialogFragment.access$000(UpdateCartItemDialogFragment.this).getSelectedItem();
        Timber.d("Selected: " + productVariant + ". Quantity: " + productQuantity, new Object[0]);
        if (productVariant != null && productVariant.getSize() != null && productQuantity != null) {
            UpdateCartItemDialogFragment.access$300(UpdateCartItemDialogFragment.this, UpdateCartItemDialogFragment.access$200(UpdateCartItemDialogFragment.this).getId(), productVariant.getId(), productQuantity.getQuantity());
            return;
        }
        Timber.e(new RuntimeException(), "Cannot obtain info about edited cart item.", new Object[0]);
        MsgUtils.showToast(UpdateCartItemDialogFragment.this.getActivity(), 1, UpdateCartItemDialogFragment.this.getString(2131230834), MsgUtils.ToastLength.SHORT);
        UpdateCartItemDialogFragment.this.dismiss();
    }
}