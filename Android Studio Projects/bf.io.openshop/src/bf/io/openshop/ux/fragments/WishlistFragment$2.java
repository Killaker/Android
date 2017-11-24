package bf.io.openshop.ux.fragments;

import android.support.v4.app.*;
import bf.io.openshop.interfaces.*;
import com.android.volley.*;
import android.os.*;
import bf.io.openshop.utils.*;
import android.app.*;

static final class WishlistFragment$2 implements ErrorListener {
    final /* synthetic */ FragmentActivity val$activity;
    final /* synthetic */ RequestListener val$requestListener;
    
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        new Handler().postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                ErrorListener.this.val$requestListener.requestFailed(volleyError);
            }
        }, 500L);
        MsgUtils.logAndShowErrorMessage(this.val$activity, volleyError);
    }
}