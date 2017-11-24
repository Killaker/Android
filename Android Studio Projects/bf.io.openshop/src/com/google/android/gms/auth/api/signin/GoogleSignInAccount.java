package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.api.*;
import android.net.*;
import com.google.android.gms.internal.*;
import android.support.annotation.*;
import com.google.android.gms.common.internal.*;
import android.text.*;
import org.json.*;
import java.util.*;
import android.os.*;

public class GoogleSignInAccount implements SafeParcelable
{
    public static final Parcelable$Creator<GoogleSignInAccount> CREATOR;
    public static zzmq zzWO;
    private static Comparator<Scope> zzWV;
    final int versionCode;
    List<Scope> zzVs;
    private String zzWP;
    private String zzWQ;
    private Uri zzWR;
    private String zzWS;
    private long zzWT;
    private String zzWU;
    private String zzWk;
    private String zzyv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
        GoogleSignInAccount.zzWO = zzmt.zzsc();
        GoogleSignInAccount.zzWV = new Comparator<Scope>() {
            public int zza(final Scope scope, final Scope scope2) {
                return scope.zzpb().compareTo(scope2.zzpb());
            }
        };
    }
    
    GoogleSignInAccount(final int versionCode, final String zzyv, final String zzWk, final String zzWP, final String zzWQ, final Uri zzWR, final String zzWS, final long zzWT, final String zzWU, final List<Scope> zzVs) {
        this.versionCode = versionCode;
        this.zzyv = zzyv;
        this.zzWk = zzWk;
        this.zzWP = zzWP;
        this.zzWQ = zzWQ;
        this.zzWR = zzWR;
        this.zzWS = zzWS;
        this.zzWT = zzWT;
        this.zzWU = zzWU;
        this.zzVs = zzVs;
    }
    
    public static GoogleSignInAccount zza(@Nullable final String s, @Nullable final String s2, @Nullable final String s3, @Nullable final String s4, @Nullable final Uri uri, @Nullable Long value, @NonNull final String s5, @NonNull final Set<Scope> set) {
        if (value == null) {
            value = GoogleSignInAccount.zzWO.currentTimeMillis() / 1000L;
        }
        return new GoogleSignInAccount(2, s, s2, s3, s4, uri, null, value, zzx.zzcM(s5), new ArrayList<Scope>(zzx.zzz(set)));
    }
    
    @Nullable
    public static GoogleSignInAccount zzbH(@Nullable final String s) throws JSONException {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        final JSONObject jsonObject = new JSONObject(s);
        final String optString = jsonObject.optString("photoUrl", (String)null);
        Uri parse;
        if (!TextUtils.isEmpty((CharSequence)optString)) {
            parse = Uri.parse(optString);
        }
        else {
            parse = null;
        }
        final long long1 = Long.parseLong(jsonObject.getString("expirationTime"));
        final HashSet<Scope> set = new HashSet<Scope>();
        final JSONArray jsonArray = jsonObject.getJSONArray("grantedScopes");
        for (int length = jsonArray.length(), i = 0; i < length; ++i) {
            set.add(new Scope(jsonArray.getString(i)));
        }
        return zza(jsonObject.optString("id"), jsonObject.optString("tokenId", (String)null), jsonObject.optString("email", (String)null), jsonObject.optString("displayName", (String)null), parse, long1, jsonObject.getString("obfuscatedIdentifier"), set).zzbI(jsonObject.optString("serverAuthCode", (String)null));
    }
    
    private JSONObject zzmJ() {
        final JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray;
        try {
            if (this.getId() != null) {
                jsonObject.put("id", (Object)this.getId());
            }
            if (this.getIdToken() != null) {
                jsonObject.put("tokenId", (Object)this.getIdToken());
            }
            if (this.getEmail() != null) {
                jsonObject.put("email", (Object)this.getEmail());
            }
            if (this.getDisplayName() != null) {
                jsonObject.put("displayName", (Object)this.getDisplayName());
            }
            if (this.getPhotoUrl() != null) {
                jsonObject.put("photoUrl", (Object)this.getPhotoUrl().toString());
            }
            if (this.getServerAuthCode() != null) {
                jsonObject.put("serverAuthCode", (Object)this.getServerAuthCode());
            }
            jsonObject.put("expirationTime", this.zzWT);
            jsonObject.put("obfuscatedIdentifier", (Object)this.zzmL());
            jsonArray = new JSONArray();
            Collections.sort(this.zzVs, GoogleSignInAccount.zzWV);
            final Iterator<Scope> iterator = this.zzVs.iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)iterator.next().zzpb());
            }
        }
        catch (JSONException ex) {
            throw new RuntimeException((Throwable)ex);
        }
        jsonObject.put("grantedScopes", (Object)jsonArray);
        return jsonObject;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof GoogleSignInAccount && ((GoogleSignInAccount)o).zzmI().equals(this.zzmI());
    }
    
    @Nullable
    public String getDisplayName() {
        return this.zzWQ;
    }
    
    @Nullable
    public String getEmail() {
        return this.zzWP;
    }
    
    @NonNull
    public Set<Scope> getGrantedScopes() {
        return new HashSet<Scope>(this.zzVs);
    }
    
    @Nullable
    public String getId() {
        return this.zzyv;
    }
    
    @Nullable
    public String getIdToken() {
        return this.zzWk;
    }
    
    @Nullable
    public Uri getPhotoUrl() {
        return this.zzWR;
    }
    
    @Nullable
    public String getServerAuthCode() {
        return this.zzWS;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
    
    public boolean zzb() {
        return GoogleSignInAccount.zzWO.currentTimeMillis() / 1000L >= this.zzWT - 300L;
    }
    
    public GoogleSignInAccount zzbI(final String zzWS) {
        this.zzWS = zzWS;
        return this;
    }
    
    public String zzmI() {
        return this.zzmJ().toString();
    }
    
    public long zzmK() {
        return this.zzWT;
    }
    
    @NonNull
    public String zzmL() {
        return this.zzWU;
    }
    
    public String zzmM() {
        final JSONObject zzmJ = this.zzmJ();
        zzmJ.remove("serverAuthCode");
        return zzmJ.toString();
    }
}
