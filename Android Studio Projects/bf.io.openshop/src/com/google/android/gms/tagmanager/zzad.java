package com.google.android.gms.tagmanager;

import java.util.*;
import com.google.android.gms.internal.*;
import android.util.*;

class zzad extends zzak
{
    private static final String ID;
    private static final String zzbiQ;
    private static final String zzbiR;
    private static final String zzbiS;
    private static final String zzbiT;
    
    static {
        ID = com.google.android.gms.internal.zzad.zzbZ.toString();
        zzbiQ = zzae.zzdV.toString();
        zzbiR = zzae.zzgu.toString();
        zzbiS = zzae.zzfQ.toString();
        zzbiT = zzae.zzgE.toString();
    }
    
    public zzad() {
        super(zzad.ID, new String[] { zzad.zzbiQ });
    }
    
    @Override
    public boolean zzFW() {
        return true;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        final zzag.zza zza = map.get(zzad.zzbiQ);
        if (zza == null || zza == zzdf.zzHF()) {
            return zzdf.zzHF();
        }
        final String zzg = zzdf.zzg(zza);
        final zzag.zza zza2 = map.get(zzad.zzbiS);
        while (true) {
        Label_0165_Outer:
            while (true) {
                Label_0079: {
                    while (true) {
                        Label_0056: {
                            if (zza2 == null) {
                                final String zzg2 = "text";
                                break Label_0056;
                            }
                            String zzg3 = null;
                            Label_0155: {
                                break Label_0155;
                                while (true) {
                                    while (true) {
                                        byte[] array = null;
                                        final int n;
                                        Label_0281: {
                                            try {
                                                final String zzg2;
                                                if ("text".equals(zzg2)) {
                                                    array = zzg.getBytes();
                                                }
                                                else if ("base16".equals(zzg2)) {
                                                    array = zzk.zzfO(zzg);
                                                }
                                                else if ("base64".equals(zzg2)) {
                                                    array = Base64.decode(zzg, n);
                                                }
                                                else {
                                                    if (!"base64url".equals(zzg2)) {
                                                        zzbg.e("Encode: unknown input format: " + zzg2);
                                                        return zzdf.zzHF();
                                                    }
                                                    array = Base64.decode(zzg, n | 0x8);
                                                }
                                                if ("base16".equals(zzg3)) {
                                                    final String s = zzk.zzj(array);
                                                    return zzdf.zzR(s);
                                                }
                                                break Label_0281;
                                                final zzag.zza zza3;
                                                zzg3 = zzdf.zzg(zza3);
                                                break Label_0079;
                                                zzg2 = zzdf.zzg(zza2);
                                                break Label_0056;
                                            }
                                            catch (IllegalArgumentException ex) {
                                                zzbg.e("Encode: invalid input:");
                                                return zzdf.zzHF();
                                            }
                                        }
                                        if ("base64".equals(zzg3)) {
                                            final String s = Base64.encodeToString(array, n);
                                            continue Label_0165_Outer;
                                        }
                                        if ("base64url".equals(zzg3)) {
                                            final String s = Base64.encodeToString(array, n | 0x8);
                                            continue Label_0165_Outer;
                                        }
                                        break;
                                    }
                                    break;
                                }
                            }
                            zzbg.e("Encode: unknown output format: " + zzg3);
                            return zzdf.zzHF();
                        }
                        final zzag.zza zza3 = map.get(zzad.zzbiT);
                        if (zza3 != null) {
                            continue;
                        }
                        break;
                    }
                    String zzg3 = "base16";
                }
                final zzag.zza zza4 = map.get(zzad.zzbiR);
                if (zza4 != null && zzdf.zzk(zza4)) {
                    final int n = 3;
                    continue Label_0165_Outer;
                }
                break;
            }
            final int n = 2;
            continue;
        }
    }
}
