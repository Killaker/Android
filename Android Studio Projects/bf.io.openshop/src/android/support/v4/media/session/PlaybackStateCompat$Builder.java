package android.support.v4.media.session;

import java.util.*;
import android.os.*;

public static final class Builder
{
    private long mActions;
    private long mActiveItemId;
    private long mBufferedPosition;
    private final List<CustomAction> mCustomActions;
    private CharSequence mErrorMessage;
    private Bundle mExtras;
    private long mPosition;
    private float mRate;
    private int mState;
    private long mUpdateTime;
    
    public Builder() {
        this.mCustomActions = new ArrayList<CustomAction>();
        this.mActiveItemId = -1L;
    }
    
    public Builder(final PlaybackStateCompat playbackStateCompat) {
        this.mCustomActions = new ArrayList<CustomAction>();
        this.mActiveItemId = -1L;
        this.mState = PlaybackStateCompat.access$300(playbackStateCompat);
        this.mPosition = PlaybackStateCompat.access$400(playbackStateCompat);
        this.mRate = PlaybackStateCompat.access$500(playbackStateCompat);
        this.mUpdateTime = PlaybackStateCompat.access$600(playbackStateCompat);
        this.mBufferedPosition = PlaybackStateCompat.access$700(playbackStateCompat);
        this.mActions = PlaybackStateCompat.access$800(playbackStateCompat);
        this.mErrorMessage = PlaybackStateCompat.access$900(playbackStateCompat);
        if (PlaybackStateCompat.access$1000(playbackStateCompat) != null) {
            this.mCustomActions.addAll(PlaybackStateCompat.access$1000(playbackStateCompat));
        }
        this.mActiveItemId = PlaybackStateCompat.access$1100(playbackStateCompat);
        this.mExtras = PlaybackStateCompat.access$1200(playbackStateCompat);
    }
    
    public Builder addCustomAction(final CustomAction customAction) {
        if (customAction == null) {
            throw new IllegalArgumentException("You may not add a null CustomAction to PlaybackStateCompat.");
        }
        this.mCustomActions.add(customAction);
        return this;
    }
    
    public Builder addCustomAction(final String s, final String s2, final int n) {
        return this.addCustomAction(new CustomAction(s, (CharSequence)s2, n, (Bundle)null));
    }
    
    public PlaybackStateCompat build() {
        return new PlaybackStateCompat(this.mState, this.mPosition, this.mBufferedPosition, this.mRate, this.mActions, this.mErrorMessage, this.mUpdateTime, this.mCustomActions, this.mActiveItemId, this.mExtras, null);
    }
    
    public Builder setActions(final long mActions) {
        this.mActions = mActions;
        return this;
    }
    
    public Builder setActiveQueueItemId(final long mActiveItemId) {
        this.mActiveItemId = mActiveItemId;
        return this;
    }
    
    public Builder setBufferedPosition(final long mBufferedPosition) {
        this.mBufferedPosition = mBufferedPosition;
        return this;
    }
    
    public Builder setErrorMessage(final CharSequence mErrorMessage) {
        this.mErrorMessage = mErrorMessage;
        return this;
    }
    
    public Builder setExtras(final Bundle mExtras) {
        this.mExtras = mExtras;
        return this;
    }
    
    public Builder setState(final int n, final long n2, final float n3) {
        return this.setState(n, n2, n3, SystemClock.elapsedRealtime());
    }
    
    public Builder setState(final int mState, final long mPosition, final float mRate, final long mUpdateTime) {
        this.mState = mState;
        this.mPosition = mPosition;
        this.mUpdateTime = mUpdateTime;
        this.mRate = mRate;
        return this;
    }
}
