package okhttp3.internal.framed;

import java.util.*;

public final class Settings
{
    static final int CLIENT_CERTIFICATE_VECTOR_SIZE = 8;
    static final int COUNT = 10;
    static final int CURRENT_CWND = 5;
    static final int DEFAULT_INITIAL_WINDOW_SIZE = 65536;
    static final int DOWNLOAD_BANDWIDTH = 2;
    static final int DOWNLOAD_RETRANS_RATE = 6;
    static final int ENABLE_PUSH = 2;
    static final int FLAG_CLEAR_PREVIOUSLY_PERSISTED_SETTINGS = 1;
    static final int FLOW_CONTROL_OPTIONS = 10;
    static final int FLOW_CONTROL_OPTIONS_DISABLED = 1;
    static final int HEADER_TABLE_SIZE = 1;
    static final int INITIAL_WINDOW_SIZE = 7;
    static final int MAX_CONCURRENT_STREAMS = 4;
    static final int MAX_FRAME_SIZE = 5;
    static final int MAX_HEADER_LIST_SIZE = 6;
    static final int PERSISTED = 2;
    static final int PERSIST_VALUE = 1;
    static final int ROUND_TRIP_TIME = 3;
    static final int UPLOAD_BANDWIDTH = 1;
    private int persistValue;
    private int persisted;
    private int set;
    private final int[] values;
    
    public Settings() {
        this.values = new int[10];
    }
    
    void clear() {
        this.persisted = 0;
        this.persistValue = 0;
        this.set = 0;
        Arrays.fill(this.values, 0);
    }
    
    int flags(final int n) {
        final boolean persisted = this.isPersisted(n);
        int n2 = 0;
        if (persisted) {
            n2 = (0x0 | 0x2);
        }
        if (this.persistValue(n)) {
            n2 |= 0x1;
        }
        return n2;
    }
    
    int get(final int n) {
        return this.values[n];
    }
    
    int getClientCertificateVectorSize(int n) {
        if ((0x100 & this.set) != 0x0) {
            n = this.values[8];
        }
        return n;
    }
    
    int getCurrentCwnd(int n) {
        if ((0x20 & this.set) != 0x0) {
            n = this.values[5];
        }
        return n;
    }
    
    int getDownloadBandwidth(int n) {
        if ((0x4 & this.set) != 0x0) {
            n = this.values[2];
        }
        return n;
    }
    
    int getDownloadRetransRate(int n) {
        if ((0x40 & this.set) != 0x0) {
            n = this.values[6];
        }
        return n;
    }
    
    boolean getEnablePush(final boolean b) {
        int n;
        if ((0x4 & this.set) != 0x0) {
            n = this.values[2];
        }
        else if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        return n == 1;
    }
    
    int getHeaderTableSize() {
        if ((0x2 & this.set) != 0x0) {
            return this.values[1];
        }
        return -1;
    }
    
    int getInitialWindowSize(int n) {
        if ((0x80 & this.set) != 0x0) {
            n = this.values[7];
        }
        return n;
    }
    
    int getMaxConcurrentStreams(int n) {
        if ((0x10 & this.set) != 0x0) {
            n = this.values[4];
        }
        return n;
    }
    
    int getMaxFrameSize(int n) {
        if ((0x20 & this.set) != 0x0) {
            n = this.values[5];
        }
        return n;
    }
    
    int getMaxHeaderListSize(int n) {
        if ((0x40 & this.set) != 0x0) {
            n = this.values[6];
        }
        return n;
    }
    
    int getRoundTripTime(int n) {
        if ((0x8 & this.set) != 0x0) {
            n = this.values[3];
        }
        return n;
    }
    
    int getUploadBandwidth(int n) {
        if ((0x2 & this.set) != 0x0) {
            n = this.values[1];
        }
        return n;
    }
    
    boolean isFlowControlDisabled() {
        int n;
        if ((0x400 & this.set) != 0x0) {
            n = this.values[10];
        }
        else {
            n = 0;
        }
        final int n2 = n & 0x1;
        boolean b = false;
        if (n2 != 0) {
            b = true;
        }
        return b;
    }
    
    boolean isPersisted(final int n) {
        return (1 << n & this.persisted) != 0x0;
    }
    
    boolean isSet(final int n) {
        return (1 << n & this.set) != 0x0;
    }
    
    void merge(final Settings settings) {
        for (int i = 0; i < 10; ++i) {
            if (settings.isSet(i)) {
                this.set(i, settings.flags(i), settings.get(i));
            }
        }
    }
    
    boolean persistValue(final int n) {
        return (1 << n & this.persistValue) != 0x0;
    }
    
    Settings set(final int n, final int n2, final int n3) {
        if (n >= this.values.length) {
            return this;
        }
        final int n4 = 1 << n;
        this.set |= n4;
        if ((n2 & 0x1) != 0x0) {
            this.persistValue |= n4;
        }
        else {
            this.persistValue &= ~n4;
        }
        if ((n2 & 0x2) != 0x0) {
            this.persisted |= n4;
        }
        else {
            this.persisted &= ~n4;
        }
        this.values[n] = n3;
        return this;
    }
    
    int size() {
        return Integer.bitCount(this.set);
    }
}
