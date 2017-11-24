package android.support.v7.widget.helper;

import android.support.v7.widget.*;
import android.view.*;
import android.support.v4.view.*;

class ItemTouchHelper$2 implements OnItemTouchListener {
    @Override
    public boolean onInterceptTouchEvent(final RecyclerView recyclerView, final MotionEvent motionEvent) {
        ItemTouchHelper.access$400(ItemTouchHelper.this).onTouchEvent(motionEvent);
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0) {
            ItemTouchHelper.this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
            ItemTouchHelper.this.mInitialTouchX = motionEvent.getX();
            ItemTouchHelper.this.mInitialTouchY = motionEvent.getY();
            ItemTouchHelper.access$500(ItemTouchHelper.this);
            if (ItemTouchHelper.this.mSelected == null) {
                final RecoverAnimation access$600 = ItemTouchHelper.access$600(ItemTouchHelper.this, motionEvent);
                if (access$600 != null) {
                    final ItemTouchHelper this$0 = ItemTouchHelper.this;
                    this$0.mInitialTouchX -= access$600.mX;
                    final ItemTouchHelper this$2 = ItemTouchHelper.this;
                    this$2.mInitialTouchY -= access$600.mY;
                    ItemTouchHelper.access$700(ItemTouchHelper.this, access$600.mViewHolder, true);
                    if (ItemTouchHelper.this.mPendingCleanup.remove(access$600.mViewHolder.itemView)) {
                        ItemTouchHelper.this.mCallback.clearView(ItemTouchHelper.access$300(ItemTouchHelper.this), access$600.mViewHolder);
                    }
                    ItemTouchHelper.access$800(ItemTouchHelper.this, access$600.mViewHolder, access$600.mActionState);
                    ItemTouchHelper.access$900(ItemTouchHelper.this, motionEvent, ItemTouchHelper.this.mSelectedFlags, 0);
                }
            }
        }
        else if (actionMasked == 3 || actionMasked == 1) {
            ItemTouchHelper.this.mActivePointerId = -1;
            ItemTouchHelper.access$800(ItemTouchHelper.this, null, 0);
        }
        else if (ItemTouchHelper.this.mActivePointerId != -1) {
            final int pointerIndex = MotionEventCompat.findPointerIndex(motionEvent, ItemTouchHelper.this.mActivePointerId);
            if (pointerIndex >= 0) {
                ItemTouchHelper.access$1000(ItemTouchHelper.this, actionMasked, motionEvent, pointerIndex);
            }
        }
        if (ItemTouchHelper.access$1100(ItemTouchHelper.this) != null) {
            ItemTouchHelper.access$1100(ItemTouchHelper.this).addMovement(motionEvent);
        }
        return ItemTouchHelper.this.mSelected != null;
    }
    
    @Override
    public void onRequestDisallowInterceptTouchEvent(final boolean b) {
        if (!b) {
            return;
        }
        ItemTouchHelper.access$800(ItemTouchHelper.this, null, 0);
    }
    
    @Override
    public void onTouchEvent(final RecyclerView recyclerView, final MotionEvent motionEvent) {
        ItemTouchHelper.access$400(ItemTouchHelper.this).onTouchEvent(motionEvent);
        if (ItemTouchHelper.access$1100(ItemTouchHelper.this) != null) {
            ItemTouchHelper.access$1100(ItemTouchHelper.this).addMovement(motionEvent);
        }
        if (ItemTouchHelper.this.mActivePointerId != -1) {
            final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
            final int pointerIndex = MotionEventCompat.findPointerIndex(motionEvent, ItemTouchHelper.this.mActivePointerId);
            if (pointerIndex >= 0) {
                ItemTouchHelper.access$1000(ItemTouchHelper.this, actionMasked, motionEvent, pointerIndex);
            }
            final ViewHolder mSelected = ItemTouchHelper.this.mSelected;
            if (mSelected != null) {
                switch (actionMasked) {
                    default: {}
                    case 3: {
                        if (ItemTouchHelper.access$1100(ItemTouchHelper.this) != null) {
                            ItemTouchHelper.access$1100(ItemTouchHelper.this).clear();
                        }
                    }
                    case 1: {
                        ItemTouchHelper.access$800(ItemTouchHelper.this, null, 0);
                        ItemTouchHelper.this.mActivePointerId = -1;
                    }
                    case 2: {
                        if (pointerIndex >= 0) {
                            ItemTouchHelper.access$900(ItemTouchHelper.this, motionEvent, ItemTouchHelper.this.mSelectedFlags, pointerIndex);
                            ItemTouchHelper.access$100(ItemTouchHelper.this, mSelected);
                            ItemTouchHelper.access$300(ItemTouchHelper.this).removeCallbacks(ItemTouchHelper.access$200(ItemTouchHelper.this));
                            ItemTouchHelper.access$200(ItemTouchHelper.this).run();
                            ItemTouchHelper.access$300(ItemTouchHelper.this).invalidate();
                            return;
                        }
                        break;
                    }
                    case 6: {
                        final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == ItemTouchHelper.this.mActivePointerId) {
                            int n = 0;
                            if (actionIndex == 0) {
                                n = 1;
                            }
                            ItemTouchHelper.this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, n);
                            ItemTouchHelper.access$900(ItemTouchHelper.this, motionEvent, ItemTouchHelper.this.mSelectedFlags, actionIndex);
                            return;
                        }
                        break;
                    }
                }
            }
        }
    }
}