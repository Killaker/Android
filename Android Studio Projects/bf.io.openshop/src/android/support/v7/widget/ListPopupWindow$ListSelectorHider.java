package android.support.v7.widget;

private class ListSelectorHider implements Runnable
{
    @Override
    public void run() {
        ListPopupWindow.this.clearListSelection();
    }
}
