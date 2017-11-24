package okhttp3;

import okhttp3.internal.io.*;
import java.io.*;
import okhttp3.internal.*;
import okhttp3.internal.http.*;
import java.util.*;
import okio.*;
import java.security.cert.*;

public final class Cache implements Closeable, Flushable
{
    private static final int ENTRY_BODY = 1;
    private static final int ENTRY_COUNT = 2;
    private static final int ENTRY_METADATA = 0;
    private static final int VERSION = 201105;
    private final DiskLruCache cache;
    private int hitCount;
    final InternalCache internalCache;
    private int networkCount;
    private int requestCount;
    private int writeAbortCount;
    private int writeSuccessCount;
    
    public Cache(final File file, final long n) {
        this(file, n, FileSystem.SYSTEM);
    }
    
    Cache(final File file, final long n, final FileSystem fileSystem) {
        this.internalCache = new InternalCache() {
            @Override
            public Response get(final Request request) throws IOException {
                return Cache.this.get(request);
            }
            
            @Override
            public CacheRequest put(final Response response) throws IOException {
                return Cache.this.put(response);
            }
            
            @Override
            public void remove(final Request request) throws IOException {
                Cache.this.remove(request);
            }
            
            @Override
            public void trackConditionalCacheHit() {
                Cache.this.trackConditionalCacheHit();
            }
            
            @Override
            public void trackResponse(final CacheStrategy cacheStrategy) {
                Cache.this.trackResponse(cacheStrategy);
            }
            
            @Override
            public void update(final Response response, final Response response2) throws IOException {
                Cache.this.update(response, response2);
            }
        };
        this.cache = DiskLruCache.create(fileSystem, file, 201105, 2, n);
    }
    
    private void abortQuietly(final DiskLruCache.Editor editor) {
        if (editor == null) {
            return;
        }
        try {
            editor.abort();
        }
        catch (IOException ex) {}
    }
    
    private CacheRequest put(final Response response) throws IOException {
        final String method = response.request().method();
        Label_0031: {
            if (!HttpMethod.invalidatesCache(response.request().method())) {
                break Label_0031;
            }
            try {
                this.remove(response.request());
                Label_0029: {
                    return null;
                }
                while (true) {
                    final Entry entry = new Entry(response);
                    Object edit = null;
                    try {
                        edit = this.cache.edit(urlToKey(response.request()));
                        if (edit != null) {
                            entry.writeTo((DiskLruCache.Editor)edit);
                            return new CacheRequestImpl((DiskLruCache.Editor)edit);
                        }
                        return null;
                    }
                    catch (IOException ex) {
                        this.abortQuietly((DiskLruCache.Editor)edit);
                        return null;
                    }
                    continue;
                }
            }
            // iftrue(Label_0029:, !method.equals((Object)"GET") || OkHeaders.hasVaryAll(response))
            catch (IOException ex2) {
                return null;
            }
        }
    }
    
    private static int readInt(final BufferedSource bufferedSource) throws IOException {
        long decimalLong;
        try {
            decimalLong = bufferedSource.readDecimalLong();
            final String utf8LineStrict = bufferedSource.readUtf8LineStrict();
            if (decimalLong < 0L || decimalLong > 2147483647L || !utf8LineStrict.isEmpty()) {
                throw new IOException("expected an int but was \"" + decimalLong + utf8LineStrict + "\"");
            }
        }
        catch (NumberFormatException ex) {
            throw new IOException(ex.getMessage());
        }
        return (int)decimalLong;
    }
    
    private void remove(final Request request) throws IOException {
        this.cache.remove(urlToKey(request));
    }
    
    private void trackConditionalCacheHit() {
        synchronized (this) {
            ++this.hitCount;
        }
    }
    
    private void trackResponse(final CacheStrategy cacheStrategy) {
        synchronized (this) {
            ++this.requestCount;
            if (cacheStrategy.networkRequest != null) {
                ++this.networkCount;
            }
            else if (cacheStrategy.cacheResponse != null) {
                ++this.hitCount;
            }
        }
    }
    
