package okhttp3;

import okhttp3.internal.*;
import okio.*;
import java.io.*;

static final class RequestBody$3 extends RequestBody {
    final /* synthetic */ MediaType val$contentType;
    final /* synthetic */ File val$file;
    
    @Override
    public long contentLength() {
        return this.val$file.length();
    }
    
    @Override
    public MediaType contentType() {
        return this.val$contentType;
    }
    
    @Override
    public void writeTo(final BufferedSink bufferedSink) throws IOException {
        Source source = null;
        try {
            source = Okio.source(this.val$file);
            bufferedSink.writeAll(source);
        }
        finally {
            Util.closeQuietly(source);
        }
    }
}