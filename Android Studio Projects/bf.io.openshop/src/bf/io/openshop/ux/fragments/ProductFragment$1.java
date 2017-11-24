package bf.io.openshop.ux.fragments;

import android.widget.*;
import android.view.*;
import bf.io.openshop.entities.product.*;
import timber.log.*;
import bf.io.openshop.utils.*;
import android.app.*;

class ProductFragment$1 implements AdapterView$OnItemSelectedListener {
    public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        final ProductVariant item = ProductFragment.access$000(ProductFragment.this).getItem(n);
        if (item == null || item.getSize() == null) {
            ProductFragment.access$102(ProductFragment.this, null);
            Timber.e(new RuntimeException(), "User click on null product variant. WTF", new Object[0]);
            MsgUtils.showToast(ProductFragment.this.getActivity(), 2, "", MsgUtils.ToastLength.SHORT);
            return;
        }
        Timber.d("selectedVariant: " + item.getId() + ", selectedSize" + item.getSize().getValue(), new Object[0]);
        if (item.getId() == -131L && item.getSize().getId() != -131L) {
            ProductFragment.access$102(ProductFragment.this, null);
            return;
        }
        ProductFragment.access$102(ProductFragment.this, item);
    }
    
    public void onNothingSelected(final AdapterView<?> adapterView) {
        ProductFragment.access$102(ProductFragment.this, null);
    }
}