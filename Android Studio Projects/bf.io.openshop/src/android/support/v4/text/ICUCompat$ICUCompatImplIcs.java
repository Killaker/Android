package android.support.v4.text;

import java.util.*;

static class ICUCompatImplIcs implements ICUCompatImpl
{
    @Override
    public String maximizeAndGetScript(final Locale locale) {
        return ICUCompatIcs.maximizeAndGetScript(locale);
    }
}
