package bf.io.openshop.ux.dialogs;

import android.support.v4.app.*;
import android.os.*;
import bf.io.openshop.*;
import android.app.*;
import bf.io.openshop.utils.*;
import bf.io.openshop.entities.*;
import bf.io.openshop.ux.*;
import android.content.*;
import android.support.annotation.*;

public class RestartDialogFragment extends DialogFragment
{
    private Shop newShopSelected;
    
    public static RestartDialogFragment newInstance(final Shop newShopSelected) {
        final RestartDialogFragment restartDialogFragment = new RestartDialogFragment();
        restartDialogFragment.newShopSelected = newShopSelected;
        return restartDialogFragment;
    }
    
    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle bundle) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.getActivity());
        alertDialog$Builder.setTitle(2131230882);
        alertDialog$Builder.setMessage(2131230793);
        alertDialog$Builder.setPositiveButton(2131230854, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                Analytics.logShopChange(SettingsMy.getActualNonNullShop(RestartDialogFragment.this.getActivity()), RestartDialogFragment.this.newShopSelected);
                Analytics.deleteAppTrackers();
                SettingsMy.setActiveUser(null);
                SettingsMy.setActualShop(RestartDialogFragment.this.newShopSelected);
                final Intent intent = new Intent((Context)RestartDialogFragment.this.getActivity(), (Class)RestartAppActivity.class);
                intent.addFlags(268468224);
                RestartDialogFragment.this.startActivity(intent);
                RestartDialogFragment.this.getActivity().finish();
                dialogInterface.dismiss();
            }
        });
        alertDialog$Builder.setNegativeButton(2131230797, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                dialogInterface.dismiss();
            }
        });
        return (Dialog)alertDialog$Builder.create();
    }
}
