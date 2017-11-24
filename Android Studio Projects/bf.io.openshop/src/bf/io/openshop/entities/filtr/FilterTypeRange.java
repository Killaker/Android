package bf.io.openshop.entities.filtr;

public class FilterTypeRange extends FilterType
{
    private int max;
    private int min;
    private String rangeTitle;
    private transient int selectedMax;
    private transient int selectedMin;
    
    public FilterTypeRange() {
        this.selectedMin = -1;
        this.selectedMax = -1;
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
                final Class<? extends FilterTypeRange> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final boolean equals = super.equals(o);
                    b2 = false;
                    if (equals) {
                        final FilterTypeRange filterTypeRange = (FilterTypeRange)o;
                        final int selectedMin = this.selectedMin;
                        final int selectedMin2 = filterTypeRange.selectedMin;
                        b2 = false;
                        if (selectedMin == selectedMin2) {
                            final int selectedMax = this.selectedMax;
                            final int selectedMax2 = filterTypeRange.selectedMax;
                            b2 = false;
                            if (selectedMax == selectedMax2) {
                                final int min = this.min;
                                final int min2 = filterTypeRange.min;
                                b2 = false;
                                if (min == min2) {
                                    final int max = this.max;
                                    final int max2 = filterTypeRange.max;
                                    b2 = false;
                                    if (max == max2) {
                                        if (this.rangeTitle != null) {
                                            if (this.rangeTitle.equals(filterTypeRange.rangeTitle)) {
                                                return b;
                                            }
                                        }
                                        else if (filterTypeRange.rangeTitle == null) {
                                            return b;
                                        }
                                        b = false;
                                        return b;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    public int getMax() {
        return this.max;
    }
    
    public int getMin() {
        return this.min;
    }
    
    public String getRangeTitle() {
        return this.rangeTitle;
    }
    
    public int getSelectedMax() {
        return this.selectedMax;
    }
    
    public int getSelectedMin() {
        return this.selectedMin;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (31 * (31 * (31 * super.hashCode() + this.selectedMin) + this.selectedMax) + this.min) + this.max);
        int hashCode;
        if (this.rangeTitle != null) {
            hashCode = this.rangeTitle.hashCode();
        }
        else {
            hashCode = 0;
        }
        return n + hashCode;
    }
    
    public void setMax(final int max) {
        this.max = max;
    }
    
    public void setMin(final int min) {
        this.min = min;
    }
    
    public void setRangeTitle(final String rangeTitle) {
        this.rangeTitle = rangeTitle;
    }
    
    public void setSelectedMax(final int selectedMax) {
        this.selectedMax = selectedMax;
    }
    
    public void setSelectedMin(final int selectedMin) {
        this.selectedMin = selectedMin;
    }
    
    @Override
    public String toString() {
        return "FilterTypeRange{selectedMin=" + this.selectedMin + ", selectedMax=" + this.selectedMax + ", min=" + this.min + ", max=" + this.max + ", rangeTitle='" + this.rangeTitle + '\'' + '}';
    }
}
