package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.entities.product.*;
import android.support.annotation.*;
import bf.io.openshop.ux.*;
import java.util.*;
import bf.io.openshop.*;

class ProductFragment$7 implements Listener<Product> {
    final /* synthetic */ long val$productId;
    
    public void onResponse(@NonNull final Product product) {
        MainActivity.setActionBarTitle(product.getName());
        if (product.getVariants() != null && product.getVariants().size() > 0) {
            ProductFragment.access$800(ProductFragment.this, this.val$productId);
        }
        ProductFragment.access$900(ProductFragment.this, product.getRelated());
        ProductFragment.access$1000(ProductFragment.this, product);
        ProductFragment.access$1100(ProductFragment.this, CONST.VISIBLE.CONTENT);
    }
}