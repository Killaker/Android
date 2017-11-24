package bf.io.openshop.ux.dialogs;

import android.view.*;
import android.os.*;

class LoginDialogFragment$8 implements View$OnClickListener {
    public void onClick(final View view) {
        new Handler().postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                LoginDialogFragment.access$200(LoginDialogFragment.this, false);
            }
        }, 200L);
    }
}