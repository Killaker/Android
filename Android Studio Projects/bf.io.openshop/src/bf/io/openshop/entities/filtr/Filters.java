package bf.io.openshop.entities.filtr;

import java.util.*;

public class Filters
{
    private List<FilterType> filters;
    
    public Filters() {
    }
    
    public Filters(final List<FilterType> filters) {
        this.filters = filters;
    }
    
    public List<FilterType> getFilters() {
        return this.filters;
    }
    
    public void setFilters(final List<FilterType> filters) {
        this.filters = filters;
    }
}
