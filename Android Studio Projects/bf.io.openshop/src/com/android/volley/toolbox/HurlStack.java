package com.android.volley.toolbox;

import com.android.volley.*;
import org.apache.http.entity.*;
import java.io.*;
import java.net.*;
import javax.net.ssl.*;
import org.apache.http.message.*;
import org.apache.http.*;
import java.util.*;

public class HurlStack implements HttpStack
{
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private final SSLSocketFactory mSslSocketFactory;
    private final UrlRewriter mUrlRewriter;
    
    public HurlStack() {
        this(null);
    }
    
    public HurlStack(final UrlRewriter urlRewriter) {
        this(urlRewriter, null);
    }
    
    public HurlStack(final UrlRewriter mUrlRewriter, final SSLSocketFactory mSslSocketFactory) {
        this.mUrlRewriter = mUrlRewriter;
        this.mSslSocketFactory = mSslSocketFactory;
    }
    
    private static void addBodyIfExists(final HttpURLConnection httpURLConnection, final Request<?> request) throws IOException, AuthFailureError {
        final byte[] body = request.getBody();
        if (body != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", request.getBodyContentType());
            final DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(body);
            dataOutputStream.close();
        }
    }
    
    private static HttpEntity entityFromConnection(final HttpURLConnection httpURLConnection) {
        final BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        while (true) {
            try {
                final InputStream content = httpURLConnection.getInputStream();
                basicHttpEntity.setContent(content);
                basicHttpEntity.setContentLength(httpURLConnection.getContentLength());
                basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
                basicHttpEntity.setContentType(httpURLConnection.getContentType());
                return basicHttpEntity;
            }
            catch (IOException ex) {
                final InputStream content = httpURLConnection.getErrorStream();
                continue;
            }
            break;
        }
    }
    
    private static boolean hasResponseBody(final int n, final int n2) {
        return n != 4 && (100 > n2 || n2 >= 200) && n2 != 204 && n2 != 304;
    }
    
    private HttpURLConnection openConnection(final URL url, final Request<?> request) throws IOException {
        final HttpURLConnection connection = this.createConnection(url);
        final int timeoutMs = request.getTimeoutMs();
        connection.setConnectTimeout(timeoutMs);
        connection.setReadTimeout(timeoutMs);
        connection.setUseCaches(false);
        connection.setDoInput(true);
        if ("https".equals(url.getProtocol()) && this.mSslSocketFactory != null) {
            ((HttpsURLConnection)connection).setSSLSocketFactory(this.mSslSocketFactory);
        }
        return connection;
    }
    
    static void setConnectionParametersForRequest(final HttpURLConnection httpURLConnection, final Request<?> request) throws IOException, AuthFailureError {
        switch (request.getMethod()) {
            default: {
                throw new IllegalStateException("Unknown method type.");
            }
            case -1: {
                final byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.addRequestProperty("Content-Type", request.getPostBodyContentType());
                    final DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(postBody);
                    dataOutputStream.close();
                }
            }
            case 0: {
                httpURLConnection.setRequestMethod("GET");
            }
            case 3: {
                httpURLConnection.setRequestMethod("DELETE");
            }
            case 1: {
                httpURLConnection.setRequestMethod("POST");
                addBodyIfExists(httpURLConnection, request);
            }
            case 2: {
                httpURLConnection.setRequestMethod("PUT");
                addBodyIfExists(httpURLConnection, request);
            }
            case 4: {
                httpURLConnection.setRequestMethod("HEAD");
            }
            case 5: {
                httpURLConnection.setRequestMethod("OPTIONS");
            }
            case 6: {
                httpURLConnection.setRequestMethod("TRACE");
            }
            case 7: {
                httpURLConnection.setRequestMethod("PATCH");
                addBodyIfExists(httpURLConnection, request);
            }
        }
    }
    
    protected HttpURLConnection createConnection(final URL url) throws IOException {
        return (HttpURLConnection)url.openConnection();
    }
    
    @Override
    public HttpResponse performRequest(final Request<?> request, final Map<String, String> map) throws IOException, AuthFailureError {
        String url = request.getUrl();
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.putAll(request.getHeaders());
        hashMap.putAll(map);
        if (this.mUrlRewriter != null) {
            final String rewriteUrl = this.mUrlRewriter.rewriteUrl(url);
            if (rewriteUrl == null) {
                throw new IOException("URL blocked by rewriter: " + url);
            }
            url = rewriteUrl;
        }
        final HttpURLConnection openConnection = this.openConnection(new URL(url), request);
        for (final String s : hashMap.keySet()) {
            openConnection.addRequestProperty(s, hashMap.get(s));
        }
        setConnectionParametersForRequest(openConnection, request);
        final ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (openConnection.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        final BasicStatusLine basicStatusLine = new BasicStatusLine(protocolVersion, openConnection.getResponseCode(), openConnection.getResponseMessage());
        final BasicHttpResponse basicHttpResponse = new BasicHttpResponse(basicStatusLine);
        if (hasResponseBody(request.getMethod(), basicStatusLine.getStatusCode())) {
            basicHttpResponse.setEntity(entityFromConnection(openConnection));
        }
        for (final Map.Entry<String, List<String>> entry : openConnection.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader(entry.getKey(), entry.getValue().get(0)));
            }
        }
        return basicHttpResponse;
    }
    
    public interface UrlRewriter
    {
        String rewriteUrl(final String p0);
    }
}
