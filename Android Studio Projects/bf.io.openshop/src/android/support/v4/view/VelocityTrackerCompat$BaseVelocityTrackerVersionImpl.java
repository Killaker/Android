package android.support.v4.view;

import android.view.*;

static class BaseVelocityTrackerVersionImpl implements VelocityTrackerVersionImpl
{
    @Override
    public float getXVelocity(final VelocityTracker velocityTracker, final int n) {
        return velocityTracker.getXVelocity();
    }
    
    @Override
    public float getYVelocity(final VelocityTracker velocityTracker, final int n) {
        return velocityTracker.getYVelocity();
    }
}
