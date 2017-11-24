package android.support.v4.media.session;

import android.os.*;

static final class MediaSessionCompat$Token$1 implements Parcelable$Creator<Token> {
    public Token createFromParcel(final Parcel parcel) {
        Object o;
        if (Build$VERSION.SDK_INT >= 21) {
            o = parcel.readParcelable((ClassLoader)null);
        }
        else {
            o = parcel.readStrongBinder();
        }
        return new Token(o);
    }
    
    public Token[] newArray(final int n) {
        return new Token[n];
    }
}