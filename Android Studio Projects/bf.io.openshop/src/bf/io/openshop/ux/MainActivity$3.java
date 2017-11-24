package bf.io.openshop.ux;

import com.android.volley.*;
import org.json.*;
import timber.log.*;

class MainActivity$3 implements Listener<JSONObject> {
    public void onResponse(final JSONObject jsonObject) {
        Timber.d("getCartCount: " + jsonObject.toString(), new Object[0]);
        try {
            MainActivity.access$000(MainActivity.this, jsonObject.getInt("product_count"));
        }
        catch (Exception ex) {
            Timber.e(ex, "Obtain cart count from response failed.", new Object[0]);
            MainActivity.access$000(MainActivity.this, 0);
        }
    }
}