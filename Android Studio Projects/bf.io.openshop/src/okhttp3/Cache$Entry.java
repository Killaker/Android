package okhttp3;

import okhttp3.internal.http.*;
import java.io.*;
import java.util.*;
import okio.*;
import java.security.cert.*;
import okhttp3.internal.*;

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
                    for (int access$1000 = Cache.access$1000(buffer), i = 0; i < access$1000; ++i) {
                        builder.addLenient(buffer.readUtf8LineStrict());
                    }
                    this.varyHeaders = builder.build();
                    final StatusLine parse = StatusLine.parse(buffer.readUtf8LineStrict());
                    this.protocol = parse.protocol;
                    this.code = parse.code;
                    this.message = parse.message;
                    final Headers.Builder builder2 = new Headers.Builder();
                    for (int access$1001 = Cache.access$1000(buffer), j = 0; j < access$1001; ++j) {
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
        final int access$1000 = Cache.access$1000(bufferedSource);
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
