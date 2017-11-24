package bf.io.openshop.ux.adapters;

import bf.io.openshop.entities.*;
import java.util.*;
import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import android.support.v4.content.*;
import com.squareup.picasso.*;

public class ShopSpinnerAdapter extends ArrayAdapter<Shop>
{
    private static final int layoutID = 2130968661;
    private LayoutInflater layoutInflater;
    private List<Shop> shops;
    
    public ShopSpinnerAdapter(final Activity activity, final List<Shop> shops) {
        super((Context)activity, 2130968661, (List)shops);
        this.layoutInflater = (LayoutInflater)activity.getSystemService("layout_inflater");
        this.shops = shops;
    }
    
    private View getCustomView(final int n, final View view, final ViewGroup viewGroup, final boolean b) {
        View inflate = view;
        ListItemHolder tag;
        if (inflate == null) {
            inflate = this.layoutInflater.inflate(2130968661, viewGroup, false);
            tag = new ListItemHolder();
            tag.shopLanguageName = (TextView)inflate.findViewById(2131624356);
            tag.shopFlagIcon = (ImageView)inflate.findViewById(2131624355);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ListItemHolder)inflate.getTag();
        }
        final Shop shop = this.shops.get(n);
        if (b) {
            tag.shopLanguageName.setTextColor(ContextCompat.getColor(this.getContext(), 2131558531));
        }
        else {
            tag.shopLanguageName.setTextColor(ContextCompat.getColor(this.getContext(), 2131558530));
        }
        Picasso.with(this.getContext()).cancelRequest(tag.shopFlagIcon);
        if (shop != null) {
            tag.shopLanguageName.setText((CharSequence)shop.getName());
            Picasso.with(this.getContext()).load(shop.getFlagIcon()).into(tag.shopFlagIcon);
        }
        return inflate;
    }
    
    public int getCount() {
        return this.shops.size();
    }
    
    public View getDropDownView(final int n, final View view, final ViewGroup viewGroup) {
        return this.getCustomView(n, view, viewGroup, true);
    }
    
    public Shop getItem(final int n) {
        return this.shops.get(n);
    }
    
    public long getItemId(final int n) {
        return this.shops.get(n).getId();
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        return this.getCustomView(n, view, viewGroup, false);
    }
    
    static class ListItemHolder
    {
        ImageView shopFlagIcon;
        TextView shopLanguageName;
    }
}
