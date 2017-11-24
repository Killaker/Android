package okhttp3;

import java.util.*;

static final class CookieJar$1 implements CookieJar {
    @Override
    public List<Cookie> loadForRequest(final HttpUrl httpUrl) {
        return Collections.emptyList();
    }
    
    @Override
    public void saveFromResponse(final HttpUrl httpUrl, final List<Cookie> list) {
    }
}