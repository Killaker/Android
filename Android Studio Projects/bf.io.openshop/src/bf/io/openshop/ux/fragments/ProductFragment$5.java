package bf.io.openshop.ux.fragments;

import bf.io.openshop.interfaces.*;
import android.view.*;
import bf.io.openshop.entities.product.*;
import bf.io.openshop.ux.*;

class ProductFragment$5 implements RelatedProductsRecyclerInterface {
    @Override
    public void onRelatedProductSelected(final View view, final int n, final Product product) {
        if (product != null && ProductFragment.this.getActivity() instanceof MainActivity) {
            ((MainActivity)ProductFragment.this.getActivity()).onProductSelected(product.getId());
        }
    }
}