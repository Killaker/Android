package bf.io.openshop.api;

import com.android.volley.toolbox.*;
import android.support.v4.app.*;
import org.json.*;
import java.util.*;
import android.util.*;
import bf.io.openshop.ux.dialogs.*;
import bf.io.openshop.*;
import com.android.volley.*;

public class JsonRequest extends JsonObjectRequest
{
    private final String accessToken;
    private FragmentManager fragmentManager;
    private int requestStatusCode;
    private String requestUrl;
    
    public JsonRequest(final int n, final String s, final JSONObject jsonObject, final Response.Listener<JSONObject> listener, final Response.ErrorListener errorListener) {
        this(n, s, jsonObject, listener, errorListener, null, null);
    }
    
    public JsonRequest(final int n, final String requestUrl, final JSONObject jsonObject, final Response.Listener<JSONObject> listener, final Response.ErrorListener errorListener, final FragmentManager fragmentManager, final String accessToken) {
        super(n, requestUrl, jsonObject, listener, errorListener);
        this.requestUrl = requestUrl;
        this.fragmentManager = fragmentManager;
        this.accessToken = accessToken;
    }
    
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("Client-Version", MyApplication.APP_VERSION);
        hashMap.put("Device-Token", MyApplication.ANDROID_ID);
        if (this.accessToken != null && !this.accessToken.isEmpty()) {
            hashMap.put("Authorization", "Basic " + Base64.encodeToString((this.accessToken + ":").getBytes(), 2));
        }
        return hashMap;
    }
    
    public int getStatusCode() {
        return this.requestStatusCode;
    }
    
    @Override
    protected VolleyError parseNetworkError(final VolleyError volleyError) {
        if (volleyError.networkResponse != null) {
            this.requestStatusCode = volleyError.networkResponse.statusCode;
            if (this.getStatusCode() == 403 && this.fragmentManager != null) {
                LoginDialogFragment.logoutUser();
                new LoginExpiredDialogFragment().show(this.fragmentManager, LoginExpiredDialogFragment.class.getSimpleName());
            }
        }
        else {
            this.requestStatusCode = CONST.MissingStatusCode;
        }
        return super.parseNetworkError(volleyError);
    }
    
    @Override
    protected Response<JSONObject> parseNetworkResponse(final NetworkResponse networkResponse) {
        try {
            this.requestStatusCode = networkResponse.statusCode;
            return super.parseNetworkResponse(networkResponse);
        }
        catch (Exception ex) {
            return Response.error(new ParseError(ex));
        }
    }
}
