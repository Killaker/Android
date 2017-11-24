package bf.io.openshop.entities;

public class Region
{
    private long id;
    private String name;
    
    public Region() {
    }
    
    public Region(final long id, final String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final Region region = (Region)o;
            if (this.id != region.id) {
                return false;
            }
            if (this.name != null) {
                if (this.name.equals(region.name)) {
                    return true;
                }
            }
            else if (region.name == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
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
        return n + hashCode;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Region{id=" + this.id + ", name='" + this.name + '\'' + '}';
    }
}
