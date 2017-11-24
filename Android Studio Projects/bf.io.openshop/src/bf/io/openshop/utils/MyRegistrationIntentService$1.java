package bf.io.openshop.utils;

import com.android.volley.*;
import timber.log.*;
import org.json.*;

class MyRegistrationIntentService$1 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        try {
            final JSONArray jsonArray = new JSONObject(new String(volleyError.networkResponse.data)).getJSONArray("body");
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < jsonArray.length(); ++i) {
                sb.append(jsonArray.get(i));
                sb.append("\n");
            }
            Timber.e("Error message:" + sb.toString(), new Object[0]);
        }
        catch (Exception ex) {
            Timber.e(ex, "GCM error response parsing failed", new Object[0]);
        }
    }
}