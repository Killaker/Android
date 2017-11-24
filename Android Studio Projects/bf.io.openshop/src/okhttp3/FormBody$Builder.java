package okhttp3;

import java.util.*;

public static final class Builder
{
    private final List<String> names;
    private final List<String> values;
    
    public Builder() {
        this.names = new ArrayList<String>();
        this.values = new ArrayList<String>();
    }
    
    public Builder add(final String s, final String s2) {
        this.names.add(HttpUrl.canonicalize(s, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
        this.values.add(HttpUrl.canonicalize(s2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
        return this;
    }
    
    public Builder addEncoded(final String s, final String s2) {
        this.names.add(HttpUrl.canonicalize(s, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true));
        this.values.add(HttpUrl.canonicalize(s2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true));
        return this;
    }
    
    public FormBody build() {
        return new FormBody(this.names, this.values, null);
    }
}
