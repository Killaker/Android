package android.support.v4.widget;

import android.database.*;
import android.os.*;

private class ChangeObserver extends ContentObserver
{
    public ChangeObserver() {
        super(new Handler());
    }
    
    public boolean deliverSelfNotifications() {
        return true;
    }
    
    public void onChange(final boolean b) {
        CursorAdapter.this.onContentChanged();
    }
}
