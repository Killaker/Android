package bf.io.openshop.ux.fragments;

class WishlistFragment$1$1 implements Runnable {
    final /* synthetic */ long val$responseId;
    
    @Override
    public void run() {
        Listener.this.val$requestListener.requestSuccess(this.val$responseId);
    }
}