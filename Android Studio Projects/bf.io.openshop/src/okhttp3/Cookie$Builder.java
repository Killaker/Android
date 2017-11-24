package okhttp3;

import okhttp3.internal.*;

public static final class Builder
{
    String domain;
    long expiresAt;
    boolean hostOnly;
    boolean httpOnly;
    String name;
    String path;
    boolean persistent;
    boolean secure;
    String value;
    
    public Builder() {
        this.expiresAt = 253402300799999L;
        this.path = "/";
    }
    
    private Builder domain(final String s, final boolean hostOnly) {
        if (s == null) {
            throw new IllegalArgumentException("domain == null");
        }
        final String domainToAscii = Util.domainToAscii(s);
        if (domainToAscii == null) {
            throw new IllegalArgumentException("unexpected domain: " + s);
        }
        this.domain = domainToAscii;
        this.hostOnly = hostOnly;
        return this;
    }
    
    public Cookie build() {
        return new Cookie(this, null);
    }
    
    public Builder domain(final String s) {
        return this.domain(s, false);
    }
    
    public Builder expiresAt(long expiresAt) {
        if (expiresAt <= 0L) {
            expiresAt = Long.MIN_VALUE;
        }
        if (expiresAt > 253402300799999L) {
            expiresAt = 253402300799999L;
        }
        this.expiresAt = expiresAt;
        this.persistent = true;
        return this;
    }
    
    public Builder hostOnlyDomain(final String s) {
        return this.domain(s, true);
    }
    
    public Builder httpOnly() {
        this.httpOnly = true;
        return this;
    }
    
    public Builder name(final String name) {
        if (name == null) {
            throw new NullPointerException("name == null");
        }
        if (!name.trim().equals(name)) {
            throw new IllegalArgumentException("name is not trimmed");
        }
        this.name = name;
        return this;
    }
    
    public Builder path(final String path) {
        if (!path.startsWith("/")) {
            throw new IllegalArgumentException("path must start with '/'");
        }
        this.path = path;
        return this;
    }
    
    public Builder secure() {
        this.secure = true;
        return this;
    }
    
    public Builder value(final String value) {
        if (value == null) {
            throw new NullPointerException("value == null");
        }
        if (!value.trim().equals(value)) {
            throw new IllegalArgumentException("value is not trimmed");
        }
        this.value = value;
        return this;
    }
}
