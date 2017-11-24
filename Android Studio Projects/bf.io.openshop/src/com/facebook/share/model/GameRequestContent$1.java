package com.facebook.share.model;

import android.os.*;

static final class GameRequestContent$1 implements Parcelable$Creator<GameRequestContent> {
    public GameRequestContent createFromParcel(final Parcel parcel) {
        return new GameRequestContent(parcel);
    }
    
    public GameRequestContent[] newArray(final int n) {
        return new GameRequestContent[n];
    }
}