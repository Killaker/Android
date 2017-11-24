package okhttp3.internal.http;

import okio.*;
import okhttp3.*;

public final class RealResponseBody extends ResponseBody
{
    private final Headers headers;
    private final BufferedSource source;
    
    public RealResponseBody(final Headers headers, final BufferedSource source) {
        this.headers = headers;
        this.source = source;
    }
    
    @Override
    public long contentLength() {
        return OkHeaders.contentLength(this.headers);
    }
    
    @Override
    public MediaType contentType() {
        final String value = this.headers.get("Content-Type");
        if (value != null) {
            return MediaType.parse(value);
        }
        return null;
    }
    
    @Override
    public BufferedSource source() {
        return this.source;
    }
}
