package com.google.android.gms.common.data;

import java.util.*;
import android.os.*;

public final class DataBufferUtils
{
    public static <T, E extends Freezable<T>> ArrayList<T> freezeAndClose(final DataBuffer<E> dataBuffer) {
        final ArrayList<T> list = new ArrayList<T>(dataBuffer.getCount());
        try {
            final Iterator<E> iterator = dataBuffer.iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next().freeze());
            }
        }
        finally {
            dataBuffer.close();
        }
        dataBuffer.close();
        return list;
    }
    
    public static boolean hasData(final DataBuffer<?> dataBuffer) {
        return dataBuffer != null && dataBuffer.getCount() > 0;
    }
    
    public static boolean hasNextPage(final DataBuffer<?> dataBuffer) {
        final Bundle zzpZ = dataBuffer.zzpZ();
        return zzpZ != null && zzpZ.getString("next_page_token") != null;
    }
    
    public static boolean hasPrevPage(final DataBuffer<?> dataBuffer) {
        final Bundle zzpZ = dataBuffer.zzpZ();
        return zzpZ != null && zzpZ.getString("prev_page_token") != null;
    }
}
