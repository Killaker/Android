package okhttp3.internal.http;

import okhttp3.internal.*;
import java.net.*;
import java.io.*;
import java.util.*;
import okhttp3.internal.framed.*;
import okhttp3.*;
import java.util.concurrent.*;
import okio.*;

public final class Http2xStream implements HttpStream
{
    private static final ByteString CONNECTION;
    private static final ByteString ENCODING;
    private static final ByteString HOST;
    private static final List<ByteString> HTTP_2_SKIPPED_REQUEST_HEADERS;
    private static final List<ByteString> HTTP_2_SKIPPED_RESPONSE_HEADERS;
    private static final ByteString KEEP_ALIVE;
    private static final ByteString PROXY_CONNECTION;
    private static final List<ByteString> SPDY_3_SKIPPED_REQUEST_HEADERS;
    private static final List<ByteString> SPDY_3_SKIPPED_RESPONSE_HEADERS;
    private static final ByteString TE;
    private static final ByteString TRANSFER_ENCODING;
    private static final ByteString UPGRADE;
    private final FramedConnection framedConnection;
    private HttpEngine httpEngine;
    private FramedStream stream;
    private final StreamAllocation streamAllocation;
    
    static {
        CONNECTION = ByteString.encodeUtf8("connection");
        HOST = ByteString.encodeUtf8("host");
        KEEP_ALIVE = ByteString.encodeUtf8("keep-alive");
        PROXY_CONNECTION = ByteString.encodeUtf8("proxy-connection");
        TRANSFER_ENCODING = ByteString.encodeUtf8("transfer-encoding");
        TE = ByteString.encodeUtf8("te");
        ENCODING = ByteString.encodeUtf8("encoding");
        UPGRADE = ByteString.encodeUtf8("upgrade");
        SPDY_3_SKIPPED_REQUEST_HEADERS = Util.immutableList(Http2xStream.CONNECTION, Http2xStream.HOST, Http2xStream.KEEP_ALIVE, Http2xStream.PROXY_CONNECTION, Http2xStream.TRANSFER_ENCODING, Header.TARGET_METHOD, Header.TARGET_PATH, Header.TARGET_SCHEME, Header.TARGET_AUTHORITY, Header.TARGET_HOST, Header.VERSION);
        SPDY_3_SKIPPED_RESPONSE_HEADERS = Util.immutableList(Http2xStream.CONNECTION, Http2xStream.HOST, Http2xStream.KEEP_ALIVE, Http2xStream.PROXY_CONNECTION, Http2xStream.TRANSFER_ENCODING);
        HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableList(Http2xStream.CONNECTION, Http2xStream.HOST, Http2xStream.KEEP_ALIVE, Http2xStream.PROXY_CONNECTION, Http2xStream.TE, Http2xStream.TRANSFER_ENCODING, Http2xStream.ENCODING, Http2xStream.UPGRADE, Header.TARGET_METHOD, Header.TARGET_PATH, Header.TARGET_SCHEME, Header.TARGET_AUTHORITY, Header.TARGET_HOST, Header.VERSION);
        HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableList(Http2xStream.CONNECTION, Http2xStream.HOST, Http2xStream.KEEP_ALIVE, Http2xStream.PROXY_CONNECTION, Http2xStream.TE, Http2xStream.TRANSFER_ENCODING, Http2xStream.ENCODING, Http2xStream.UPGRADE);
    }
    
    public Http2xStream(final StreamAllocation streamAllocation, final FramedConnection framedConnection) {
        this.streamAllocation = streamAllocation;
        this.framedConnection = framedConnection;
    }
    
