package android.support.v4.media.session;

import android.os.*;

static final class PlaybackStateCompat$CustomAction$1 implements Parcelable$Creator<CustomAction> {
    public CustomAction createFromParcel(final Parcel parcel) {
        return new CustomAction(parcel);
    }
    
    public CustomAction[] newArray(final int n) {
        return new CustomAction[n];
    }
}