package bf.io.openshop.ux.dialogs;

import android.app.*;
import android.content.*;
import android.view.inputmethod.*;

class DiscountDialogFragment$1 extends Dialog {
    public void dismiss() {
        if (DiscountDialogFragment.this.getActivity() != null && DiscountDialogFragment.this.getView() != null) {
            ((InputMethodManager)DiscountDialogFragment.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(DiscountDialogFragment.this.getView().getWindowToken(), 0);
        }
        DiscountDialogFragment.this.requestListener = null;
        super.dismiss();
    }
}