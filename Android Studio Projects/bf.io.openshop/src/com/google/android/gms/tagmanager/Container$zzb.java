package com.google.android.gms.tagmanager;

import java.util.*;

private class zzb implements zzt.zza
{
    @Override
    public Object zzc(final String s, final Map<String, Object> map) {
        final FunctionCallTagCallback zzfQ = Container.this.zzfQ(s);
        if (zzfQ != null) {
            zzfQ.execute(s, map);
        }
        return zzdf.zzHE();
    }
}
