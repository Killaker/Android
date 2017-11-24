package bf.io.openshop.ux.fragments;

import bf.io.openshop.interfaces.*;
import android.view.*;
import bf.io.openshop.entities.product.*;
import android.os.*;
import android.transition.*;
import android.content.*;
import bf.io.openshop.ux.*;

class CategoryFragment$6 implements CategoryRecyclerInterface {
    @Override
    public void onProductSelected(final View view, final Product product) {
        if (Build$VERSION.SDK_INT > 21) {
            CategoryFragment.this.setReenterTransition(TransitionInflater.from((Context)CategoryFragment.this.getActivity()).inflateTransition(17760258));
        }
        ((MainActivity)CategoryFragment.this.getActivity()).onProductSelected(product.getId());
    }
}