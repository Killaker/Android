package okhttp3;

class ConnectionPool$1 implements Runnable {
    @Override
    public void run() {
        while (true) {
            final long cleanup = ConnectionPool.this.cleanup(System.nanoTime());
            if (cleanup == -1L) {
                break;
            }
            if (cleanup <= 0L) {
                continue;
            }
            final long n = cleanup / 1000000L;
            final long n2 = cleanup - n * 1000000L;
            final ConnectionPool this$0 = ConnectionPool.this;
            // monitorenter(this$0)
            while (true) {
                try {
                    try {
                        ConnectionPool.this.wait(n, (int)n2);
                    }
                    finally {
                    }
                    // monitorexit(this$0)
                }
                catch (InterruptedException ex) {
                    continue;
                }
                break;
            }
        }
    }
}