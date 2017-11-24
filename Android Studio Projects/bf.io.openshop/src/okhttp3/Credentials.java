package okhttp3;

import okio.*;
import java.io.*;

public final class Credentials
{
    public static String basic(final String s, final String s2) {
        try {
            return "Basic " + ByteString.of((s + ":" + s2).getBytes("ISO-8859-1")).base64();
        }
        catch (UnsupportedEncodingException ex) {
            throw new AssertionError();
        }
    }
}
