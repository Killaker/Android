package com.google.android.gms.measurement.internal;

import android.os.*;
import android.app.*;
import android.content.*;
import android.content.pm.*;
import java.util.*;
import java.security.*;
import com.google.android.gms.common.internal.*;
import android.text.*;
import android.support.annotation.*;
import com.google.android.gms.internal.*;
import java.util.zip.*;
import java.io.*;

public class zzaj extends zzy
{
    zzaj(final zzw zzw) {
        super(zzw);
    }
    
    public static boolean zzI(final Bundle bundle) {
        return bundle.getLong("_c") == 1L;
    }
    
    public static boolean zzQ(final String s, final String s2) {
        return (s == null && s2 == null) || (s != null && s.equals(s2));
    }
    
    private Object zza(final int n, Object value, final boolean b) {
        if (value == null) {
            value = null;
        }
        else if (!(value instanceof Long) && !(value instanceof Float)) {
            if (value instanceof Integer) {
                return value;
            }
            if (value instanceof Byte) {
                return value;
            }
            if (value instanceof Short) {
                return value;
            }
            if (value instanceof Boolean) {
                long n2;
                if (value) {
                    n2 = 1L;
                }
                else {
                    n2 = 0L;
                }
                return n2;
            }
            if (value instanceof Double) {
                return (float)(double)value;
            }
            if (!(value instanceof String) && !(value instanceof Character) && !(value instanceof CharSequence)) {
                return null;
            }
            value = String.valueOf(value);
            if (((String)value).length() > n) {
                if (b) {
                    return ((String)value).substring(0, n);
                }
                return null;
            }
        }
        return value;
    }
    
    private void zza(final String s, final String s2, final int n, final Object o) {
        if (o == null) {
            this.zzAo().zzCH().zzj(s + " value can't be null. Ignoring " + s, s2);
        }
        else if (!(o instanceof Long) && !(o instanceof Float) && !(o instanceof Integer) && !(o instanceof Byte) && !(o instanceof Short) && !(o instanceof Boolean) && !(o instanceof Double) && (o instanceof String || o instanceof Character || o instanceof CharSequence)) {
            final String value = String.valueOf(o);
            if (value.length() > n) {
                this.zzAo().zzCH().zze("Ignoring " + s + ". Value is too long. name, value length", s2, value.length());
            }
        }
    }
    
    private static void zza(final StringBuilder sb, final int n) {
        for (int i = 0; i < n; ++i) {
            sb.append("  ");
        }
    }
    
    private static void zza(final StringBuilder sb, final int n, final zzqb.zze zze) {
        if (zze == null) {
            return;
        }
        zza(sb, n);
        sb.append("bundle {\n");
        zza(sb, n, "protocol_version", zze.zzbal);
        zza(sb, n, "platform", zze.zzbat);
        zza(sb, n, "gmp_version", zze.zzbax);
        zza(sb, n, "uploading_gmp_version", zze.zzbay);
        zza(sb, n, "gmp_app_id", zze.zzaVt);
        zza(sb, n, "app_id", zze.appId);
        zza(sb, n, "app_version", zze.zzaMV);
        zza(sb, n, "dev_cert_hash", zze.zzbaC);
        zza(sb, n, "app_store", zze.zzaVu);
        zza(sb, n, "upload_timestamp_millis", zze.zzbao);
        zza(sb, n, "start_timestamp_millis", zze.zzbap);
        zza(sb, n, "end_timestamp_millis", zze.zzbaq);
        zza(sb, n, "previous_bundle_start_timestamp_millis", zze.zzbar);
        zza(sb, n, "previous_bundle_end_timestamp_millis", zze.zzbas);
        zza(sb, n, "app_instance_id", zze.zzbaB);
        zza(sb, n, "resettable_device_id", zze.zzbaz);
        zza(sb, n, "limited_ad_tracking", zze.zzbaA);
        zza(sb, n, "os_version", zze.osVersion);
        zza(sb, n, "device_model", zze.zzbau);
        zza(sb, n, "user_default_language", zze.zzbav);
        zza(sb, n, "time_zone_offset_minutes", zze.zzbaw);
        zza(sb, n, "bundle_sequential_index", zze.zzbaD);
        zza(sb, n, "service_upload", zze.zzbaE);
        zza(sb, n, "health_monitor", zze.zzaVx);
        zza(sb, n, zze.zzban);
        zza(sb, n, zze.zzbaF);
        zza(sb, n, zze.zzbam);
        zza(sb, n);
        sb.append("}\n");
    }
    
