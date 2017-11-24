package bf.io.openshop.ux.fragments;

import org.json.*;
import bf.io.openshop.interfaces.*;
import timber.log.*;
import android.os.*;
import com.android.volley.*;

static final class WishlistFragment$1 implements Listener<JSONObject> {
    final /* synthetic */ RequestListener val$requestListener;
    
    public void onResponse(final JSONObject jsonObject) {
        Timber.d("AddToWishlist response" + jsonObject.toString(), new Object[0]);
        try {
            new Handler().postDelayed((Runnable)new Runnable() {
                final /* synthetic */ long val$responseId = jsonObject.getLong("id");
                
                @Override
                public void run() {
                    Listener.this.val$requestListener.requestSuccess(this.val$responseId);
                }
            }, 500L);
        }
        catch (Exception ex) {
            Timber.e(ex, "Parsing addToWishList response failed. Response: " + jsonObject, new Object[0]);
            new Handler().postDelayed((Runnable)new Runnable() {
                @Override
                public void run() {
                    Listener.this.val$requestListener.requestFailed(null);
                }
            }, 500L);
        }
    }
}