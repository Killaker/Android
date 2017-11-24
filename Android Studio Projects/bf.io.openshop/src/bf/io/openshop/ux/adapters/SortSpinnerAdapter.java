package bf.io.openshop.ux.adapters;

import android.widget.*;
import bf.io.openshop.entities.*;
import android.app.*;
import android.content.*;
import java.util.*;

public class SortSpinnerAdapter extends ArrayAdapter<SortItem>
{
    public List<SortItem> sortItemList;
    
    public SortSpinnerAdapter(final Activity activity) {
        super((Context)activity, 2130968686);
        this.sortItemList = new ArrayList<SortItem>();
        this.setDropDownViewResource(2130968687);
        this.sortItemList.add(new SortItem("newest", activity.getString(2131230917)));
        this.sortItemList.add(new SortItem("popularity", activity.getString(2131230878)));
        this.sortItemList.add(new SortItem("price_DESC", activity.getString(2131230831)));
        this.sortItemList.add(new SortItem("price_ASC", activity.getString(2131230844)));
    }
    
    public int getCount() {
        return this.sortItemList.size();
    }
    
    public SortItem getItem(final int n) {
        return this.sortItemList.get(n);
    }
}
