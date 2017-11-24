package okhttp3.internal.framed;

import okio.*;
import java.io.*;

class NameValueBlockReader$1 extends ForwardingSource {
    @Override
    public long read(final Buffer buffer, final long n) throws IOException {
        if (NameValueBlockReader.access$000(NameValueBlockReader.this) == 0) {
            return -1L;
        }
        final long read = super.read(buffer, Math.min(n, NameValueBlockReader.access$000(NameValueBlockReader.this)));
        if (read == -1L) {
            return -1L;
        }
        NameValueBlockReader.access$022(NameValueBlockReader.this, read);
        return read;
    }
}