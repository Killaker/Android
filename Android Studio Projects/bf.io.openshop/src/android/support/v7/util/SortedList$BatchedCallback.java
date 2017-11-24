package android.support.v7.util;

public static class BatchedCallback<T2> extends Callback<T2>
{
    static final int TYPE_ADD = 1;
    static final int TYPE_CHANGE = 3;
    static final int TYPE_MOVE = 4;
    static final int TYPE_NONE = 0;
    static final int TYPE_REMOVE = 2;
    int mLastEventCount;
    int mLastEventPosition;
    int mLastEventType;
    private final Callback<T2> mWrappedCallback;
    
    public BatchedCallback(final Callback<T2> mWrappedCallback) {
        this.mLastEventType = 0;
        this.mLastEventPosition = -1;
        this.mLastEventCount = -1;
        this.mWrappedCallback = mWrappedCallback;
    }
    
    @Override
    public boolean areContentsTheSame(final T2 t2, final T2 t3) {
        return this.mWrappedCallback.areContentsTheSame(t2, t3);
    }
    
    @Override
    public boolean areItemsTheSame(final T2 t2, final T2 t3) {
        return this.mWrappedCallback.areItemsTheSame(t2, t3);
    }
    
    @Override
    public int compare(final T2 t2, final T2 t3) {
        return this.mWrappedCallback.compare(t2, t3);
    }
    
    public void dispatchLastEvent() {
        if (this.mLastEventType == 0) {
            return;
        }
        switch (this.mLastEventType) {
            case 1: {
                this.mWrappedCallback.onInserted(this.mLastEventPosition, this.mLastEventCount);
                break;
            }
            case 2: {
                this.mWrappedCallback.onRemoved(this.mLastEventPosition, this.mLastEventCount);
                break;
            }
            case 3: {
                this.mWrappedCallback.onChanged(this.mLastEventPosition, this.mLastEventCount);
                break;
            }
        }
        this.mLastEventType = 0;
    }
    
    @Override
    public void onChanged(final int mLastEventPosition, final int mLastEventCount) {
        if (this.mLastEventType == 3 && mLastEventPosition <= this.mLastEventPosition + this.mLastEventCount && mLastEventPosition + mLastEventCount >= this.mLastEventPosition) {
            final int n = this.mLastEventPosition + this.mLastEventCount;
            this.mLastEventPosition = Math.min(mLastEventPosition, this.mLastEventPosition);
            this.mLastEventCount = Math.max(n, mLastEventPosition + mLastEventCount) - this.mLastEventPosition;
            return;
        }
        this.dispatchLastEvent();
        this.mLastEventPosition = mLastEventPosition;
        this.mLastEventCount = mLastEventCount;
        this.mLastEventType = 3;
    }
    
    @Override
    public void onInserted(final int mLastEventPosition, final int mLastEventCount) {
        if (this.mLastEventType == 1 && mLastEventPosition >= this.mLastEventPosition && mLastEventPosition <= this.mLastEventPosition + this.mLastEventCount) {
            this.mLastEventCount += mLastEventCount;
            this.mLastEventPosition = Math.min(mLastEventPosition, this.mLastEventPosition);
            return;
        }
        this.dispatchLastEvent();
        this.mLastEventPosition = mLastEventPosition;
        this.mLastEventCount = mLastEventCount;
        this.mLastEventType = 1;
    }
    
    @Override
    public void onMoved(final int n, final int n2) {
        this.dispatchLastEvent();
        this.mWrappedCallback.onMoved(n, n2);
    }
    
    @Override
    public void onRemoved(final int mLastEventPosition, final int mLastEventCount) {
        if (this.mLastEventType == 2 && this.mLastEventPosition == mLastEventPosition) {
            this.mLastEventCount += mLastEventCount;
            return;
        }
        this.dispatchLastEvent();
        this.mLastEventPosition = mLastEventPosition;
        this.mLastEventCount = mLastEventCount;
        this.mLastEventType = 2;
    }
}
