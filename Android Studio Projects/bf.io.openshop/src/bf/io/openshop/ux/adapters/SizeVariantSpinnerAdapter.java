package bf.io.openshop.ux.adapters;

import bf.io.openshop.entities.product.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import timber.log.*;
import java.util.*;

public class SizeVariantSpinnerAdapter extends ArrayAdapter<ProductVariant>
{
    private static final int layoutID = 2130968684;
    private final LayoutInflater layoutInflater;
    public List<ProductVariant> productSizeList;
    
    public SizeVariantSpinnerAdapter(final Context context) {
        super(context, 2130968684);
        this.productSizeList = new ArrayList<ProductVariant>();
        this.layoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }
    
    public SizeVariantSpinnerAdapter(final Context context, final List<ProductVariant> productSizeList) {
        super(context, 2130968684, (List)productSizeList);
        this.productSizeList = productSizeList;
        this.layoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }
    
    private View getCustomView(final int n, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        ListItemHolder tag;
        if (inflate == null) {
            inflate = this.layoutInflater.inflate(2130968684, viewGroup, false);
            tag = new ListItemHolder();
            tag.sizeText = (TextView)inflate.findViewById(2131624393);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ListItemHolder)inflate.getTag();
        }
        if (this.productSizeList.get(n) != null && this.productSizeList.get(n).getSize() != null) {
            tag.sizeText.setText((CharSequence)this.productSizeList.get(n).getSize().getValue());
            return inflate;
        }
        Timber.e("Received null productSize in " + this.getClass().getSimpleName(), new Object[0]);
        return inflate;
    }
    
    public int getCount() {
        return this.productSizeList.size();
    }
    
    public View getDropDownView(final int n, final View view, final ViewGroup viewGroup) {
        return this.getCustomView(n, view, viewGroup);
    }
    
    public ProductVariant getItem(final int n) {
        return this.productSizeList.get(n);
    }
    
    public long getItemId(final int n) {
        return this.productSizeList.get(n).getId();
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        return this.getCustomView(n, view, viewGroup);
    }
    
    public void setProductSizeList(final List<ProductVariant> list) {
        if (list != null) {
            this.productSizeList.clear();
            this.productSizeList.addAll(list);
            this.notifyDataSetChanged();
            return;
        }
        Timber.e("Trying set null size list in " + this.getClass().getSimpleName(), new Object[0]);
    }
    
    static class ListItemHolder
    {
        TextView sizeText;
    }
}
