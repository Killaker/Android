package com.android.volley.toolbox;

import org.apache.http.client.methods.*;
import java.net.*;

public static final class HttpPatch extends HttpEntityEnclosingRequestBase
{
    public static final String METHOD_NAME = "PATCH";
    
    public HttpPatch() {
    }
    
    public HttpPatch(final String s) {
        this.setURI(URI.create(s));
    }
    
    public HttpPatch(final URI uri) {
        this.setURI(uri);
    }
    
    @Override
    public String getMethod() {
        return "PATCH";
    }
}
