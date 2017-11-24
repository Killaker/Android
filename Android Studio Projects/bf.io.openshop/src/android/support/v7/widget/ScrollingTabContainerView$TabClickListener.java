package android.support.v7.widget;

import android.view.*;

private class TabClickListener implements View$OnClickListener
{
    public void onClick(final View view) {
        ((TabView)view).getTab().select();
        for (int childCount = ScrollingTabContainerView.access$200(ScrollingTabContainerView.this).getChildCount(), i = 0; i < childCount; ++i) {
            final View child = ScrollingTabContainerView.access$200(ScrollingTabContainerView.this).getChildAt(i);
            child.setSelected(child == view);
        }
    }
}
