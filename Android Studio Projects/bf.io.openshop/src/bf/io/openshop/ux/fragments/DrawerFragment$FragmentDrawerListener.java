package bf.io.openshop.ux.fragments;

import bf.io.openshop.entities.drawerMenu.*;
import java.util.*;

public interface FragmentDrawerListener
{
    void onAccountSelected();
    
    void onDrawerBannersSelected();
    
    void onDrawerItemCategorySelected(final DrawerItemCategory p0);
    
    void onDrawerItemPageSelected(final DrawerItemPage p0);
    
    void prepareSearchSuggestions(final List<DrawerItemCategory> p0);
}
