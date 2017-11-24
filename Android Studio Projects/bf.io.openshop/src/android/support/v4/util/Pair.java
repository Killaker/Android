package android.support.v4.util;

public class Pair<F, S>
{
    public final F first;
    public final S second;
    
    public Pair(final F first, final S second) {
        this.first = first;
        this.second = second;
    }
    
    public static <A, B> Pair<A, B> create(final A a, final B b) {
        return new Pair<A, B>(a, b);
    }
    
    private static boolean objectsEqual(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof Pair) {
            final Pair pair = (Pair)o;
            if (objectsEqual(pair.first, this.first) && objectsEqual(pair.second, this.second)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.first == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.first.hashCode();
        }
        final S second = this.second;
        int hashCode2 = 0;
        if (second != null) {
            hashCode2 = this.second.hashCode();
        }
        return hashCode ^ hashCode2;
    }
}
