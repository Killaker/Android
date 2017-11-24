package okhttp3.internal.http;

import okio.*;
import java.io.*;

class StreamFinishingSource extends ForwardingSource
{
    public StreamFinishingSource(final Source source) {
        super(source);
    }
    
    @Override
    public void close() throws IOException {
        Http2xStream.access$000(Http2xStream.this).streamFinished(false, Http2xStream.this);
        super.close();
    }
}
