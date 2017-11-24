package bf.io.openshop.entities.filtr;

public class FilterValueSelect
{
    private long id;
    private String value;
    
    public FilterValueSelect() {
        this.id = 0L;
    }
    
    public FilterValueSelect(final long id, final String value) {
        this.id = 0L;
        this.id = id;
        this.value = value;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final FilterValueSelect filterValueSelect = (FilterValueSelect)o;
            if (this.id != filterValueSelect.id) {
                return false;
            }
            if (this.value != null) {
                if (this.value.equals(filterValueSelect.value)) {
                    return true;
                }
            }
            else if (filterValueSelect.value == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    public long getId() {
        return this.id;
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
        return n + hashCode;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "FilterValueSelect{id=" + this.id + ", value='" + this.value + '\'' + '}';
    }
}
