package android.support.v4.widget;

class SearchViewCompat$SearchViewCompatHoneycombImpl$1 implements OnQueryTextListenerCompatBridge {
    final /* synthetic */ OnQueryTextListenerCompat val$listener;
    
    @Override
    public boolean onQueryTextChange(final String s) {
        return this.val$listener.onQueryTextChange(s);
    }
    
    @Override
    public boolean onQueryTextSubmit(final String s) {
        return this.val$listener.onQueryTextSubmit(s);
    }
}