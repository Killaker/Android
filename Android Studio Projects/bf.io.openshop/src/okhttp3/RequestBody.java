package okhttp3;

import okhttp3.internal.*;
import java.io.*;
import java.nio.charset.*;
import okio.*;

public abstract class RequestBody
{
    public static RequestBody create(final MediaType mediaType, final File file) {
        if (file == null) {
            throw new NullPointerException("content == null");
        }
        return new RequestBody() {
            @Override
            public long contentLength() {
                return file.length();
            }
            
            @Override
            public MediaType contentType() {
                return mediaType;
            }
            
            @Override
            public void writeTo(final BufferedSink bufferedSink) throws IOException {
                Source source = null;
                try {
                    source = Okio.source(file);
                    bufferedSink.writeAll(source);
                }
                finally {
                    Util.closeQuietly(source);
                }
            }
        };
    }
    
    public static RequestBody create(MediaType parse, final String s) {
        Charset charset = Util.UTF_8;
        if (parse != null) {
            charset = parse.charset();
            if (charset == null) {
                charset = Util.UTF_8;
                parse = MediaType.parse(parse + "; charset=utf-8");
            }
        }
        return create(parse, s.getBytes(charset));
    }
    
    public static RequestBody create(final MediaType mediaType, final ByteString byteString) {
        return new RequestBody() {
            @Override
            public long contentLength() throws IOException {
                return byteString.size();
            }
            
            @Override
            public MediaType contentType() {
                return mediaType;
            }
            
            @Override
            public void writeTo(final BufferedSink bufferedSink) throws IOException {
                bufferedSink.write(byteString);
            }
        };
    }
    
    public static RequestBody create(final MediaType mediaType, final byte[] array) {
        return create(mediaType, array, 0, array.length);
    }
    
    public static RequestBody create(final MediaType mediaType, final byte[] array, final int n, final int n2) {
        if (array == null) {
            throw new NullPointerException("content == null");
        }
        Util.checkOffsetAndCount(array.length, n, n2);
        return new RequestBody() {
            @Override
            public long contentLength() {
                return n2;
            }
            
            @Override
            public MediaType contentType() {
                return mediaType;
            }
            
            @Override
            public void writeTo(final BufferedSink bufferedSink) throws IOException {
                bufferedSink.write(array, n, n2);
            }
        };
    }
    
    public long contentLength() throws IOException {
        return -1L;
    }
    
    public abstract MediaType contentType();
    
    public abstract void writeTo(final BufferedSink p0) throws IOException;
}
