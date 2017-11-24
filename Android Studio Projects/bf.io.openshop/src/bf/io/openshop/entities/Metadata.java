package bf.io.openshop.entities;

import bf.io.openshop.entities.filtr.*;
import com.google.gson.annotations.*;

public class Metadata
{
    private Filters filters;
    private Links links;
    @SerializedName("records_count")
    private int recordsCount;
    private String sorting;
    
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
                final Class<? extends Metadata> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final Metadata metadata = (Metadata)o;
                    final int recordsCount = this.recordsCount;
                    final int recordsCount2 = metadata.recordsCount;
                    b2 = false;
                    if (recordsCount == recordsCount2) {
                        if (this.links != null) {
                            final boolean equals = this.links.equals(metadata.links);
                            b2 = false;
                            if (!equals) {
                                return b2;
                            }
                        }
                        else if (metadata.links != null) {
                            return false;
                        }
                        if (this.filters != null) {
                            final boolean equals2 = this.filters.equals(metadata.filters);
                            b2 = false;
                            if (!equals2) {
                                return b2;
                            }
                        }
                        else if (metadata.filters != null) {
                            return false;
                        }
                        if (this.sorting != null) {
                            if (this.sorting.equals(metadata.sorting)) {
                                return b;
                            }
                        }
                        else if (metadata.sorting == null) {
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
    
    public Filters getFilters() {
        return this.filters;
    }
    
    public Links getLinks() {
        return this.links;
    }
    
    public int getRecordsCount() {
        return this.recordsCount;
    }
    
    public String getSorting() {
        return this.sorting;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.links != null) {
            hashCode = this.links.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n = hashCode * 31;
        int hashCode2;
        if (this.filters != null) {
            hashCode2 = this.filters.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int n2 = 31 * (n + hashCode2);
        final String sorting = this.sorting;
        int hashCode3 = 0;
        if (sorting != null) {
            hashCode3 = this.sorting.hashCode();
        }
        return 31 * (n2 + hashCode3) + this.recordsCount;
    }
    
    public void setFilters(final Filters filters) {
        this.filters = filters;
    }
    
    public void setLinks(final Links links) {
        this.links = links;
    }
    
    public void setRecordsCount(final int recordsCount) {
        this.recordsCount = recordsCount;
    }
    
    public void setSorting(final String sorting) {
        this.sorting = sorting;
    }
    
    @Override
    public String toString() {
        return "Metadata{links=" + this.links + ", filters=" + this.filters + ", sorting='" + this.sorting + '\'' + ", recordsCount=" + this.recordsCount + '}';
    }
}
