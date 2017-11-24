package bf.io.openshop.ux.fragments;

import android.widget.*;
import android.view.*;
import bf.io.openshop.entities.*;
import bf.io.openshop.*;
import android.app.*;
import bf.io.openshop.ux.dialogs.*;
import timber.log.*;

class SettingsFragment$4 implements AdapterView$OnItemSelectedListener {
    public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        final Shop shop = (Shop)adapterView.getItemAtPosition(n);
        if (shop != null && shop.getId() != SettingsMy.getActualNonNullShop(SettingsFragment.this.getActivity()).getId()) {
            RestartDialogFragment.newInstance(shop).show(SettingsFragment.this.getFragmentManager(), RestartDialogFragment.class.getSimpleName());
            return;
        }
        Timber.e("Selected null or same shop.", new Object[0]);
    }
    
    public void onNothingSelected(final AdapterView<?> adapterView) {
        Timber.d("Nothing selected", new Object[0]);
    }
}