package android.support.v4.text;

private static class FirstStrong implements TextDirectionAlgorithm
{
    public static final FirstStrong INSTANCE;
    
    static {
        INSTANCE = new FirstStrong();
    }
    
    @Override
    public int checkRtl(final CharSequence charSequence, final int n, final int n2) {
        int access$100 = 2;
        for (int n3 = n; n3 < n + n2 && access$100 == 2; access$100 = TextDirectionHeuristicsCompat.access$100(Character.getDirectionality(charSequence.charAt(n3))), ++n3) {}
        return access$100;
    }
}
