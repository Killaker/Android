package bf.io.openshop.ux.dialogs;

import android.view.*;
import android.os.*;

class LoginDialogFragment$6 implements View$OnClickListener {
    public void onClick(final View view) {
        new Handler().postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                LoginDialogFragment.access$100(LoginDialogFragment.this, false);
            }
        }, 200L);
    }
}