package com.google.android.gms.internal;

import java.io.*;

public static class zza extends IOException
{
    zza(final int n, final int n2) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space (pos " + n + " limit " + n2 + ").");
    }
}
