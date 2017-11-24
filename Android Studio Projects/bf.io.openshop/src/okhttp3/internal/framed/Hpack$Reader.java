package okhttp3.internal.framed;

import okio.*;
import java.io.*;
import java.util.*;

static final class Reader
{
    Header[] dynamicTable;
    int dynamicTableByteCount;
    int headerCount;
    private final List<Header> headerList;
    private int headerTableSizeSetting;
    private int maxDynamicTableByteCount;
    int nextHeaderIndex;
    private final BufferedSource source;
    
    Reader(final int n, final Source source) {
        this.headerList = new ArrayList<Header>();
        this.dynamicTable = new Header[8];
        this.nextHeaderIndex = -1 + this.dynamicTable.length;
        this.headerCount = 0;
        this.dynamicTableByteCount = 0;
        this.headerTableSizeSetting = n;
        this.maxDynamicTableByteCount = n;
        this.source = Okio.buffer(source);
    }
    
    private void adjustDynamicTableByteCount() {
        if (this.maxDynamicTableByteCount < this.dynamicTableByteCount) {
            if (this.maxDynamicTableByteCount != 0) {
                this.evictToRecoverBytes(this.dynamicTableByteCount - this.maxDynamicTableByteCount);
                return;
            }
            this.clearDynamicTable();
        }
    }
    
    private void clearDynamicTable() {
        this.headerList.clear();
        Arrays.fill(this.dynamicTable, null);
        this.nextHeaderIndex = -1 + this.dynamicTable.length;
        this.headerCount = 0;
        this.dynamicTableByteCount = 0;
    }
    
    private int dynamicTableIndex(final int n) {
        return n + (1 + this.nextHeaderIndex);
    }
    
    private int evictToRecoverBytes(int n) {
        int n2 = 0;
        if (n > 0) {
            for (int n3 = -1 + this.dynamicTable.length; n3 >= this.nextHeaderIndex && n > 0; n -= this.dynamicTable[n3].hpackSize, this.dynamicTableByteCount -= this.dynamicTable[n3].hpackSize, --this.headerCount, ++n2, --n3) {}
            System.arraycopy(this.dynamicTable, 1 + this.nextHeaderIndex, this.dynamicTable, n2 + (1 + this.nextHeaderIndex), this.headerCount);
            this.nextHeaderIndex += n2;
        }
        return n2;
    }
    
    private ByteString getName(final int n) {
        if (this.isStaticHeader(n)) {
            return Hpack.access$000()[n].name;
        }
        return this.dynamicTable[this.dynamicTableIndex(n - Hpack.access$000().length)].name;
    }
    
    private void insertIntoDynamicTable(final int n, final Header header) {
        this.headerList.add(header);
        int hpackSize = header.hpackSize;
        if (n != -1) {
            hpackSize -= this.dynamicTable[this.dynamicTableIndex(n)].hpackSize;
        }
        if (hpackSize > this.maxDynamicTableByteCount) {
            this.clearDynamicTable();
            return;
        }
        final int evictToRecoverBytes = this.evictToRecoverBytes(hpackSize + this.dynamicTableByteCount - this.maxDynamicTableByteCount);
        if (n == -1) {
            if (1 + this.headerCount > this.dynamicTable.length) {
                final Header[] dynamicTable = new Header[2 * this.dynamicTable.length];
                System.arraycopy(this.dynamicTable, 0, dynamicTable, this.dynamicTable.length, this.dynamicTable.length);
                this.nextHeaderIndex = -1 + this.dynamicTable.length;
                this.dynamicTable = dynamicTable;
            }
            this.dynamicTable[this.nextHeaderIndex--] = header;
            ++this.headerCount;
        }
        else {
            this.dynamicTable[n + (evictToRecoverBytes + this.dynamicTableIndex(n))] = header;
        }
        this.dynamicTableByteCount += hpackSize;
    }
    
    private boolean isStaticHeader(final int n) {
        return n >= 0 && n <= -1 + Hpack.access$000().length;
    }
    
