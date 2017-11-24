package android.support.v4.view;

import android.view.*;

static class HoneycombVelocityTrackerVersionImpl implements VelocityTrackerVersionImpl
{
    @Override
    public float getXVelocity(final VelocityTracker velocityTracker, final int n) {
        return VelocityTrackerCompatHoneycomb.getXVelocity(velocityTracker, n);
    }
    
    @Override
    public float getYVelocity(final VelocityTracker velocityTracker, final int n) {
        return VelocityTrackerCompatHoneycomb.getYVelocity(velocityTracker, n);
    }
}
