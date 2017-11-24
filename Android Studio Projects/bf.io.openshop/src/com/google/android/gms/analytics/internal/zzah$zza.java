package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.*;
import java.io.*;

private class zza
{
    private int zzTe;
    private ByteArrayOutputStream zzTf;
    
    public zza() {
        this.zzTf = new ByteArrayOutputStream();
    }
    
    public byte[] getPayload() {
        return this.zzTf.toByteArray();
    }
    
    public boolean zzj(final zzab zzab) {
        zzx.zzz(zzab);
        if (1 + this.zzTe > zzah.this.zzjn().zzkD()) {
            return false;
        }
        final String zza = zzah.this.zza(zzab, false);
        if (zza == null) {
            zzah.this.zzjm().zza(zzab, "Error formatting hit");
            return true;
        }
        final byte[] bytes = zza.getBytes();
        int length = bytes.length;
        if (length > zzah.this.zzjn().zzkv()) {
            zzah.this.zzjm().zza(zzab, "Hit size exceeds the maximum size limit");
            return true;
        }
        if (this.zzTf.size() > 0) {
            ++length;
        }
        if (length + this.zzTf.size() > zzah.this.zzjn().zzkx()) {
            return false;
        }
        try {
            if (this.zzTf.size() > 0) {
                this.zzTf.write(zzah.zzlD());
            }
            this.zzTf.write(bytes);
            ++this.zzTe;
            return true;
        }
        catch (IOException ex) {
            zzah.this.zze("Failed to write payload when batching hits", ex);
            return true;
        }
    }
    
    public int zzlE() {
        return this.zzTe;
    }
}
