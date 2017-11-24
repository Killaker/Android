package okhttp3;

import java.net.*;
import java.util.*;

static final class Dns$1 implements Dns {
    @Override
    public List<InetAddress> lookup(final String s) throws UnknownHostException {
        if (s == null) {
            throw new UnknownHostException("hostname == null");
        }
        return Arrays.asList(InetAddress.getAllByName(s));
    }
}