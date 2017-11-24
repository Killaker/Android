package com.google.android.gms.maps.model;

import java.io.*;
import java.net.*;

public abstract class UrlTileProvider implements TileProvider
{
    private final int zzoG;
    private final int zzoH;
    
    public UrlTileProvider(final int zzoG, final int zzoH) {
        this.zzoG = zzoG;
        this.zzoH = zzoH;
    }
    
    private static long zza(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final byte[] array = new byte[4096];
        long n = 0L;
        while (true) {
            final int read = inputStream.read(array);
            if (read == -1) {
                break;
            }
            outputStream.write(array, 0, read);
            n += read;
        }
        return n;
    }
    
    private static byte[] zzl(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        zza(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    
    @Override
    public final Tile getTile(final int n, final int n2, final int n3) {
        final URL tileUrl = this.getTileUrl(n, n2, n3);
        if (tileUrl == null) {
            return UrlTileProvider.NO_TILE;
        }
        try {
            return new Tile(this.zzoG, this.zzoH, zzl(tileUrl.openStream()));
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public abstract URL getTileUrl(final int p0, final int p1, final int p2);
}
