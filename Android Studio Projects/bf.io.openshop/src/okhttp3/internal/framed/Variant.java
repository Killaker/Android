package okhttp3.internal.framed;

import okhttp3.*;
import okio.*;

public interface Variant
{
    Protocol getProtocol();
    
    FrameReader newReader(final BufferedSource p0, final boolean p1);
    
    FrameWriter newWriter(final BufferedSink p0, final boolean p1);
}
