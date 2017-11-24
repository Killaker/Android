package bf.io.openshop.ux.dialogs;

import android.view.*;
import bf.io.openshop.entities.filtr.*;
import java.util.*;

class FilterDialogFragment$2 implements View$OnClickListener {
    public void onClick(final View view) {
        if (FilterDialogFragment.access$100(FilterDialogFragment.this) != null) {
            for (final FilterType filterType : FilterDialogFragment.access$100(FilterDialogFragment.this).getFilters()) {
                if ("range".equals(filterType.getType())) {
                    ((FilterTypeRange)filterType).setSelectedMin(-1);
                    ((FilterTypeRange)filterType).setSelectedMax(-1);
                }
                else if ("color".equals(filterType.getType())) {
                    ((FilterTypeColor)filterType).setSelectedValue(null);
                }
                else {
                    if (!"select".equals(filterType.getType())) {
                        continue;
                    }
                    ((FilterTypeSelect)filterType).setSelectedValue(null);
                }
            }
        }
        FilterDialogFragment.access$000(FilterDialogFragment.this).onFilterCancelled();
        FilterDialogFragment.this.dismiss();
    }
}