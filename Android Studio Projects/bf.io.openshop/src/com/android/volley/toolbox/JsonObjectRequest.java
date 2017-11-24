package com.android.volley.toolbox;

import com.android.volley.*;
import java.io.*;
import org.json.*;

public class JsonObjectRequest extends JsonRequest<JSONObject>
{
    public JsonObjectRequest(final int n, final String s, final JSONObject jsonObject, final Response.Listener<JSONObject> listener, final Response.ErrorListener errorListener) {
        String string;
        if (jsonObject == null) {
            string = null;
        }
        else {
            string = jsonObject.toString();
        }
        super(n, s, string, listener, errorListener);
    }
    
    public JsonObjectRequest(final String s, final JSONObject jsonObject, final Response.Listener<JSONObject> listener, final Response.ErrorListener errorListener) {
        int n;
        if (jsonObject == null) {
            n = 0;
        }
        else {
            n = 1;
        }
        this(n, s, jsonObject, listener, errorListener);
    }
    
    @Override
    protected Response<JSONObject> parseNetworkResponse(final NetworkResponse networkResponse) {
        try {
            return Response.success(new JSONObject(new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers, "utf-8"))), HttpHeaderParser.parseCacheHeaders(networkResponse));
        }
        catch (UnsupportedEncodingException ex) {
            return Response.error(new ParseError(ex));
        }
        catch (JSONException ex2) {
            return Response.error(new ParseError((Throwable)ex2));
        }
    }
}
