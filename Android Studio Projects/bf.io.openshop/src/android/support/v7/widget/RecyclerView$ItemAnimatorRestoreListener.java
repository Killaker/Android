package android.support.v7.widget;

import android.view.*;
import java.util.*;
import android.support.v4.view.*;
import android.util.*;

private class ItemAnimatorRestoreListener implements ItemAnimatorListener
{
    @Override
    public void onAnimationFinished(final ViewHolder viewHolder) {
        viewHolder.setIsRecyclable(true);
        if (viewHolder.mShadowedHolder != null && viewHolder.mShadowingHolder == null) {
            viewHolder.mShadowedHolder = null;
        }
        viewHolder.mShadowingHolder = null;
        if (!viewHolder.shouldBeKeptAsChild() && !RecyclerView.access$6400(RecyclerView.this, viewHolder.itemView) && viewHolder.isTmpDetached()) {
            RecyclerView.this.removeDetachedView(viewHolder.itemView, false);
        }
    }
}
