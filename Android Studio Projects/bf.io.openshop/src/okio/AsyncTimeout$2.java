package okio;

import java.io.*;

class AsyncTimeout$2 implements Source {
    final /* synthetic */ Source val$source;
    
    @Override
    public void close() throws IOException {
        try {
            this.val$source.close();
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
    public long read(final Buffer buffer, final long n) throws IOException {
        AsyncTimeout.this.enter();
        try {
            final long read = this.val$source.read(buffer, n);
            AsyncTimeout.this.exit(true);
            return read;
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
        return "AsyncTimeout.source(" + this.val$source + ")";
    }
}