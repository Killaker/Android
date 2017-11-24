package android.support.v7.widget;

import android.view.animation.*;
import android.util.*;

public static class Action
{
    public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
    private boolean changed;
    private int consecutiveUpdates;
    private int mDuration;
    private int mDx;
    private int mDy;
    private Interpolator mInterpolator;
    private int mJumpToPosition;
    
    public Action(final int n, final int n2) {
        this(n, n2, Integer.MIN_VALUE, null);
    }
    
    public Action(final int n, final int n2, final int n3) {
        this(n, n2, n3, null);
    }
    
    public Action(final int mDx, final int mDy, final int mDuration, final Interpolator mInterpolator) {
        this.mJumpToPosition = -1;
        this.changed = false;
        this.consecutiveUpdates = 0;
        this.mDx = mDx;
        this.mDy = mDy;
        this.mDuration = mDuration;
        this.mInterpolator = mInterpolator;
    }
    
    private void runIfNecessary(final RecyclerView recyclerView) {
        if (this.mJumpToPosition >= 0) {
            final int mJumpToPosition = this.mJumpToPosition;
            this.mJumpToPosition = -1;
            RecyclerView.access$6200(recyclerView, mJumpToPosition);
            this.changed = false;
            return;
        }
        if (this.changed) {
            this.validate();
            if (this.mInterpolator == null) {
                if (this.mDuration == Integer.MIN_VALUE) {
                    RecyclerView.access$5900(recyclerView).smoothScrollBy(this.mDx, this.mDy);
                }
                else {
                    RecyclerView.access$5900(recyclerView).smoothScrollBy(this.mDx, this.mDy, this.mDuration);
                }
            }
            else {
                RecyclerView.access$5900(recyclerView).smoothScrollBy(this.mDx, this.mDy, this.mDuration, this.mInterpolator);
            }
            ++this.consecutiveUpdates;
            if (this.consecutiveUpdates > 10) {
                Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
            }
            this.changed = false;
            return;
        }
        this.consecutiveUpdates = 0;
    }
    
    private void validate() {
        if (this.mInterpolator != null && this.mDuration < 1) {
            throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
        }
        if (this.mDuration < 1) {
            throw new IllegalStateException("Scroll duration must be a positive number");
        }
    }
    
    public int getDuration() {
        return this.mDuration;
    }
    
    public int getDx() {
        return this.mDx;
    }
    
    public int getDy() {
        return this.mDy;
    }
    
    public Interpolator getInterpolator() {
        return this.mInterpolator;
    }
    
    boolean hasJumpTarget() {
        return this.mJumpToPosition >= 0;
    }
    
    public void jumpTo(final int mJumpToPosition) {
        this.mJumpToPosition = mJumpToPosition;
    }
    
    public void setDuration(final int mDuration) {
        this.changed = true;
        this.mDuration = mDuration;
    }
    
    public void setDx(final int mDx) {
        this.changed = true;
        this.mDx = mDx;
    }
    
    public void setDy(final int mDy) {
        this.changed = true;
        this.mDy = mDy;
    }
    
    public void setInterpolator(final Interpolator mInterpolator) {
        this.changed = true;
        this.mInterpolator = mInterpolator;
    }
    
    public void update(final int mDx, final int mDy, final int mDuration, final Interpolator mInterpolator) {
        this.mDx = mDx;
        this.mDy = mDy;
        this.mDuration = mDuration;
        this.mInterpolator = mInterpolator;
        this.changed = true;
    }
}