    private int readByte() throws IOException {
        return 0xFF & this.source.readByte();
    }
    
    private void readIndexedHeader(final int n) throws IOException {
        if (this.isStaticHeader(n)) {
            this.headerList.add(Hpack.access$000()[n]);
            return;
        }
        final int dynamicTableIndex = this.dynamicTableIndex(n - Hpack.access$000().length);
        if (dynamicTableIndex < 0 || dynamicTableIndex > -1 + this.dynamicTable.length) {
            throw new IOException("Header index too large " + (n + 1));
        }
        this.headerList.add(this.dynamicTable[dynamicTableIndex]);
    }
    
    private void readLiteralHeaderWithIncrementalIndexingIndexedName(final int n) throws IOException {
        this.insertIntoDynamicTable(-1, new Header(this.getName(n), this.readByteString()));
    }
    
    private void readLiteralHeaderWithIncrementalIndexingNewName() throws IOException {
        this.insertIntoDynamicTable(-1, new Header(Hpack.access$100(this.readByteString()), this.readByteString()));
    }
    
    private void readLiteralHeaderWithoutIndexingIndexedName(final int n) throws IOException {
        this.headerList.add(new Header(this.getName(n), this.readByteString()));
    }
    
    private void readLiteralHeaderWithoutIndexingNewName() throws IOException {
        this.headerList.add(new Header(Hpack.access$100(this.readByteString()), this.readByteString()));
    }
    
    public List<Header> getAndResetHeaderList() {
        final ArrayList<Header> list = new ArrayList<Header>(this.headerList);
        this.headerList.clear();
        return list;
    }
    
    void headerTableSizeSetting(final int n) {
        this.headerTableSizeSetting = n;
        this.maxDynamicTableByteCount = n;
        this.adjustDynamicTableByteCount();
    }
    
    int maxDynamicTableByteCount() {
        return this.maxDynamicTableByteCount;
    }
    
    ByteString readByteString() throws IOException {
        final int byte1 = this.readByte();
        int n;
        if ((byte1 & 0x80) == 0x80) {
            n = 1;
        }
        else {
            n = 0;
        }
        final int int1 = this.readInt(byte1, 127);
        if (n != 0) {
            return ByteString.of(Huffman.get().decode(this.source.readByteArray(int1)));
        }
        return this.source.readByteString(int1);
    }
    
    void readHeaders() throws IOException {
        while (!this.source.exhausted()) {
            final int n = 0xFF & this.source.readByte();
            if (n == 128) {
                throw new IOException("index == 0");
            }
            if ((n & 0x80) == 0x80) {
                this.readIndexedHeader(-1 + this.readInt(n, 127));
            }
            else if (n == 64) {
                this.readLiteralHeaderWithIncrementalIndexingNewName();
            }
            else if ((n & 0x40) == 0x40) {
                this.readLiteralHeaderWithIncrementalIndexingIndexedName(-1 + this.readInt(n, 63));
            }
            else if ((n & 0x20) == 0x20) {
                this.maxDynamicTableByteCount = this.readInt(n, 31);
                if (this.maxDynamicTableByteCount < 0 || this.maxDynamicTableByteCount > this.headerTableSizeSetting) {
                    throw new IOException("Invalid dynamic table size update " + this.maxDynamicTableByteCount);
                }
                this.adjustDynamicTableByteCount();
            }
            else if (n == 16 || n == 0) {
                this.readLiteralHeaderWithoutIndexingNewName();
            }
            else {
                this.readLiteralHeaderWithoutIndexingIndexedName(-1 + this.readInt(n, 15));
            }
        }
    }
    
    int readInt(final int n, final int n2) throws IOException {
        final int n3 = n & n2;
        if (n3 < n2) {
            return n3;
        }
        int n4 = n2;
        int n5 = 0;
        int byte1;
        while (true) {
            byte1 = this.readByte();
            if ((byte1 & 0x80) == 0x0) {
                break;
            }
            n4 += (byte1 & 0x7F) << n5;
            n5 += 7;
        }
        return n4 + (byte1 << n5);
    }
}
