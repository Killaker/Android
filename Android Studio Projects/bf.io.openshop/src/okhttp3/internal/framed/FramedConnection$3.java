package okhttp3.internal.framed;

import okhttp3.internal.*;
import java.io.*;

class FramedConnection$3 extends NamedRunnable {
    final /* synthetic */ int val$payload1;
    final /* synthetic */ int val$payload2;
    final /* synthetic */ Ping val$ping;
    final /* synthetic */ boolean val$reply;
    
    public void execute() {
        try {
            FramedConnection.access$900(FramedConnection.this, this.val$reply, this.val$payload1, this.val$payload2, this.val$ping);
        }
        catch (IOException ex) {}
    }
}