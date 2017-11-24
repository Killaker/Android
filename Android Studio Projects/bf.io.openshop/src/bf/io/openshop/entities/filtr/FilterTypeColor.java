package bf.io.openshop.entities.filtr;

import java.util.*;

public class FilterTypeColor extends FilterType
{
    private transient FilterValueColor selectedValue;
    private List<FilterValueColor> values;
    
    public FilterTypeColor() {
        this.selectedValue = null;
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
                final Class<? extends FilterTypeColor> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final boolean equals = super.equals(o);
                    b2 = false;
                    if (equals) {
                        final FilterTypeColor filterTypeColor = (FilterTypeColor)o;
                        if (this.selectedValue != null) {
                            final boolean equals2 = this.selectedValue.equals(filterTypeColor.selectedValue);
                            b2 = false;
                            if (!equals2) {
                                return b2;
                            }
                        }
                        else if (filterTypeColor.selectedValue != null) {
                            return false;
                        }
                        if (this.values != null) {
                            if (this.values.equals(filterTypeColor.values)) {
                                return b;
                            }
                        }
                        else if (filterTypeColor.values == null) {
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
    
    public FilterValueColor getSelectedValue() {
        return this.selectedValue;
    }
    
    public List<FilterValueColor> getValues() {
        return this.values;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * super.hashCode();
        int hashCode;
        if (this.selectedValue != null) {
            hashCode = this.selectedValue.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n2 = 31 * (n + hashCode);
        final List<FilterValueColor> values = this.values;
        int hashCode2 = 0;
        if (values != null) {
            hashCode2 = this.values.hashCode();
        }
        return n2 + hashCode2;
    }
    
    public void setSelectedValue(final FilterValueColor selectedValue) {
        this.selectedValue = selectedValue;
    }
    
    public void setValues(final List<FilterValueColor> values) {
        this.values = values;
    }
    
    @Override
    public String toString() {
        return super.toString() + ". FilterTypeColor{" + "selectedValue=" + this.selectedValue + ", values= ..." + '}';
    }
}
