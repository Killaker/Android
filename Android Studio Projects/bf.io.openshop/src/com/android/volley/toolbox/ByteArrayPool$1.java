package com.android.volley.toolbox;

import java.util.*;

static final class ByteArrayPool$1 implements Comparator<byte[]> {
    @Override
    public int compare(final byte[] array, final byte[] array2) {
        return array.length - array2.length;
    }
}