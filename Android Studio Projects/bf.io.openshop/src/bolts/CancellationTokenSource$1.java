package bolts;

import java.util.concurrent.*;

class CancellationTokenSource$1 implements Runnable {
    @Override
    public void run() {
        synchronized (CancellationTokenSource.access$000(CancellationTokenSource.this)) {
            CancellationTokenSource.access$102(CancellationTokenSource.this, null);
            // monitorexit(CancellationTokenSource.access$000(this.this$0))
            CancellationTokenSource.this.cancel();
        }
    }
}