    private void update(final Response response, final Response response2) {
        final Entry entry = new Entry(response2);
        final DiskLruCache.Snapshot access$500 = ((CacheResponseBody)response.body()).snapshot;
        DiskLruCache.Editor edit = null;
        try {
            edit = access$500.edit();
            if (edit != null) {
                entry.writeTo(edit);
                edit.commit();
            }
        }
        catch (IOException ex) {
            this.abortQuietly(edit);
        }
    }
    
    private static String urlToKey(final Request request) {
        return Util.md5Hex(request.url().toString());
    }
    
    @Override
    public void close() throws IOException {
        this.cache.close();
    }
    
    public void delete() throws IOException {
        this.cache.delete();
    }
    
    public File directory() {
        return this.cache.getDirectory();
    }
    
    public void evictAll() throws IOException {
        this.cache.evictAll();
    }
    
    @Override
    public void flush() throws IOException {
        this.cache.flush();
    }
    
    Response get(final Request request) {
        while (true) {
            final String urlToKey = urlToKey(request);
            DiskLruCache.Snapshot value;
            try {
                value = this.cache.get(urlToKey);
                if (value == null) {
                    return null;
                }
            }
            catch (IOException ex) {
                return null;
            }
            try {
                final Entry entry = new Entry(value.getSource(0));
                final Response response = entry.response(value);
                if (!entry.matches(request, response)) {
                    Util.closeQuietly(response.body());
                    return null;
                }
                return response;
            }
            catch (IOException ex2) {
                Util.closeQuietly(value);
                return null;
            }
        }
    }
    
    public int hitCount() {
        synchronized (this) {
            return this.hitCount;
        }
    }
    
    public void initialize() throws IOException {
        this.cache.initialize();
    }
    
    public boolean isClosed() {
        return this.cache.isClosed();
    }
    
    public long maxSize() {
        return this.cache.getMaxSize();
    }
    
    public int networkCount() {
        synchronized (this) {
            return this.networkCount;
        }
    }
    
    public int requestCount() {
        synchronized (this) {
            return this.requestCount;
        }
    }
    
    public long size() throws IOException {
        return this.cache.size();
    }
    
