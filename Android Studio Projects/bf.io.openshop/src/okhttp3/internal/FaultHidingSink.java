package okhttp3.internal;

import java.io.*;
import okio.*;

class FaultHidingSink extends ForwardingSink
{
    private boolean hasErrors;
    
    public FaultHidingSink(final Sink sink) {
        super(sink);
    }
    
    @Override
    public void close() throws IOException {
        if (this.hasErrors) {
            return;
        }
        try {
            super.close();
        }
        catch (IOException ex) {
            this.hasErrors = true;
            this.onException(ex);
        }
    }
    
    @Override
    public void flush() throws IOException {
        if (this.hasErrors) {
            return;
        }
        try {
            super.flush();
        }
        catch (IOException ex) {
            this.hasErrors = true;
            this.onException(ex);
        }
    }
    
    protected void onException(final IOException ex) {
    }
    
    @Override
    public void write(final Buffer buffer, final long n) throws IOException {
        if (this.hasErrors) {
            buffer.skip(n);
            return;
        }
        try {
            super.write(buffer, n);
        }
        catch (IOException ex) {
            this.hasErrors = true;
            this.onException(ex);
        }
    }
}
