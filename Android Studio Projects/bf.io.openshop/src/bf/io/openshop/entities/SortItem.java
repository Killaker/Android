package bf.io.openshop.entities;

public class SortItem
{
    private String description;
    private String value;
    
    public SortItem() {
    }
    
    public SortItem(final String value, final String description) {
        this.value = value;
        this.description = description;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final SortItem sortItem = (SortItem)o;
            Label_0059: {
                if (this.value != null) {
                    if (this.value.equals(sortItem.value)) {
                        break Label_0059;
                    }
                }
                else if (sortItem.value == null) {
                    break Label_0059;
                }
                return false;
            }
            if (this.description != null) {
                if (this.description.equals(sortItem.description)) {
                    return true;
                }
            }
            else if (sortItem.description == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getValue() {
        return this.value;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.value != null) {
            hashCode = this.value.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n = hashCode * 31;
        final String description = this.description;
        int hashCode2 = 0;
        if (description != null) {
            hashCode2 = this.description.hashCode();
        }
        return n + hashCode2;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return this.description;
    }
}
