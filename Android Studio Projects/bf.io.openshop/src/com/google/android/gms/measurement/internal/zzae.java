package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;
import java.util.*;
import java.util.regex.*;

class zzae
{
    final boolean zzaXg;
    final int zzaZh;
    final boolean zzaZi;
    final String zzaZj;
    final List<String> zzaZk;
    final String zzaZl;
    
    public zzae(final zzpz.zzf zzf) {
        zzx.zzz(zzf);
        final boolean zzaXg = zzf.zzaZN != null && zzf.zzaZN != 0 && ((zzf.zzaZN != 6) ? (zzf.zzaZO != null) : (zzf.zzaZQ != null && zzf.zzaZQ.length != 0));
        if (zzaXg) {
            this.zzaZh = zzf.zzaZN;
            final Boolean zzaZP = zzf.zzaZP;
            boolean zzaZi = false;
            if (zzaZP != null) {
                final boolean booleanValue = zzf.zzaZP;
                zzaZi = false;
                if (booleanValue) {
                    zzaZi = true;
                }
            }
            this.zzaZi = zzaZi;
            if (this.zzaZi || this.zzaZh == 1 || this.zzaZh == 6) {
                this.zzaZj = zzf.zzaZO;
            }
            else {
                this.zzaZj = zzf.zzaZO.toUpperCase(Locale.ENGLISH);
            }
            List<String> zza;
            if (zzf.zzaZQ == null) {
                zza = null;
            }
            else {
                zza = this.zza(zzf.zzaZQ, this.zzaZi);
            }
            this.zzaZk = zza;
            if (this.zzaZh == 1) {
                this.zzaZl = this.zzaZj;
            }
            else {
                this.zzaZl = null;
            }
        }
        else {
            this.zzaZh = 0;
            this.zzaZi = false;
            this.zzaZj = null;
            this.zzaZk = null;
            this.zzaZl = null;
        }
        this.zzaXg = zzaXg;
    }
    
    private List<String> zza(final String[] array, final boolean b) {
        List<String> list;
        if (b) {
            list = Arrays.asList(array);
        }
        else {
            list = new ArrayList<String>();
            for (int length = array.length, i = 0; i < length; ++i) {
                list.add(array[i].toUpperCase(Locale.ENGLISH));
            }
        }
        return list;
    }
    
    public Boolean zzfp(String upperCase) {
        if (!this.zzaXg) {
            return null;
        }
        if (!this.zzaZi && this.zzaZh != 1) {
            upperCase = upperCase.toUpperCase(Locale.ENGLISH);
        }
        switch (this.zzaZh) {
            default: {
                return null;
            }
            case 1: {
                int n;
                if (this.zzaZi) {
                    n = 0;
                }
                else {
                    n = 66;
                }
                return Pattern.compile(this.zzaZl, n).matcher(upperCase).matches();
            }
            case 2: {
                return upperCase.startsWith(this.zzaZj);
            }
            case 3: {
                return upperCase.endsWith(this.zzaZj);
            }
            case 4: {
                return upperCase.contains(this.zzaZj);
            }
            case 5: {
                return upperCase.equals(this.zzaZj);
            }
            case 6: {
                return this.zzaZk.contains(upperCase);
            }
        }
    }
}
