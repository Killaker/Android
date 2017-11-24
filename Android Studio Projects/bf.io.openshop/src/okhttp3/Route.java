package okhttp3;

import java.net.*;

public final class Route
{
    final Address address;
    final InetSocketAddress inetSocketAddress;
    final Proxy proxy;
    
    public Route(final Address address, final Proxy proxy, final InetSocketAddress inetSocketAddress) {
        if (address == null) {
            throw new NullPointerException("address == null");
        }
        if (proxy == null) {
            throw new NullPointerException("proxy == null");
        }
        if (inetSocketAddress == null) {
            throw new NullPointerException("inetSocketAddress == null");
        }
        this.address = address;
        this.proxy = proxy;
        this.inetSocketAddress = inetSocketAddress;
    }
    
    public Address address() {
        return this.address;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = o instanceof Route;
        boolean b2 = false;
        if (b) {
            final Route route = (Route)o;
            final boolean equals = this.address.equals(route.address);
            b2 = false;
            if (equals) {
                final boolean equals2 = this.proxy.equals(route.proxy);
                b2 = false;
                if (equals2) {
                    final boolean equals3 = this.inetSocketAddress.equals(route.inetSocketAddress);
                    b2 = false;
                    if (equals3) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return 31 * (31 * (527 + this.address.hashCode()) + this.proxy.hashCode()) + this.inetSocketAddress.hashCode();
    }
    
    public Proxy proxy() {
        return this.proxy;
    }
    
    public boolean requiresTunnel() {
        return this.address.sslSocketFactory != null && this.proxy.type() == Proxy.Type.HTTP;
    }
    
    public InetSocketAddress socketAddress() {
        return this.inetSocketAddress;
    }
}
