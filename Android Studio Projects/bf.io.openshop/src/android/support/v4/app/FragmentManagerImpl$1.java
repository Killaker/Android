package android.support.v4.app;

class FragmentManagerImpl$1 implements Runnable {
    @Override
    public void run() {
        FragmentManagerImpl.this.execPendingActions();
    }
}