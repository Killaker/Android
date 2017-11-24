package bf.io.openshop.ux.adapters;

import bf.io.openshop.entities.product.*;
import java.util.*;
import android.content.*;
import android.view.*;
import android.widget.*;

public class CartColorTextSpinnerAdapter extends ArrayAdapter<ProductColor>
{
    private static final int layoutID = 2130968685;
    public List<ProductColor> colors;
    public Context context;
    private final LayoutInflater layoutInflater;
    
    public CartColorTextSpinnerAdapter(final Context context, final List<ProductColor> colors) {
        super(context, 2130968685, (List)colors);
        this.context = context;
        this.colors = colors;
        this.layoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }
    
    private View getCustomView(final int n, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        ListItemHolder tag;
        if (inflate == null) {
            inflate = this.layoutInflater.inflate(2130968685, viewGroup, false);
            tag = new ListItemHolder();
            tag.colorText = (TextView)inflate.findViewById(2131624373);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ListItemHolder)inflate.getTag();
        }
        tag.colorText.setText((CharSequence)this.getItem(n).getValue());
        return inflate;
    }
    
    public int getCount() {
        return this.colors.size();
    }
    
    public View getDropDownView(final int n, final View view, final ViewGroup viewGroup) {
        return this.getCustomView(n, view, viewGroup);
    }
    
    public ProductColor getItem(final int n) {
        return this.colors.get(n);
    }
    
    public long getItemId(final int n) {
        return this.colors.get(n).getId();
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        return this.getCustomView(n, view, viewGroup);
    }
    
    static class ListItemHolder
    {
        TextView colorText;
    }
}
