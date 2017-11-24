package android.support.v4.widget;

class ContentLoadingProgressBar$2 implements Runnable {
    @Override
    public void run() {
        ContentLoadingProgressBar.access$202(ContentLoadingProgressBar.this, false);
        if (!ContentLoadingProgressBar.access$300(ContentLoadingProgressBar.this)) {
            ContentLoadingProgressBar.access$102(ContentLoadingProgressBar.this, System.currentTimeMillis());
            ContentLoadingProgressBar.this.setVisibility(0);
        }
    }
}