    public static List<Header> http2HeadersList(final Request request) {
        final Headers headers = request.headers();
        final ArrayList list = new ArrayList<Header>(4 + headers.size());
        list.add(new Header(Header.TARGET_METHOD, request.method()));
        list.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(request.url())));
        list.add(new Header(Header.TARGET_AUTHORITY, Util.hostHeader(request.url())));
        list.add(new Header(Header.TARGET_SCHEME, request.url().scheme()));
        for (int i = 0; i < headers.size(); ++i) {
            final ByteString encodeUtf8 = ByteString.encodeUtf8(headers.name(i).toLowerCase(Locale.US));
            if (!Http2xStream.HTTP_2_SKIPPED_REQUEST_HEADERS.contains(encodeUtf8)) {
                list.add(new Header(encodeUtf8, headers.value(i)));
            }
        }
        return (List<Header>)list;
    }
    
    private static String joinOnNull(final String s, final String s2) {
        return s + '\0' + s2;
    }
    
    public static Response.Builder readHttp2HeadersList(final List<Header> list) throws IOException {
        String s = null;
        final Headers.Builder builder = new Headers.Builder();
        for (int i = 0; i < list.size(); ++i) {
            final ByteString name = list.get(i).name;
            final String utf8 = list.get(i).value.utf8();
            if (name.equals(Header.RESPONSE_STATUS)) {
                s = utf8;
            }
            else if (!Http2xStream.HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(name)) {
                builder.add(name.utf8(), utf8);
            }
        }
        if (s == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        final StatusLine parse = StatusLine.parse("HTTP/1.1 " + s);
        return new Response.Builder().protocol(Protocol.HTTP_2).code(parse.code).message(parse.message).headers(builder.build());
    }
    
    public static Response.Builder readSpdy3HeadersList(final List<Header> list) throws IOException {
        String s = null;
        String s2 = "HTTP/1.1";
        final Headers.Builder builder = new Headers.Builder();
        for (int i = 0; i < list.size(); ++i) {
            final ByteString name = list.get(i).name;
            final String utf8 = list.get(i).value.utf8();
            int n;
            for (int j = 0; j < utf8.length(); j = n + 1) {
                n = utf8.indexOf(0, j);
                if (n == -1) {
                    n = utf8.length();
                }
                final String substring = utf8.substring(j, n);
                if (name.equals(Header.RESPONSE_STATUS)) {
                    s = substring;
                }
                else if (name.equals(Header.VERSION)) {
                    s2 = substring;
                }
                else if (!Http2xStream.SPDY_3_SKIPPED_RESPONSE_HEADERS.contains(name)) {
                    builder.add(name.utf8(), substring);
                }
            }
        }
        if (s == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        final StatusLine parse = StatusLine.parse(s2 + " " + s);
        return new Response.Builder().protocol(Protocol.SPDY_3).code(parse.code).message(parse.message).headers(builder.build());
    }
    
    public static List<Header> spdy3HeadersList(final Request request) {
        final Headers headers = request.headers();
        final ArrayList list = new ArrayList<Object>(5 + headers.size());
        list.add(new Header(Header.TARGET_METHOD, request.method()));
        list.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(request.url())));
        list.add(new Header(Header.VERSION, "HTTP/1.1"));
        list.add(new Header(Header.TARGET_HOST, Util.hostHeader(request.url())));
        list.add(new Header(Header.TARGET_SCHEME, request.url().scheme()));
        final LinkedHashSet<ByteString> set = new LinkedHashSet<ByteString>();
        for (int i = 0; i < headers.size(); ++i) {
            final ByteString encodeUtf8 = ByteString.encodeUtf8(headers.name(i).toLowerCase(Locale.US));
            if (!Http2xStream.SPDY_3_SKIPPED_REQUEST_HEADERS.contains(encodeUtf8)) {
                final String value = headers.value(i);
                if (set.add(encodeUtf8)) {
                    list.add(new Header(encodeUtf8, value));
                }
                else {
                    for (int j = 0; j < list.size(); ++j) {
                        if (((Header)list.get(j)).name.equals(encodeUtf8)) {
                            list.set(j, new Header(encodeUtf8, joinOnNull(((Header)list.get(j)).value.utf8(), value)));
                            break;
                        }
                    }
                }
            }
        }
        return (List<Header>)list;
    }
    
    @Override
    public void cancel() {
        if (this.stream != null) {
            this.stream.closeLater(ErrorCode.CANCEL);
        }
    }
    
    @Override
    public Sink createRequestBody(final Request request, final long n) throws IOException {
        return this.stream.getSink();
    }
    
    @Override
    public void finishRequest() throws IOException {
        this.stream.getSink().close();
    }
    
    @Override
    public ResponseBody openResponseBody(final Response response) throws IOException {
        return new RealResponseBody(response.headers(), Okio.buffer(new StreamFinishingSource(this.stream.getSource())));
    }
    
    @Override
    public Response.Builder readResponseHeaders() throws IOException {
        if (this.framedConnection.getProtocol() == Protocol.HTTP_2) {
            return readHttp2HeadersList(this.stream.getResponseHeaders());
        }
        return readSpdy3HeadersList(this.stream.getResponseHeaders());
    }
    
    @Override
    public void setHttpEngine(final HttpEngine httpEngine) {
        this.httpEngine = httpEngine;
    }
    
    @Override
    public void writeRequestBody(final RetryableSink retryableSink) throws IOException {
        retryableSink.writeToSocket(this.stream.getSink());
    }
    
    @Override
    public void writeRequestHeaders(final Request request) throws IOException {
        if (this.stream != null) {
            return;
        }
        this.httpEngine.writingRequestHeaders();
        final boolean permitsRequestBody = this.httpEngine.permitsRequestBody(request);
        List<Header> list;
        if (this.framedConnection.getProtocol() == Protocol.HTTP_2) {
            list = http2HeadersList(request);
        }
        else {
            list = spdy3HeadersList(request);
        }
        this.stream = this.framedConnection.newStream(list, permitsRequestBody, true);
        this.stream.readTimeout().timeout(this.httpEngine.client.readTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.stream.writeTimeout().timeout(this.httpEngine.client.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
    }
    
    class StreamFinishingSource extends ForwardingSource
    {
        public StreamFinishingSource(final Source source) {
            super(source);
        }
        
        @Override
        public void close() throws IOException {
            Http2xStream.this.streamAllocation.streamFinished(false, Http2xStream.this);
            super.close();
        }
    }
}
