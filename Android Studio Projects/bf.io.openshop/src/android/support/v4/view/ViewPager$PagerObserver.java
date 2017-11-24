package android.support.v4.view;

import android.database.*;

private class PagerObserver extends DataSetObserver
{
    public void onChanged() {
        ViewPager.this.dataSetChanged();
    }
    
    public void onInvalidated() {
        ViewPager.this.dataSetChanged();
    }
}
