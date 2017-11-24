package com.facebook.share.model;

import android.os.*;

static final class ShareOpenGraphAction$1 implements Parcelable$Creator<ShareOpenGraphAction> {
    public ShareOpenGraphAction createFromParcel(final Parcel parcel) {
        return new ShareOpenGraphAction(parcel);
    }
    
    public ShareOpenGraphAction[] newArray(final int n) {
        return new ShareOpenGraphAction[n];
    }
}