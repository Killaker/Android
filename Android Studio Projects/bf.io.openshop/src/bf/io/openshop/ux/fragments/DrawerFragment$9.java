package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.utils.*;
import android.app.*;

class DrawerFragment$9 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        MsgUtils.logAndShowErrorMessage(DrawerFragment.this.getActivity(), volleyError);
        DrawerFragment.access$002(DrawerFragment.this, false);
        if (DrawerFragment.access$800(DrawerFragment.this) != null) {
            DrawerFragment.access$800(DrawerFragment.this).setVisibility(8);
        }
        if (DrawerFragment.access$900(DrawerFragment.this) != null) {
            DrawerFragment.access$900(DrawerFragment.this).setVisibility(0);
        }
    }
}