package android.support.v4.text;

import java.util.*;

static class ICUCompatImplLollipop implements ICUCompatImpl
{
    @Override
    public String maximizeAndGetScript(final Locale locale) {
        return ICUCompatApi23.maximizeAndGetScript(locale);
    }
}
