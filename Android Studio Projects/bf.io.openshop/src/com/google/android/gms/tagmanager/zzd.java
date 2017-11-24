package com.google.android.gms.tagmanager;

import android.content.*;
import java.util.*;
import android.net.*;

class zzd implements zzb
{
    private final Context context;
    
    public zzd(final Context context) {
        this.context = context;
    }
    
    @Override
    public void zzQ(final Map<String, Object> map) {
        final Map<String, Object> value = map.get("gtm.url");
        while (true) {
            Label_0090: {
                if (value != null) {
                    break Label_0090;
                }
                final Map<String, Object> value2 = map.get("gtm");
                if (value2 == null || !(value2 instanceof Map)) {
                    break Label_0090;
                }
                final Object value3 = value2.get("url");
                if (value3 != null && value3 instanceof String) {
                    final String queryParameter = Uri.parse((String)value3).getQueryParameter("referrer");
                    if (queryParameter != null) {
                        zzax.zzn(this.context, queryParameter);
                        return;
                    }
                }
                return;
            }
            final Object value3 = value;
            continue;
        }
    }
}
