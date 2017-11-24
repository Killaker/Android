package com.facebook.login;

import android.os.*;
import java.util.*;

private static class RequestState implements Parcelable
{
    public static final Parcelable$Creator<RequestState> CREATOR;
    private long interval;
    private long lastPoll;
    private String requestCode;
    private String userCode;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<RequestState>() {
            public RequestState createFromParcel(final Parcel parcel) {
                return new RequestState(parcel);
            }
            
            public RequestState[] newArray(final int n) {
                return new RequestState[n];
            }
        };
    }
    
    RequestState() {
    }
    
    protected RequestState(final Parcel parcel) {
        this.userCode = parcel.readString();
        this.requestCode = parcel.readString();
        this.interval = parcel.readLong();
        this.lastPoll = parcel.readLong();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public long getInterval() {
        return this.interval;
    }
    
    public String getRequestCode() {
        return this.requestCode;
    }
    
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setInterval(final long interval) {
        this.interval = interval;
    }
    
    public void setLastPoll(final long lastPoll) {
        this.lastPoll = lastPoll;
    }
    
    public void setRequestCode(final String requestCode) {
        this.requestCode = requestCode;
    }
    
    public void setUserCode(final String userCode) {
        this.userCode = userCode;
    }
    
    public boolean withinLastRefreshWindow() {
        return this.lastPoll != 0L && new Date().getTime() - this.lastPoll - 1000L * this.interval < 0L;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.userCode);
        parcel.writeString(this.requestCode);
        parcel.writeLong(this.interval);
        parcel.writeLong(this.lastPoll);
    }
}
