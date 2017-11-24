package bf.io.openshop.entities.drawerMenu;

public class DrawerItemPage
{
    private long id;
    private String name;
    
    public DrawerItemPage() {
    }
    
    public DrawerItemPage(final long id, final String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof DrawerItemPage)) {
                return false;
            }
            final DrawerItemPage drawerItemPage = (DrawerItemPage)o;
            if (this.getId() != drawerItemPage.getId()) {
                return false;
            }
            if (this.getName() != null) {
                if (this.getName().equals(drawerItemPage.getName())) {
                    return true;
                }
            }
            else if (drawerItemPage.getName() == null) {
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
        final int n = 31 * (int)(this.getId() ^ this.getId() >>> 32);
        int hashCode;
        if (this.getName() != null) {
            hashCode = this.getName().hashCode();
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
        return "DrawerItemPage{id=" + this.id + ", name='" + this.name + '\'' + '}';
    }
}
