package bf.io.openshop.ux.fragments;

import bf.io.openshop.interfaces.*;

class CategoryFragment$2$1 implements FilterDialogInterface {
    @Override
    public void onFilterCancelled() {
        CategoryFragment.access$202(OnSingleClickListener.this.this$0, null);
        CategoryFragment.access$300(OnSingleClickListener.this.this$0).setImageResource(2130837672);
        CategoryFragment.access$400(OnSingleClickListener.this.this$0, null);
    }
    
    @Override
    public void onFilterSelected(final String s) {
        CategoryFragment.access$202(OnSingleClickListener.this.this$0, s);
        CategoryFragment.access$300(OnSingleClickListener.this.this$0).setImageResource(2130837671);
        CategoryFragment.access$400(OnSingleClickListener.this.this$0, null);
    }
}