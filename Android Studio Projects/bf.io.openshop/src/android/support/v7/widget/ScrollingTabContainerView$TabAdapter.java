package android.support.v7.widget;

import android.widget.*;
import android.view.*;
import android.support.v7.app.*;

private class TabAdapter extends BaseAdapter
{
    public int getCount() {
        return ScrollingTabContainerView.access$200(ScrollingTabContainerView.this).getChildCount();
    }
    
    public Object getItem(final int n) {
        return ((TabView)ScrollingTabContainerView.access$200(ScrollingTabContainerView.this).getChildAt(n)).getTab();
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        if (view == null) {
            return (View)ScrollingTabContainerView.access$300(ScrollingTabContainerView.this, (ActionBar.Tab)this.getItem(n), true);
        }
        ((TabView)view).bindTab((ActionBar.Tab)this.getItem(n));
        return view;
    }
}
