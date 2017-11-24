package bf.io.openshop.ux.fragments;

import bf.io.openshop.interfaces.*;
import com.android.volley.*;
import android.support.design.widget.*;
import android.support.v4.content.*;
import android.content.*;
import android.view.*;
import bf.io.openshop.ux.*;
import android.widget.*;

class ProductFragment$11$2 implements RequestListener {
    @Override
    public void requestFailed(final VolleyError volleyError) {
        ProductFragment$11.access$1502(View$OnClickListener.this, false);
        ProductFragment.access$700(View$OnClickListener.this.this$0).showProgress(false);
    }
    
    @Override
    public void requestSuccess(final long n) {
        ProductFragment$11.access$1502(View$OnClickListener.this, false);
        ProductFragment.access$700(View$OnClickListener.this.this$0).onProgressCompleted();
        ProductFragment.access$1402(View$OnClickListener.this.this$0, n);
        final Snackbar setAction = Snackbar.make((View)ProductFragment.access$1600(View$OnClickListener.this.this$0), View$OnClickListener.this.this$0.getString(2131230871), 0).setActionTextColor(ContextCompat.getColor((Context)View$OnClickListener.this.this$0.getActivity(), 2131558426)).setAction(2131230830, (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (View$OnClickListener.this.this$0.getActivity() instanceof MainActivity) {
                    ((MainActivity)View$OnClickListener.this.this$0.getActivity()).onWishlistSelected();
                }
            }
        });
        ((TextView)setAction.getView().findViewById(2131624091)).setTextColor(-1);
        setAction.show();
    }
}