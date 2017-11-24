package com.google.android.gms.tagmanager;

import java.security.*;
import java.util.*;
import com.google.android.gms.internal.*;

class zzap extends zzak
{
    private static final String ID;
    private static final String zzbiQ;
    private static final String zzbiS;
    private static final String zzbiW;
    
    static {
        ID = zzad.zzcb.toString();
        zzbiQ = zzae.zzdV.toString();
        zzbiW = zzae.zzdL.toString();
        zzbiS = zzae.zzfQ.toString();
    }
    
    public zzap() {
        super(zzap.ID, new String[] { zzap.zzbiQ });
    }
    
    private byte[] zzg(final String s, final byte[] array) throws NoSuchAlgorithmException {
        final MessageDigest instance = MessageDigest.getInstance(s);
        instance.update(array);
        return instance.digest();
    }
    
    @Override
    public boolean zzFW() {
        return true;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        final zzag.zza zza = map.get(zzap.zzbiQ);
        if (zza == null || zza == zzdf.zzHF()) {
            return zzdf.zzHF();
        }
        final String zzg = zzdf.zzg(zza);
        final zzag.zza zza2 = map.get(zzap.zzbiW);
        Label_0114: {
            if (zza2 != null) {
                break Label_0114;
            }
            String zzg2 = "MD5";
        Label_0079_Outer:
            while (true) {
                final zzag.zza zza3 = map.get(zzap.zzbiS);
                Label_0124: {
                    if (zza3 != null) {
                        break Label_0124;
                    }
                    String zzg3 = "text";
                    while (true) {
                        Label_0134: {
                            if (!"text".equals(zzg3)) {
                                break Label_0134;
                            }
                            byte[] array = zzg.getBytes();
                            try {
                                return zzdf.zzR(zzk.zzj(this.zzg(zzg2, array)));
                                zzg2 = zzdf.zzg(zza2);
                                continue Label_0079_Outer;
                                Label_0153: {
                                    zzbg.e("Hash: unknown input format: " + zzg3);
                                }
                                return zzdf.zzHF();
                                zzg3 = zzdf.zzg(zza3);
                                continue;
                                // iftrue(Label_0153:, !"base16".equals((Object)zzg3))
                                array = zzk.zzfO(zzg);
                                return zzdf.zzR(zzk.zzj(this.zzg(zzg2, array)));
                            }
                            catch (NoSuchAlgorithmException ex) {
                                zzbg.e("Hash: unknown algorithm: " + zzg2);
                                return zzdf.zzHF();
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
}
