package android.support.v7.widget;

class RecyclerView$1 implements Runnable {
    @Override
    public void run() {
        if (!RecyclerView.access$100(RecyclerView.this) || RecyclerView.this.isLayoutRequested()) {
            return;
        }
        if (RecyclerView.access$200(RecyclerView.this)) {
            RecyclerView.access$302(RecyclerView.this, true);
            return;
        }
        RecyclerView.access$400(RecyclerView.this);
    }
}