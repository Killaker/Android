package android.support.v4.media.session;

import android.os.*;
import android.support.v4.media.*;
import java.util.*;

private class StubCompat extends Stub
{
    public void onEvent(final String s, final Bundle bundle) throws RemoteException {
        Callback.access$200(Callback.this).post(1, s, bundle);
    }
    
    public void onExtrasChanged(final Bundle bundle) throws RemoteException {
        Callback.access$200(Callback.this).post(7, bundle, null);
    }
    
    public void onMetadataChanged(final MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
        Callback.access$200(Callback.this).post(3, mediaMetadataCompat, null);
    }
    
    public void onPlaybackStateChanged(final PlaybackStateCompat playbackStateCompat) throws RemoteException {
        Callback.access$200(Callback.this).post(2, playbackStateCompat, null);
    }
    
    public void onQueueChanged(final List<MediaSessionCompat.QueueItem> list) throws RemoteException {
        Callback.access$200(Callback.this).post(5, list, null);
    }
    
    public void onQueueTitleChanged(final CharSequence charSequence) throws RemoteException {
        Callback.access$200(Callback.this).post(6, charSequence, null);
    }
    
    public void onSessionDestroyed() throws RemoteException {
        Callback.access$200(Callback.this).post(8, null, null);
    }
    
    public void onVolumeInfoChanged(final ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
        Object o = null;
        if (parcelableVolumeInfo != null) {
            o = new PlaybackInfo(parcelableVolumeInfo.volumeType, parcelableVolumeInfo.audioStream, parcelableVolumeInfo.controlType, parcelableVolumeInfo.maxVolume, parcelableVolumeInfo.currentVolume);
        }
        Callback.access$200(Callback.this).post(4, o, null);
    }
}
