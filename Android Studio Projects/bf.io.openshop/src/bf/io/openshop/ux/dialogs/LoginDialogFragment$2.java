package bf.io.openshop.ux.dialogs;

import android.widget.*;
import android.view.*;

class LoginDialogFragment$2 implements TextView$OnEditorActionListener {
    public boolean onEditorAction(final TextView textView, final int n, final KeyEvent keyEvent) {
        if (n == 4 || n == 124) {
            LoginDialogFragment.access$400(LoginDialogFragment.this);
            return true;
        }
        return false;
    }
}