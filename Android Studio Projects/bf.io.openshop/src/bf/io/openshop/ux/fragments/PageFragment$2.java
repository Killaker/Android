package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.utils.*;
import android.app.*;

class PageFragment$2 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (PageFragment.access$100(PageFragment.this) != null) {
            PageFragment.access$100(PageFragment.this).cancel();
        }
        PageFragment.access$200(PageFragment.this, false);
        MsgUtils.logAndShowErrorMessage(PageFragment.this.getActivity(), volleyError);
    }
}