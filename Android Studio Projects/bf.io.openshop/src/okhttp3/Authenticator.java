package okhttp3;

import java.io.*;

public interface Authenticator
{
    public static final Authenticator NONE = new Authenticator() {
        @Override
        public Request authenticate(final Route route, final Response response) {
            return null;
        }
    };
    
    Request authenticate(final Route p0, final Response p1) throws IOException;
}
