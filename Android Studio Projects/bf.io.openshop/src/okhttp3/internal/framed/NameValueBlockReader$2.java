package okhttp3.internal.framed;

import java.util.zip.*;

class NameValueBlockReader$2 extends Inflater {
    @Override
    public int inflate(final byte[] array, final int n, final int n2) throws DataFormatException {
        int n3 = super.inflate(array, n, n2);
        if (n3 == 0 && this.needsDictionary()) {
            this.setDictionary(Spdy3.DICTIONARY);
            n3 = super.inflate(array, n, n2);
        }
        return n3;
    }
}