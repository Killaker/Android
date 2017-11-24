package android.support.v7.widget;

static class ContainerHelpers
{
    static final boolean[] EMPTY_BOOLEANS;
    static final int[] EMPTY_INTS;
    static final long[] EMPTY_LONGS;
    static final Object[] EMPTY_OBJECTS;
    
    static {
        EMPTY_BOOLEANS = new boolean[0];
        EMPTY_INTS = new int[0];
        EMPTY_LONGS = new long[0];
        EMPTY_OBJECTS = new Object[0];
    }
    
    static int binarySearch(final int[] array, final int n, final int n2) {
        int i = 0;
        int n3 = n - 1;
        while (i <= n3) {
            final int n4 = i + n3 >>> 1;
            final int n5 = array[n4];
            if (n5 < n2) {
                i = n4 + 1;
            }
            else {
                if (n5 <= n2) {
                    return n4;
                }
                n3 = n4 - 1;
            }
        }
        return ~i;
    }
}
