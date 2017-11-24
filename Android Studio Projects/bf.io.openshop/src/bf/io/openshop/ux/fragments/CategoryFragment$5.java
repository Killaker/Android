package bf.io.openshop.ux.fragments;

import bf.io.openshop.listeners.*;
import android.view.*;

class CategoryFragment$5 extends OnSingleClickListener {
    @Override
    public void onSingleClick(final View view) {
        if (CategoryFragment.access$600(CategoryFragment.this)) {
            CategoryFragment.access$602(CategoryFragment.this, false);
            CategoryFragment.access$700(CategoryFragment.this).setImageResource(2130837674);
            CategoryFragment.access$800(CategoryFragment.this).defineImagesQuality(false);
            CategoryFragment.access$900(CategoryFragment.this, 2);
            return;
        }
        CategoryFragment.access$602(CategoryFragment.this, true);
        CategoryFragment.access$700(CategoryFragment.this).setImageResource(2130837673);
        CategoryFragment.access$800(CategoryFragment.this).defineImagesQuality(true);
        CategoryFragment.access$900(CategoryFragment.this, 1);
    }
}