package okhttp3.internal.framed;

import okio.*;
import java.io.*;
import java.util.*;

static final class Writer
{
    private final Buffer out;
    
    Writer(final Buffer out) {
        this.out = out;
    }
    
    void writeByteString(final ByteString byteString) throws IOException {
        this.writeInt(byteString.size(), 127, 0);
        this.out.write(byteString);
    }
    
    void writeHeaders(final List<Header> list) throws IOException {
        for (int i = 0; i < list.size(); ++i) {
            final ByteString asciiLowercase = list.get(i).name.toAsciiLowercase();
            final Integer n = Hpack.access$200().get(asciiLowercase);
            if (n != null) {
                this.writeInt(1 + n, 15, 0);
                this.writeByteString(list.get(i).value);
            }
            else {
                this.out.writeByte(0);
                this.writeByteString(asciiLowercase);
                this.writeByteString(list.get(i).value);
            }
        }
    }
    
    void writeInt(final int n, final int n2, final int n3) throws IOException {
        if (n < n2) {
            this.out.writeByte(n3 | n);
            return;
        }
        this.out.writeByte(n3 | n2);
        int i;
        for (i = n - n2; i >= 128; i >>>= 7) {
            this.out.writeByte((i & 0x7F) | 0x80);
        }
        this.out.writeByte(i);
    }
}
