package android.support.v4.widget;

import android.database.*;

private class MyDataSetObserver extends DataSetObserver
{
    public void onChanged() {
        CursorAdapter.this.mDataValid = true;
        CursorAdapter.this.notifyDataSetChanged();
    }
    
    public void onInvalidated() {
        CursorAdapter.this.mDataValid = false;
        CursorAdapter.this.notifyDataSetInvalidated();
    }
}
