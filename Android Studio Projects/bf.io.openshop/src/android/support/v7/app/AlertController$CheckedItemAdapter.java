package android.support.v7.app;

import android.widget.*;
import android.content.*;

private static class CheckedItemAdapter extends ArrayAdapter<CharSequence>
{
    public CheckedItemAdapter(final Context context, final int n, final int n2, final CharSequence[] array) {
        super(context, n, n2, (Object[])array);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public boolean hasStableIds() {
        return true;
    }
}
