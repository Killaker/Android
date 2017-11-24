package com.google.android.gms.tagmanager;

import org.json.*;
import java.util.*;
import com.google.android.gms.internal.*;

class zzaz
{
    private static zzag.zza zzK(final Object o) throws JSONException {
        return zzdf.zzR(zzL(o));
    }
    
    static Object zzL(Object o) throws JSONException {
        if (o instanceof JSONArray) {
            throw new RuntimeException("JSONArrays are not supported");
        }
        if (JSONObject.NULL.equals(o)) {
            throw new RuntimeException("JSON nulls are not supported");
        }
        if (o instanceof JSONObject) {
            final JSONObject jsonObject = (JSONObject)o;
            final HashMap<String, Object> hashMap = new HashMap<String, Object>();
            final Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                final String s = keys.next();
                hashMap.put(s, zzL(jsonObject.get(s)));
            }
            o = hashMap;
        }
        return o;
    }
    
    public static zzrs.zzc zzgi(final String s) throws JSONException {
        final zzag.zza zzK = zzK(new JSONObject(s));
        final zzrs.zzd zzHK = zzrs.zzc.zzHK();
        for (int i = 0; i < zzK.zzjz.length; ++i) {
            zzHK.zzc(zzrs.zza.zzHH().zzb(zzae.zzfR.toString(), zzK.zzjz[i]).zzb(zzae.zzfG.toString(), zzdf.zzgt(zzn.zzFZ())).zzb(zzn.zzGa(), zzK.zzjA[i]).zzHJ());
        }
        return zzHK.zzHN();
    }
}
