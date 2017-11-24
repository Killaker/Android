package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.entities.*;
import android.support.annotation.*;
import timber.log.*;

class BannersFragment$4 implements Listener<BannersResponse> {
    public void onResponse(@NonNull final BannersResponse bannersResponse) {
        Timber.d("response:" + bannersResponse.toString(), new Object[0]);
        BannersFragment.access$002(BannersFragment.this, bannersResponse.getMetadata());
        BannersFragment.access$200(BannersFragment.this).addBanners(bannersResponse.getRecords());
        if (BannersFragment.access$200(BannersFragment.this).getItemCount() > 0) {
            BannersFragment.access$300(BannersFragment.this).setVisibility(4);
            BannersFragment.access$400(BannersFragment.this).setVisibility(0);
        }
        else {
            BannersFragment.access$300(BannersFragment.this).setVisibility(0);
            BannersFragment.access$400(BannersFragment.this).setVisibility(4);
        }
        BannersFragment.access$500(BannersFragment.this).cancel();
    }
}