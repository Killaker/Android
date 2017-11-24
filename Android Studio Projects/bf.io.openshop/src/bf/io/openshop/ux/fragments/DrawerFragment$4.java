package bf.io.openshop.ux.fragments;

import bf.io.openshop.interfaces.*;
import android.view.*;
import bf.io.openshop.entities.drawerMenu.*;

class DrawerFragment$4 implements DrawerSubmenuRecyclerInterface {
    @Override
    public void onSubCategorySelected(final View view, final DrawerItemCategory drawerItemCategory) {
        if (DrawerFragment.access$300(DrawerFragment.this) != null) {
            DrawerFragment.access$300(DrawerFragment.this).onDrawerItemCategorySelected(drawerItemCategory);
            DrawerFragment.this.closeDrawerMenu();
        }
    }
}