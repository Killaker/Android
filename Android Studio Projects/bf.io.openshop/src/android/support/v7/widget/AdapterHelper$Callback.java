package android.support.v7.widget;

interface Callback
{
    RecyclerView.ViewHolder findViewHolder(final int p0);
    
    void markViewHoldersUpdated(final int p0, final int p1, final Object p2);
    
    void offsetPositionsForAdd(final int p0, final int p1);
    
    void offsetPositionsForMove(final int p0, final int p1);
    
    void offsetPositionsForRemovingInvisible(final int p0, final int p1);
    
    void offsetPositionsForRemovingLaidOutOrNewView(final int p0, final int p1);
    
    void onDispatchFirstPass(final UpdateOp p0);
    
    void onDispatchSecondPass(final UpdateOp p0);
}
