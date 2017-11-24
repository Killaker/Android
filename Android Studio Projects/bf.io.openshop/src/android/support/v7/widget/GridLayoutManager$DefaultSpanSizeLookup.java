package android.support.v7.widget;

public static final class DefaultSpanSizeLookup extends SpanSizeLookup
{
    @Override
    public int getSpanIndex(final int n, final int n2) {
        return n % n2;
    }
    
    @Override
    public int getSpanSize(final int n) {
        return 1;
    }
}
