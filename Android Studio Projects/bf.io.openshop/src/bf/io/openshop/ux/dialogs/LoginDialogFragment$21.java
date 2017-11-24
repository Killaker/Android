package bf.io.openshop.ux.dialogs;

import com.android.volley.*;
import bf.io.openshop.entities.*;
import android.support.annotation.*;
import timber.log.*;

class LoginDialogFragment$21 implements Listener<User> {
    public void onResponse(@NonNull final User user) {
        Timber.d("response:" + user.toString(), new Object[0]);
        LoginDialogFragment.this.handleUserLogin(user);
    }
}