package okhttp3.internal.framed;

import okio.*;
import java.io.*;
import java.util.*;

static final class PushObserver$1 implements PushObserver {
    @Override
    public boolean onData(final int n, final BufferedSource bufferedSource, final int n2, final boolean b) throws IOException {
        bufferedSource.skip(n2);
        return true;
    }
    
    @Override
    public boolean onHeaders(final int n, final List<Header> list, final boolean b) {
        return true;
    }
    
    @Override
    public boolean onRequest(final int n, final List<Header> list) {
        return true;
    }
    
    @Override
    public void onReset(final int n, final ErrorCode errorCode) {
    }
}