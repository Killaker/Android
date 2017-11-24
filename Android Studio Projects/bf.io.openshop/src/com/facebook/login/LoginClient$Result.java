package com.facebook.login;

import java.util.*;
import com.facebook.*;
import android.os.*;
import com.facebook.internal.*;
import android.text.*;

public static class Result implements Parcelable
{
    public static final Parcelable$Creator<Result> CREATOR;
    final Code code;
    final String errorCode;
    final String errorMessage;
    public Map<String, String> loggingExtras;
    final Request request;
    final AccessToken token;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator() {
            public Result createFromParcel(final Parcel parcel) {
                return new Result(parcel);
            }
            
            public Result[] newArray(final int n) {
                return new Result[n];
            }
        };
    }
    
    private Result(final Parcel parcel) {
        this.code = Code.valueOf(parcel.readString());
        this.token = (AccessToken)parcel.readParcelable(AccessToken.class.getClassLoader());
        this.errorMessage = parcel.readString();
        this.errorCode = parcel.readString();
        this.request = (Request)parcel.readParcelable(Request.class.getClassLoader());
        this.loggingExtras = Utility.readStringMapFromParcel(parcel);
    }
    
    Result(final Request request, final Code code, final AccessToken token, final String errorMessage, final String errorCode) {
        Validate.notNull(code, "code");
        this.request = request;
        this.token = token;
        this.errorMessage = errorMessage;
        this.code = code;
        this.errorCode = errorCode;
    }
    
    static Result createCancelResult(final Request request, final String s) {
        return new Result(request, Code.CANCEL, null, s, null);
    }
    
    static Result createErrorResult(final Request request, final String s, final String s2) {
        return createErrorResult(request, s, s2, null);
    }
    
    static Result createErrorResult(final Request request, final String s, final String s2, final String s3) {
        return new Result(request, Code.ERROR, null, TextUtils.join((CharSequence)": ", (Iterable)Utility.asListNoNulls(s, s2)), s3);
    }
    
    static Result createTokenResult(final Request request, final AccessToken accessToken) {
        return new Result(request, Code.SUCCESS, accessToken, null, null);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.code.name());
        parcel.writeParcelable((Parcelable)this.token, n);
        parcel.writeString(this.errorMessage);
        parcel.writeString(this.errorCode);
        parcel.writeParcelable((Parcelable)this.request, n);
        Utility.writeStringMapToParcel(parcel, this.loggingExtras);
    }
    
    enum Code
    {
        CANCEL("cancel"), 
        ERROR("error"), 
        SUCCESS("success");
        
        private final String loggingValue;
        
        private Code(final String loggingValue) {
            this.loggingValue = loggingValue;
        }
        
        String getLoggingValue() {
            return this.loggingValue;
        }
    }
}
