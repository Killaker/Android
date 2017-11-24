package okio;

import java.io.*;
import java.net.*;
import java.util.logging.*;

static final class Okio$3 extends AsyncTimeout {
    final /* synthetic */ Socket val$socket;
    
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
        try {
            this.val$socket.close();
        }
        catch (Exception ex) {
            Okio.access$000().log(Level.WARNING, "Failed to close timed out socket " + this.val$socket, ex);
        }
        catch (AssertionError assertionError) {
            if (assertionError.getCause() != null && assertionError.getMessage() != null && assertionError.getMessage().contains("getsockname failed")) {
                Okio.access$000().log(Level.WARNING, "Failed to close timed out socket " + this.val$socket, assertionError);
                return;
            }
            throw assertionError;
        }
    }
}