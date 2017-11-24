package android.support.v4.media;

import android.media.*;

static final class VolumeProviderCompatApi21$1 extends VolumeProvider {
    final /* synthetic */ Delegate val$delegate;
    
    public void onAdjustVolume(final int n) {
        this.val$delegate.onAdjustVolume(n);
    }
    
    public void onSetVolumeTo(final int n) {
        this.val$delegate.onSetVolumeTo(n);
    }
}