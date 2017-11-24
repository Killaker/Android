package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.utils.*;
import android.app.*;

class SettingsFragment$3 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (SettingsFragment.access$100(SettingsFragment.this) != null) {
            SettingsFragment.access$100(SettingsFragment.this).cancel();
        }
        MsgUtils.logAndShowErrorMessage(SettingsFragment.this.getActivity(), volleyError);
    }
}