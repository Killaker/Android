package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.entities.product.*;
import android.support.annotation.*;

class CategoryFragment$9 implements Listener<ProductListResponse> {
    public void onResponse(@NonNull final ProductListResponse productListResponse) {
        CategoryFragment.this.firstTimeSort = false;
        CategoryFragment.access$800(CategoryFragment.this).addProducts(productListResponse.getProducts());
        CategoryFragment.access$502(CategoryFragment.this, productListResponse.getMetadata());
        if (CategoryFragment.access$100(CategoryFragment.this) == null) {
            CategoryFragment.access$102(CategoryFragment.this, CategoryFragment.access$500(CategoryFragment.this).getFilters());
        }
        CategoryFragment.access$1200(CategoryFragment.this);
        CategoryFragment.access$1300(CategoryFragment.this).setVisibility(8);
    }
}