    public Iterator<String> urls() throws IOException {
        return new Iterator<String>() {
            boolean canRemove;
            final Iterator<DiskLruCache.Snapshot> delegate = Cache.this.cache.snapshots();
            String nextUrl;
            
            @Override
            public boolean hasNext() {
                if (this.nextUrl != null) {
                    return true;
                }
                this.canRemove = false;
                while (this.delegate.hasNext()) {
                    final DiskLruCache.Snapshot snapshot = this.delegate.next();
                    try {
                        this.nextUrl = Okio.buffer(snapshot.getSource(0)).readUtf8LineStrict();
                        return true;
                    }
                    catch (IOException ex) {
                        continue;
                    }
                    finally {
                        snapshot.close();
                    }
                    break;
                }
                return false;
            }
            
            @Override
            public String next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                final String nextUrl = this.nextUrl;
                this.nextUrl = null;
                this.canRemove = true;
                return nextUrl;
            }
            
            @Override
            public void remove() {
                if (!this.canRemove) {
                    throw new IllegalStateException("remove() before next()");
                }
                this.delegate.remove();
            }
        };
    }
    
    public int writeAbortCount() {
        synchronized (this) {
            return this.writeAbortCount;
        }
    }
    
    public int writeSuccessCount() {
        synchronized (this) {
            return this.writeSuccessCount;
        }
    }
    
    private final class CacheRequestImpl implements CacheRequest
    {
        private Sink body;
        private Sink cacheOut;
        private boolean done;
        private final DiskLruCache.Editor editor;
        
        public CacheRequestImpl(final DiskLruCache.Editor editor) throws IOException {
            this.editor = editor;
            this.cacheOut = editor.newSink(1);
            this.body = new ForwardingSink(this.cacheOut) {
                @Override
                public void close() throws IOException {
                    synchronized (Cache.this) {
                        if (CacheRequestImpl.this.done) {
                            return;
                        }
                        CacheRequestImpl.this.done = true;
                        Cache.this.writeSuccessCount++;
                        // monitorexit(this.this$1.this$0)
                        super.close();
                        editor.commit();
                    }
                }
            };
        }
        
        @Override
        public void abort() {
            synchronized (Cache.this) {
                if (this.done) {
                    return;
                }
                this.done = true;
                Cache.this.writeAbortCount++;
                // monitorexit(this.this$0)
                Util.closeQuietly(this.cacheOut);
                try {
                    this.editor.abort();
                }
                catch (IOException ex) {}
            }
        }
        
        @Override
        public Sink body() {
            return this.body;
        }
    }
    
    private static class CacheResponseBody extends ResponseBody
    {
        private final BufferedSource bodySource;
        private final String contentLength;
        private final String contentType;
        private final DiskLruCache.Snapshot snapshot;
        
        public CacheResponseBody(final DiskLruCache.Snapshot snapshot, final String contentType, final String contentLength) {
            this.snapshot = snapshot;
            this.contentType = contentType;
            this.contentLength = contentLength;
            this.bodySource = Okio.buffer(new ForwardingSource(snapshot.getSource(1)) {
                @Override
                public void close() throws IOException {
                    snapshot.close();
                    super.close();
                }
            });
        }
        
        @Override
        public long contentLength() {
            long long1 = -1L;
            try {
                if (this.contentLength != null) {
                    long1 = Long.parseLong(this.contentLength);
                }
                return long1;
            }
            catch (NumberFormatException ex) {
                return long1;
            }
        }
        
        @Override
        public MediaType contentType() {
            if (this.contentType != null) {
                return MediaType.parse(this.contentType);
            }
            return null;
        }
        
        @Override
        public BufferedSource source() {
            return this.bodySource;
        }
    }
    
    private static final class Entry
    {
        private final int code;
        private final Handshake handshake;
        private final String message;
        private final Protocol protocol;
        private final String requestMethod;
        private final Headers responseHeaders;
        private final String url;
        private final Headers varyHeaders;
        
        public Entry(final Response response) {
            this.url = response.request().url().toString();
            this.varyHeaders = OkHeaders.varyHeaders(response);
            this.requestMethod = response.request().method();
            this.protocol = response.protocol();
            this.code = response.code();
            this.message = response.message();
            this.responseHeaders = response.headers();
            this.handshake = response.handshake();
        }
        
        public Entry(final Source source) throws IOException {
            while (true) {
                Label_0309: {
                    BufferedSource buffer;
                    try {
                        buffer = Okio.buffer(source);
                        this.url = buffer.readUtf8LineStrict();
                        this.requestMethod = buffer.readUtf8LineStrict();
                        final Headers.Builder builder = new Headers.Builder();
                        for (int access$1000 = readInt(buffer), i = 0; i < access$1000; ++i) {
                            builder.addLenient(buffer.readUtf8LineStrict());
                        }
                        this.varyHeaders = builder.build();
                        final StatusLine parse = StatusLine.parse(buffer.readUtf8LineStrict());
                        this.protocol = parse.protocol;
                        this.code = parse.code;
                        this.message = parse.message;
                        final Headers.Builder builder2 = new Headers.Builder();
                        for (int access$1001 = readInt(buffer), j = 0; j < access$1001; ++j) {
                            builder2.addLenient(buffer.readUtf8LineStrict());
                        }
                        this.responseHeaders = builder2.build();
                        if (!this.isHttps()) {
                            break Label_0309;
                        }
                        final String utf8LineStrict = buffer.readUtf8LineStrict();
                        if (utf8LineStrict.length() > 0) {
                            throw new IOException("expected \"\" but was \"" + utf8LineStrict + "\"");
                        }
                    }
                    finally {
                        source.close();
                    }
                    final CipherSuite forJavaName = CipherSuite.forJavaName(buffer.readUtf8LineStrict());
                    final List<Certificate> certificateList = this.readCertificateList(buffer);
                    final List<Certificate> certificateList2 = this.readCertificateList(buffer);
                    TlsVersion forJavaName2;
                    if (!buffer.exhausted()) {
                        forJavaName2 = TlsVersion.forJavaName(buffer.readUtf8LineStrict());
                    }
                    else {
                        forJavaName2 = null;
                    }
                    this.handshake = Handshake.get(forJavaName2, forJavaName, certificateList, certificateList2);
                    source.close();
                    return;
                }
                this.handshake = null;
                continue;
            }
        }
        
        private boolean isHttps() {
            return this.url.startsWith("https://");
        }
        
        private List<Certificate> readCertificateList(final BufferedSource bufferedSource) throws IOException {
            final int access$1000 = readInt(bufferedSource);
            List<Certificate> emptyList;
            if (access$1000 == -1) {
                emptyList = Collections.emptyList();
            }
            else {
                try {
                    final CertificateFactory instance = CertificateFactory.getInstance("X.509");
                    emptyList = new ArrayList<Certificate>(access$1000);
                    for (int i = 0; i < access$1000; ++i) {
                        final String utf8LineStrict = bufferedSource.readUtf8LineStrict();
                        final Buffer buffer = new Buffer();
                        buffer.write(ByteString.decodeBase64(utf8LineStrict));
                        emptyList.add(instance.generateCertificate(buffer.inputStream()));
                    }
                }
                catch (CertificateException ex) {
                    throw new IOException(ex.getMessage());
                }
            }
            return emptyList;
        }
        
        private void writeCertList(final BufferedSink bufferedSink, final List<Certificate> list) throws IOException {
            try {
                bufferedSink.writeDecimalLong(list.size());
                bufferedSink.writeByte(10);
                for (int i = 0; i < list.size(); ++i) {
                    bufferedSink.writeUtf8(ByteString.of(list.get(i).getEncoded()).base64());
                    bufferedSink.writeByte(10);
                }
            }
            catch (CertificateEncodingException ex) {
                throw new IOException(ex.getMessage());
            }
        }
        
        public boolean matches(final Request request, final Response response) {
            return this.url.equals(request.url().toString()) && this.requestMethod.equals(request.method()) && OkHeaders.varyMatches(response, this.varyHeaders, request);
        }
        
        public Response response(final DiskLruCache.Snapshot snapshot) {
            return new Response.Builder().request(new Request.Builder().url(this.url).method(this.requestMethod, null).headers(this.varyHeaders).build()).protocol(this.protocol).code(this.code).message(this.message).headers(this.responseHeaders).body(new CacheResponseBody(snapshot, this.responseHeaders.get("Content-Type"), this.responseHeaders.get("Content-Length"))).handshake(this.handshake).build();
        }
        
        public void writeTo(final DiskLruCache.Editor editor) throws IOException {
            final BufferedSink buffer = Okio.buffer(editor.newSink(0));
            buffer.writeUtf8(this.url);
            buffer.writeByte(10);
            buffer.writeUtf8(this.requestMethod);
            buffer.writeByte(10);
            buffer.writeDecimalLong(this.varyHeaders.size());
            buffer.writeByte(10);
            for (int i = 0; i < this.varyHeaders.size(); ++i) {
                buffer.writeUtf8(this.varyHeaders.name(i));
                buffer.writeUtf8(": ");
                buffer.writeUtf8(this.varyHeaders.value(i));
                buffer.writeByte(10);
            }
            buffer.writeUtf8(new StatusLine(this.protocol, this.code, this.message).toString());
            buffer.writeByte(10);
            buffer.writeDecimalLong(this.responseHeaders.size());
            buffer.writeByte(10);
            for (int j = 0; j < this.responseHeaders.size(); ++j) {
                buffer.writeUtf8(this.responseHeaders.name(j));
                buffer.writeUtf8(": ");
                buffer.writeUtf8(this.responseHeaders.value(j));
                buffer.writeByte(10);
            }
            if (this.isHttps()) {
                buffer.writeByte(10);
                buffer.writeUtf8(this.handshake.cipherSuite().javaName());
                buffer.writeByte(10);
                this.writeCertList(buffer, this.handshake.peerCertificates());
                this.writeCertList(buffer, this.handshake.localCertificates());
                if (this.handshake.tlsVersion() != null) {
                    buffer.writeUtf8(this.handshake.tlsVersion().javaName());
                    buffer.writeByte(10);
                }
            }
            buffer.close();
        }
    }
}
