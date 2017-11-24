package bf.io.openshop.entities.drawerMenu;

import java.util.*;

public class DrawerResponse
{
    private List<DrawerItemCategory> navigation;
    private List<DrawerItemPage> pages;
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof DrawerResponse)) {
                return false;
            }
            final DrawerResponse drawerResponse = (DrawerResponse)o;
            Label_0053: {
                if (this.getNavigation() != null) {
                    if (this.getNavigation().equals(drawerResponse.getNavigation())) {
                        break Label_0053;
                    }
                }
                else if (drawerResponse.getNavigation() == null) {
                    break Label_0053;
                }
                return false;
            }
            if (this.getPages() != null) {
                if (this.getPages().equals(drawerResponse.getPages())) {
                    return true;
                }
            }
            else if (drawerResponse.getPages() == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    public List<DrawerItemCategory> getNavigation() {
        return this.navigation;
    }
    
    public List<DrawerItemPage> getPages() {
        return this.pages;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.getNavigation() != null) {
            hashCode = this.getNavigation().hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n = hashCode * 31;
        final List<DrawerItemPage> pages = this.getPages();
        int hashCode2 = 0;
        if (pages != null) {
            hashCode2 = this.getPages().hashCode();
        }
        return n + hashCode2;
    }
    
    public void setNavigation(final List<DrawerItemCategory> navigation) {
        this.navigation = navigation;
    }
    
    public void setPages(final List<DrawerItemPage> pages) {
        this.pages = pages;
    }
    
    @Override
    public String toString() {
        return "DrawerResponse{navigation=" + this.navigation + ", pages=" + this.pages + '}';
    }
}
