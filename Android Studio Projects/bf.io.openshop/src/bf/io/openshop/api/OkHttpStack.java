package bf.io.openshop.api;

import com.android.volley.toolbox.*;
import com.android.volley.*;
import org.apache.http.entity.*;
import java.io.*;
import java.util.concurrent.*;
import org.apache.http.message.*;
import org.apache.http.*;
import java.util.*;
import okhttp3.*;

public class OkHttpStack implements HttpStack
{
    private static RequestBody createRequestBody(final Request request) throws AuthFailureError {
        final byte[] body = request.getBody();
        if (body == null) {
            return null;
        }
        return RequestBody.create(MediaType.parse(request.getBodyContentType()), body);
    }
    
    private static HttpEntity entityFromOkHttpResponse(final Response response) throws IOException {
        final BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        final ResponseBody body = response.body();
        basicHttpEntity.setContent(body.byteStream());
        basicHttpEntity.setContentLength(body.contentLength());
        basicHttpEntity.setContentEncoding(response.header("Content-Encoding"));
        if (body.contentType() != null) {
            basicHttpEntity.setContentType(body.contentType().type());
        }
        return basicHttpEntity;
    }
    
    private static ProtocolVersion parseProtocol(final Protocol protocol) {
        switch (protocol) {
            default: {
                throw new IllegalAccessError("Unkwown protocol");
            }
            case HTTP_1_0: {
                return new ProtocolVersion("HTTP", 1, 0);
            }
            case HTTP_1_1: {
                return new ProtocolVersion("HTTP", 1, 1);
            }
            case SPDY_3: {
                return new ProtocolVersion("SPDY", 3, 1);
            }
            case HTTP_2: {
                return new ProtocolVersion("HTTP", 2, 0);
            }
        }
    }
    
    private static void setConnectionParametersForRequest(final okhttp3.Request.Builder builder, final Request<?> request) throws IOException, AuthFailureError {
        switch (request.getMethod()) {
            default: {
                throw new IllegalStateException("Unknown method type.");
            }
            case -1: {
                final byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    builder.post(RequestBody.create(MediaType.parse(request.getPostBodyContentType()), postBody));
                }
            }
            case 0: {
                builder.get();
            }
            case 3: {
                builder.delete();
            }
            case 1: {
                builder.post(createRequestBody(request));
            }
            case 2: {
                builder.put(createRequestBody(request));
            }
            case 4: {
                builder.head();
            }
            case 5: {
                builder.method("OPTIONS", null);
            }
            case 6: {
                builder.method("TRACE", null);
            }
            case 7: {
                builder.patch(createRequestBody(request));
            }
        }
    }
    
    @Override
    public HttpResponse performRequest(final Request<?> request, final Map<String, String> map) throws IOException, AuthFailureError {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        final int timeoutMs = request.getTimeoutMs();
        builder.connectTimeout(timeoutMs, TimeUnit.MILLISECONDS);
        builder.readTimeout(timeoutMs, TimeUnit.MILLISECONDS);
        builder.writeTimeout(timeoutMs, TimeUnit.MILLISECONDS);
        final okhttp3.Request.Builder builder2 = new okhttp3.Request.Builder();
        builder2.url(request.getUrl());
        final Map<String, String> headers = request.getHeaders();
        for (final String s : headers.keySet()) {
            builder2.addHeader(s, headers.get(s));
        }
        for (final String s2 : map.keySet()) {
            builder2.addHeader(s2, map.get(s2));
        }
        setConnectionParametersForRequest(builder2, request);
        final Response execute = builder.build().newCall(builder2.build()).execute();
        final BasicHttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(parseProtocol(execute.protocol()), execute.code(), execute.message()));
        basicHttpResponse.setEntity(entityFromOkHttpResponse(execute));
        final Headers headers2 = execute.headers();
        for (int i = 0; i < headers2.size(); ++i) {
            final String name = headers2.name(i);
            final String value = headers2.value(i);
            if (name != null) {
                basicHttpResponse.addHeader(new BasicHeader(name, value));
            }
        }
        return basicHttpResponse;
    }
}
