package okhttp3.internal.framed;

import okhttp3.internal.*;
import java.util.*;
import java.io.*;

class FramedConnection$5 extends NamedRunnable {
    final /* synthetic */ boolean val$inFinished;
    final /* synthetic */ List val$requestHeaders;
    final /* synthetic */ int val$streamId;
    
    public void execute() {
        final boolean onHeaders = FramedConnection.access$2700(FramedConnection.this).onHeaders(this.val$streamId, this.val$requestHeaders, this.val$inFinished);
        while (true) {
            if (onHeaders) {
                try {
                    FramedConnection.this.frameWriter.rstStream(this.val$streamId, ErrorCode.CANCEL);
                    if (onHeaders || this.val$inFinished) {
                        synchronized (FramedConnection.this) {
                            FramedConnection.access$2800(FramedConnection.this).remove(this.val$streamId);
                        }
                    }
                }
                catch (IOException ex) {}
                return;
            }
            continue;
        }
    }
}