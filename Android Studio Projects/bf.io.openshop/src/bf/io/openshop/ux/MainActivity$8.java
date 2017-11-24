package bf.io.openshop.ux;

import android.support.v7.widget.*;
import android.view.*;
import timber.log.*;

class MainActivity$8 implements OnQueryTextListener {
    final /* synthetic */ MenuItem val$searchItem;
    final /* synthetic */ SearchView val$searchView;
    
    @Override
    public boolean onQueryTextChange(final String s) {
        Timber.d("Search query text changed to: " + s, new Object[0]);
        MainActivity.access$300(MainActivity.this, s, this.val$searchView);
        return false;
    }
    
    @Override
    public boolean onQueryTextSubmit(final String s) {
        MainActivity.access$400(MainActivity.this, s);
        this.val$searchView.setQuery("", false);
        this.val$searchView.setIconified(true);
        this.val$searchItem.collapseActionView();
        return true;
    }
}