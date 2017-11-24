package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.*;
import java.util.*;

public class zzbb extends zzak
{
    private static final String ID;
    
    static {
        ID = zzad.zzbM.toString();
    }
    
    public zzbb() {
        super(zzbb.ID, new String[0]);
    }
    
    @Override
    public boolean zzFW() {
        return false;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        final Locale default1 = Locale.getDefault();
        if (default1 == null) {
            return zzdf.zzHF();
        }
        final String language = default1.getLanguage();
        if (language == null) {
            return zzdf.zzHF();
        }
        return zzdf.zzR(language.toLowerCase());
    }
}
