package okio;

private static final class Watchdog extends Thread
{
    public Watchdog() {
        super("Okio Watchdog");
        this.setDaemon(true);
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                while (true) {
                    final AsyncTimeout access$000 = AsyncTimeout.access$000();
                    if (access$000 != null) {
                        access$000.timedOut();
                    }
                }
            }
            catch (InterruptedException ex) {
                continue;
            }
            break;
        }
    }
}
