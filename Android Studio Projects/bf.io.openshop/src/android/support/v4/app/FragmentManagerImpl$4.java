package android.support.v4.app;

class FragmentManagerImpl$4 implements Runnable {
    final /* synthetic */ int val$flags;
    final /* synthetic */ int val$id;
    
    @Override
    public void run() {
        FragmentManagerImpl.this.popBackStackState(FragmentManagerImpl.this.mHost.getHandler(), null, this.val$id, this.val$flags);
    }
}