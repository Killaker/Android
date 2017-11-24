package android.support.v4.media.session;

import android.os.*;

static final class ResultReceiverWrapper implements Parcelable
{
    public static final Parcelable$Creator<ResultReceiverWrapper> CREATOR;
    private ResultReceiver mResultReceiver;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ResultReceiverWrapper>() {
            public ResultReceiverWrapper createFromParcel(final Parcel parcel) {
                return new ResultReceiverWrapper(parcel);
            }
            
            public ResultReceiverWrapper[] newArray(final int n) {
                return new ResultReceiverWrapper[n];
            }
        };
    }
    
    ResultReceiverWrapper(final Parcel parcel) {
        this.mResultReceiver = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
    }
    
    public ResultReceiverWrapper(final ResultReceiver mResultReceiver) {
        this.mResultReceiver = mResultReceiver;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        this.mResultReceiver.writeToParcel(parcel, n);
    }
}
