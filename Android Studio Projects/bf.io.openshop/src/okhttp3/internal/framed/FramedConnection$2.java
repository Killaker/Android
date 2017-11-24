package okhttp3.internal.framed;

import okhttp3.internal.*;
import java.io.*;

class FramedConnection$2 extends NamedRunnable {
    final /* synthetic */ int val$streamId;
    final /* synthetic */ long val$unacknowledgedBytesRead;
    
    public void execute() {
        try {
            FramedConnection.this.frameWriter.windowUpdate(this.val$streamId, this.val$unacknowledgedBytesRead);
        }
        catch (IOException ex) {}
    }
}