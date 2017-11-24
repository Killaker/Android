package okhttp3;

import okio.*;
import java.io.*;

static final class RequestBody$2 extends RequestBody {
    final /* synthetic */ int val$byteCount;
    final /* synthetic */ byte[] val$content;
    final /* synthetic */ MediaType val$contentType;
    final /* synthetic */ int val$offset;
    
    @Override
    public long contentLength() {
        return this.val$byteCount;
    }
    
    @Override
    public MediaType contentType() {
        return this.val$contentType;
    }
    
    @Override
    public void writeTo(final BufferedSink bufferedSink) throws IOException {
        bufferedSink.write(this.val$content, this.val$offset, this.val$byteCount);
    }
}