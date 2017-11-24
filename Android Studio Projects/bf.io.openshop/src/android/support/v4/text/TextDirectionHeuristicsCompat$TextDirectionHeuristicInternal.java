package android.support.v4.text;

private static class TextDirectionHeuristicInternal extends TextDirectionHeuristicImpl
{
    private final boolean mDefaultIsRtl;
    
    private TextDirectionHeuristicInternal(final TextDirectionAlgorithm textDirectionAlgorithm, final boolean mDefaultIsRtl) {
        super(textDirectionAlgorithm);
        this.mDefaultIsRtl = mDefaultIsRtl;
    }
    
    @Override
    protected boolean defaultIsRtl() {
        return this.mDefaultIsRtl;
    }
}
