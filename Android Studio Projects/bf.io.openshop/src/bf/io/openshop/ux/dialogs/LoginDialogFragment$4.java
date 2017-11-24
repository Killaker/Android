package bf.io.openshop.ux.dialogs;

import android.view.*;
import android.os.*;

class LoginDialogFragment$4 implements View$OnClickListener {
    public void onClick(final View view) {
        new Handler().postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                LoginDialogFragment.access$300(LoginDialogFragment.this, false);
            }
        }, 200L);
    }
}