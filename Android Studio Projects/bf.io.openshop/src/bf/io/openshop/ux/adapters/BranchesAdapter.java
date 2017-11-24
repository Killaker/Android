package bf.io.openshop.ux.adapters;

import bf.io.openshop.entities.delivery.*;
import android.content.*;
import java.util.*;
import android.view.*;
import android.widget.*;

public class BranchesAdapter extends BaseAdapter
{
    private List<Branch> branches;
    public Context context;
    private LayoutInflater layoutInflater;
    
    public BranchesAdapter(final Context context, final List<Branch> branches) {
        this.branches = new ArrayList<Branch>();
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        this.branches = branches;
    }
    
    public int getCount() {
        return this.branches.size();
    }
    
    public Branch getItem(final int n) {
        return this.branches.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        ViewHolder tag;
        if (inflate == null) {
            tag = new ViewHolder();
            inflate = this.layoutInflater.inflate(2130968659, viewGroup, false);
            tag.title = (TextView)inflate.findViewById(2131624351);
            tag.description = (TextView)inflate.findViewById(2131624352);
            tag.price = (TextView)inflate.findViewById(2131624353);
            tag.shopInfo = (ImageView)inflate.findViewById(2131624354);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ViewHolder)inflate.getTag();
        }
        final Branch branch = this.branches.get(n);
        tag.price.setVisibility(8);
        tag.title.setText((CharSequence)branch.getName());
        tag.description.setText((CharSequence)branch.getAddress());
        tag.shopInfo.setVisibility(0);
        return inflate;
    }
    
    public int getViewTypeCount() {
        return 1;
    }
    
    public static class ViewHolder
    {
        public TextView description;
        public TextView price;
        public ImageView shopInfo;
        public TextView title;
    }
}
