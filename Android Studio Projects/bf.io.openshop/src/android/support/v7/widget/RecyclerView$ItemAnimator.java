package android.support.v7.widget;

import android.support.annotation.*;
import java.lang.annotation.*;
import android.view.*;
import java.util.*;
import android.support.v4.view.*;
import android.util.*;

public abstract static class ItemAnimator
{
    public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
    public static final int FLAG_CHANGED = 2;
    public static final int FLAG_INVALIDATED = 4;
    public static final int FLAG_MOVED = 2048;
    public static final int FLAG_REMOVED = 8;
    private long mAddDuration;
    private long mChangeDuration;
    private ArrayList<ItemAnimatorFinishedListener> mFinishedListeners;
    private ItemAnimatorListener mListener;
    private long mMoveDuration;
    private long mRemoveDuration;
    
    public ItemAnimator() {
        this.mListener = null;
        this.mFinishedListeners = new ArrayList<ItemAnimatorFinishedListener>();
        this.mAddDuration = 120L;
        this.mRemoveDuration = 120L;
        this.mMoveDuration = 250L;
        this.mChangeDuration = 250L;
    }
    
    static int buildAdapterChangeFlagsForAnimations(final ViewHolder viewHolder) {
        int n = 0xE & viewHolder.mFlags;
        if (viewHolder.isInvalid()) {
            return 4;
        }
        if ((n & 0x4) == 0x0) {
            final int oldPosition = viewHolder.getOldPosition();
            final int adapterPosition = viewHolder.getAdapterPosition();
            if (oldPosition != -1 && adapterPosition != -1 && oldPosition != adapterPosition) {
                n |= 0x800;
            }
        }
        return n;
    }
    
    public abstract boolean animateAppearance(@NonNull final ViewHolder p0, @Nullable final ItemHolderInfo p1, @NonNull final ItemHolderInfo p2);
    
    public abstract boolean animateChange(@NonNull final ViewHolder p0, @NonNull final ViewHolder p1, @NonNull final ItemHolderInfo p2, @NonNull final ItemHolderInfo p3);
    
    public abstract boolean animateDisappearance(@NonNull final ViewHolder p0, @NonNull final ItemHolderInfo p1, @Nullable final ItemHolderInfo p2);
    
    public abstract boolean animatePersistence(@NonNull final ViewHolder p0, @NonNull final ItemHolderInfo p1, @NonNull final ItemHolderInfo p2);
    
    public boolean canReuseUpdatedViewHolder(@NonNull final ViewHolder viewHolder) {
        return true;
    }
    
    public boolean canReuseUpdatedViewHolder(@NonNull final ViewHolder viewHolder, @NonNull final List<Object> list) {
        return this.canReuseUpdatedViewHolder(viewHolder);
    }
    
    public final void dispatchAnimationFinished(final ViewHolder viewHolder) {
        this.onAnimationFinished(viewHolder);
        if (this.mListener != null) {
            this.mListener.onAnimationFinished(viewHolder);
        }
    }
    
    public final void dispatchAnimationStarted(final ViewHolder viewHolder) {
        this.onAnimationStarted(viewHolder);
    }
    
    public final void dispatchAnimationsFinished() {
        for (int size = this.mFinishedListeners.size(), i = 0; i < size; ++i) {
            this.mFinishedListeners.get(i).onAnimationsFinished();
        }
        this.mFinishedListeners.clear();
    }
    
    public abstract void endAnimation(final ViewHolder p0);
    
    public abstract void endAnimations();
    
    public long getAddDuration() {
        return this.mAddDuration;
    }
    
    public long getChangeDuration() {
        return this.mChangeDuration;
    }
    
    public long getMoveDuration() {
        return this.mMoveDuration;
    }
    
    public long getRemoveDuration() {
        return this.mRemoveDuration;
    }
    
    public abstract boolean isRunning();
    
    public final boolean isRunning(final ItemAnimatorFinishedListener itemAnimatorFinishedListener) {
        final boolean running = this.isRunning();
        if (itemAnimatorFinishedListener != null) {
            if (running) {
                this.mFinishedListeners.add(itemAnimatorFinishedListener);
                return running;
            }
            itemAnimatorFinishedListener.onAnimationsFinished();
        }
        return running;
    }
    
    public ItemHolderInfo obtainHolderInfo() {
        return new ItemHolderInfo();
    }
    
    public void onAnimationFinished(final ViewHolder viewHolder) {
    }
    
    public void onAnimationStarted(final ViewHolder viewHolder) {
    }
    
    @NonNull
    public ItemHolderInfo recordPostLayoutInformation(@NonNull final State state, @NonNull final ViewHolder from) {
        return this.obtainHolderInfo().setFrom(from);
    }
    
    @NonNull
    public ItemHolderInfo recordPreLayoutInformation(@NonNull final State state, @NonNull final ViewHolder from, final int n, @NonNull final List<Object> list) {
        return this.obtainHolderInfo().setFrom(from);
    }
    
    public abstract void runPendingAnimations();
    
    public void setAddDuration(final long mAddDuration) {
        this.mAddDuration = mAddDuration;
    }
    
    public void setChangeDuration(final long mChangeDuration) {
        this.mChangeDuration = mChangeDuration;
    }
    
    void setListener(final ItemAnimatorListener mListener) {
        this.mListener = mListener;
    }
    
    public void setMoveDuration(final long mMoveDuration) {
        this.mMoveDuration = mMoveDuration;
    }
    
    public void setRemoveDuration(final long mRemoveDuration) {
        this.mRemoveDuration = mRemoveDuration;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface AdapterChanges {
    }
    
    public interface ItemAnimatorFinishedListener
    {
        void onAnimationsFinished();
    }
    
    interface ItemAnimatorListener
    {
        void onAnimationFinished(final ViewHolder p0);
    }
    
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
}
