package bf.io.openshop.ux.adapters;

import bf.io.openshop.entities.product.*;
import android.content.*;
import java.util.*;
import android.view.*;
import android.widget.*;
import timber.log.*;

public class CartSizeSpinnerAdapter extends ArrayAdapter<ProductVariant>
{
    private static final int layoutID = 2130968685;
    public Context context;
    private final LayoutInflater layoutInflater;
    public List<ProductVariant> sizes;
    
    public CartSizeSpinnerAdapter(final Context context, final List<ProductVariant> sizes) {
        super(context, 2130968685, (List)sizes);
        this.context = context;
        this.sizes = sizes;
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
        if (this.getItem(n) != null && this.getItem(n).getSize() != null) {
            tag.text.setText((CharSequence)this.getItem(n).getSize().getValue());
            return inflate;
        }
        Timber.e("Received null productSize in " + this.getClass().getSimpleName(), new Object[0]);
        return inflate;
    }
    
    public int getCount() {
        return this.sizes.size();
    }
    
    public View getDropDownView(final int n, final View view, final ViewGroup viewGroup) {
        return this.getCustomView(n, view, viewGroup);
    }
    
    public ProductVariant getItem(final int n) {
        return this.sizes.get(n);
    }
    
    public long getItemId(final int n) {
        return this.sizes.get(n).getId();
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        return this.getCustomView(n, view, viewGroup);
    }
    
    static class ListItemHolder
    {
        TextView text;
    }
}
