package okhttp3.internal.framed;

import okhttp3.internal.*;
import java.util.*;
import java.io.*;

class FramedConnection$4 extends NamedRunnable {
    final /* synthetic */ List val$requestHeaders;
    final /* synthetic */ int val$streamId;
    
    public void execute() {
        if (FramedConnection.access$2700(FramedConnection.this).onRequest(this.val$streamId, this.val$requestHeaders)) {
            try {
                FramedConnection.this.frameWriter.rstStream(this.val$streamId, ErrorCode.CANCEL);
                synchronized (FramedConnection.this) {
                    FramedConnection.access$2800(FramedConnection.this).remove(this.val$streamId);
                }
            }
            catch (IOException ex) {}
        }
    }
}