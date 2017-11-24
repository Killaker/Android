package android.support.v4.media.session;

import android.os.*;

public static final class Token implements Parcelable
{
    public static final Parcelable$Creator<Token> CREATOR;
    private final Object mInner;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<Token>() {
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
        };
    }
    
    Token(final Object mInner) {
        this.mInner = mInner;
    }
    
    public static Token fromToken(final Object o) {
        if (o == null || Build$VERSION.SDK_INT < 21) {
            return null;
        }
        return new Token(MediaSessionCompatApi21.verifyToken(o));
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Object getToken() {
        return this.mInner;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (Build$VERSION.SDK_INT >= 21) {
            parcel.writeParcelable((Parcelable)this.mInner, n);
            return;
        }
        parcel.writeStrongBinder((IBinder)this.mInner);
    }
}
