package bolts;

import java.util.concurrent.*;

private static class ImmediateExecutor implements Executor
{
    private static final int MAX_DEPTH = 15;
    private ThreadLocal<Integer> executionDepth;
    
    private ImmediateExecutor() {
        this.executionDepth = new ThreadLocal<Integer>();
    }
    
    private int decrementDepth() {
        Integer value = this.executionDepth.get();
        if (value == null) {
            value = 0;
        }
        final int n = -1 + value;
        if (n == 0) {
            this.executionDepth.remove();
            return n;
        }
        this.executionDepth.set(n);
        return n;
    }
    
    private int incrementDepth() {
        Integer value = this.executionDepth.get();
        if (value == null) {
            value = 0;
        }
        final int n = 1 + value;
        this.executionDepth.set(n);
        return n;
    }
    
    @Override
    public void execute(final Runnable runnable) {
        Label_0021: {
            if (this.incrementDepth() > 15) {
                break Label_0021;
            }
            try {
                runnable.run();
                return;
                BoltsExecutors.background().execute(runnable);
            }
            finally {
                this.decrementDepth();
            }
        }
    }
}
