package okhttp3.internal.framed;

import okhttp3.internal.*;

class FramedConnection$7 extends NamedRunnable {
    final /* synthetic */ ErrorCode val$errorCode;
    final /* synthetic */ int val$streamId;
    
    public void execute() {
        FramedConnection.access$2700(FramedConnection.this).onReset(this.val$streamId, this.val$errorCode);
        synchronized (FramedConnection.this) {
            FramedConnection.access$2800(FramedConnection.this).remove(this.val$streamId);
        }
    }
}