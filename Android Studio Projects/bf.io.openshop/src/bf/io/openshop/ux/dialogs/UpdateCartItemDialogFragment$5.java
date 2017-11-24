package bf.io.openshop.ux.dialogs;

import android.widget.*;
import android.view.*;
import bf.io.openshop.entities.product.*;
import timber.log.*;

class UpdateCartItemDialogFragment$5 implements AdapterView$OnItemSelectedListener {
    final /* synthetic */ Product val$product;
    
    public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        final ProductColor productColor = (ProductColor)adapterView.getItemAtPosition(n);
        if (productColor != null) {
            Timber.d("ColorPicker selected color: " + productColor.toString(), new Object[0]);
            UpdateCartItemDialogFragment.access$600(UpdateCartItemDialogFragment.this, this.val$product, productColor);
            return;
        }
        Timber.e("Retrieved null color from spinner.", new Object[0]);
    }
    
    public void onNothingSelected(final AdapterView<?> adapterView) {
        Timber.d("Nothing selected in product colors spinner.", new Object[0]);
    }
}