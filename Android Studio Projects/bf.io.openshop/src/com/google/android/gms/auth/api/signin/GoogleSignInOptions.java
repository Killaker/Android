package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.api.*;
import android.accounts.*;
import android.text.*;
import org.json.*;
import com.google.android.gms.auth.api.signin.internal.*;
import android.os.*;
import android.support.annotation.*;
import com.google.android.gms.common.internal.*;
import java.util.*;

public class GoogleSignInOptions implements Optional, SafeParcelable
{
    public static final Parcelable$Creator<GoogleSignInOptions> CREATOR;
    public static final GoogleSignInOptions DEFAULT_SIGN_IN;
    private static Comparator<Scope> zzWV;
    public static final Scope zzWW;
    public static final Scope zzWX;
    public static final Scope zzWY;
    final int versionCode;
    private Account zzTI;
    private final ArrayList<Scope> zzWZ;
    private boolean zzXa;
    private final boolean zzXb;
    private final boolean zzXc;
    private String zzXd;
    private String zzXe;
    
    static {
        zzWW = new Scope("profile");
        zzWX = new Scope("email");
        zzWY = new Scope("openid");
        DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
        CREATOR = (Parcelable$Creator)new com.google.android.gms.auth.api.signin.zzc();
        GoogleSignInOptions.zzWV = new Comparator<Scope>() {
            public int zza(final Scope scope, final Scope scope2) {
                return scope.zzpb().compareTo(scope2.zzpb());
            }
        };
    }
    
    GoogleSignInOptions(final int versionCode, final ArrayList<Scope> zzWZ, final Account zzTI, final boolean zzXa, final boolean zzXb, final boolean zzXc, final String zzXd, final String zzXe) {
        this.versionCode = versionCode;
        this.zzWZ = zzWZ;
        this.zzTI = zzTI;
        this.zzXa = zzXa;
        this.zzXb = zzXb;
        this.zzXc = zzXc;
        this.zzXd = zzXd;
        this.zzXe = zzXe;
    }
    
    private GoogleSignInOptions(final Set<Scope> set, final Account account, final boolean b, final boolean b2, final boolean b3, final String s, final String s2) {
        this(2, new ArrayList<Scope>(set), account, b, b2, b3, s, s2);
    }
    
    @Nullable
    public static GoogleSignInOptions zzbJ(@Nullable final String s) throws JSONException {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        final JSONObject jsonObject = new JSONObject(s);
        final HashSet<Scope> set = new HashSet<Scope>();
        final JSONArray jsonArray = jsonObject.getJSONArray("scopes");
        for (int length = jsonArray.length(), i = 0; i < length; ++i) {
            set.add(new Scope(jsonArray.getString(i)));
        }
        final String optString = jsonObject.optString("accountName", (String)null);
        Account account;
        if (!TextUtils.isEmpty((CharSequence)optString)) {
            account = new Account(optString, "com.google");
        }
        else {
            account = null;
        }
        return new GoogleSignInOptions(set, account, jsonObject.getBoolean("idTokenRequested"), jsonObject.getBoolean("serverAuthRequested"), jsonObject.getBoolean("forceCodeForRefreshToken"), jsonObject.optString("serverClientId", (String)null), jsonObject.optString("hostedDomain", (String)null));
    }
    
