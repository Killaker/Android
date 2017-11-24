package com.google.android.gms.tagmanager;

class zzde extends Number implements Comparable<zzde>
{
    private double zzblB;
    private long zzblC;
    private boolean zzblD;
    
    private zzde(final double zzblB) {
        this.zzblB = zzblB;
        this.zzblD = false;
    }
    
    private zzde(final long zzblC) {
        this.zzblC = zzblC;
        this.zzblD = true;
    }
    
    public static zzde zza(final Double n) {
        return new zzde(n);
    }
    
    public static zzde zzam(final long n) {
        return new zzde(n);
    }
    
    public static zzde zzgs(final String s) throws NumberFormatException {
        try {
            return new zzde(Long.parseLong(s));
        }
        catch (NumberFormatException ex) {
            try {
                return new zzde(Double.parseDouble(s));
            }
            catch (NumberFormatException ex2) {
                throw new NumberFormatException(s + " is not a valid TypedNumber");
            }
        }
    }
    
    @Override
    public byte byteValue() {
        return (byte)this.longValue();
    }
    
    @Override
    public double doubleValue() {
        if (this.zzHv()) {
            return this.zzblC;
        }
        return this.zzblB;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof zzde && this.zza((zzde)o) == 0;
    }
    
    @Override
    public float floatValue() {
        return (float)this.doubleValue();
    }
    
    @Override
    public int hashCode() {
        return new Long(this.longValue()).hashCode();
    }
    
    @Override
    public int intValue() {
        return this.zzHx();
    }
    
    @Override
    public long longValue() {
        return this.zzHw();
    }
    
    @Override
    public short shortValue() {
        return this.zzHy();
    }
    
    @Override
    public String toString() {
        if (this.zzHv()) {
            return Long.toString(this.zzblC);
        }
        return Double.toString(this.zzblB);
    }
    
    public boolean zzHu() {
        return !this.zzHv();
    }
    
    public boolean zzHv() {
        return this.zzblD;
    }
    
    public long zzHw() {
        if (this.zzHv()) {
            return this.zzblC;
        }
        return (long)this.zzblB;
    }
    
    public int zzHx() {
        return (int)this.longValue();
    }
    
    public short zzHy() {
        return (short)this.longValue();
    }
    
    public int zza(final zzde zzde) {
        if (this.zzHv() && zzde.zzHv()) {
            return new Long(this.zzblC).compareTo(Long.valueOf(zzde.zzblC));
        }
        return Double.compare(this.doubleValue(), zzde.doubleValue());
    }
}
