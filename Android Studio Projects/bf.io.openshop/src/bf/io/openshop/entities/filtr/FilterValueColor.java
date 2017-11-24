package bf.io.openshop.entities.filtr;

public class FilterValueColor
{
    private String code;
    private long id;
    private String img;
    private String value;
    
    public FilterValueColor() {
        this.id = 0L;
    }
    
    public FilterValueColor(final long id, final String value) {
        this.id = 0L;
        this.id = id;
        this.value = value;
    }
    
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
                final Class<? extends FilterValueColor> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final FilterValueColor filterValueColor = (FilterValueColor)o;
                    final long n = lcmp(this.id, filterValueColor.id);
                    b2 = false;
                    if (n == 0) {
                        if (this.value != null) {
                            final boolean equals = this.value.equals(filterValueColor.value);
                            b2 = false;
                            if (!equals) {
                                return b2;
                            }
                        }
                        else if (filterValueColor.value != null) {
                            return false;
                        }
                        if (this.code != null) {
                            final boolean equals2 = this.code.equals(filterValueColor.code);
                            b2 = false;
                            if (!equals2) {
                                return b2;
                            }
                        }
                        else if (filterValueColor.code != null) {
                            return false;
                        }
                        if (this.img != null) {
                            if (this.img.equals(filterValueColor.img)) {
                                return b;
                            }
                        }
                        else if (filterValueColor.img == null) {
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
    
    public String getCode() {
        return this.code;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getImg() {
        return this.img;
    }
    
    public String getValue() {
        return this.value;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (int)(this.id ^ this.id >>> 32);
        int hashCode;
        if (this.value != null) {
            hashCode = this.value.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n2 = 31 * (n + hashCode);
        int hashCode2;
        if (this.code != null) {
            hashCode2 = this.code.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int n3 = 31 * (n2 + hashCode2);
        final String img = this.img;
        int hashCode3 = 0;
        if (img != null) {
            hashCode3 = this.img.hashCode();
        }
        return n3 + hashCode3;
    }
    
    public void setCode(final String code) {
        this.code = code;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setImg(final String img) {
        this.img = img;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "FilterValueColor{id=" + this.id + ", value='" + this.value + '\'' + ", code='" + this.code + '\'' + ", img='" + this.img + '\'' + '}';
    }
}
