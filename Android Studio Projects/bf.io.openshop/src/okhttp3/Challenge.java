package okhttp3;

import okhttp3.internal.*;

public final class Challenge
{
    private final String realm;
    private final String scheme;
    
    public Challenge(final String scheme, final String realm) {
        this.scheme = scheme;
        this.realm = realm;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof Challenge && Util.equal(this.scheme, ((Challenge)o).scheme) && Util.equal(this.realm, ((Challenge)o).realm);
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.realm != null) {
            hashCode = this.realm.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n = 31 * (hashCode + 899);
        final String scheme = this.scheme;
        int hashCode2 = 0;
        if (scheme != null) {
            hashCode2 = this.scheme.hashCode();
        }
        return n + hashCode2;
    }
    
    public String realm() {
        return this.realm;
    }
    
    public String scheme() {
        return this.scheme;
    }
    
    @Override
    public String toString() {
        return this.scheme + " realm=\"" + this.realm + "\"";
    }
}
