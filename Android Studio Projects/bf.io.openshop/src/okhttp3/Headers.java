package okhttp3;

import okhttp3.internal.http.*;
import java.util.*;

public final class Headers
{
    private final String[] namesAndValues;
    
    private Headers(final Builder builder) {
        this.namesAndValues = builder.namesAndValues.toArray(new String[builder.namesAndValues.size()]);
    }
    
    private Headers(final String[] namesAndValues) {
        this.namesAndValues = namesAndValues;
    }
    
    private static String get(final String[] array, final String s) {
        for (int i = -2 + array.length; i >= 0; i -= 2) {
            if (s.equalsIgnoreCase(array[i])) {
                return array[i + 1];
            }
        }
        return null;
    }
    
    public static Headers of(final Map<String, String> map) {
        if (map == null) {
            throw new IllegalArgumentException("Expected map with header names and values");
        }
        final String[] array = new String[2 * map.size()];
        int n = 0;
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            final String trim = entry.getKey().trim();
            final String trim2 = entry.getValue().trim();
            if (trim.length() == 0 || trim.indexOf(0) != -1 || trim2.indexOf(0) != -1) {
                throw new IllegalArgumentException("Unexpected header: " + trim + ": " + trim2);
            }
            array[n] = trim;
            array[n + 1] = trim2;
            n += 2;
        }
        return new Headers(array);
    }
    
    public static Headers of(final String... array) {
        if (array == null || array.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values");
        }
        final String[] array2 = array.clone();
        for (int i = 0; i < array2.length; ++i) {
            if (array2[i] == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            array2[i] = array2[i].trim();
        }
        for (int j = 0; j < array2.length; j += 2) {
            final String s = array2[j];
            final String s2 = array2[j + 1];
            if (s.length() == 0 || s.indexOf(0) != -1 || s2.indexOf(0) != -1) {
                throw new IllegalArgumentException("Unexpected header: " + s + ": " + s2);
            }
        }
        return new Headers(array2);
    }
    
    public String get(final String s) {
        return get(this.namesAndValues, s);
    }
    
    public Date getDate(final String s) {
        final String value = this.get(s);
        if (value != null) {
            return HttpDate.parse(value);
        }
        return null;
    }
    
    public String name(final int n) {
        return this.namesAndValues[n * 2];
    }
    
    public Set<String> names() {
        final TreeSet<String> set = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < this.size(); ++i) {
            set.add(this.name(i));
        }
        return (Set<String>)Collections.unmodifiableSet((Set<?>)set);
    }
    
    public Builder newBuilder() {
        final Builder builder = new Builder();
        Collections.addAll(builder.namesAndValues, this.namesAndValues);
        return builder;
    }
    
    public int size() {
        return this.namesAndValues.length / 2;
    }
    
    public Map<String, List<String>> toMultimap() {
        final LinkedHashMap<String, List<String>> linkedHashMap = (LinkedHashMap<String, List<String>>)new LinkedHashMap<Object, List<String>>();
        for (int i = 0; i < this.size(); ++i) {
            final String name = this.name(i);
            List<String> list = linkedHashMap.get(name);
            if (list == null) {
                list = new ArrayList<String>(2);
                linkedHashMap.put(name, list);
            }
            list.add(this.value(i));
        }
        return linkedHashMap;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size(); ++i) {
            sb.append(this.name(i)).append(": ").append(this.value(i)).append("\n");
        }
        return sb.toString();
    }
    
    public String value(final int n) {
        return this.namesAndValues[1 + n * 2];
    }
    
    public List<String> values(final String s) {
        List<? extends String> list = null;
        for (int i = 0; i < this.size(); ++i) {
            if (s.equalsIgnoreCase(this.name(i))) {
                if (list == null) {
                    list = new ArrayList<String>(2);
                }
                list.add(this.value(i));
            }
        }
        if (list != null) {
            return (List<String>)Collections.unmodifiableList((List<?>)list);
        }
        return Collections.emptyList();
    }
    
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
}
