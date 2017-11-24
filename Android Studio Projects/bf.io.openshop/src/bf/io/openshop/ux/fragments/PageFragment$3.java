package bf.io.openshop.ux.fragments;

class PageFragment$3 implements Runnable {
    @Override
    public void run() {
        if (PageFragment.access$100(PageFragment.this) != null) {
            PageFragment.access$100(PageFragment.this).cancel();
        }
    }
}