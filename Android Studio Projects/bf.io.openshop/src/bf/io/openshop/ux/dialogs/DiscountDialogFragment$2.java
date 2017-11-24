package bf.io.openshop.ux.dialogs;

import android.view.*;

class DiscountDialogFragment$2 implements View$OnClickListener {
    public void onClick(final View view) {
        if (DiscountDialogFragment.this.isRequiredFieldsOk()) {
            DiscountDialogFragment.access$100(DiscountDialogFragment.this, DiscountDialogFragment.access$000(DiscountDialogFragment.this).getEditText());
        }
    }
}