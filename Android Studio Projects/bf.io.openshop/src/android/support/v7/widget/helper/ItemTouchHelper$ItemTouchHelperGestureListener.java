package android.support.v7.widget.helper;

import android.view.*;
import android.support.v7.widget.*;
import android.view.animation.*;
import android.os.*;
import android.graphics.*;
import java.util.*;
import android.support.v7.recyclerview.*;
import android.support.v4.view.*;

private class ItemTouchHelperGestureListener extends GestureDetector$SimpleOnGestureListener
{
    public boolean onDown(final MotionEvent motionEvent) {
        return true;
    }
    
    public void onLongPress(final MotionEvent motionEvent) {
        final View access$2400 = ItemTouchHelper.access$2400(ItemTouchHelper.this, motionEvent);
        if (access$2400 != null) {
            final RecyclerView.ViewHolder childViewHolder = ItemTouchHelper.access$300(ItemTouchHelper.this).getChildViewHolder(access$2400);
            if (childViewHolder != null && ItemTouchHelper.this.mCallback.hasDragFlag(ItemTouchHelper.access$300(ItemTouchHelper.this), childViewHolder) && MotionEventCompat.getPointerId(motionEvent, 0) == ItemTouchHelper.this.mActivePointerId) {
                final int pointerIndex = MotionEventCompat.findPointerIndex(motionEvent, ItemTouchHelper.this.mActivePointerId);
                final float x = MotionEventCompat.getX(motionEvent, pointerIndex);
                final float y = MotionEventCompat.getY(motionEvent, pointerIndex);
                ItemTouchHelper.this.mInitialTouchX = x;
                ItemTouchHelper.this.mInitialTouchY = y;
                final ItemTouchHelper this$0 = ItemTouchHelper.this;
                ItemTouchHelper.this.mDy = 0.0f;
                this$0.mDx = 0.0f;
                if (ItemTouchHelper.this.mCallback.isLongPressDragEnabled()) {
                    ItemTouchHelper.access$800(ItemTouchHelper.this, childViewHolder, 2);
                }
            }
        }
    }
}
