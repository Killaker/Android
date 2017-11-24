package bf.io.openshop.ux.dialogs;

import android.support.v4.app.*;
import android.os.*;
import timber.log.*;
import android.app.*;
import android.content.*;
import bf.io.openshop.ux.*;
import android.support.annotation.*;

public class LoginExpiredDialogFragment extends DialogFragment
{
    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle bundle) {
        Timber.d(LoginExpiredDialogFragment.class.getSimpleName() + " onCreateDialog", new Object[0]);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.getActivity(), 2131427710);
        alertDialog$Builder.setTitle(2131230855);
        alertDialog$Builder.setMessage(2131230921);
        alertDialog$Builder.setPositiveButton(2131230854, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                if (LoginExpiredDialogFragment.this.getActivity() instanceof MainActivity) {
                    ((MainActivity)LoginExpiredDialogFragment.this.getActivity()).onDrawerBannersSelected();
                }
                dialogInterface.dismiss();
            }
        });
        return (Dialog)alertDialog$Builder.create();
    }
}
