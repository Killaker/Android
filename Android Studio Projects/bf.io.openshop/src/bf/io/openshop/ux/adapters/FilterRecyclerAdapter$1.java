package bf.io.openshop.ux.adapters;

import bf.io.openshop.views.*;
import bf.io.openshop.entities.filtr.*;

class FilterRecyclerAdapter$1 implements OnRangeSeekBarChangeListener<Integer> {
    final /* synthetic */ FilterTypeRange val$filterTypeRange;
    final /* synthetic */ ViewHolderRange val$viewHolderRange;
    
    public void onRangeSeekBarValuesChanged(final RangeSeekBar<?> rangeSeekBar, final Integer n, final Integer n2) {
        this.val$viewHolderRange.rangeResult.setText((CharSequence)FilterRecyclerAdapter.access$000(FilterRecyclerAdapter.this).getString(2131230939, new Object[] { n, this.val$filterTypeRange.getRangeTitle(), n2, this.val$filterTypeRange.getRangeTitle() }));
        this.val$filterTypeRange.setSelectedMin(n);
        this.val$filterTypeRange.setSelectedMax(n2);
    }
}