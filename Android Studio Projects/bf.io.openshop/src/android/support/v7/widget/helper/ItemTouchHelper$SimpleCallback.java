package android.support.v7.widget.helper;

import android.support.v7.widget.*;

public abstract static class SimpleCallback extends Callback
{
    private int mDefaultDragDirs;
    private int mDefaultSwipeDirs;
    
    public SimpleCallback(final int mDefaultDragDirs, final int mDefaultSwipeDirs) {
        this.mDefaultSwipeDirs = mDefaultSwipeDirs;
        this.mDefaultDragDirs = mDefaultDragDirs;
    }
    
    public int getDragDirs(final RecyclerView recyclerView, final ViewHolder viewHolder) {
        return this.mDefaultDragDirs;
    }
    
    @Override
    public int getMovementFlags(final RecyclerView recyclerView, final ViewHolder viewHolder) {
        return Callback.makeMovementFlags(this.getDragDirs(recyclerView, viewHolder), this.getSwipeDirs(recyclerView, viewHolder));
    }
    
    public int getSwipeDirs(final RecyclerView recyclerView, final ViewHolder viewHolder) {
        return this.mDefaultSwipeDirs;
    }
    
    public void setDefaultDragDirs(final int mDefaultDragDirs) {
        this.mDefaultDragDirs = mDefaultDragDirs;
    }
    
    public void setDefaultSwipeDirs(final int mDefaultSwipeDirs) {
        this.mDefaultSwipeDirs = mDefaultSwipeDirs;
    }
}
