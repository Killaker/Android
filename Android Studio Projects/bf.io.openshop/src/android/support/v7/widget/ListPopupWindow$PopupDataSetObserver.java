package android.support.v7.widget;

import android.database.*;

private class PopupDataSetObserver extends DataSetObserver
{
    public void onChanged() {
        if (ListPopupWindow.this.isShowing()) {
            ListPopupWindow.this.show();
        }
    }
    
    public void onInvalidated() {
        ListPopupWindow.this.dismiss();
    }
}
