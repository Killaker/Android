package okio;

import java.io.*;

final class Base64
{
    private static final byte[] MAP;
    private static final byte[] URL_MAP;
    
    static {
        MAP = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
        URL_MAP = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 };
    }
    
    public static byte[] decode(final String s) {
        int i;
        for (i = s.length(); i > 0; --i) {
            final char char1 = s.charAt(i - 1);
            if (char1 != '=' && char1 != '\n' && char1 != '\r' && char1 != ' ' && char1 != '\t') {
                break;
            }
        }
        byte[] array = new byte[(int)(6L * i / 8L)];
        int n = 0;
        int n2 = 0;
        int j = 0;
        int n3 = 0;
    Label_0178_Outer:
        while (j < i) {
            final char char2 = s.charAt(j);
            while (true) {
                Label_0448: {
                    int n4;
                    if (char2 >= 'A' && char2 <= 'Z') {
                        n4 = char2 - 'A';
                    }
                    else if (char2 >= 'a' && char2 <= 'z') {
                        n4 = char2 - 'G';
                    }
                    else if (char2 >= '0' && char2 <= '9') {
                        n4 = char2 + '\u0004';
                    }
                    else if (char2 == '+' || char2 == '-') {
                        n4 = 62;
                    }
                    else if (char2 == '/' || char2 == '_') {
                        n4 = 63;
                    }
                    else {
                        if (char2 == '\n' || char2 == '\r' || char2 == ' ') {
                            break Label_0448;
                        }
                        if (char2 == '\t') {
                            final int n5 = n3;
                            break Label_0178;
                        }
                        array = null;
                        return array;
                    }
                    n2 = (n2 << 6 | (byte)n4);
                    if (++n % 4 != 0) {
                        break Label_0448;
                    }
                    final int n6 = n3 + 1;
                    array[n3] = (byte)(n2 >> 16);
                    final int n7 = n6 + 1;
                    array[n6] = (byte)(n2 >> 8);
                    final int n5 = n7 + 1;
                    array[n7] = (byte)n2;
                    ++j;
                    n3 = n5;
                    continue Label_0178_Outer;
                }
                final int n5 = n3;
                continue;
            }
        }
        final int n8 = n % 4;
        if (n8 == 1) {
            return null;
        }
        int n10;
        if (n8 == 2) {
            final int n9 = n2 << 12;
            n10 = n3 + 1;
            array[n3] = (byte)(n9 >> 16);
        }
        else {
            if (n8 == 3) {
                final int n11 = n2 << 6;
                final int n12 = n3 + 1;
                array[n3] = (byte)(n11 >> 16);
                n3 = n12 + 1;
                array[n12] = (byte)(n11 >> 8);
            }
            n10 = n3;
        }
        if (n10 != array.length) {
            final byte[] array2 = new byte[n10];
            System.arraycopy(array, 0, array2, 0, n10);
            return array2;
        }
        return array;
    }
    
    public static String encode(final byte[] array) {
        return encode(array, Base64.MAP);
    }
    
    private static String encode(final byte[] array, final byte[] array2) {
        final byte[] array3 = new byte[4 * (2 + array.length) / 3];
        final int n = array.length - array.length % 3;
        int i = 0;
        int n2 = 0;
        while (i < n) {
            final int n3 = n2 + 1;
            array3[n2] = array2[(0xFF & array[i]) >> 2];
            final int n4 = n3 + 1;
            array3[n3] = array2[(0x3 & array[i]) << 4 | (0xFF & array[i + 1]) >> 4];
            final int n5 = n4 + 1;
            array3[n4] = array2[(0xF & array[i + 1]) << 2 | (0xFF & array[i + 2]) >> 6];
            n2 = n5 + 1;
            array3[n5] = array2[0x3F & array[i + 2]];
            i += 3;
        }
        while (true) {
            while (true) {
                switch (array.length % 3) {
                    case 1: {
                        Label_0198: {
                            break Label_0198;
                            try {
                                final int n6;
                                return new String(array3, 0, n6, "US-ASCII");
                                final int n7 = n2 + 1;
                                array3[n2] = array2[(0xFF & array[n]) >> 2];
                                final int n8 = n7 + 1;
                                array3[n7] = array2[(0x3 & array[n]) << 4 | (0xFF & array[n + 1]) >> 4];
                                final int n9 = n8 + 1;
                                array3[n8] = array2[(0xF & array[n + 1]) << 2];
                                n2 = n9 + 1;
                                array3[n9] = 61;
                                break;
                                final int n10 = n2 + 1;
                                array3[n2] = array2[(0xFF & array[n]) >> 2];
                                final int n11 = n10 + 1;
                                array3[n10] = array2[(0x3 & array[n]) << 4];
                                final int n12 = n11 + 1;
                                array3[n11] = 61;
                                final int n13 = n12 + 1;
                                array3[n12] = 61;
                                n6 = n13;
                                return new String(array3, 0, n6, "US-ASCII");
                            }
                            catch (UnsupportedEncodingException ex) {
                                throw new AssertionError((Object)ex);
                            }
                        }
                        break;
                    }
                    case 2: {
                        continue;
                    }
                }
                break;
            }
            int n6 = n2;
            continue;
        }
    }
    
    public static String encodeUrl(final byte[] array) {
        return encode(array, Base64.URL_MAP);
    }
}
