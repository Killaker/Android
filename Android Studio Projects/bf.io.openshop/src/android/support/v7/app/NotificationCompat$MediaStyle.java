package android.support.v7.app;

import android.support.v4.app.*;
import android.app.*;
import android.support.v4.media.session.*;

public static class MediaStyle extends Style
{
    int[] mActionsToShowInCompact;
    PendingIntent mCancelButtonIntent;
    boolean mShowCancelButton;
    MediaSessionCompat.Token mToken;
    
    public MediaStyle() {
        this.mActionsToShowInCompact = null;
    }
    
    public MediaStyle(final Builder builder) {
        this.mActionsToShowInCompact = null;
        ((Style)this).setBuilder(builder);
    }
    
    public MediaStyle setCancelButtonIntent(final PendingIntent mCancelButtonIntent) {
        this.mCancelButtonIntent = mCancelButtonIntent;
        return this;
    }
    
    public MediaStyle setMediaSession(final MediaSessionCompat.Token mToken) {
        this.mToken = mToken;
        return this;
    }
    
    public MediaStyle setShowActionsInCompactView(final int... mActionsToShowInCompact) {
        this.mActionsToShowInCompact = mActionsToShowInCompact;
        return this;
    }
    
    public MediaStyle setShowCancelButton(final boolean mShowCancelButton) {
        this.mShowCancelButton = mShowCancelButton;
        return this;
    }
}
