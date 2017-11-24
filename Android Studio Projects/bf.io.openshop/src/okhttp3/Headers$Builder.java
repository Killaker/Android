package okhttp3;

import java.util.*;

public static final class Builder
{
    private final List<String> namesAndValues;
    
    public Builder() {
        this.namesAndValues = new ArrayList<String>(20);
    }
    
    private void checkNameAndValue(final String s, final String s2) {
        if (s == null) {
            throw new IllegalArgumentException("name == null");
        }
        if (s.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        }
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 <= '\u001f' || char1 >= '\u007f') {
                throw new IllegalArgumentException(String.format("Unexpected char %#04x at %d in header name: %s", (int)char1, i, s));
            }
        }
        if (s2 == null) {
            throw new IllegalArgumentException("value == null");
        }
        for (int j = 0; j < s2.length(); ++j) {
            final char char2 = s2.charAt(j);
            if (char2 <= '\u001f' || char2 >= '\u007f') {
                throw new IllegalArgumentException(String.format("Unexpected char %#04x at %d in %s value: %s", (int)char2, j, s, s2));
            }
        }
    }
    
    public Builder add(final String s) {
        final int index = s.indexOf(":");
        if (index == -1) {
            throw new IllegalArgumentException("Unexpected header: " + s);
        }
        return this.add(s.substring(0, index).trim(), s.substring(index + 1));
    }
    
    public Builder add(final String s, final String s2) {
        this.checkNameAndValue(s, s2);
        return this.addLenient(s, s2);
    }
    
    Builder addLenient(final String s) {
        final int index = s.indexOf(":", 1);
        if (index != -1) {
            return this.addLenient(s.substring(0, index), s.substring(index + 1));
        }
        if (s.startsWith(":")) {
            return this.addLenient("", s.substring(1));
        }
        return this.addLenient("", s);
    }
    
    Builder addLenient(final String s, final String s2) {
        this.namesAndValues.add(s);
        this.namesAndValues.add(s2.trim());
        return this;
    }
    
    public Headers build() {
        return new Headers(this, null);
    }
    
    public String get(final String s) {
        for (int i = -2 + this.namesAndValues.size(); i >= 0; i -= 2) {
            if (s.equalsIgnoreCase(this.namesAndValues.get(i))) {
                return this.namesAndValues.get(i + 1);
            }
        }
        return null;
    }
    
    public Builder removeAll(final String s) {
        for (int i = 0; i < this.namesAndValues.size(); i += 2) {
            if (s.equalsIgnoreCase(this.namesAndValues.get(i))) {
                this.namesAndValues.remove(i);
                this.namesAndValues.remove(i);
                i -= 2;
            }
        }
        return this;
    }
    
    public Builder set(final String s, final String s2) {
        this.checkNameAndValue(s, s2);
        this.removeAll(s);
        this.addLenient(s, s2);
        return this;
    }
}
