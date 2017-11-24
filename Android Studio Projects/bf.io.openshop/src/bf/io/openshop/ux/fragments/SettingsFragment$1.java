package bf.io.openshop.ux.fragments;

import android.view.*;
import bf.io.openshop.ux.dialogs.*;

class SettingsFragment$1 implements View$OnClickListener {
    public void onClick(final View view) {
        new LicensesDialogFragment().show(SettingsFragment.this.getFragmentManager(), LicensesDialogFragment.class.getSimpleName());
    }
}