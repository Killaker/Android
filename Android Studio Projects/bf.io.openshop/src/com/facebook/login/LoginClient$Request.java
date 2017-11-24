package com.facebook.login;

import android.os.*;
import java.util.*;
import com.facebook.internal.*;

public static class Request implements Parcelable
{
    public static final Parcelable$Creator<Request> CREATOR;
    private final String applicationId;
    private final String authId;
    private final DefaultAudience defaultAudience;
    private boolean isRerequest;
    private final LoginBehavior loginBehavior;
    private Set<String> permissions;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator() {
            public Request createFromParcel(final Parcel parcel) {
                return new Request(parcel);
            }
            
            public Request[] newArray(final int n) {
                return new Request[n];
            }
        };
    }
    
    private Request(final Parcel parcel) {
        this.isRerequest = false;
        final String string = parcel.readString();
        LoginBehavior value;
        if (string != null) {
            value = LoginBehavior.valueOf(string);
        }
        else {
            value = null;
        }
        this.loginBehavior = value;
        final ArrayList<String> list = new ArrayList<String>();
        parcel.readStringList((List)list);
        this.permissions = new HashSet<String>(list);
        final String string2 = parcel.readString();
        DefaultAudience value2 = null;
        if (string2 != null) {
            value2 = DefaultAudience.valueOf(string2);
        }
        this.defaultAudience = value2;
        this.applicationId = parcel.readString();
        this.authId = parcel.readString();
        this.isRerequest = (parcel.readByte() != 0);
    }
    
    Request(final LoginBehavior loginBehavior, Set<String> permissions, final DefaultAudience defaultAudience, final String applicationId, final String authId) {
        this.isRerequest = false;
        this.loginBehavior = loginBehavior;
        if (permissions == null) {
            permissions = new HashSet<String>();
        }
        this.permissions = permissions;
        this.defaultAudience = defaultAudience;
        this.applicationId = applicationId;
        this.authId = authId;
    }
    
    public int describeContents() {
        return 0;
    }
    
    String getApplicationId() {
        return this.applicationId;
    }
    
    String getAuthId() {
        return this.authId;
    }
    
    DefaultAudience getDefaultAudience() {
        return this.defaultAudience;
    }
    
    LoginBehavior getLoginBehavior() {
        return this.loginBehavior;
    }
    
    Set<String> getPermissions() {
        return this.permissions;
    }
    
    boolean hasPublishPermission() {
        final Iterator<String> iterator = this.permissions.iterator();
        while (iterator.hasNext()) {
            if (LoginManager.isPublishPermission(iterator.next())) {
                return true;
            }
        }
        return false;
    }
    
    boolean isRerequest() {
        return this.isRerequest;
    }
    
    void setPermissions(final Set<String> permissions) {
        Validate.notNull(permissions, "permissions");
        this.permissions = permissions;
    }
    
    void setRerequest(final boolean isRerequest) {
        this.isRerequest = isRerequest;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        String name;
        if (this.loginBehavior != null) {
            name = this.loginBehavior.name();
        }
        else {
            name = null;
        }
        parcel.writeString(name);
        parcel.writeStringList((List)new ArrayList(this.permissions));
        final DefaultAudience defaultAudience = this.defaultAudience;
        String name2 = null;
        if (defaultAudience != null) {
            name2 = this.defaultAudience.name();
        }
        parcel.writeString(name2);
        parcel.writeString(this.applicationId);
        parcel.writeString(this.authId);
        boolean b;
        if (this.isRerequest) {
            b = true;
        }
        else {
            b = false;
        }
        parcel.writeByte((byte)(byte)(b ? 1 : 0));
    }
}
