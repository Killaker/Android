package android.support.v4.app;

class FragmentManagerImpl$2 implements Runnable {
    @Override
    public void run() {
        FragmentManagerImpl.this.popBackStackState(FragmentManagerImpl.this.mHost.getHandler(), null, -1, 0);
    }
}