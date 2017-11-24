package bf.io.openshop.ux.dialogs;

import android.content.*;
import bf.io.openshop.ux.*;

class LoginExpiredDialogFragment$1 implements DialogInterface$OnClickListener {
    public void onClick(final DialogInterface dialogInterface, final int n) {
        if (LoginExpiredDialogFragment.this.getActivity() instanceof MainActivity) {
            ((MainActivity)LoginExpiredDialogFragment.this.getActivity()).onDrawerBannersSelected();
        }
        dialogInterface.dismiss();
    }
}