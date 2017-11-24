package android.support.v7.view;

import android.view.*;

public interface Callback
{
    boolean onActionItemClicked(final ActionMode p0, final MenuItem p1);
    
    boolean onCreateActionMode(final ActionMode p0, final Menu p1);
    
    void onDestroyActionMode(final ActionMode p0);
    
    boolean onPrepareActionMode(final ActionMode p0, final Menu p1);
}
