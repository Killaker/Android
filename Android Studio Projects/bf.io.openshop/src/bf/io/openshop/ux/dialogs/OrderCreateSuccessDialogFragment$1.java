package bf.io.openshop.ux.dialogs;

import android.view.*;
import bf.io.openshop.ux.*;

class OrderCreateSuccessDialogFragment$1 implements View$OnClickListener {
    public void onClick(final View view) {
        if (OrderCreateSuccessDialogFragment.this.getActivity() instanceof MainActivity) {
            ((MainActivity)OrderCreateSuccessDialogFragment.this.getActivity()).onDrawerBannersSelected();
        }
        OrderCreateSuccessDialogFragment.this.dismiss();
    }
}