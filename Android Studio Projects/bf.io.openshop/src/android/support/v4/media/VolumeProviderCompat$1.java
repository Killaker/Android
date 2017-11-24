package android.support.v4.media;

class VolumeProviderCompat$1 implements Delegate {
    @Override
    public void onAdjustVolume(final int n) {
        VolumeProviderCompat.this.onAdjustVolume(n);
    }
    
    @Override
    public void onSetVolumeTo(final int n) {
        VolumeProviderCompat.this.onSetVolumeTo(n);
    }
}