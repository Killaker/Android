package android.support.design.widget;

class Snackbar$3 implements SnackbarManager.Callback {
    @Override
    public void dismiss(final int n) {
        Snackbar.access$100().sendMessage(Snackbar.access$100().obtainMessage(1, n, 0, (Object)Snackbar.this));
    }
    
    @Override
    public void show() {
        Snackbar.access$100().sendMessage(Snackbar.access$100().obtainMessage(0, (Object)Snackbar.this));
    }
}