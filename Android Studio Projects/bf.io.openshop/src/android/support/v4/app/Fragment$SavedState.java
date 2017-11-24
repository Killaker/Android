package android.support.v4.app;

import android.os.*;

public static class SavedState implements Parcelable
{
    public static final Parcelable$Creator<SavedState> CREATOR;
    final Bundle mState;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<SavedState>() {
            public SavedState createFromParcel(final Parcel parcel) {
                return new SavedState(parcel, null);
            }
            
            public SavedState[] newArray(final int n) {
                return new SavedState[n];
            }
        };
    }
    
    SavedState(final Bundle mState) {
        this.mState = mState;
    }
    
    SavedState(final Parcel parcel, final ClassLoader classLoader) {
        this.mState = parcel.readBundle();
        if (classLoader != null && this.mState != null) {
            this.mState.setClassLoader(classLoader);
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeBundle(this.mState);
    }
}
