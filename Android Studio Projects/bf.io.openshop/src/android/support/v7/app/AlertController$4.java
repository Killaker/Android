package android.support.v7.app;

import android.view.*;
import android.widget.*;

class AlertController$4 implements AbsListView$OnScrollListener {
    final /* synthetic */ View val$bottom;
    final /* synthetic */ View val$top;
    
    public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
        AlertController.access$800((View)absListView, this.val$top, this.val$bottom);
    }
    
    public void onScrollStateChanged(final AbsListView absListView, final int n) {
    }
}