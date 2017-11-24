package android.support.v4.app;

class FragmentManagerImpl$3 implements Runnable {
    final /* synthetic */ int val$flags;
    final /* synthetic */ String val$name;
    
    @Override
    public void run() {
        FragmentManagerImpl.this.popBackStackState(FragmentManagerImpl.this.mHost.getHandler(), this.val$name, -1, this.val$flags);
    }
}