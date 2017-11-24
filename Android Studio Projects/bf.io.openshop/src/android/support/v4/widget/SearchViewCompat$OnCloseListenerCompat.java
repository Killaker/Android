package android.support.v4.widget;

public abstract static class OnCloseListenerCompat
{
    final Object mListener;
    
    public OnCloseListenerCompat() {
        this.mListener = SearchViewCompat.access$000().newOnCloseListener(this);
    }
    
    public boolean onClose() {
        return false;
    }
}
