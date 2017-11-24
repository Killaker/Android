package okhttp3.internal.http;

import okhttp3.*;
import okio.*;

static final class HttpEngine$1 extends ResponseBody {
    @Override
    public long contentLength() {
        return 0L;
    }
    
    @Override
    public MediaType contentType() {
        return null;
    }
    
    @Override
    public BufferedSource source() {
        return new Buffer();
    }
}