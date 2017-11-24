package android.support.v4.app;

import android.os.*;

private static class ActivityOptionsImpl21 extends ActivityOptionsCompat
{
    private final ActivityOptionsCompat21 mImpl;
    
    ActivityOptionsImpl21(final ActivityOptionsCompat21 mImpl) {
        this.mImpl = mImpl;
    }
    
    @Override
    public Bundle toBundle() {
        return this.mImpl.toBundle();
    }
    
    @Override
    public void update(final ActivityOptionsCompat activityOptionsCompat) {
        if (activityOptionsCompat instanceof ActivityOptionsImpl21) {
            this.mImpl.update(((ActivityOptionsImpl21)activityOptionsCompat).mImpl);
        }
    }
}
