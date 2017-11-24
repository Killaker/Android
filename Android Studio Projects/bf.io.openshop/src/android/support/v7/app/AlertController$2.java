package android.support.v7.app;

import android.support.v4.widget.*;
import android.view.*;

class AlertController$2 implements OnScrollChangeListener {
    final /* synthetic */ View val$bottom;
    final /* synthetic */ View val$top;
    
    @Override
    public void onScrollChange(final NestedScrollView nestedScrollView, final int n, final int n2, final int n3, final int n4) {
        AlertController.access$800((View)nestedScrollView, this.val$top, this.val$bottom);
    }
}