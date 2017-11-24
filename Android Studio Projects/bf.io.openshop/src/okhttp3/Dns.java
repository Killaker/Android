package okhttp3;

import java.net.*;
import java.util.*;

public interface Dns
{
    public static final Dns SYSTEM = new Dns() {
        @Override
        public List<InetAddress> lookup(final String s) throws UnknownHostException {
            if (s == null) {
                throw new UnknownHostException("hostname == null");
            }
            return Arrays.asList(InetAddress.getAllByName(s));
        }
    };
    
    List<InetAddress> lookup(final String p0) throws UnknownHostException;
}
