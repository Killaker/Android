package bf.io.openshop.ux.fragments;

import bf.io.openshop.interfaces.*;
import android.view.*;
import timber.log.*;
import bf.io.openshop.entities.drawerMenu.*;

class DrawerFragment$3 implements DrawerRecyclerInterface {
    @Override
    public void onCategorySelected(final View view, final DrawerItemCategory drawerItemCategory) {
        if (drawerItemCategory.getChildren() != null && !drawerItemCategory.getChildren().isEmpty()) {
            DrawerFragment.access$400(DrawerFragment.this, drawerItemCategory);
            return;
        }
        if (DrawerFragment.access$300(DrawerFragment.this) != null) {
            if (drawerItemCategory.getId() == -123L) {
                DrawerFragment.access$300(DrawerFragment.this).onDrawerBannersSelected();
            }
            else {
                DrawerFragment.access$300(DrawerFragment.this).onDrawerItemCategorySelected(drawerItemCategory);
            }
            DrawerFragment.this.closeDrawerMenu();
            return;
        }
        Timber.e(new RuntimeException(), "Null drawer listener. WTF.", new Object[0]);
    }
    
    @Override
    public void onHeaderSelected() {
        if (DrawerFragment.access$300(DrawerFragment.this) != null) {
            DrawerFragment.access$300(DrawerFragment.this).onAccountSelected();
            DrawerFragment.this.closeDrawerMenu();
            return;
        }
        Timber.e(new RuntimeException(), "Null drawer listener. WTF.", new Object[0]);
    }
    
    @Override
    public void onPageSelected(final View view, final DrawerItemPage drawerItemPage) {
        if (DrawerFragment.access$300(DrawerFragment.this) != null) {
            DrawerFragment.access$300(DrawerFragment.this).onDrawerItemPageSelected(drawerItemPage);
            DrawerFragment.this.closeDrawerMenu();
            return;
        }
        Timber.e(new RuntimeException(), "Null drawer listener. WTF.", new Object[0]);
    }
}