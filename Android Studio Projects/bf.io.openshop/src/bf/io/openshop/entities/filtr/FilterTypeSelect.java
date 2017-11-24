package bf.io.openshop.entities.filtr;

import java.util.*;

public class FilterTypeSelect extends FilterType
{
    private transient FilterValueSelect selectedValue;
    private List<FilterValueSelect> values;
    
    public FilterTypeSelect() {
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
                final Class<? extends FilterTypeSelect> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final boolean equals = super.equals(o);
                    b2 = false;
                    if (equals) {
                        final FilterTypeSelect filterTypeSelect = (FilterTypeSelect)o;
                        if (this.selectedValue != null) {
                            final boolean equals2 = this.selectedValue.equals(filterTypeSelect.selectedValue);
                            b2 = false;
                            if (!equals2) {
                                return b2;
                            }
                        }
                        else if (filterTypeSelect.selectedValue != null) {
                            return false;
                        }
                        if (this.values != null) {
                            if (this.values.equals(filterTypeSelect.values)) {
                                return b;
                            }
                        }
                        else if (filterTypeSelect.values == null) {
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
    
    public FilterValueSelect getSelectedValue() {
        return this.selectedValue;
    }
    
    public List<FilterValueSelect> getValues() {
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
        final List<FilterValueSelect> values = this.values;
        int hashCode2 = 0;
        if (values != null) {
            hashCode2 = this.values.hashCode();
        }
        return n2 + hashCode2;
    }
    
    public void setSelectedValue(final FilterValueSelect selectedValue) {
        this.selectedValue = selectedValue;
    }
    
    public void setValues(final List<FilterValueSelect> values) {
        this.values = values;
    }
    
    @Override
    public String toString() {
        return super.toString() + ".FilterTypeSelect{" + "selectedValue=" + this.selectedValue + ", values= ..." + '}';
    }
}
