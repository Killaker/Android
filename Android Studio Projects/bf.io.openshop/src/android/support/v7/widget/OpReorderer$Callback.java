package android.support.v7.widget;

interface Callback
{
    AdapterHelper.UpdateOp obtainUpdateOp(final int p0, final int p1, final int p2, final Object p3);
    
    void recycleUpdateOp(final AdapterHelper.UpdateOp p0);
}
