package okhttp3.internal.framed;

import okhttp3.internal.*;
import java.io.*;

class FramedConnection$Reader$3 extends NamedRunnable {
    final /* synthetic */ Settings val$peerSettings;
    
    public void execute() {
        try {
            Reader.this.this$0.frameWriter.ackSettings(this.val$peerSettings);
        }
        catch (IOException ex) {}
    }
}