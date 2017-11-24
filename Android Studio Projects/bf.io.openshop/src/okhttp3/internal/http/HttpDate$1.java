package okhttp3.internal.http;

import java.util.*;
import java.text.*;
import okhttp3.internal.*;

static final class HttpDate$1 extends ThreadLocal<DateFormat> {
    @Override
    protected DateFormat initialValue() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        simpleDateFormat.setLenient(false);
        simpleDateFormat.setTimeZone(Util.UTC);
        return simpleDateFormat;
    }
}