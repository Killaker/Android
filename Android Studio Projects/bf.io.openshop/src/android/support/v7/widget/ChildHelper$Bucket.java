package android.support.v7.widget;

static class Bucket
{
    static final int BITS_PER_WORD = 64;
    static final long LAST_BIT = Long.MIN_VALUE;
    long mData;
    Bucket next;
    
    Bucket() {
        this.mData = 0L;
    }
    
    private void ensureNext() {
        if (this.next == null) {
            this.next = new Bucket();
        }
    }
    
    void clear(final int n) {
        if (n >= 64) {
            if (this.next != null) {
                this.next.clear(n - 64);
            }
            return;
        }
        this.mData &= (-1L ^ 1L << n);
    }
    
    int countOnesBefore(final int n) {
        if (this.next == null) {
            if (n >= 64) {
                return Long.bitCount(this.mData);
            }
            return Long.bitCount(this.mData & (1L << n) - 1L);
        }
        else {
            if (n < 64) {
                return Long.bitCount(this.mData & (1L << n) - 1L);
            }
            return this.next.countOnesBefore(n - 64) + Long.bitCount(this.mData);
        }
    }
    
    boolean get(final int n) {
        if (n >= 64) {
            this.ensureNext();
            return this.next.get(n - 64);
        }
        return (this.mData & 1L << n) != 0x0L;
    }
    
    void insert(final int n, final boolean b) {
        if (n >= 64) {
            this.ensureNext();
            this.next.insert(n - 64, b);
        }
        else {
            final boolean b2 = (Long.MIN_VALUE & this.mData) != 0x0L;
            final long n2 = (1L << n) - 1L;
            this.mData = ((n2 & this.mData) | (this.mData & (-1L ^ n2)) << 1);
            if (b) {
                this.set(n);
            }
            else {
                this.clear(n);
            }
            if (b2 || this.next != null) {
                this.ensureNext();
                this.next.insert(0, b2);
            }
        }
    }
    
    boolean remove(final int n) {
        boolean remove;
        if (n >= 64) {
            this.ensureNext();
            remove = this.next.remove(n - 64);
        }
        else {
            final long n2 = 1L << n;
            remove = ((n2 & this.mData) != 0x0L);
            this.mData &= (-1L ^ n2);
            final long n3 = n2 - 1L;
            this.mData = ((n3 & this.mData) | Long.rotateRight(this.mData & (-1L ^ n3), 1));
            if (this.next != null) {
                if (this.next.get(0)) {
                    this.set(63);
                }
                this.next.remove(0);
                return remove;
            }
        }
        return remove;
    }
    
    void reset() {
        this.mData = 0L;
        if (this.next != null) {
            this.next.reset();
        }
    }
    
    void set(final int n) {
        if (n >= 64) {
            this.ensureNext();
            this.next.set(n - 64);
            return;
        }
        this.mData |= 1L << n;
    }
    
    @Override
    public String toString() {
        if (this.next == null) {
            return Long.toBinaryString(this.mData);
        }
        return this.next.toString() + "xx" + Long.toBinaryString(this.mData);
    }
}
