package com.google.android.gms.tagmanager;

import java.util.*;

class TagManager$1 implements zzb {
    @Override
    public void zzQ(final Map<String, Object> map) {
        final Object value = map.get("event");
        if (value != null) {
            TagManager.zza(TagManager.this, value.toString());
        }
    }
}