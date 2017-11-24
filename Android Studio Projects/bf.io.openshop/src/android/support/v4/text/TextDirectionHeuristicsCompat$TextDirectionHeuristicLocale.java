package android.support.v4.text;

import java.util.*;

private static class TextDirectionHeuristicLocale extends TextDirectionHeuristicImpl
{
    public static final TextDirectionHeuristicLocale INSTANCE;
    
    static {
        INSTANCE = new TextDirectionHeuristicLocale();
    }
    
    public TextDirectionHeuristicLocale() {
        super(null);
    }
    
    @Override
    protected boolean defaultIsRtl() {
        return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
    }
}
