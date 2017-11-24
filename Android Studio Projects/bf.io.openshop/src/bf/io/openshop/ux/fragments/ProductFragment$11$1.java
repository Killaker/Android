package bf.io.openshop.ux.fragments;

import bf.io.openshop.interfaces.*;
import com.android.volley.*;

class ProductFragment$11$1 implements RequestListener {
    @Override
    public void requestFailed(final VolleyError volleyError) {
        ProductFragment$11.access$1502(View$OnClickListener.this, false);
        ProductFragment.access$700(View$OnClickListener.this.this$0).showProgress(false);
    }
    
    @Override
    public void requestSuccess(final long n) {
        ProductFragment$11.access$1502(View$OnClickListener.this, false);
        ProductFragment.access$700(View$OnClickListener.this.this$0).onProgressCompleted();
        ProductFragment.access$1402(View$OnClickListener.this.this$0, -131L);
    }
}