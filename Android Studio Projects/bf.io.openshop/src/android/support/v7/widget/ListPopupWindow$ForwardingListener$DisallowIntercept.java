package android.support.v7.widget;

private class DisallowIntercept implements Runnable
{
    @Override
    public void run() {
        ForwardingListener.access$900(ForwardingListener.this).getParent().requestDisallowInterceptTouchEvent(true);
    }
}
