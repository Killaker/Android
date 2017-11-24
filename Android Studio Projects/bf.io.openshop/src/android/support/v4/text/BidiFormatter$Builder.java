package android.support.v4.text;

import java.util.*;

public static final class Builder
{
    private int mFlags;
    private boolean mIsRtlContext;
    private TextDirectionHeuristicCompat mTextDirectionHeuristicCompat;
    
    public Builder() {
        this.initialize(BidiFormatter.access$000(Locale.getDefault()));
    }
    
    public Builder(final Locale locale) {
        this.initialize(BidiFormatter.access$000(locale));
    }
    
    public Builder(final boolean b) {
        this.initialize(b);
    }
    
    private static BidiFormatter getDefaultInstanceFromContext(final boolean b) {
        if (b) {
            return BidiFormatter.access$200();
        }
        return BidiFormatter.access$300();
    }
    
    private void initialize(final boolean mIsRtlContext) {
        this.mIsRtlContext = mIsRtlContext;
        this.mTextDirectionHeuristicCompat = BidiFormatter.access$100();
        this.mFlags = 2;
    }
    
    public BidiFormatter build() {
        if (this.mFlags == 2 && this.mTextDirectionHeuristicCompat == BidiFormatter.access$100()) {
            return getDefaultInstanceFromContext(this.mIsRtlContext);
        }
        return new BidiFormatter(this.mIsRtlContext, this.mFlags, this.mTextDirectionHeuristicCompat, null);
    }
    
    public Builder setTextDirectionHeuristic(final TextDirectionHeuristicCompat mTextDirectionHeuristicCompat) {
        this.mTextDirectionHeuristicCompat = mTextDirectionHeuristicCompat;
        return this;
    }
    
    public Builder stereoReset(final boolean b) {
        if (b) {
            this.mFlags |= 0x2;
            return this;
        }
        this.mFlags &= 0xFFFFFFFD;
        return this;
    }
}
