package com.android.volley.toolbox;

import com.android.volley.*;
import java.io.*;
import org.json.*;

public class JsonArrayRequest extends JsonRequest<JSONArray>
{
    public JsonArrayRequest(final int n, final String s, final JSONArray jsonArray, final Response.Listener<JSONArray> listener, final Response.ErrorListener errorListener) {
        String string;
        if (jsonArray == null) {
            string = null;
        }
        else {
            string = jsonArray.toString();
        }
        super(n, s, string, listener, errorListener);
    }
    
    public JsonArrayRequest(final String s, final Response.Listener<JSONArray> listener, final Response.ErrorListener errorListener) {
        super(0, s, null, listener, errorListener);
    }
    
    @Override
    protected Response<JSONArray> parseNetworkResponse(final NetworkResponse networkResponse) {
        try {
            return Response.success(new JSONArray(new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers, "utf-8"))), HttpHeaderParser.parseCacheHeaders(networkResponse));
        }
        catch (UnsupportedEncodingException ex) {
            return Response.error(new ParseError(ex));
        }
        catch (JSONException ex2) {
            return Response.error(new ParseError((Throwable)ex2));
        }
    }
}
