package android.support.v4.text;

import java.util.*;
import android.support.annotation.*;

private static class TextUtilsCompatJellybeanMr1Impl extends TextUtilsCompatImpl
{
    @Override
    public int getLayoutDirectionFromLocale(@Nullable final Locale locale) {
        return TextUtilsCompatJellybeanMr1.getLayoutDirectionFromLocale(locale);
    }
    
    @NonNull
    @Override
    public String htmlEncode(@NonNull final String s) {
        return TextUtilsCompatJellybeanMr1.htmlEncode(s);
    }
}
