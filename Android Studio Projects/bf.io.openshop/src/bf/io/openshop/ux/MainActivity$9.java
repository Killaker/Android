package bf.io.openshop.ux;

import android.support.v7.widget.*;
import android.view.*;
import android.database.*;

class MainActivity$9 implements OnSuggestionListener {
    final /* synthetic */ MenuItem val$searchItem;
    final /* synthetic */ SearchView val$searchView;
    
    @Override
    public boolean onSuggestionClick(final int n) {
        MainActivity.access$400(MainActivity.this, ((MatrixCursor)MainActivity.access$500(MainActivity.this).getItem(n)).getString(1));
        this.val$searchView.setQuery("", false);
        this.val$searchView.setIconified(true);
        this.val$searchItem.collapseActionView();
        return true;
    }
    
    @Override
    public boolean onSuggestionSelect(final int n) {
        return false;
    }
}