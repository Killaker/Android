package android.support.v4.text;

import java.util.*;
import android.support.annotation.*;

private static class TextUtilsCompatImpl
{
    private static int getLayoutDirectionFromFirstChar(@NonNull final Locale locale) {
        switch (Character.getDirectionality(locale.getDisplayName(locale).charAt(0))) {
            default: {
                return 0;
            }
            case 1:
            case 2: {
                return 1;
            }
        }
    }
    
    public int getLayoutDirectionFromLocale(@Nullable final Locale locale) {
        if (locale != null && !locale.equals(TextUtilsCompat.ROOT)) {
            final String maximizeAndGetScript = ICUCompat.maximizeAndGetScript(locale);
            if (maximizeAndGetScript == null) {
                return getLayoutDirectionFromFirstChar(locale);
            }
            if (maximizeAndGetScript.equalsIgnoreCase(TextUtilsCompat.access$000()) || maximizeAndGetScript.equalsIgnoreCase(TextUtilsCompat.access$100())) {
                return 1;
            }
        }
        return 0;
    }
    
    @NonNull
    public String htmlEncode(@NonNull final String s) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            switch (char1) {
                default: {
                    sb.append(char1);
                    break;
                }
                case 60: {
                    sb.append("&lt;");
                    break;
                }
                case 62: {
                    sb.append("&gt;");
                    break;
                }
                case 38: {
                    sb.append("&amp;");
                    break;
                }
                case 39: {
                    sb.append("&#39;");
                    break;
                }
                case 34: {
                    sb.append("&quot;");
                    break;
                }
            }
        }
        return sb.toString();
    }
}
