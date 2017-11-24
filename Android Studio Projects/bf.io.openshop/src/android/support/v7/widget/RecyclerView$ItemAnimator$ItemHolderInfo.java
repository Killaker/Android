package android.support.v7.widget;

import android.view.*;

public static class ItemHolderInfo
{
    public int bottom;
    public int changeFlags;
    public int left;
    public int right;
    public int top;
    
    public ItemHolderInfo setFrom(final ViewHolder viewHolder) {
        return this.setFrom(viewHolder, 0);
    }
    
    public ItemHolderInfo setFrom(final ViewHolder viewHolder, final int n) {
        final View itemView = viewHolder.itemView;
        this.left = itemView.getLeft();
        this.top = itemView.getTop();
        this.right = itemView.getRight();
        this.bottom = itemView.getBottom();
        return this;
    }
}
