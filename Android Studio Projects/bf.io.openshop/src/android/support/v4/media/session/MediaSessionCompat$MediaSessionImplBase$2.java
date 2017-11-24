package android.support.v4.media.session;

import android.support.v4.media.*;

class MediaSessionCompat$MediaSessionImplBase$2 implements MediaSessionCompatApi19.Callback {
    @Override
    public void onSeekTo(final long n) {
        MediaSessionImplBase.access$700(MediaSessionImplBase.this, 11, n);
    }
    
    @Override
    public void onSetRating(final Object o) {
        MediaSessionImplBase.access$700(MediaSessionImplBase.this, 12, RatingCompat.fromRating(o));
    }
}