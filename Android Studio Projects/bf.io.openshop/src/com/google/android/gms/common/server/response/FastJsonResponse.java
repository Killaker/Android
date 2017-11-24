package com.google.android.gms.common.server.response;

import com.google.android.gms.internal.*;
import java.util.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.server.converter.*;
import android.os.*;
import java.io.*;

public abstract class FastJsonResponse
{
    private void zza(final StringBuilder sb, final Field field, final Object o) {
        if (field.zzrj() == 11) {
            sb.append(((FastJsonResponse)field.zzrt().cast(o)).toString());
            return;
        }
        if (field.zzrj() == 7) {
            sb.append("\"");
            sb.append(zznb.zzcU((String)o));
            sb.append("\"");
            return;
        }
        sb.append(o);
    }
    
    private void zza(final StringBuilder sb, final Field field, final ArrayList<Object> list) {
        sb.append("[");
        for (int i = 0; i < list.size(); ++i) {
            if (i > 0) {
                sb.append(",");
            }
            final Object value = list.get(i);
            if (value != null) {
                this.zza(sb, field, value);
            }
        }
        sb.append("]");
    }
    
    @Override
    public String toString() {
        final Map<String, Field<?, ?>> zzrl = this.zzrl();
        final StringBuilder sb = new StringBuilder(100);
        for (final String s : zzrl.keySet()) {
            final Field<Object, Object> field = zzrl.get(s);
            if (this.zza(field)) {
                final HashMap<String, String> zza = this.zza((Field<HashMap<String, String>, Object>)field, this.zzb(field));
                if (sb.length() == 0) {
                    sb.append("{");
                }
                else {
                    sb.append(",");
                }
                sb.append("\"").append(s).append("\":");
                if (zza == null) {
                    sb.append("null");
                }
                else {
                    switch (field.zzrk()) {
                        default: {
                            if (field.zzrp()) {
                                this.zza(sb, field, (ArrayList<Object>)zza);
                                continue;
                            }
                            this.zza(sb, field, zza);
                            continue;
                        }
                        case 8: {
                            sb.append("\"").append(zzmo.zzj((byte[])(Object)zza)).append("\"");
                            continue;
                        }
                        case 9: {
                            sb.append("\"").append(zzmo.zzk((byte[])(Object)zza)).append("\"");
                            continue;
                        }
                        case 10: {
                            zznc.zza(sb, zza);
                            continue;
                        }
                    }
                }
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        }
        else {
            sb.append("{}");
        }
        return sb.toString();
    }
    
    protected <O, I> I zza(final Field<I, O> field, Object convertBack) {
        if (((Field<Object, Object>)field).zzamU != null) {
            convertBack = field.convertBack(convertBack);
        }
        return (I)convertBack;
    }
    
    protected boolean zza(final Field field) {
        if (field.zzrk() != 11) {
            return this.zzcO(field.zzrr());
        }
        if (field.zzrq()) {
            return this.zzcQ(field.zzrr());
        }
        return this.zzcP(field.zzrr());
    }
    
    protected Object zzb(final Field field) {
        final String zzrr = field.zzrr();
        if (field.zzrt() != null) {
            zzx.zza(this.zzcN(field.zzrr()) == null, "Concrete field shouldn't be value object: %s", field.zzrr());
            HashMap<String, Object> hashMap;
            if (field.zzrq()) {
                hashMap = this.zzrn();
            }
            else {
                hashMap = this.zzrm();
            }
            if (hashMap != null) {
                return hashMap.get(zzrr);
            }
            try {
                return this.getClass().getMethod("get" + Character.toUpperCase(zzrr.charAt(0)) + zzrr.substring(1), (Class<?>[])new Class[0]).invoke(this, new Object[0]);
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return this.zzcN(field.zzrr());
    }
    
    protected abstract Object zzcN(final String p0);
    
    protected abstract boolean zzcO(final String p0);
    
    protected boolean zzcP(final String s) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }
    
    protected boolean zzcQ(final String s) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }
    
    public abstract Map<String, Field<?, ?>> zzrl();
    
    public HashMap<String, Object> zzrm() {
        return null;
    }
    
    public HashMap<String, Object> zzrn() {
        return null;
    }
    
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
    
    public interface zza<I, O>
    {
        I convertBack(final O p0);
        
        int zzrj();
        
        int zzrk();
    }
}
