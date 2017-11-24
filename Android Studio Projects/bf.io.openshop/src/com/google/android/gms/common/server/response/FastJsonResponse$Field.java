package com.google.android.gms.common.server.response;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.server.converter.*;
import android.os.*;
import java.util.*;
import com.google.android.gms.common.internal.*;

public static class Field<I, O> implements SafeParcelable
{
    public static final com.google.android.gms.common.server.response.zza CREATOR;
    private final int mVersionCode;
    protected final int zzamL;
    protected final boolean zzamM;
    protected final int zzamN;
    protected final boolean zzamO;
    protected final String zzamP;
    protected final int zzamQ;
    protected final Class<? extends FastJsonResponse> zzamR;
    protected final String zzamS;
    private FieldMappingDictionary zzamT;
    private zza<I, O> zzamU;
    
    static {
        CREATOR = new com.google.android.gms.common.server.response.zza();
    }
    
    Field(final int mVersionCode, final int zzamL, final boolean zzamM, final int zzamN, final boolean zzamO, final String zzamP, final int zzamQ, final String zzamS, final ConverterWrapper converterWrapper) {
        this.mVersionCode = mVersionCode;
        this.zzamL = zzamL;
        this.zzamM = zzamM;
        this.zzamN = zzamN;
        this.zzamO = zzamO;
        this.zzamP = zzamP;
        this.zzamQ = zzamQ;
        if (zzamS == null) {
            this.zzamR = null;
            this.zzamS = null;
        }
        else {
            this.zzamR = SafeParcelResponse.class;
            this.zzamS = zzamS;
        }
        if (converterWrapper == null) {
            this.zzamU = null;
            return;
        }
        this.zzamU = (zza<I, O>)converterWrapper.zzrh();
    }
    
    protected Field(final int zzamL, final boolean zzamM, final int zzamN, final boolean zzamO, final String zzamP, final int zzamQ, final Class<? extends FastJsonResponse> zzamR, final zza<I, O> zzamU) {
        this.mVersionCode = 1;
        this.zzamL = zzamL;
        this.zzamM = zzamM;
        this.zzamN = zzamN;
        this.zzamO = zzamO;
        this.zzamP = zzamP;
        this.zzamQ = zzamQ;
        this.zzamR = zzamR;
        if (zzamR == null) {
            this.zzamS = null;
        }
        else {
            this.zzamS = zzamR.getCanonicalName();
        }
        this.zzamU = zzamU;
    }
    
    public static Field zza(final String s, final int n, final zza<?, ?> zza, final boolean b) {
        return new Field(zza.zzrj(), b, zza.zzrk(), false, s, n, null, (zza<I, O>)zza);
    }
    
    public static <T extends FastJsonResponse> Field<T, T> zza(final String s, final int n, final Class<T> clazz) {
        return new Field<T, T>(11, false, 11, false, s, n, clazz, null);
    }
    
    public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> zzb(final String s, final int n, final Class<T> clazz) {
        return new Field<ArrayList<T>, ArrayList<T>>(11, true, 11, true, s, n, clazz, null);
    }
    
    public static Field<Integer, Integer> zzi(final String s, final int n) {
        return new Field<Integer, Integer>(0, false, 0, false, s, n, null, null);
    }
    
    public static Field<Double, Double> zzj(final String s, final int n) {
        return new Field<Double, Double>(4, false, 4, false, s, n, null, null);
    }
    
    public static Field<Boolean, Boolean> zzk(final String s, final int n) {
        return new Field<Boolean, Boolean>(6, false, 6, false, s, n, null, null);
    }
    
    public static Field<String, String> zzl(final String s, final int n) {
        return new Field<String, String>(7, false, 7, false, s, n, null, null);
    }
    
    public static Field<ArrayList<String>, ArrayList<String>> zzm(final String s, final int n) {
        return new Field<ArrayList<String>, ArrayList<String>>(7, true, 7, true, s, n, null, null);
    }
    
    public I convertBack(final O o) {
        return this.zzamU.convertBack(o);
    }
    
    public int describeContents() {
        final com.google.android.gms.common.server.response.zza creator = Field.CREATOR;
        return 0;
    }
    
    public int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Field\n");
        sb.append("            versionCode=").append(this.mVersionCode).append('\n');
        sb.append("                 typeIn=").append(this.zzamL).append('\n');
        sb.append("            typeInArray=").append(this.zzamM).append('\n');
        sb.append("                typeOut=").append(this.zzamN).append('\n');
        sb.append("           typeOutArray=").append(this.zzamO).append('\n');
        sb.append("        outputFieldName=").append(this.zzamP).append('\n');
        sb.append("      safeParcelFieldId=").append(this.zzamQ).append('\n');
        sb.append("       concreteTypeName=").append(this.zzru()).append('\n');
        if (this.zzrt() != null) {
            sb.append("     concreteType.class=").append(this.zzrt().getCanonicalName()).append('\n');
        }
        final StringBuilder append = sb.append("          converterName=");
        String canonicalName;
        if (this.zzamU == null) {
            canonicalName = "null";
        }
        else {
            canonicalName = this.zzamU.getClass().getCanonicalName();
        }
        append.append(canonicalName).append('\n');
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final com.google.android.gms.common.server.response.zza creator = Field.CREATOR;
        com.google.android.gms.common.server.response.zza.zza(this, parcel, n);
    }
    
    public void zza(final FieldMappingDictionary zzamT) {
        this.zzamT = zzamT;
    }
    
    public int zzrj() {
        return this.zzamL;
    }
    
    public int zzrk() {
        return this.zzamN;
    }
    
    public Field<I, O> zzro() {
        return new Field<I, O>(this.mVersionCode, this.zzamL, this.zzamM, this.zzamN, this.zzamO, this.zzamP, this.zzamQ, this.zzamS, this.zzrw());
    }
    
    public boolean zzrp() {
        return this.zzamM;
    }
    
    public boolean zzrq() {
        return this.zzamO;
    }
    
    public String zzrr() {
        return this.zzamP;
    }
    
    public int zzrs() {
        return this.zzamQ;
    }
    
    public Class<? extends FastJsonResponse> zzrt() {
        return this.zzamR;
    }
    
    String zzru() {
        if (this.zzamS == null) {
            return null;
        }
        return this.zzamS;
    }
    
    public boolean zzrv() {
        return this.zzamU != null;
    }
    
    ConverterWrapper zzrw() {
        if (this.zzamU == null) {
            return null;
        }
        return ConverterWrapper.zza(this.zzamU);
    }
    
    public Map<String, Field<?, ?>> zzrx() {
        zzx.zzz(this.zzamS);
        zzx.zzz(this.zzamT);
        return this.zzamT.zzcR(this.zzamS);
    }
}
