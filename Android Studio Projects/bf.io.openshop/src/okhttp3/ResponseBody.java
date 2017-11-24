package okhttp3;

import java.nio.charset.*;
import okhttp3.internal.*;
import okio.*;
import java.io.*;

public abstract class ResponseBody implements Closeable
{
    private Reader reader;
    
    private Charset charset() {
        final MediaType contentType = this.contentType();
        if (contentType != null) {
            return contentType.charset(Util.UTF_8);
        }
        return Util.UTF_8;
    }
    
    public static ResponseBody create(final MediaType mediaType, final long n, final BufferedSource bufferedSource) {
        if (bufferedSource == null) {
            throw new NullPointerException("source == null");
        }
        return new ResponseBody() {
            @Override
            public long contentLength() {
                return n;
            }
            
            @Override
            public MediaType contentType() {
                return mediaType;
            }
            
            @Override
            public BufferedSource source() {
                return bufferedSource;
            }
        };
    }
    
    public static ResponseBody create(MediaType parse, final String s) {
        Charset charset = Util.UTF_8;
        if (parse != null) {
            charset = parse.charset();
            if (charset == null) {
                charset = Util.UTF_8;
                parse = MediaType.parse(parse + "; charset=utf-8");
            }
        }
        final Buffer writeString = new Buffer().writeString(s, charset);
        return create(parse, writeString.size(), writeString);
    }
    
    public static ResponseBody create(final MediaType mediaType, final byte[] array) {
        return create(mediaType, array.length, new Buffer().write(array));
    }
    
    public final InputStream byteStream() {
        return this.source().inputStream();
    }
    
    public final byte[] bytes() throws IOException {
        final long contentLength = this.contentLength();
        if (contentLength > 2147483647L) {
            throw new IOException("Cannot buffer entire body for content length: " + contentLength);
        }
        final BufferedSource source = this.source();
        byte[] byteArray;
        try {
            byteArray = source.readByteArray();
            Util.closeQuietly(source);
            if (contentLength != -1L && contentLength != byteArray.length) {
                throw new IOException("Content-Length and stream length disagree");
            }
        }
        finally {
            Util.closeQuietly(source);
        }
        return byteArray;
    }
    
    public final Reader charStream() {
        final Reader reader = this.reader;
        if (reader != null) {
            return reader;
        }
        return this.reader = new InputStreamReader(this.byteStream(), this.charset());
    }
    
    @Override
    public void close() {
        Util.closeQuietly(this.source());
    }
    
    public abstract long contentLength();
    
    public abstract MediaType contentType();
    
    public abstract BufferedSource source();
    
    public final String string() throws IOException {
        return new String(this.bytes(), this.charset().name());
    }
}
