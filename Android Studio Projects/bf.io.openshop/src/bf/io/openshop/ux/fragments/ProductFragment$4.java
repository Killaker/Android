package bf.io.openshop.ux.fragments;

import bf.io.openshop.interfaces.*;
import android.view.*;
import bf.io.openshop.ux.dialogs.*;
import java.util.*;
import timber.log.*;

class ProductFragment$4 implements ProductImagesRecyclerInterface {
    @Override
    public void onImageSelected(final View view, final int n) {
        final ProductImagesDialogFragment instance = ProductImagesDialogFragment.newInstance(ProductFragment.access$400(ProductFragment.this), n);
        if (instance != null) {
            instance.show(ProductFragment.this.getFragmentManager(), ProductImagesDialogFragment.class.getSimpleName());
            return;
        }
        Timber.e(ProductImagesDialogFragment.class.getSimpleName() + "Called with empty image list", new Object[0]);
    }
}