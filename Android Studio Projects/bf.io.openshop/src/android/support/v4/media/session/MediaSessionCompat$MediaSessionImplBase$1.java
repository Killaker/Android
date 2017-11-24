package android.support.v4.media.session;

import android.support.v4.media.*;

class MediaSessionCompat$MediaSessionImplBase$1 extends VolumeProviderCompat.Callback {
    @Override
    public void onVolumeChanged(final VolumeProviderCompat volumeProviderCompat) {
        if (MediaSessionImplBase.access$300(MediaSessionImplBase.this) != volumeProviderCompat) {
            return;
        }
        MediaSessionImplBase.access$600(MediaSessionImplBase.this, new ParcelableVolumeInfo(MediaSessionImplBase.access$400(MediaSessionImplBase.this), MediaSessionImplBase.access$500(MediaSessionImplBase.this), volumeProviderCompat.getVolumeControl(), volumeProviderCompat.getMaxVolume(), volumeProviderCompat.getCurrentVolume()));
    }
}