package android.support.v7.util;

import java.util.*;

public abstract static class Callback<T2> implements Comparator<T2>
{
    public abstract boolean areContentsTheSame(final T2 p0, final T2 p1);
    
    public abstract boolean areItemsTheSame(final T2 p0, final T2 p1);
    
    @Override
    public abstract int compare(final T2 p0, final T2 p1);
    
    public abstract void onChanged(final int p0, final int p1);
    
    public abstract void onInserted(final int p0, final int p1);
    
    public abstract void onMoved(final int p0, final int p1);
    
    public abstract void onRemoved(final int p0, final int p1);
}
