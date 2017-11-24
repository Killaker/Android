package okio;

import java.io.*;
import java.util.concurrent.*;

static final class Timeout$1 extends Timeout {
    @Override
    public Timeout deadlineNanoTime(final long n) {
        return this;
    }
    
    @Override
    public void throwIfReached() throws IOException {
    }
    
    @Override
    public Timeout timeout(final long n, final TimeUnit timeUnit) {
        return this;
    }
}