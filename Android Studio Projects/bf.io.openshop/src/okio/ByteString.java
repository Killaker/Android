package okio;

import java.security.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;

public class ByteString implements Serializable, Comparable<ByteString>
{
    public static final ByteString EMPTY;
    static final char[] HEX_DIGITS;
    private static final long serialVersionUID = 1L;
    final byte[] data;
    transient int hashCode;
    transient String utf8;
    
    static {
        HEX_DIGITS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        EMPTY = of(new byte[0]);
    }
    
    ByteString(final byte[] data) {
        this.data = data;
    }
    
    public static ByteString decodeBase64(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("base64 == null");
        }
        final byte[] decode = Base64.decode(s);
        if (decode != null) {
            return new ByteString(decode);
        }
        return null;
    }
    
    public static ByteString decodeHex(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("hex == null");
        }
        if (s.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: " + s);
        }
        final byte[] array = new byte[s.length() / 2];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (byte)((decodeHexDigit(s.charAt(i * 2)) << 4) + decodeHexDigit(s.charAt(1 + i * 2)));
        }
        return of(array);
    }
    
    private static int decodeHexDigit(final char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return '\n' + (c - 'a');
        }
        if (c >= 'A' && c <= 'F') {
            return '\n' + (c - 'A');
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c);
    }
    
    private ByteString digest(final String s) {
        try {
            return of(MessageDigest.getInstance(s).digest(this.data));
        }
        catch (NoSuchAlgorithmException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    public static ByteString encodeUtf8(final String utf8) {
        if (utf8 == null) {
            throw new IllegalArgumentException("s == null");
        }
        final ByteString byteString = new ByteString(utf8.getBytes(Util.UTF_8));
        byteString.utf8 = utf8;
        return byteString;
    }
    
    public static ByteString of(final byte... array) {
        if (array == null) {
            throw new IllegalArgumentException("data == null");
        }
        return new ByteString(array.clone());
    }
    
    public static ByteString of(final byte[] array, final int n, final int n2) {
        if (array == null) {
            throw new IllegalArgumentException("data == null");
        }
        Util.checkOffsetAndCount(array.length, n, n2);
        final byte[] array2 = new byte[n2];
        System.arraycopy(array, n, array2, 0, n2);
        return new ByteString(array2);
    }
    
    public static ByteString read(final InputStream inputStream, final int n) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (n < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + n);
        }
        final byte[] array = new byte[n];
        int read;
        for (int i = 0; i < n; i += read) {
            read = inputStream.read(array, i, n - i);
            if (read == -1) {
                throw new EOFException();
            }
        }
        return new ByteString(array);
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException {
        final ByteString read = read(objectInputStream, objectInputStream.readInt());
        try {
            final Field declaredField = ByteString.class.getDeclaredField("data");
            declaredField.setAccessible(true);
            declaredField.set(this, read.data);
        }
        catch (NoSuchFieldException ex) {
            throw new AssertionError();
        }
        catch (IllegalAccessException ex2) {
            throw new AssertionError();
        }
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.write(this.data);
    }
    
    public String base64() {
        return Base64.encode(this.data);
    }
    
    public String base64Url() {
        return Base64.encodeUrl(this.data);
    }
    
    @Override
    public int compareTo(final ByteString byteString) {
        final int size = this.size();
        final int size2 = byteString.size();
        int i = 0;
        while (i < Math.min(size, size2)) {
            final int n = 0xFF & this.getByte(i);
            final int n2 = 0xFF & byteString.getByte(i);
            if (n == n2) {
                ++i;
            }
            else {
                if (n < n2) {
                    return -1;
                }
                return 1;
            }
        }
        if (size == size2) {
            return 0;
        }
        if (size >= size2) {
            return 1;
        }
        return -1;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof ByteString && ((ByteString)o).size() == this.data.length && ((ByteString)o).rangeEquals(0, this.data, 0, this.data.length));
    }
    
    public byte getByte(final int n) {
        return this.data[n];
    }
    
    @Override
    public int hashCode() {
        final int hashCode = this.hashCode;
        if (hashCode != 0) {
            return hashCode;
        }
        return this.hashCode = Arrays.hashCode(this.data);
    }
    
    public String hex() {
        final char[] array = new char[2 * this.data.length];
        final byte[] data = this.data;
        final int length = data.length;
        int i = 0;
        int n = 0;
        while (i < length) {
            final byte b = data[i];
            final int n2 = n + 1;
            array[n] = ByteString.HEX_DIGITS[0xF & b >> 4];
            n = n2 + 1;
            array[n2] = ByteString.HEX_DIGITS[b & 0xF];
            ++i;
        }
        return new String(array);
    }
    
    public ByteString md5() {
        return this.digest("MD5");
    }
    
    public boolean rangeEquals(final int n, final ByteString byteString, final int n2, final int n3) {
        return byteString.rangeEquals(n2, this.data, n, n3);
    }
    
    public boolean rangeEquals(final int n, final byte[] array, final int n2, final int n3) {
        return n <= this.data.length - n3 && n2 <= array.length - n3 && Util.arrayRangeEquals(this.data, n, array, n2, n3);
    }
    
    public ByteString sha256() {
        return this.digest("SHA-256");
    }
    
    public int size() {
        return this.data.length;
    }
    
    public ByteString substring(final int n) {
        return this.substring(n, this.data.length);
    }
    
    public ByteString substring(final int n, final int n2) {
        if (n < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        if (n2 > this.data.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.data.length + ")");
        }
        final int n3 = n2 - n;
        if (n3 < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        if (n == 0 && n2 == this.data.length) {
            return this;
        }
        final byte[] array = new byte[n3];
        System.arraycopy(this.data, n, array, 0, n3);
        return new ByteString(array);
    }
    
    public ByteString toAsciiLowercase() {
        ByteString byteString = null;
        for (int i = 0; i < this.data.length; ++i) {
            final byte b = this.data[i];
            if (b >= 65 && b <= 90) {
                final byte[] array = this.data.clone();
                final int n = i + 1;
                array[i] = (byte)(b + 32);
                for (int j = n; j < array.length; ++j) {
                    final byte b2 = array[j];
                    if (b2 >= 65 && b2 <= 90) {
                        array[j] = (byte)(b2 + 32);
                    }
                }
                byteString = new ByteString(array);
                break;
            }
        }
        return byteString;
    }
    
    public ByteString toAsciiUppercase() {
        ByteString byteString = null;
        for (int i = 0; i < this.data.length; ++i) {
            final byte b = this.data[i];
            if (b >= 97 && b <= 122) {
                final byte[] array = this.data.clone();
                final int n = i + 1;
                array[i] = (byte)(b - 32);
                for (int j = n; j < array.length; ++j) {
                    final byte b2 = array[j];
                    if (b2 >= 97 && b2 <= 122) {
                        array[j] = (byte)(b2 - 32);
                    }
                }
                byteString = new ByteString(array);
                break;
            }
        }
        return byteString;
    }
    
    public byte[] toByteArray() {
        return this.data.clone();
    }
    
    @Override
    public String toString() {
        if (this.data.length == 0) {
            return "ByteString[size=0]";
        }
        if (this.data.length <= 16) {
            return String.format("ByteString[size=%s data=%s]", this.data.length, this.hex());
        }
        return String.format("ByteString[size=%s md5=%s]", this.data.length, this.md5().hex());
    }
    
    public String utf8() {
        final String utf8 = this.utf8;
        if (utf8 != null) {
            return utf8;
        }
        return this.utf8 = new String(this.data, Util.UTF_8);
    }
    
    public void write(final OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        outputStream.write(this.data);
    }
    
    void write(final Buffer buffer) {
        buffer.write(this.data, 0, this.data.length);
    }
}
