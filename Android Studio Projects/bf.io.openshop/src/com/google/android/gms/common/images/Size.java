package com.google.android.gms.common.images;

public final class Size
{
    private final int zzoG;
    private final int zzoH;
    
    public Size(final int zzoG, final int zzoH) {
        this.zzoG = zzoG;
        this.zzoH = zzoH;
    }
    
    public static Size parseSize(final String s) throws NumberFormatException {
        if (s == null) {
            throw new IllegalArgumentException("string must not be null");
        }
        int n = s.indexOf(42);
        if (n < 0) {
            n = s.indexOf(120);
        }
        if (n < 0) {
            throw zzcC(s);
        }
        try {
            return new Size(Integer.parseInt(s.substring(0, n)), Integer.parseInt(s.substring(n + 1)));
        }
        catch (NumberFormatException ex) {
            throw zzcC(s);
        }
    }
    
    private static NumberFormatException zzcC(final String s) {
        throw new NumberFormatException("Invalid Size: \"" + s + "\"");
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (o != null) {
            if (this == o) {
                return b;
            }
            if (o instanceof Size) {
                final Size size = (Size)o;
                if (this.zzoG != size.zzoG || this.zzoH != size.zzoH) {
                    b = false;
                }
                return b;
            }
        }
        return false;
    }
    
    public int getHeight() {
        return this.zzoH;
    }
    
    public int getWidth() {
        return this.zzoG;
    }
    
    @Override
    public int hashCode() {
        return this.zzoH ^ (this.zzoG << 16 | this.zzoG >>> 16);
    }
    
    @Override
    public String toString() {
        return this.zzoG + "x" + this.zzoH;
    }
}
