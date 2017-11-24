package okhttp3.internal.framed;

import java.io.*;

public abstract static class Listener
{
    public static final Listener REFUSE_INCOMING_STREAMS;
    
    static {
        REFUSE_INCOMING_STREAMS = new Listener() {
            @Override
            public void onStream(final FramedStream framedStream) throws IOException {
                framedStream.close(ErrorCode.REFUSED_STREAM);
            }
        };
    }
    
    public void onSettings(final FramedConnection framedConnection) {
    }
    
    public abstract void onStream(final FramedStream p0) throws IOException;
}
