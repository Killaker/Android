package com.google.android.gms.common.server.response;

import com.google.android.gms.common.internal.*;
import com.google.android.gms.internal.*;
import android.os.*;
import java.util.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class SafeParcelResponse extends FastJsonResponse implements SafeParcelable
{
    public static final zze CREATOR;
    private final String mClassName;
    private final int mVersionCode;
    private final FieldMappingDictionary zzamT;
    private final Parcel zzana;
    private final int zzanb;
    private int zzanc;
    private int zzand;
    
    static {
        CREATOR = new zze();
    }
    
    SafeParcelResponse(final int mVersionCode, final Parcel parcel, final FieldMappingDictionary zzamT) {
        this.mVersionCode = mVersionCode;
        this.zzana = zzx.zzz(parcel);
        this.zzanb = 2;
        this.zzamT = zzamT;
        if (this.zzamT == null) {
            this.mClassName = null;
        }
        else {
            this.mClassName = this.zzamT.zzrB();
        }
        this.zzanc = 2;
    }
    
    private SafeParcelResponse(final SafeParcelable safeParcelable, final FieldMappingDictionary fieldMappingDictionary, final String s) {
        this.mVersionCode = 1;
        safeParcelable.writeToParcel(this.zzana = Parcel.obtain(), 0);
        this.zzanb = 1;
        this.zzamT = zzx.zzz(fieldMappingDictionary);
        this.mClassName = zzx.zzz(s);
        this.zzanc = 2;
    }
    
    private static HashMap<Integer, Map.Entry<String, Field<?, ?>>> zzN(final Map<String, Field<?, ?>> map) {
        final HashMap<Integer, Map.Entry<K, Field>> hashMap = (HashMap<Integer, Map.Entry<K, Field>>)new HashMap<Integer, Map.Entry<String, Field<?, ?>>>();
        for (final Map.Entry<String, Field<?, ?>> entry : map.entrySet()) {
            hashMap.put(((Field)entry.getValue()).zzrs(), entry);
        }
        return (HashMap<Integer, Map.Entry<String, Field<?, ?>>>)hashMap;
    }
    
    public static <T extends com.google.android.gms.common.server.response.FastJsonResponse> SafeParcelResponse zza(final T t) {
        return new SafeParcelResponse((SafeParcelable)t, zzb((FastJsonResponse)t), t.getClass().getCanonicalName());
    }
    
    private static void zza(final FieldMappingDictionary fieldMappingDictionary, final FastJsonResponse fastJsonResponse) {
        final Class<? extends FastJsonResponse> class1 = fastJsonResponse.getClass();
        if (!fieldMappingDictionary.zzb(class1)) {
            final Map<String, Field<?, ?>> zzrl = fastJsonResponse.zzrl();
            fieldMappingDictionary.zza(class1, zzrl);
            final Iterator<String> iterator = zzrl.keySet().iterator();
            while (iterator.hasNext()) {
                final Field<?, ?> field = zzrl.get(iterator.next());
                final Class<? extends FastJsonResponse> zzrt = field.zzrt();
                if (zzrt != null) {
                    try {
                        zza(fieldMappingDictionary, (FastJsonResponse)zzrt.newInstance());
                        continue;
                    }
                    catch (InstantiationException ex) {
                        throw new IllegalStateException("Could not instantiate an object of type " + field.zzrt().getCanonicalName(), ex);
                    }
                    catch (IllegalAccessException ex2) {
                        throw new IllegalStateException("Could not access object of type " + field.zzrt().getCanonicalName(), ex2);
                    }
                    break;
                }
            }
        }
    }
    
    private void zza(final StringBuilder sb, final int n, final Object o) {
        switch (n) {
            default: {
                throw new IllegalArgumentException("Unknown type = " + n);
            }
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6: {
                sb.append(o);
            }
            case 7: {
                sb.append("\"").append(zznb.zzcU(o.toString())).append("\"");
            }
            case 8: {
                sb.append("\"").append(zzmo.zzj((byte[])o)).append("\"");
            }
            case 9: {
                sb.append("\"").append(zzmo.zzk((byte[])o));
                sb.append("\"");
            }
            case 10: {
                zznc.zza(sb, (HashMap<String, String>)o);
            }
            case 11: {
                throw new IllegalArgumentException("Method does not accept concrete type.");
            }
        }
    }
    
    private void zza(final StringBuilder sb, final Field<?, ?> field, final Parcel parcel, final int n) {
        switch (field.zzrk()) {
            default: {
                throw new IllegalArgumentException("Unknown field out type = " + field.zzrk());
            }
            case 0: {
                this.zzb(sb, field, this.zza(field, com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, n)));
            }
            case 1: {
                this.zzb(sb, field, this.zza(field, com.google.android.gms.common.internal.safeparcel.zza.zzk(parcel, n)));
            }
            case 2: {
                this.zzb(sb, field, this.zza(field, com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, n)));
            }
            case 3: {
                this.zzb(sb, field, this.zza(field, com.google.android.gms.common.internal.safeparcel.zza.zzl(parcel, n)));
            }
            case 4: {
                this.zzb(sb, field, this.zza(field, com.google.android.gms.common.internal.safeparcel.zza.zzn(parcel, n)));
            }
            case 5: {
                this.zzb(sb, field, this.zza(field, com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, n)));
            }
            case 6: {
                this.zzb(sb, field, this.zza(field, com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, n)));
            }
            case 7: {
                this.zzb(sb, field, this.zza(field, com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, n)));
            }
            case 8:
            case 9: {
                this.zzb(sb, field, this.zza(field, com.google.android.gms.common.internal.safeparcel.zza.zzs(parcel, n)));
            }
            case 10: {
                this.zzb(sb, field, this.zza(field, zzl(com.google.android.gms.common.internal.safeparcel.zza.zzr(parcel, n))));
            }
            case 11: {
                throw new IllegalArgumentException("Method does not accept concrete type.");
            }
        }
    }
    
    private void zza(final StringBuilder sb, final String s, final Field<?, ?> field, final Parcel parcel, final int n) {
        sb.append("\"").append(s).append("\":");
        if (field.zzrv()) {
            this.zza(sb, field, parcel, n);
            return;
        }
        this.zzb(sb, field, parcel, n);
    }
    
    private void zza(final StringBuilder sb, final Map<String, Field<?, ?>> map, final Parcel parcel) {
        final HashMap<Integer, Map.Entry<String, Field<?, ?>>> zzN = zzN(map);
        sb.append('{');
        final int zzau = com.google.android.gms.common.internal.safeparcel.zza.zzau(parcel);
        int n = 0;
        while (parcel.dataPosition() < zzau) {
            final int zzat = com.google.android.gms.common.internal.safeparcel.zza.zzat(parcel);
            final Map.Entry<String, V> entry = (Map.Entry<String, V>)zzN.get(com.google.android.gms.common.internal.safeparcel.zza.zzca(zzat));
            if (entry != null) {
                if (n != 0) {
                    sb.append(",");
                }
                this.zza(sb, entry.getKey(), (Field<?, ?>)entry.getValue(), parcel, zzat);
                n = 1;
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        sb.append('}');
    }
    
    private static FieldMappingDictionary zzb(final FastJsonResponse fastJsonResponse) {
        final FieldMappingDictionary fieldMappingDictionary = new FieldMappingDictionary(fastJsonResponse.getClass());
        zza(fieldMappingDictionary, fastJsonResponse);
        fieldMappingDictionary.zzrz();
        fieldMappingDictionary.zzry();
        return fieldMappingDictionary;
    }
    
    private void zzb(final StringBuilder sb, final Field<?, ?> field, final Parcel parcel, final int n) {
        if (field.zzrq()) {
            sb.append("[");
            switch (field.zzrk()) {
                default: {
                    throw new IllegalStateException("Unknown field type out.");
                }
                case 0: {
                    zzmn.zza(sb, com.google.android.gms.common.internal.safeparcel.zza.zzv(parcel, n));
                    break;
                }
                case 1: {
                    zzmn.zza(sb, com.google.android.gms.common.internal.safeparcel.zza.zzx(parcel, n));
                    break;
                }
                case 2: {
                    zzmn.zza(sb, com.google.android.gms.common.internal.safeparcel.zza.zzw(parcel, n));
                    break;
                }
                case 3: {
                    zzmn.zza(sb, com.google.android.gms.common.internal.safeparcel.zza.zzy(parcel, n));
                    break;
                }
                case 4: {
                    zzmn.zza(sb, com.google.android.gms.common.internal.safeparcel.zza.zzz(parcel, n));
                    break;
                }
                case 5: {
                    zzmn.zza(sb, com.google.android.gms.common.internal.safeparcel.zza.zzA(parcel, n));
                    break;
                }
                case 6: {
                    zzmn.zza(sb, com.google.android.gms.common.internal.safeparcel.zza.zzu(parcel, n));
                    break;
                }
                case 7: {
                    zzmn.zza(sb, com.google.android.gms.common.internal.safeparcel.zza.zzB(parcel, n));
                    break;
                }
                case 8:
                case 9:
                case 10: {
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                }
                case 11: {
                    final Parcel[] zzF = com.google.android.gms.common.internal.safeparcel.zza.zzF(parcel, n);
                    for (int length = zzF.length, i = 0; i < length; ++i) {
                        if (i > 0) {
                            sb.append(",");
                        }
                        zzF[i].setDataPosition(0);
                        this.zza(sb, field.zzrx(), zzF[i]);
                    }
                    break;
                }
            }
            sb.append("]");
            return;
        }
        switch (field.zzrk()) {
            default: {
                throw new IllegalStateException("Unknown field type out");
            }
            case 0: {
                sb.append(com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, n));
            }
            case 1: {
                sb.append(com.google.android.gms.common.internal.safeparcel.zza.zzk(parcel, n));
            }
            case 2: {
                sb.append(com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, n));
            }
            case 3: {
                sb.append(com.google.android.gms.common.internal.safeparcel.zza.zzl(parcel, n));
            }
            case 4: {
                sb.append(com.google.android.gms.common.internal.safeparcel.zza.zzn(parcel, n));
            }
            case 5: {
                sb.append(com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, n));
            }
            case 6: {
                sb.append(com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, n));
            }
            case 7: {
                sb.append("\"").append(zznb.zzcU(com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, n))).append("\"");
            }
            case 8: {
                sb.append("\"").append(zzmo.zzj(com.google.android.gms.common.internal.safeparcel.zza.zzs(parcel, n))).append("\"");
            }
            case 9: {
                sb.append("\"").append(zzmo.zzk(com.google.android.gms.common.internal.safeparcel.zza.zzs(parcel, n)));
                sb.append("\"");
            }
            case 10: {
                final Bundle zzr = com.google.android.gms.common.internal.safeparcel.zza.zzr(parcel, n);
                final Set keySet = zzr.keySet();
                keySet.size();
                sb.append("{");
                final Iterator<String> iterator = keySet.iterator();
                int n2 = 1;
                while (iterator.hasNext()) {
                    final String s = iterator.next();
                    if (n2 == 0) {
                        sb.append(",");
                    }
                    sb.append("\"").append(s).append("\"");
                    sb.append(":");
                    sb.append("\"").append(zznb.zzcU(zzr.getString(s))).append("\"");
                    n2 = 0;
                }
                sb.append("}");
            }
            case 11: {
                final Parcel zzE = com.google.android.gms.common.internal.safeparcel.zza.zzE(parcel, n);
                zzE.setDataPosition(0);
                this.zza(sb, field.zzrx(), zzE);
            }
        }
    }
    
    private void zzb(final StringBuilder sb, final Field<?, ?> field, final Object o) {
        if (field.zzrp()) {
            this.zzb(sb, field, (ArrayList<?>)o);
            return;
        }
        this.zza(sb, field.zzrj(), o);
    }
    
    private void zzb(final StringBuilder sb, final Field<?, ?> field, final ArrayList<?> list) {
        sb.append("[");
        for (int size = list.size(), i = 0; i < size; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            this.zza(sb, field.zzrj(), list.get(i));
        }
        sb.append("]");
    }
    
    public static HashMap<String, String> zzl(final Bundle bundle) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        for (final String s : bundle.keySet()) {
            hashMap.put(s, bundle.getString(s));
        }
        return hashMap;
    }
    
    public int describeContents() {
        final zze creator = SafeParcelResponse.CREATOR;
        return 0;
    }
    
    public int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public String toString() {
        zzx.zzb(this.zzamT, "Cannot convert to JSON on client side.");
        final Parcel zzrD = this.zzrD();
        zzrD.setDataPosition(0);
        final StringBuilder sb = new StringBuilder(100);
        this.zza(sb, this.zzamT.zzcR(this.mClassName), zzrD);
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final zze creator = SafeParcelResponse.CREATOR;
        zze.zza(this, parcel, n);
    }
    
    @Override
    protected Object zzcN(final String s) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }
    
    @Override
    protected boolean zzcO(final String s) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }
    
    public Parcel zzrD() {
        switch (this.zzanc) {
            case 0: {
                this.zzand = zzb.zzav(this.zzana);
                zzb.zzI(this.zzana, this.zzand);
                this.zzanc = 2;
                break;
            }
            case 1: {
                zzb.zzI(this.zzana, this.zzand);
                this.zzanc = 2;
                break;
            }
        }
        return this.zzana;
    }
    
    FieldMappingDictionary zzrE() {
        switch (this.zzanb) {
            default: {
                throw new IllegalStateException("Invalid creation type: " + this.zzanb);
            }
            case 0: {
                return null;
            }
            case 1: {
                return this.zzamT;
            }
            case 2: {
                return this.zzamT;
            }
        }
    }
    
    @Override
    public Map<String, Field<?, ?>> zzrl() {
        if (this.zzamT == null) {
            return null;
        }
        return this.zzamT.zzcR(this.mClassName);
    }
}
