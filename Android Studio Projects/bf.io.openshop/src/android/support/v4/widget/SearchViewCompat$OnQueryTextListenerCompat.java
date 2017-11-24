package android.support.v4.widget;

public abstract static class OnQueryTextListenerCompat
{
    final Object mListener;
    
    public OnQueryTextListenerCompat() {
        this.mListener = SearchViewCompat.access$000().newOnQueryTextListener(this);
    }
    
    public boolean onQueryTextChange(final String s) {
        return false;
    }
    
    public boolean onQueryTextSubmit(final String s) {
        return false;
    }
}
