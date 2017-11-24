package bf.io.openshop.ux.dialogs;

import bf.io.openshop.*;
import android.app.*;
import bf.io.openshop.utils.*;
import bf.io.openshop.entities.*;
import bf.io.openshop.ux.*;
import android.content.*;

class RestartDialogFragment$1 implements DialogInterface$OnClickListener {
    public void onClick(final DialogInterface dialogInterface, final int n) {
        Analytics.logShopChange(SettingsMy.getActualNonNullShop(RestartDialogFragment.this.getActivity()), RestartDialogFragment.access$000(RestartDialogFragment.this));
        Analytics.deleteAppTrackers();
        SettingsMy.setActiveUser(null);
        SettingsMy.setActualShop(RestartDialogFragment.access$000(RestartDialogFragment.this));
        final Intent intent = new Intent((Context)RestartDialogFragment.this.getActivity(), (Class)RestartAppActivity.class);
        intent.addFlags(268468224);
        RestartDialogFragment.this.startActivity(intent);
        RestartDialogFragment.this.getActivity().finish();
        dialogInterface.dismiss();
    }
}