package okhttp3.internal.framed;

import okhttp3.internal.*;
import java.util.logging.*;
import java.io.*;

class FramedConnection$Reader$1 extends NamedRunnable {
    final /* synthetic */ FramedStream val$newStream;
    
    public void execute() {
        try {
            FramedConnection.access$2000(Reader.this.this$0).onStream(this.val$newStream);
        }
        catch (IOException ex) {
            Internal.logger.log(Level.INFO, "FramedConnection.Listener failure for " + FramedConnection.access$1100(Reader.this.this$0), ex);
            try {
                this.val$newStream.close(ErrorCode.PROTOCOL_ERROR);
            }
            catch (IOException ex2) {}
        }
    }
}