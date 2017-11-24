package okhttp3.internal.framed;

static final class FrameLogger
{
    private static final String[] BINARY;
    private static final String[] FLAGS;
    private static final String[] TYPES;
    
    static {
        TYPES = new String[] { "DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION" };
        FLAGS = new String[64];
        BINARY = new String[256];
        for (int i = 0; i < FrameLogger.BINARY.length; ++i) {
            FrameLogger.BINARY[i] = String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
        }
        FrameLogger.FLAGS[0] = "";
        FrameLogger.FLAGS[1] = "END_STREAM";
        final int[] array = { 1 };
        FrameLogger.FLAGS[8] = "PADDED";
        for (final int n : array) {
            FrameLogger.FLAGS[n | 0x8] = FrameLogger.FLAGS[n] + "|PADDED";
        }
        FrameLogger.FLAGS[4] = "END_HEADERS";
        FrameLogger.FLAGS[32] = "PRIORITY";
        FrameLogger.FLAGS[36] = "END_HEADERS|PRIORITY";
        for (final int n2 : new int[] { 4, 32, 36 }) {
            for (final int n3 : array) {
                FrameLogger.FLAGS[n3 | n2] = FrameLogger.FLAGS[n3] + '|' + FrameLogger.FLAGS[n2];
                FrameLogger.FLAGS[0x8 | (n3 | n2)] = FrameLogger.FLAGS[n3] + '|' + FrameLogger.FLAGS[n2] + "|PADDED";
            }
        }
        for (int n4 = 0; n4 < FrameLogger.FLAGS.length; ++n4) {
            if (FrameLogger.FLAGS[n4] == null) {
                FrameLogger.FLAGS[n4] = FrameLogger.BINARY[n4];
            }
        }
    }
    
    static String formatFlags(final byte b, final byte b2) {
        if (b2 == 0) {
            return "";
        }
        switch (b) {
            default: {
                String s;
                if (b2 < FrameLogger.FLAGS.length) {
                    s = FrameLogger.FLAGS[b2];
                }
                else {
                    s = FrameLogger.BINARY[b2];
                }
                if (b == 5 && (b2 & 0x4) != 0x0) {
                    return s.replace("HEADERS", "PUSH_PROMISE");
                }
                if (b == 0 && (b2 & 0x20) != 0x0) {
                    return s.replace("PRIORITY", "COMPRESSED");
                }
                return s;
            }
            case 4:
            case 6: {
                if (b2 == 1) {
                    return "ACK";
                }
                return FrameLogger.BINARY[b2];
            }
            case 2:
            case 3:
            case 7:
            case 8: {
                return FrameLogger.BINARY[b2];
            }
        }
    }
    
    static String formatHeader(final boolean b, final int n, final int n2, final byte b2, final byte b3) {
        String format;
        if (b2 < FrameLogger.TYPES.length) {
            format = FrameLogger.TYPES[b2];
        }
        else {
            format = String.format("0x%02x", b2);
        }
        final String formatFlags = formatFlags(b2, b3);
        final Object[] array = new Object[5];
        String s;
        if (b) {
            s = "<<";
        }
        else {
            s = ">>";
        }
        array[0] = s;
        array[1] = n;
        array[2] = n2;
        array[3] = format;
        array[4] = formatFlags;
        return String.format("%s 0x%08x %5d %-13s %s", array);
    }
}
