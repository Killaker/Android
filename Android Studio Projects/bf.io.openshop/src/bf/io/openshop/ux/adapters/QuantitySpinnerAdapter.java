package bf.io.openshop.ux.adapters;

import bf.io.openshop.entities.product.*;
import android.content.*;
import java.util.*;
import android.view.*;
import android.widget.*;
import timber.log.*;

public class QuantitySpinnerAdapter extends ArrayAdapter<ProductQuantity>
{
    private static final int layoutID = 2130968685;
    public Context context;
    private final LayoutInflater layoutInflater;
    public List<ProductQuantity> quantities;
    
    public QuantitySpinnerAdapter(final Context context, final List<ProductQuantity> quantities) {
        super(context, 2130968685, (List)quantities);
        this.context = context;
        this.quantities = quantities;
        this.layoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }
    
    private View getCustomView(final int n, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        ListItemHolder tag;
        if (inflate == null) {
            inflate = this.layoutInflater.inflate(2130968685, viewGroup, false);
            tag = new ListItemHolder();
            tag.text = (TextView)inflate.findViewById(2131624373);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ListItemHolder)inflate.getTag();
        }
        if (this.getItem(n) != null) {
            tag.text.setText((CharSequence)this.getItem(n).getValue());
            return inflate;
        }
        Timber.e("Received null value in " + this.getClass().getSimpleName(), new Object[0]);
        return inflate;
    }
    
    public int getCount() {
        return this.quantities.size();
    }
    
    public View getDropDownView(final int n, final View view, final ViewGroup viewGroup) {
        return this.getCustomView(n, view, viewGroup);
    }
    
    public ProductQuantity getItem(final int n) {
        return this.quantities.get(n);
    }
    
    public long getItemId(final int n) {
        return this.quantities.get(n).getQuantity();
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        return this.getCustomView(n, view, viewGroup);
    }
    
    static class ListItemHolder
    {
        TextView text;
    }
}
