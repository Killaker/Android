package bf.io.openshop.interfaces;

import android.view.*;
import bf.io.openshop.entities.drawerMenu.*;

public interface DrawerRecyclerInterface
{
    void onCategorySelected(final View p0, final DrawerItemCategory p1);
    
    void onHeaderSelected();
    
    void onPageSelected(final View p0, final DrawerItemPage p1);
}
