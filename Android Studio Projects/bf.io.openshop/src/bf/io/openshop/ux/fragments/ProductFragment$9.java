package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import org.json.*;

class ProductFragment$9 implements Listener<JSONObject> {
    public void onResponse(final JSONObject jsonObject) {
        ProductFragment.access$1200(ProductFragment.this, jsonObject);
    }
}