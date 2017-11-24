package okhttp3;

import okhttp3.internal.*;
import java.io.*;
import okio.*;

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
