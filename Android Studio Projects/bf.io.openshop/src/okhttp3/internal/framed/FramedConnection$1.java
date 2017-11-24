package okhttp3.internal.framed;

import okhttp3.internal.*;
import java.io.*;

class FramedConnection$1 extends NamedRunnable {
    final /* synthetic */ ErrorCode val$errorCode;
    final /* synthetic */ int val$streamId;
    
    public void execute() {
        try {
            FramedConnection.this.writeSynReset(this.val$streamId, this.val$errorCode);
        }
        catch (IOException ex) {}
    }
}