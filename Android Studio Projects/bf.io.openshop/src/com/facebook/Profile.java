package com.facebook;

import android.net.*;
import android.os.*;
import android.support.annotation.*;
import com.facebook.internal.*;
import org.json.*;

public final class Profile implements Parcelable
{
    public static final Parcelable$Creator<Profile> CREATOR;
    private static final String FIRST_NAME_KEY = "first_name";
    private static final String ID_KEY = "id";
    private static final String LAST_NAME_KEY = "last_name";
    private static final String LINK_URI_KEY = "link_uri";
    private static final String MIDDLE_NAME_KEY = "middle_name";
    private static final String NAME_KEY = "name";
    private final String firstName;
    private final String id;
    private final String lastName;
    private final Uri linkUri;
    private final String middleName;
    private final String name;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator() {
            public Profile createFromParcel(final Parcel parcel) {
                return new Profile(parcel, null);
            }
            
            public Profile[] newArray(final int n) {
                return new Profile[n];
            }
        };
    }
    
    private Profile(final Parcel parcel) {
        this.id = parcel.readString();
        this.firstName = parcel.readString();
        this.middleName = parcel.readString();
        this.lastName = parcel.readString();
        this.name = parcel.readString();
        final String string = parcel.readString();
        Uri parse;
        if (string == null) {
            parse = null;
        }
        else {
            parse = Uri.parse(string);
        }
        this.linkUri = parse;
    }
    
    public Profile(final String id, @Nullable final String firstName, @Nullable final String middleName, @Nullable final String lastName, @Nullable final String name, @Nullable final Uri linkUri) {
        Validate.notNullOrEmpty(id, "id");
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.name = name;
        this.linkUri = linkUri;
    }
    
    Profile(final JSONObject jsonObject) {
        this.id = jsonObject.optString("id", (String)null);
        this.firstName = jsonObject.optString("first_name", (String)null);
        this.middleName = jsonObject.optString("middle_name", (String)null);
        this.lastName = jsonObject.optString("last_name", (String)null);
        this.name = jsonObject.optString("name", (String)null);
        final String optString = jsonObject.optString("link_uri", (String)null);
        Uri parse = null;
        if (optString != null) {
            parse = Uri.parse(optString);
        }
        this.linkUri = parse;
    }
    
    public static void fetchProfileForCurrentAccessToken() {
        final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        if (currentAccessToken == null) {
            setCurrentProfile(null);
            return;
        }
        Utility.getGraphMeRequestWithCacheAsync(currentAccessToken.getToken(), (Utility.GraphMeRequestWithCacheCallback)new Utility.GraphMeRequestWithCacheCallback() {
            @Override
            public void onFailure(final FacebookException ex) {
            }
            
            @Override
            public void onSuccess(final JSONObject jsonObject) {
                final String optString = jsonObject.optString("id");
                if (optString == null) {
                    return;
                }
                final String optString2 = jsonObject.optString("link");
                final String optString3 = jsonObject.optString("first_name");
                final String optString4 = jsonObject.optString("middle_name");
                final String optString5 = jsonObject.optString("last_name");
                final String optString6 = jsonObject.optString("name");
                Uri parse;
                if (optString2 != null) {
                    parse = Uri.parse(optString2);
                }
                else {
                    parse = null;
                }
                Profile.setCurrentProfile(new Profile(optString, optString3, optString4, optString5, optString6, parse));
            }
        });
    }
    
    public static Profile getCurrentProfile() {
        return ProfileManager.getInstance().getCurrentProfile();
    }
    
    public static void setCurrentProfile(final Profile currentProfile) {
        ProfileManager.getInstance().setCurrentProfile(currentProfile);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof Profile)) {
                return false;
            }
            final Profile profile = (Profile)o;
            if (this.id.equals(profile.id) && this.firstName == null) {
                if (profile.firstName != null) {
                    return false;
                }
            }
            else if (this.firstName.equals(profile.firstName) && this.middleName == null) {
                if (profile.middleName != null) {
                    return false;
                }
            }
            else if (this.middleName.equals(profile.middleName) && this.lastName == null) {
                if (profile.lastName != null) {
                    return false;
                }
            }
            else if (this.lastName.equals(profile.lastName) && this.name == null) {
                if (profile.name != null) {
                    return false;
                }
            }
            else {
                if (!this.name.equals(profile.name) || this.linkUri != null) {
                    return this.linkUri.equals((Object)profile.linkUri);
                }
                if (profile.linkUri != null) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public Uri getLinkUri() {
        return this.linkUri;
    }
    
    public String getMiddleName() {
        return this.middleName;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Uri getProfilePictureUri(final int n, final int n2) {
        return ImageRequest.getProfilePictureUri(this.id, n, n2);
    }
    
    @Override
    public int hashCode() {
        int n = 527 + this.id.hashCode();
        if (this.firstName != null) {
            n = n * 31 + this.firstName.hashCode();
        }
        if (this.middleName != null) {
            n = n * 31 + this.middleName.hashCode();
        }
        if (this.lastName != null) {
            n = n * 31 + this.lastName.hashCode();
        }
        if (this.name != null) {
            n = n * 31 + this.name.hashCode();
        }
        if (this.linkUri != null) {
            n = n * 31 + this.linkUri.hashCode();
        }
        return n;
    }
    
    JSONObject toJSONObject() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", (Object)this.id);
            jsonObject.put("first_name", (Object)this.firstName);
            jsonObject.put("middle_name", (Object)this.middleName);
            jsonObject.put("last_name", (Object)this.lastName);
            jsonObject.put("name", (Object)this.name);
            if (this.linkUri != null) {
                jsonObject.put("link_uri", (Object)this.linkUri.toString());
            }
            return jsonObject;
        }
        catch (JSONException ex) {
            return null;
        }
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.id);
        parcel.writeString(this.firstName);
        parcel.writeString(this.middleName);
        parcel.writeString(this.lastName);
        parcel.writeString(this.name);
        String string;
        if (this.linkUri == null) {
            string = null;
        }
        else {
            string = this.linkUri.toString();
        }
        parcel.writeString(string);
    }
}
