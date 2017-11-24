package okhttp3.internal.http;

import okio.*;
import java.io.*;

public interface CacheRequest
{
    void abort();
    
    Sink body() throws IOException;
}