    private JSONObject zzmJ() {
        final JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray();
            Collections.sort(this.zzWZ, GoogleSignInOptions.zzWV);
            final Iterator<Scope> iterator = this.zzWZ.iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)iterator.next().zzpb());
            }
        }
        catch (JSONException ex) {
            throw new RuntimeException((Throwable)ex);
        }
        jsonObject.put("scopes", (Object)jsonArray);
        if (this.zzTI != null) {
            jsonObject.put("accountName", (Object)this.zzTI.name);
        }
        jsonObject.put("idTokenRequested", this.zzXa);
        jsonObject.put("forceCodeForRefreshToken", this.zzXc);
        jsonObject.put("serverAuthRequested", this.zzXb);
        if (!TextUtils.isEmpty((CharSequence)this.zzXd)) {
            jsonObject.put("serverClientId", (Object)this.zzXd);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzXe)) {
            jsonObject.put("hostedDomain", (Object)this.zzXe);
        }
        return jsonObject;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != null) {
            try {
                final GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions)o;
                if (this.zzWZ.size() == googleSignInOptions.zzmN().size() && this.zzWZ.containsAll(googleSignInOptions.zzmN())) {
                    if (this.zzTI == null) {
                        if (googleSignInOptions.getAccount() != null) {
                            return false;
                        }
                    }
                    else if (!this.zzTI.equals((Object)googleSignInOptions.getAccount())) {
                        return false;
                    }
                    if (TextUtils.isEmpty((CharSequence)this.zzXd)) {
                        if (!TextUtils.isEmpty((CharSequence)googleSignInOptions.zzmR())) {
                            return false;
                        }
                    }
                    else if (!this.zzXd.equals(googleSignInOptions.zzmR())) {
                        return false;
                    }
                    if (this.zzXc == googleSignInOptions.zzmQ() && this.zzXa == googleSignInOptions.zzmO() && this.zzXb == googleSignInOptions.zzmP()) {
                        return true;
                    }
                }
            }
            catch (ClassCastException ex) {
                return false;
            }
        }
        return false;
    }
    
    public Account getAccount() {
        return this.zzTI;
    }
    
    public Scope[] getScopeArray() {
        return this.zzWZ.toArray(new Scope[this.zzWZ.size()]);
    }
    
    @Override
    public int hashCode() {
        final ArrayList<String> list = (ArrayList<String>)new ArrayList<Comparable>();
        final Iterator<Scope> iterator = this.zzWZ.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().zzpb());
        }
        Collections.sort((List<Comparable>)list);
        return new com.google.android.gms.auth.api.signin.internal.zze().zzp(list).zzp(this.zzTI).zzp(this.zzXd).zzP(this.zzXc).zzP(this.zzXa).zzP(this.zzXb).zzne();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        com.google.android.gms.auth.api.signin.zzc.zza(this, parcel, n);
    }
    
    public String zzmI() {
        return this.zzmJ().toString();
    }
    
    public ArrayList<Scope> zzmN() {
        return new ArrayList<Scope>(this.zzWZ);
    }
    
    public boolean zzmO() {
        return this.zzXa;
    }
    
    public boolean zzmP() {
        return this.zzXb;
    }
    
    public boolean zzmQ() {
        return this.zzXc;
    }
    
    public String zzmR() {
        return this.zzXd;
    }
    
    public String zzmS() {
        return this.zzXe;
    }
    
    public static final class Builder
    {
        private Account zzTI;
        private boolean zzXa;
        private boolean zzXb;
        private boolean zzXc;
        private String zzXd;
        private String zzXe;
        private Set<Scope> zzXf;
        
        public Builder() {
            this.zzXf = new HashSet<Scope>();
        }
        
        public Builder(@NonNull final GoogleSignInOptions googleSignInOptions) {
            this.zzXf = new HashSet<Scope>();
            zzx.zzz(googleSignInOptions);
            this.zzXf = new HashSet<Scope>(googleSignInOptions.zzWZ);
            this.zzXb = googleSignInOptions.zzXb;
            this.zzXc = googleSignInOptions.zzXc;
            this.zzXa = googleSignInOptions.zzXa;
            this.zzXd = googleSignInOptions.zzXd;
            this.zzTI = googleSignInOptions.zzTI;
            this.zzXe = googleSignInOptions.zzXe;
        }
        
        private String zzbK(final String s) {
            zzx.zzcM(s);
            zzx.zzb(this.zzXd == null || this.zzXd.equals(s), (Object)"two different server client ids provided");
            return s;
        }
        
        public GoogleSignInOptions build() {
            if (this.zzXa && (this.zzTI == null || !this.zzXf.isEmpty())) {
                this.requestId();
            }
            return new GoogleSignInOptions(this.zzXf, this.zzTI, this.zzXa, this.zzXb, this.zzXc, this.zzXd, this.zzXe, null);
        }
        
        public Builder requestEmail() {
            this.zzXf.add(GoogleSignInOptions.zzWX);
            return this;
        }
        
        public Builder requestId() {
            this.zzXf.add(GoogleSignInOptions.zzWY);
            return this;
        }
        
        public Builder requestIdToken(final String s) {
            this.zzXa = true;
            this.zzXd = this.zzbK(s);
            return this;
        }
        
        public Builder requestProfile() {
            this.zzXf.add(GoogleSignInOptions.zzWW);
            return this;
        }
        
        public Builder requestScopes(final Scope scope, final Scope... array) {
            this.zzXf.add(scope);
            this.zzXf.addAll(Arrays.asList(array));
            return this;
        }
        
        public Builder requestServerAuthCode(final String s) {
            return this.requestServerAuthCode(s, false);
        }
        
        public Builder requestServerAuthCode(final String s, final boolean zzXc) {
            this.zzXb = true;
            this.zzXd = this.zzbK(s);
            this.zzXc = zzXc;
            return this;
        }
        
        public Builder setAccountName(final String s) {
            this.zzTI = new Account(zzx.zzcM(s), "com.google");
            return this;
        }
        
        public Builder setHostedDomain(final String s) {
            this.zzXe = zzx.zzcM(s);
            return this;
        }
    }
}
