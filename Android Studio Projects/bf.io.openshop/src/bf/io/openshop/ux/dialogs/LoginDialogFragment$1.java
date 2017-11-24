package bf.io.openshop.ux.dialogs;

import android.content.*;
import android.view.*;

class LoginDialogFragment$1 implements DialogInterface$OnKeyListener {
    public boolean onKey(final DialogInterface dialogInterface, final int n, final KeyEvent keyEvent) {
        boolean b = true;
        if (n == 4 && keyEvent.getRepeatCount() == 0) {
            switch (LoginDialogFragment.access$000(LoginDialogFragment.this)) {
                default: {
                    b = false;
                    break;
                }
                case REGISTRATION: {
                    if (keyEvent.getAction() == (b ? 1 : 0)) {
                        LoginDialogFragment.access$100(LoginDialogFragment.this, false);
                        return b;
                    }
                    break;
                }
                case FORGOTTEN_PASSWORD: {
                    if (keyEvent.getAction() == (b ? 1 : 0)) {
                        LoginDialogFragment.access$200(LoginDialogFragment.this, false);
                        return b;
                    }
                    break;
                }
                case EMAIL: {
                    if (keyEvent.getAction() == (b ? 1 : 0)) {
                        LoginDialogFragment.access$300(LoginDialogFragment.this, false);
                        return b;
                    }
                    break;
                }
            }
            return b;
        }
        return false;
    }
}