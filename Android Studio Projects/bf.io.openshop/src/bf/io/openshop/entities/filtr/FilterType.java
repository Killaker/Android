package bf.io.openshop.entities.filtr;

public class FilterType
{
    private long id;
    private String label;
    private String name;
    private String type;
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        boolean b2;
        if (this == o) {
            b2 = b;
        }
        else {
            b2 = false;
            if (o != null) {
                final Class<? extends FilterType> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final FilterType filterType = (FilterType)o;
                    final long n = lcmp(this.id, filterType.id);
                    b2 = false;
                    if (n == 0) {
                        if (this.name != null) {
                            final boolean equals = this.name.equals(filterType.name);
                            b2 = false;
                            if (!equals) {
                                return b2;
                            }
                        }
                        else if (filterType.name != null) {
                            return false;
                        }
                        if (this.type != null) {
                            final boolean equals2 = this.type.equals(filterType.type);
                            b2 = false;
                            if (!equals2) {
                                return b2;
                            }
                        }
                        else if (filterType.type != null) {
                            return false;
                        }
                        if (this.label != null) {
                            if (this.label.equals(filterType.label)) {
                                return b;
                            }
                        }
                        else if (filterType.label == null) {
                            return b;
                        }
                        b = false;
                        return b;
                    }
                }
            }
        }
        return b2;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getType() {
        return this.type;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (int)(this.id ^ this.id >>> 32);
        int hashCode;
        if (this.name != null) {
            hashCode = this.name.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n2 = 31 * (n + hashCode);
        int hashCode2;
        if (this.type != null) {
            hashCode2 = this.type.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int n3 = 31 * (n2 + hashCode2);
        final String label = this.label;
        int hashCode3 = 0;
        if (label != null) {
            hashCode3 = this.label.hashCode();
        }
        return n3 + hashCode3;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setLabel(final String label) {
        this.label = label;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "FilterType{id=" + this.id + ", name='" + this.name + '\'' + ", type='" + this.type + '\'' + ", label='" + this.label + '\'' + '}';
    }
}
