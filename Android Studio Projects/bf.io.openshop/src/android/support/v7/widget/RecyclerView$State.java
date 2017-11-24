package android.support.v7.widget;

import android.util.*;

public static class State
{
    static final int STEP_ANIMATIONS = 4;
    static final int STEP_LAYOUT = 2;
    static final int STEP_START = 1;
    private SparseArray<Object> mData;
    private int mDeletedInvisibleItemCountSincePreviousLayout;
    private boolean mInPreLayout;
    private boolean mIsMeasuring;
    int mItemCount;
    private int mLayoutStep;
    private int mPreviousLayoutItemCount;
    private boolean mRunPredictiveAnimations;
    private boolean mRunSimpleAnimations;
    private boolean mStructureChanged;
    private int mTargetPosition;
    private boolean mTrackOldChangeHolders;
    
    public State() {
        this.mTargetPosition = -1;
        this.mLayoutStep = 1;
        this.mItemCount = 0;
        this.mPreviousLayoutItemCount = 0;
        this.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        this.mStructureChanged = false;
        this.mInPreLayout = false;
        this.mRunSimpleAnimations = false;
        this.mRunPredictiveAnimations = false;
        this.mTrackOldChangeHolders = false;
        this.mIsMeasuring = false;
    }
    
    void assertLayoutStep(final int n) {
        if ((n & this.mLayoutStep) == 0x0) {
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(n) + " but it is " + Integer.toBinaryString(this.mLayoutStep));
        }
    }
    
    public boolean didStructureChange() {
        return this.mStructureChanged;
    }
    
    public <T> T get(final int n) {
        if (this.mData == null) {
            return null;
        }
        return (T)this.mData.get(n);
    }
    
    public int getItemCount() {
        if (this.mInPreLayout) {
            return this.mPreviousLayoutItemCount - this.mDeletedInvisibleItemCountSincePreviousLayout;
        }
        return this.mItemCount;
    }
    
    public int getTargetScrollPosition() {
        return this.mTargetPosition;
    }
    
    public boolean hasTargetScrollPosition() {
        return this.mTargetPosition != -1;
    }
    
    public boolean isMeasuring() {
        return this.mIsMeasuring;
    }
    
    public boolean isPreLayout() {
        return this.mInPreLayout;
    }
    
    public void put(final int n, final Object o) {
        if (this.mData == null) {
            this.mData = (SparseArray<Object>)new SparseArray();
        }
        this.mData.put(n, o);
    }
    
    public void remove(final int n) {
        if (this.mData == null) {
            return;
        }
        this.mData.remove(n);
    }
    
    State reset() {
        this.mTargetPosition = -1;
        if (this.mData != null) {
            this.mData.clear();
        }
        this.mItemCount = 0;
        this.mStructureChanged = false;
        this.mIsMeasuring = false;
        return this;
    }
    
    @Override
    public String toString() {
        return "State{mTargetPosition=" + this.mTargetPosition + ", mData=" + this.mData + ", mItemCount=" + this.mItemCount + ", mPreviousLayoutItemCount=" + this.mPreviousLayoutItemCount + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.mDeletedInvisibleItemCountSincePreviousLayout + ", mStructureChanged=" + this.mStructureChanged + ", mInPreLayout=" + this.mInPreLayout + ", mRunSimpleAnimations=" + this.mRunSimpleAnimations + ", mRunPredictiveAnimations=" + this.mRunPredictiveAnimations + '}';
    }
    
    public boolean willRunPredictiveAnimations() {
        return this.mRunPredictiveAnimations;
    }
    
    public boolean willRunSimpleAnimations() {
        return this.mRunSimpleAnimations;
    }
}
