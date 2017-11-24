package okhttp3.internal.framed;

import okio.*;
import java.io.*;
import java.net.*;

class StreamTimeout extends AsyncTimeout
{
    public void exitAndThrowIfTimedOut() throws IOException {
        if (this.exit()) {
            throw this.newTimeoutException(null);
        }
    }
    
    @Override
    protected IOException newTimeoutException(final IOException ex) {
        final SocketTimeoutException ex2 = new SocketTimeoutException("timeout");
        if (ex != null) {
            ex2.initCause(ex);
        }
        return ex2;
    }
    
    @Override
    protected void timedOut() {
        FramedStream.this.closeLater(ErrorCode.CANCEL);
    }
}
