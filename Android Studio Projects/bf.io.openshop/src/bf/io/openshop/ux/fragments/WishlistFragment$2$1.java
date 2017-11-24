package bf.io.openshop.ux.fragments;

import com.android.volley.*;

class WishlistFragment$2$1 implements Runnable {
    final /* synthetic */ VolleyError val$error;
    
    @Override
    public void run() {
        ErrorListener.this.val$requestListener.requestFailed(this.val$error);
    }
}