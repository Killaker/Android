package okhttp3.internal.http;

import okio.*;
import java.io.*;
import okhttp3.*;

public interface HttpStream
{
    public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;
    
    void cancel();
    
    Sink createRequestBody(final Request p0, final long p1) throws IOException;
    
    void finishRequest() throws IOException;
    
    ResponseBody openResponseBody(final Response p0) throws IOException;
    
    Response.Builder readResponseHeaders() throws IOException;
    
    void setHttpEngine(final HttpEngine p0);
    
    void writeRequestBody(final RetryableSink p0) throws IOException;
    
    void writeRequestHeaders(final Request p0) throws IOException;
}
