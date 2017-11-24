package android.support.v7.widget;

import android.support.v7.view.*;
import android.view.*;

class ActionBarContextView$1 implements View$OnClickListener {
    final /* synthetic */ ActionMode val$mode;
    
    public void onClick(final View view) {
        this.val$mode.finish();
    }
}