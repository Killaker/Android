package okhttp3;

import java.io.*;
import okio.*;

static final class RequestBody$1 extends RequestBody {
    final /* synthetic */ ByteString val$content;
    final /* synthetic */ MediaType val$contentType;
    
    @Override
    public long contentLength() throws IOException {
        return this.val$content.size();
    }
    
    @Override
    public MediaType contentType() {
        return this.val$contentType;
    }
    
    @Override
    public void writeTo(final BufferedSink bufferedSink) throws IOException {
        bufferedSink.write(this.val$content);
    }
}