package android.support.v7.widget;

class RecyclerView$2 implements Runnable {
    @Override
    public void run() {
        if (RecyclerView.this.mItemAnimator != null) {
            RecyclerView.this.mItemAnimator.runPendingAnimations();
        }
        RecyclerView.access$602(RecyclerView.this, false);
    }
}