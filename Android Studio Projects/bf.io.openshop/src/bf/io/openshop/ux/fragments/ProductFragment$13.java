package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import org.json.*;
import bf.io.openshop.utils.*;
import bf.io.openshop.ux.*;
import android.support.design.widget.*;
import android.support.v4.content.*;
import android.content.*;
import android.view.*;
import android.widget.*;

class ProductFragment$13 implements Listener<JSONObject> {
    public void onResponse(final JSONObject jsonObject) {
        if (ProductFragment.access$1800(ProductFragment.this) != null) {
            ProductFragment.access$1800(ProductFragment.this).setVisibility(0);
        }
        if (ProductFragment.access$1900(ProductFragment.this) != null) {
            ProductFragment.access$1900(ProductFragment.this).setVisibility(4);
        }
        Analytics.logAddProductToCart(ProductFragment.access$300(ProductFragment.this).getRemoteId(), ProductFragment.access$300(ProductFragment.this).getName(), ProductFragment.access$300(ProductFragment.this).getDiscountPrice());
        MainActivity.updateCartCountNotification();
        final Snackbar setAction = Snackbar.make((View)ProductFragment.access$1600(ProductFragment.this), ProductFragment.this.getString(2131230870) + " " + ProductFragment.this.getString(2131230924), 0).setActionTextColor(ContextCompat.getColor((Context)ProductFragment.this.getActivity(), 2131558426)).setAction(2131230829, (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (ProductFragment.this.getActivity() instanceof MainActivity) {
                    ((MainActivity)ProductFragment.this.getActivity()).onCartSelected();
                }
            }
        });
        ((TextView)setAction.getView().findViewById(2131624091)).setTextColor(-1);
        setAction.show();
    }
}