    private static void zza(final StringBuilder sb, final int n, final String s, final zzqb.zzf zzf) {
        int i = 0;
        if (zzf == null) {
            return;
        }
        final int n2 = n + 1;
        zza(sb, n2);
        sb.append(s);
        sb.append(" {\n");
        if (zzf.zzbaH != null) {
            zza(sb, n2 + 1);
            sb.append("results: ");
            final long[] zzbaH = zzf.zzbaH;
            final int length = zzbaH.length;
            int j = 0;
            int n3 = 0;
            while (j < length) {
                final Long value = zzbaH[j];
                final int n4 = n3 + 1;
                if (n3 != 0) {
                    sb.append(", ");
                }
                sb.append(value);
                ++j;
                n3 = n4;
            }
            sb.append('\n');
        }
        if (zzf.zzbaG != null) {
            zza(sb, n2 + 1);
            sb.append("status: ");
            final long[] zzbaG = zzf.zzbaG;
            final int length2 = zzbaG.length;
            int n5 = 0;
            while (i < length2) {
                final Long value2 = zzbaG[i];
                final int n6 = n5 + 1;
                if (n5 != 0) {
                    sb.append(", ");
                }
                sb.append(value2);
                ++i;
                n5 = n6;
            }
            sb.append('\n');
        }
        zza(sb, n2);
        sb.append("}\n");
    }
    
    private static void zza(final StringBuilder sb, final int n, final String s, final Object o) {
        if (o == null) {
            return;
        }
        zza(sb, n + 1);
        sb.append(s);
        sb.append(": ");
        sb.append(o);
        sb.append('\n');
    }
    
    private static void zza(final StringBuilder sb, final int n, final zzqb.zza[] array) {
        if (array != null) {
            final int n2 = n + 1;
            for (final zzqb.zza zza : array) {
                if (zza != null) {
                    zza(sb, n2);
                    sb.append("audience_membership {\n");
                    zza(sb, n2, "audience_id", zza.zzaZr);
                    zza(sb, n2, "new_audience", zza.zzbac);
                    zza(sb, n2, "current_data", zza.zzbaa);
                    zza(sb, n2, "previous_data", zza.zzbab);
                    zza(sb, n2);
                    sb.append("}\n");
                }
            }
        }
    }
    
    private static void zza(final StringBuilder sb, final int n, final zzqb.zzb[] array) {
        if (array != null) {
            final int n2 = n + 1;
            for (final zzqb.zzb zzb : array) {
                if (zzb != null) {
                    zza(sb, n2);
                    sb.append("event {\n");
                    zza(sb, n2, "name", zzb.name);
                    zza(sb, n2, "timestamp_millis", zzb.zzbaf);
                    zza(sb, n2, "previous_timestamp_millis", zzb.zzbag);
                    zza(sb, n2, "count", zzb.count);
                    zza(sb, n2, zzb.zzbae);
                    zza(sb, n2);
                    sb.append("}\n");
                }
            }
        }
    }
    
    private static void zza(final StringBuilder sb, final int n, final zzqb.zzc[] array) {
        if (array != null) {
            final int n2 = n + 1;
            for (final zzqb.zzc zzc : array) {
                if (zzc != null) {
                    zza(sb, n2);
                    sb.append("event {\n");
                    zza(sb, n2, "name", zzc.name);
                    zza(sb, n2, "string_value", zzc.zzamJ);
                    zza(sb, n2, "int_value", zzc.zzbai);
                    zza(sb, n2, "float_value", zzc.zzaZo);
                    zza(sb, n2);
                    sb.append("}\n");
                }
            }
        }
    }
    
    private static void zza(final StringBuilder sb, final int n, final zzqb.zzg[] array) {
        if (array != null) {
            final int n2 = n + 1;
            for (final zzqb.zzg zzg : array) {
                if (zzg != null) {
                    zza(sb, n2);
                    sb.append("user_property {\n");
                    zza(sb, n2, "set_timestamp_millis", zzg.zzbaJ);
                    zza(sb, n2, "name", zzg.name);
                    zza(sb, n2, "string_value", zzg.zzamJ);
                    zza(sb, n2, "int_value", zzg.zzbai);
                    zza(sb, n2, "float_value", zzg.zzaZo);
                    zza(sb, n2);
                    sb.append("}\n");
                }
            }
        }
    }
    
