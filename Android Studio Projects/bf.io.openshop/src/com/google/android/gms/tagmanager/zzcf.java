package com.google.android.gms.tagmanager;

import java.util.*;
import com.google.android.gms.internal.*;
import java.util.regex.*;

class zzcf extends zzcz
{
    private static final String ID;
    private static final String zzbkc;
    
    static {
        ID = zzad.zzcy.toString();
        zzbkc = zzae.zzfO.toString();
    }
    
    public zzcf() {
        super(zzcf.ID);
    }
    
    @Override
    protected boolean zza(final String s, final String s2, final Map<String, zzag.zza> map) {
        while (true) {
            Label_0047: {
                if (!zzdf.zzk(map.get(zzcf.zzbkc))) {
                    break Label_0047;
                }
                final int n = 66;
                try {
                    return Pattern.compile(s2, n).matcher(s).find();
                }
                catch (PatternSyntaxException ex) {
                    return false;
                }
            }
            final int n = 64;
            continue;
        }
    }
}
