package okio;

import java.io.*;

class AsyncTimeout$1 implements Sink {
    final /* synthetic */ Sink val$sink;
    
    @Override
    public void close() throws IOException {
        AsyncTimeout.this.enter();
        try {
            this.val$sink.close();
            AsyncTimeout.this.exit(true);
        }
        catch (IOException ex) {
            throw AsyncTimeout.this.exit(ex);
        }
        finally {
            AsyncTimeout.this.exit(false);
        }
    }
    
    @Override
    public void flush() throws IOException {
        AsyncTimeout.this.enter();
        try {
            this.val$sink.flush();
            AsyncTimeout.this.exit(true);
        }
        catch (IOException ex) {
            throw AsyncTimeout.this.exit(ex);
        }
        finally {
            AsyncTimeout.this.exit(false);
        }
    }
    
    @Override
    public Timeout timeout() {
        return AsyncTimeout.this;
    }
    
    @Override
    public String toString() {
        return "AsyncTimeout.sink(" + this.val$sink + ")";
    }
    
    @Override
    public void write(final Buffer buffer, final long n) throws IOException {
        AsyncTimeout.this.enter();
        try {
            this.val$sink.write(buffer, n);
            AsyncTimeout.this.exit(true);
        }
        catch (IOException ex) {
            throw AsyncTimeout.this.exit(ex);
        }
        finally {
            AsyncTimeout.this.exit(false);
        }
    }
}