package okhttp3.internal.framed;

import java.io.*;

static final class FramedConnection$Listener$1 extends Listener {
    @Override
    public void onStream(final FramedStream framedStream) throws IOException {
        framedStream.close(ErrorCode.REFUSED_STREAM);
    }
}