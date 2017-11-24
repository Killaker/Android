package bf.io.openshop.api;

import android.support.v4.app.*;
import android.support.annotation.*;
import java.io.*;
import java.util.*;
import android.util.*;
import bf.io.openshop.ux.dialogs.*;
import bf.io.openshop.*;
import bf.io.openshop.utils.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.google.gson.*;

public class GsonRequest<T> extends Request<T>
{
    protected static final String PROTOCOL_CHARSET = "utf-8";
    private final String accessToken;
    private final Class<T> clazz;
    private FragmentManager fragmentManager;
    private final String requestBody;
    private int requestStatusCode;
    private String requestUrl;
    private final Response.Listener<T> successListener;
    
    public GsonRequest(final int n, final String s, final String s2, final Class<T> clazz, final Response.Listener<T> listener, final Response.ErrorListener errorListener) {
        this(n, s, s2, clazz, listener, errorListener, null, null);
    }
    
    public GsonRequest(final int n, final String requestUrl, final String requestBody, final Class<T> clazz, final Response.Listener<T> successListener, final Response.ErrorListener errorListener, final FragmentManager fragmentManager, final String accessToken) {
        super(n, requestUrl, errorListener);
        this.clazz = clazz;
        this.requestUrl = requestUrl;
        this.requestBody = requestBody;
        this.successListener = successListener;
        this.fragmentManager = fragmentManager;
        this.accessToken = accessToken;
    }
    
    @Override
    protected void deliverResponse(@NonNull final T t) {
        this.successListener.onResponse(t);
    }
    
    @Override
    public byte[] getBody() {
        try {
            if (this.requestBody == null) {
                return null;
            }
            return this.requestBody.getBytes("utf-8");
        }
        catch (UnsupportedEncodingException ex) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", this.requestBody, "utf-8");
            return null;
        }
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
    protected Response<T> parseNetworkResponse(final NetworkResponse networkResponse) {
        try {
            this.requestStatusCode = networkResponse.statusCode;
            final T fromJson = Utils.getGsonParser().fromJson(new String(networkResponse.data, "utf-8"), this.clazz);
            if (fromJson == null) {
                return Response.error(new ParseError(new NullPointerException()));
            }
            return Response.success(fromJson, HttpHeaderParser.parseCacheHeaders(networkResponse));
        }
        catch (UnsupportedEncodingException ex) {
            return Response.error(new ParseError(ex));
        }
        catch (JsonSyntaxException ex2) {
            return Response.error(new ParseError(ex2));
        }
        catch (Exception ex3) {
            return Response.error(new ParseError(ex3));
        }
    }
}
