package com.android.volley;

public class DefaultRetryPolicy implements RetryPolicy
{
    public static final float DEFAULT_BACKOFF_MULT = 1.0f;
    public static final int DEFAULT_MAX_RETRIES = 1;
    public static final int DEFAULT_TIMEOUT_MS = 2500;
    private final float mBackoffMultiplier;
    private int mCurrentRetryCount;
    private int mCurrentTimeoutMs;
    private final int mMaxNumRetries;
    
    public DefaultRetryPolicy() {
        this(2500, 1, 1.0f);
    }
    
    public DefaultRetryPolicy(final int mCurrentTimeoutMs, final int mMaxNumRetries, final float mBackoffMultiplier) {
        this.mCurrentTimeoutMs = mCurrentTimeoutMs;
        this.mMaxNumRetries = mMaxNumRetries;
        this.mBackoffMultiplier = mBackoffMultiplier;
    }
    
    public float getBackoffMultiplier() {
        return this.mBackoffMultiplier;
    }
    
    @Override
    public int getCurrentRetryCount() {
        return this.mCurrentRetryCount;
    }
    
    @Override
    public int getCurrentTimeout() {
        return this.mCurrentTimeoutMs;
    }
    
    protected boolean hasAttemptRemaining() {
        return this.mCurrentRetryCount <= this.mMaxNumRetries;
    }
    
    @Override
    public void retry(final VolleyError volleyError) throws VolleyError {
        ++this.mCurrentRetryCount;
        this.mCurrentTimeoutMs += (int)(this.mCurrentTimeoutMs * this.mBackoffMultiplier);
        if (!this.hasAttemptRemaining()) {
            throw volleyError;
        }
    }
}
