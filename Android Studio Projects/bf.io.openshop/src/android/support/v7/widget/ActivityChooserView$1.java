package android.support.v7.widget;

import android.database.*;

class ActivityChooserView$1 extends DataSetObserver {
    public void onChanged() {
        super.onChanged();
        ActivityChooserView.access$000(ActivityChooserView.this).notifyDataSetChanged();
    }
    
    public void onInvalidated() {
        super.onInvalidated();
        ActivityChooserView.access$000(ActivityChooserView.this).notifyDataSetInvalidated();
    }
}