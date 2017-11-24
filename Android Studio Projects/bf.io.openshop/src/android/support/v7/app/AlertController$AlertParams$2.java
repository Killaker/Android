package android.support.v7.app;

import android.content.*;
import android.database.*;
import android.widget.*;
import android.view.*;

class AlertController$AlertParams$2 extends CursorAdapter {
    private final int mIsCheckedIndex;
    private final int mLabelIndex;
    final /* synthetic */ AlertController val$dialog;
    final /* synthetic */ ListView val$listView;
    
    {
        final Cursor cursor2 = this.getCursor();
        this.mLabelIndex = cursor2.getColumnIndexOrThrow(AlertParams.this.mLabelColumn);
        this.mIsCheckedIndex = cursor2.getColumnIndexOrThrow(AlertParams.this.mIsCheckedColumn);
    }
    
    public void bindView(final View view, final Context context, final Cursor cursor) {
        boolean b = true;
        ((CheckedTextView)view.findViewById(16908308)).setText((CharSequence)cursor.getString(this.mLabelIndex));
        final ListView val$listView = this.val$listView;
        final int position = cursor.getPosition();
        if (cursor.getInt(this.mIsCheckedIndex) != (b ? 1 : 0)) {
            b = false;
        }
        val$listView.setItemChecked(position, b);
    }
    
    public View newView(final Context context, final Cursor cursor, final ViewGroup viewGroup) {
        return AlertParams.this.mInflater.inflate(AlertController.access$1200(this.val$dialog), viewGroup, false);
    }
}