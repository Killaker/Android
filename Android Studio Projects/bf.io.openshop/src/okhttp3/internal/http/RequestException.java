package okhttp3.internal.http;

import java.io.*;

public final class RequestException extends Exception
{
    public RequestException(final IOException ex) {
        super(ex);
    }
    
    @Override
    public IOException getCause() {
        return (IOException)super.getCause();
    }
}
