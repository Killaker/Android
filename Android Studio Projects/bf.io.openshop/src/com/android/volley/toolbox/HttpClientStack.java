package com.android.volley.toolbox;

import org.apache.http.client.*;
import org.apache.http.entity.*;
import org.apache.http.client.methods.*;
import com.android.volley.*;
import java.util.*;
import org.apache.http.message.*;
import java.io.*;
import org.apache.http.*;
import org.apache.http.params.*;
import java.net.*;

public class HttpClientStack implements HttpStack
{
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    protected final HttpClient mClient;
    
    public HttpClientStack(final HttpClient mClient) {
        this.mClient = mClient;
    }
    
    private static void addHeaders(final HttpUriRequest httpUriRequest, final Map<String, String> map) {
        for (final String s : map.keySet()) {
            httpUriRequest.setHeader(s, map.get(s));
        }
    }
    
    static HttpUriRequest createHttpRequest(final Request<?> request, final Map<String, String> map) throws AuthFailureError {
        switch (request.getMethod()) {
            default: {
                throw new IllegalStateException("Unknown request method.");
            }
            case -1: {
                final byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    final HttpPost httpPost = new HttpPost(request.getUrl());
                    httpPost.addHeader("Content-Type", request.getPostBodyContentType());
                    httpPost.setEntity(new ByteArrayEntity(postBody));
                    return httpPost;
                }
                return new HttpGet(request.getUrl());
            }
            case 0: {
                return new HttpGet(request.getUrl());
            }
            case 3: {
                return new HttpDelete(request.getUrl());
            }
            case 1: {
                final HttpPost httpPost2 = new HttpPost(request.getUrl());
                httpPost2.addHeader("Content-Type", request.getBodyContentType());
                setEntityIfNonEmptyBody(httpPost2, request);
                return httpPost2;
            }
            case 2: {
                final HttpPut httpPut = new HttpPut(request.getUrl());
                httpPut.addHeader("Content-Type", request.getBodyContentType());
                setEntityIfNonEmptyBody(httpPut, request);
                return httpPut;
            }
            case 4: {
                return new HttpHead(request.getUrl());
            }
            case 5: {
                return new HttpOptions(request.getUrl());
            }
            case 6: {
                return new HttpTrace(request.getUrl());
            }
            case 7: {
                final HttpPatch httpPatch = new HttpPatch(request.getUrl());
                httpPatch.addHeader("Content-Type", request.getBodyContentType());
                setEntityIfNonEmptyBody(httpPatch, request);
                return httpPatch;
            }
        }
    }
    
    private static List<NameValuePair> getPostParameterPairs(final Map<String, String> map) {
        final ArrayList list = new ArrayList(map.size());
        for (final String s : map.keySet()) {
            list.add(new BasicNameValuePair(s, map.get(s)));
        }
        return (List<NameValuePair>)list;
    }
    
    private static void setEntityIfNonEmptyBody(final HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, final Request<?> request) throws AuthFailureError {
        final byte[] body = request.getBody();
        if (body != null) {
            httpEntityEnclosingRequestBase.setEntity(new ByteArrayEntity(body));
        }
    }
    
    protected void onPrepareRequest(final HttpUriRequest httpUriRequest) throws IOException {
    }
    
    @Override
    public HttpResponse performRequest(final Request<?> request, final Map<String, String> map) throws IOException, AuthFailureError {
        final HttpUriRequest httpRequest = createHttpRequest(request, map);
        addHeaders(httpRequest, map);
        addHeaders(httpRequest, request.getHeaders());
        this.onPrepareRequest(httpRequest);
        final HttpParams params = httpRequest.getParams();
        final int timeoutMs = request.getTimeoutMs();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, timeoutMs);
        return this.mClient.execute(httpRequest);
    }
    
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
}
