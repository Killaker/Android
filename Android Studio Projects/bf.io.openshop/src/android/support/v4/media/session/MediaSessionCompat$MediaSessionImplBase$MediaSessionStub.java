package android.support.v4.media.session;

import android.app.*;
import java.util.*;
import android.net.*;
import android.support.v4.media.*;
import android.view.*;
import android.os.*;

class MediaSessionStub extends Stub
{
    public void adjustVolume(final int n, final int n2, final String s) {
        MediaSessionImplBase.access$1700(MediaSessionImplBase.this, n, n2);
    }
    
    public void fastForward() throws RemoteException {
        MediaSessionImplBase.access$1900(MediaSessionImplBase.this, 9);
    }
    
    public Bundle getExtras() {
        synchronized (MediaSessionImplBase.access$1400(MediaSessionImplBase.this)) {
            return MediaSessionImplBase.access$2500(MediaSessionImplBase.this);
        }
    }
    
    public long getFlags() {
        synchronized (MediaSessionImplBase.access$1400(MediaSessionImplBase.this)) {
            return MediaSessionImplBase.access$900(MediaSessionImplBase.this);
        }
    }
    
    public PendingIntent getLaunchPendingIntent() {
        synchronized (MediaSessionImplBase.access$1400(MediaSessionImplBase.this)) {
            return MediaSessionImplBase.access$1500(MediaSessionImplBase.this);
        }
    }
    
    public MediaMetadataCompat getMetadata() {
        return MediaSessionImplBase.access$2100(MediaSessionImplBase.this);
    }
    
    public String getPackageName() {
        return MediaSessionImplBase.access$1200(MediaSessionImplBase.this);
    }
    
    public PlaybackStateCompat getPlaybackState() {
        return MediaSessionImplBase.access$2200(MediaSessionImplBase.this);
    }
    
    public List<QueueItem> getQueue() {
        synchronized (MediaSessionImplBase.access$1400(MediaSessionImplBase.this)) {
            return (List<QueueItem>)MediaSessionImplBase.access$2300(MediaSessionImplBase.this);
        }
    }
    
    public CharSequence getQueueTitle() {
        return MediaSessionImplBase.access$2400(MediaSessionImplBase.this);
    }
    
    public int getRatingType() {
        return MediaSessionImplBase.access$2600(MediaSessionImplBase.this);
    }
    
    public String getTag() {
        return MediaSessionImplBase.access$1300(MediaSessionImplBase.this);
    }
    
    public ParcelableVolumeInfo getVolumeAttributes() {
        synchronized (MediaSessionImplBase.access$1400(MediaSessionImplBase.this)) {
            final int access$400 = MediaSessionImplBase.access$400(MediaSessionImplBase.this);
            final int access$401 = MediaSessionImplBase.access$500(MediaSessionImplBase.this);
            final VolumeProviderCompat access$402 = MediaSessionImplBase.access$300(MediaSessionImplBase.this);
            int volumeControl;
            int n;
            int n2;
            if (access$400 == 2) {
                volumeControl = access$402.getVolumeControl();
                n = access$402.getMaxVolume();
                n2 = access$402.getCurrentVolume();
            }
            else {
                volumeControl = 2;
                n = MediaSessionImplBase.access$1600(MediaSessionImplBase.this).getStreamMaxVolume(access$401);
                n2 = MediaSessionImplBase.access$1600(MediaSessionImplBase.this).getStreamVolume(access$401);
            }
            // monitorexit(MediaSessionImplBase.access$1400(this.this$0))
            return new ParcelableVolumeInfo(access$400, access$401, volumeControl, n, n2);
        }
    }
    
    public boolean isTransportControlEnabled() {
        return (0x2 & MediaSessionImplBase.access$900(MediaSessionImplBase.this)) != 0x0;
    }
    
    public void next() throws RemoteException {
        MediaSessionImplBase.access$1900(MediaSessionImplBase.this, 7);
    }
    
    public void pause() throws RemoteException {
        MediaSessionImplBase.access$1900(MediaSessionImplBase.this, 5);
    }
    
    public void play() throws RemoteException {
        MediaSessionImplBase.access$1900(MediaSessionImplBase.this, 1);
    }
    
    public void playFromMediaId(final String s, final Bundle bundle) throws RemoteException {
        MediaSessionImplBase.access$2000(MediaSessionImplBase.this, 2, s, bundle);
    }
    
    public void playFromSearch(final String s, final Bundle bundle) throws RemoteException {
        MediaSessionImplBase.access$2000(MediaSessionImplBase.this, 3, s, bundle);
    }
    
    public void playFromUri(final Uri uri, final Bundle bundle) throws RemoteException {
        MediaSessionImplBase.access$2000(MediaSessionImplBase.this, 18, uri, bundle);
    }
    
    public void previous() throws RemoteException {
        MediaSessionImplBase.access$1900(MediaSessionImplBase.this, 8);
    }
    
    public void rate(final RatingCompat ratingCompat) throws RemoteException {
        MediaSessionImplBase.access$700(MediaSessionImplBase.this, 12, ratingCompat);
    }
    
    public void registerCallbackListener(final IMediaControllerCallback mediaControllerCallback) {
        Label_0017: {
            if (!MediaSessionImplBase.access$1000(MediaSessionImplBase.this)) {
                break Label_0017;
            }
            try {
                mediaControllerCallback.onSessionDestroyed();
                return;
                MediaSessionImplBase.access$1100(MediaSessionImplBase.this).register((IInterface)mediaControllerCallback);
            }
            catch (Exception ex) {}
        }
    }
    
    public void rewind() throws RemoteException {
        MediaSessionImplBase.access$1900(MediaSessionImplBase.this, 10);
    }
    
    public void seekTo(final long n) throws RemoteException {
        MediaSessionImplBase.access$700(MediaSessionImplBase.this, 11, n);
    }
    
    public void sendCommand(final String s, final Bundle bundle, final ResultReceiverWrapper resultReceiverWrapper) {
        MediaSessionImplBase.access$700(MediaSessionImplBase.this, 15, new Command(s, bundle, resultReceiverWrapper.mResultReceiver));
    }
    
    public void sendCustomAction(final String s, final Bundle bundle) throws RemoteException {
        MediaSessionImplBase.access$2000(MediaSessionImplBase.this, 13, s, bundle);
    }
    
    public boolean sendMediaButton(final KeyEvent keyEvent) {
        final boolean b = (0x1 & MediaSessionImplBase.access$900(MediaSessionImplBase.this)) != 0x0;
        if (b) {
            MediaSessionImplBase.access$700(MediaSessionImplBase.this, 14, keyEvent);
        }
        return b;
    }
    
    public void setVolumeTo(final int n, final int n2, final String s) {
        MediaSessionImplBase.access$1800(MediaSessionImplBase.this, n, n2);
    }
    
    public void skipToQueueItem(final long n) {
        MediaSessionImplBase.access$700(MediaSessionImplBase.this, 4, n);
    }
    
    public void stop() throws RemoteException {
        MediaSessionImplBase.access$1900(MediaSessionImplBase.this, 6);
    }
    
    public void unregisterCallbackListener(final IMediaControllerCallback mediaControllerCallback) {
        MediaSessionImplBase.access$1100(MediaSessionImplBase.this).unregister((IInterface)mediaControllerCallback);
    }
}