    public static boolean zza(final Context context, final Class<? extends Service> clazz) {
        try {
            final ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, (Class)clazz), 4);
            if (serviceInfo != null && serviceInfo.enabled) {
                return true;
            }
        }
        catch (PackageManager$NameNotFoundException ex) {}
        return false;
    }
    
    public static boolean zza(final Context context, final Class<? extends BroadcastReceiver> clazz, final boolean b) {
        try {
            final ActivityInfo receiverInfo = context.getPackageManager().getReceiverInfo(new ComponentName(context, (Class)clazz), 2);
            if (receiverInfo != null && receiverInfo.enabled && (!b || receiverInfo.exported)) {
                return true;
            }
        }
        catch (PackageManager$NameNotFoundException ex) {}
        return false;
    }
    
    public static boolean zza(final long[] array, final int n) {
        return n < 64 * array.length && (array[n / 64] & 1L << n % 64) != 0x0L;
    }
    
    public static long[] zza(final BitSet set) {
        final int n = (63 + set.length()) / 64;
        final long[] array = new long[n];
        for (int i = 0; i < n; ++i) {
            array[i] = 0L;
            for (int n2 = 0; n2 < 64 && n2 + i * 64 < set.length(); ++n2) {
                if (set.get(n2 + i * 64)) {
                    array[i] |= 1L << n2;
                }
            }
        }
        return array;
    }
    
    public static String zzb(final zzqb.zzd zzd) {
        if (zzd == null) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        if (zzd.zzbaj != null) {
            for (final zzqb.zze zze : zzd.zzbaj) {
                if (zze != null) {
                    zza(sb, 1, zze);
                }
            }
        }
        sb.append("}\n");
        return sb.toString();
    }
    
    static MessageDigest zzbv(final String s) {
        for (int i = 0; i < 2; ++i) {
            try {
                final MessageDigest instance = MessageDigest.getInstance(s);
                if (instance != null) {
                    return instance;
                }
            }
            catch (NoSuchAlgorithmException ex) {}
        }
        return null;
    }
    
    static boolean zzfq(final String s) {
        zzx.zzcM(s);
        final char char1 = s.charAt(0);
        boolean b = false;
        if (char1 != '_') {
            b = true;
        }
        return b;
    }
    
    private int zzfu(final String s) {
        if ("_ldl".equals(s)) {
            return this.zzCp().zzBG();
        }
        return this.zzCp().zzBF();
    }
    
    public static boolean zzfv(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && s.startsWith("_");
    }
    
    static long zzq(final byte[] array) {
        int n = 0;
        zzx.zzz(array);
        zzx.zzab(array.length > 0);
        long n2 = 0L;
        for (int n3 = -1 + array.length; n3 >= 0 && n3 >= -8 + array.length; --n3) {
            n2 += (0xFFL & array[n3]) << n;
            n += 8;
        }
        return n2;
    }
    
    public void zza(final Bundle bundle, final String s, final Object o) {
        if (o instanceof Long) {
            bundle.putLong(s, (long)o);
        }
        else {
            if (o instanceof Float) {
                bundle.putFloat(s, (float)o);
                return;
            }
            if (o instanceof String) {
                bundle.putString(s, String.valueOf(o));
                return;
            }
            if (s != null) {
                this.zzAo().zzCH().zze("Not putting event parameter. Invalid value type. name, type", s, o.getClass().getSimpleName());
            }
        }
    }
    
    public void zza(final zzqb.zzc zzc, final Object o) {
        zzx.zzz(o);
        zzc.zzamJ = null;
        zzc.zzbai = null;
        zzc.zzaZo = null;
        if (o instanceof String) {
            zzc.zzamJ = (String)o;
            return;
        }
        if (o instanceof Long) {
            zzc.zzbai = (Long)o;
            return;
        }
        if (o instanceof Float) {
            zzc.zzaZo = (Float)o;
            return;
        }
        this.zzAo().zzCE().zzj("Ignoring invalid (type) event param value", o);
    }
    
    public void zza(final zzqb.zzg zzg, final Object o) {
        zzx.zzz(o);
        zzg.zzamJ = null;
        zzg.zzbai = null;
        zzg.zzaZo = null;
        if (o instanceof String) {
            zzg.zzamJ = (String)o;
            return;
        }
        if (o instanceof Long) {
            zzg.zzbai = (Long)o;
            return;
        }
        if (o instanceof Float) {
            zzg.zzaZo = (Float)o;
            return;
        }
        this.zzAo().zzCE().zzj("Ignoring invalid (type) user attribute value", o);
    }
    
    public byte[] zza(final zzqb.zzd zzd) {
        try {
            final byte[] array = new byte[zzd.getSerializedSize()];
            final zzsn zzE = zzsn.zzE(array);
            zzd.writeTo(zzE);
            zzE.zzJo();
            return array;
        }
        catch (IOException ex) {
            this.zzAo().zzCE().zzj("Data loss. Failed to serialize batch", ex);
            return null;
        }
    }
    
    @WorkerThread
    public boolean zzbk(final String s) {
        this.zzjk();
        if (this.getContext().checkCallingOrSelfPermission(s) == 0) {
            return true;
        }
        this.zzAo().zzCJ().zzj("Permission not granted", s);
        return false;
    }
    
    void zzc(final String s, final int n, final String s2) {
        if (s2 == null) {
            throw new IllegalArgumentException(s + " name is required and can't be null");
        }
        if (s2.length() == 0) {
            throw new IllegalArgumentException(s + " name is required and can't be empty");
        }
        final char char1 = s2.charAt(0);
        if (!Character.isLetter(char1) && char1 != '_') {
            throw new IllegalArgumentException(s + " name must start with a letter or _");
        }
        for (int i = 1; i < s2.length(); ++i) {
            final char char2 = s2.charAt(i);
            if (char2 != '_' && !Character.isLetterOrDigit(char2)) {
                throw new IllegalArgumentException(s + " name must consist of letters, digits or _ (underscores)");
            }
        }
        if (s2.length() > n) {
            throw new IllegalArgumentException(s + " name is too long. The maximum supported length is " + n);
        }
    }
    
    public boolean zzc(final long n, final long n2) {
        return n == 0L || n2 <= 0L || Math.abs(this.zzjl().currentTimeMillis() - n) > n2;
    }
    
    public void zzfr(final String s) {
        this.zzc("event", this.zzCp().zzBB(), s);
    }
    
    public void zzfs(final String s) {
        this.zzc("user attribute", this.zzCp().zzBC(), s);
    }
    
    public void zzft(final String s) {
        this.zzc("event param", this.zzCp().zzBC(), s);
    }
    
    public byte[] zzg(final byte[] array) throws IOException {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(array);
            gzipOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        }
        catch (IOException ex) {
            this.zzAo().zzCE().zzj("Failed to gzip content", ex);
            throw ex;
        }
    }
    
    public Object zzk(final String s, final Object o) {
        int n;
        if (zzfv(s)) {
            n = this.zzCp().zzBE();
        }
        else {
            n = this.zzCp().zzBD();
        }
        return this.zza(n, o, false);
    }
    
    public void zzl(final String s, final Object o) {
        if ("_ldl".equals(s)) {
            this.zza("user attribute referrer", s, this.zzfu(s), o);
            return;
        }
        this.zza("user attribute", s, this.zzfu(s), o);
    }
    
    public Object zzm(final String s, final Object o) {
        if ("_ldl".equals(s)) {
            return this.zza(this.zzfu(s), o, true);
        }
        return this.zza(this.zzfu(s), o, false);
    }
    
    public byte[] zzp(final byte[] array) throws IOException {
        try {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
            final GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array2 = new byte[1024];
            while (true) {
                final int read = gzipInputStream.read(array2);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(array2, 0, read);
            }
            gzipInputStream.close();
            byteArrayInputStream.close();
            return byteArrayOutputStream.toByteArray();
        }
        catch (IOException ex) {
            this.zzAo().zzCE().zzj("Failed to ungzip content", ex);
            throw ex;
        }
    }
    
    public long zzr(final byte[] array) {
        zzx.zzz(array);
        final MessageDigest zzbv = zzbv("MD5");
        if (zzbv == null) {
            this.zzAo().zzCE().zzfg("Failed to get MD5");
            return 0L;
        }
        return zzq(zzbv.digest(array));
    }
}
