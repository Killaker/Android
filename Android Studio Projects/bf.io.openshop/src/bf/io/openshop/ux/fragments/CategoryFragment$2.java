package bf.io.openshop.ux.fragments;

import bf.io.openshop.listeners.*;
import android.view.*;
import bf.io.openshop.utils.*;
import android.app.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.ux.dialogs.*;

class CategoryFragment$2 extends OnSingleClickListener {
    @Override
    public void onSingleClick(final View view) {
        if (CategoryFragment.access$100(CategoryFragment.this) == null) {
            MsgUtils.showToast(CategoryFragment.this.getActivity(), 1, CategoryFragment.this.getString(2131230826), MsgUtils.ToastLength.SHORT);
            return;
        }
        final FilterDialogFragment instance = FilterDialogFragment.newInstance(CategoryFragment.access$100(CategoryFragment.this), new FilterDialogInterface() {
            @Override
            public void onFilterCancelled() {
                CategoryFragment.access$202(CategoryFragment.this, null);
                CategoryFragment.access$300(CategoryFragment.this).setImageResource(2130837672);
                CategoryFragment.access$400(CategoryFragment.this, null);
            }
            
            @Override
            public void onFilterSelected(final String s) {
                CategoryFragment.access$202(CategoryFragment.this, s);
                CategoryFragment.access$300(CategoryFragment.this).setImageResource(2130837671);
                CategoryFragment.access$400(CategoryFragment.this, null);
            }
        });
        if (instance != null) {
            instance.show(CategoryFragment.this.getFragmentManager(), "filterDialogFragment");
            return;
        }
        MsgUtils.showToast(CategoryFragment.this.getActivity(), 2, null, MsgUtils.ToastLength.SHORT);
    }
}