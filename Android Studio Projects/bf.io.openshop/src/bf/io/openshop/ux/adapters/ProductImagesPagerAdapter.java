package bf.io.openshop.ux.adapters;

import android.support.v4.view.*;
import android.content.*;
import java.util.*;
import android.view.*;
import com.squareup.picasso.*;
import bf.io.openshop.views.*;
import android.widget.*;

public class ProductImagesPagerAdapter extends PagerAdapter
{
    private Context context;
    private ArrayList<String> images;
    
    public ProductImagesPagerAdapter(final Context context, final ArrayList<String> images) {
        this.context = context;
        this.images = images;
    }
    
    @Override
    public void destroyItem(final ViewGroup viewGroup, final int n, final Object o) {
        viewGroup.removeView((View)o);
    }
    
    @Override
    public int getCount() {
        return this.images.size();
    }
    
    @Override
    public Object instantiateItem(final ViewGroup viewGroup, final int n) {
        final View inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968642, viewGroup, false);
        Picasso.with(this.context).load(this.images.get(n)).fit().centerInside().placeholder(2130837697).error(2130837696).into((ImageView)inflate.findViewById(2131624301));
        viewGroup.addView(inflate);
        return inflate;
    }
    
    @Override
    public boolean isViewFromObject(final View view, final Object o) {
        return view == o;
    }
}
