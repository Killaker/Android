package okhttp3.internal.framed;

import okhttp3.internal.*;
import okio.*;
import java.io.*;

class FramedConnection$6 extends NamedRunnable {
    final /* synthetic */ Buffer val$buffer;
    final /* synthetic */ int val$byteCount;
    final /* synthetic */ boolean val$inFinished;
    final /* synthetic */ int val$streamId;
    
    public void execute() {
        try {
            final boolean onData = FramedConnection.access$2700(FramedConnection.this).onData(this.val$streamId, this.val$buffer, this.val$byteCount, this.val$inFinished);
            if (onData) {
                FramedConnection.this.frameWriter.rstStream(this.val$streamId, ErrorCode.CANCEL);
            }
            if (onData || this.val$inFinished) {
                synchronized (FramedConnection.this) {
                    FramedConnection.access$2800(FramedConnection.this).remove(this.val$streamId);
                }
            }
        }
        catch (IOException ex) {}
    }
}