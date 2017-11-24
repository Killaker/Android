package android.support.v4.app;

import android.os.*;

private static class ActivityOptionsImplJB extends ActivityOptionsCompat
{
    private final ActivityOptionsCompatJB mImpl;
    
    ActivityOptionsImplJB(final ActivityOptionsCompatJB mImpl) {
        this.mImpl = mImpl;
    }
    
    @Override
    public Bundle toBundle() {
        return this.mImpl.toBundle();
    }
    
    @Override
    public void update(final ActivityOptionsCompat activityOptionsCompat) {
        if (activityOptionsCompat instanceof ActivityOptionsImplJB) {
            this.mImpl.update(((ActivityOptionsImplJB)activityOptionsCompat).mImpl);
        }
    }
}
