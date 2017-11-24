package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import org.json.*;
import bf.io.openshop.interfaces.*;
import timber.log.*;
import android.os.*;

static final class WishlistFragment$3 implements Listener<JSONObject> {
    final /* synthetic */ RequestListener val$requestListener;
    
    public void onResponse(final JSONObject jsonObject) {
        Timber.d("RemoveFromWishlist response" + jsonObject.toString(), new Object[0]);
        new Handler().postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                Listener.this.val$requestListener.requestSuccess(0L);
            }
        }, 500L);
    }
}