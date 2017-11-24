package android.support.v4.content;

import android.database.*;
import android.os.*;

public final class ForceLoadContentObserver extends ContentObserver
{
    public ForceLoadContentObserver() {
        super(new Handler());
    }
    
    public boolean deliverSelfNotifications() {
        return true;
    }
    
    public void onChange(final boolean b) {
        Loader.this.onContentChanged();
    }
}
