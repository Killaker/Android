package bf.io.openshop.ux.adapters;

import bf.io.openshop.entities.product.*;
import android.content.*;
import android.view.*;
import bf.io.openshop.views.*;
import com.squareup.picasso.*;
import android.widget.*;
import timber.log.*;
import android.graphics.drawable.*;
import android.graphics.*;
import java.util.*;

public class ColorSpinnerAdapter extends ArrayAdapter<ProductColor>
{
    private static final int layoutID = 2130968683;
    private final LayoutInflater layoutInflater;
    public List<ProductColor> productColorList;
    
    public ColorSpinnerAdapter(final Context context) {
        super(context, 2130968683);
        this.productColorList = new ArrayList<ProductColor>();
        this.layoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }
    
    private View getCustomView(final int n, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        ListItemHolder tag;
        if (inflate == null) {
            inflate = this.layoutInflater.inflate(2130968683, viewGroup, false);
            tag = new ListItemHolder();
            tag.colorImage = (RoundedImageView)inflate.findViewById(2131624391);
            tag.colorText = (TextView)inflate.findViewById(2131624392);
            tag.colorStroke = (LinearLayout)inflate.findViewById(2131624390);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ListItemHolder)inflate.getTag();
        }
        final ProductColor productColor = this.productColorList.get(n);
        Picasso.with(this.getContext()).cancelRequest(tag.colorImage);
        if (productColor == null) {
            Timber.e("Received null productColor in " + this.getClass().getSimpleName(), new Object[0]);
            return inflate;
        }
        tag.colorText.setText((CharSequence)productColor.getValue());
        if (productColor.getId() == -5L) {
            tag.colorStroke.setVisibility(4);
            return inflate;
        }
        if (productColor.getCode() != null && !productColor.getCode().isEmpty()) {
            final String code = productColor.getCode();
            final GradientDrawable gradientDrawable = (GradientDrawable)tag.colorImage.getBackground();
            int color = -1;
            while (true) {
                try {
                    color = Color.parseColor(code);
                    gradientDrawable.setColor(color);
                    return inflate;
                }
                catch (Exception ex) {
                    Timber.e(ex, "CustomSpinnerColors parse color exception", new Object[0]);
                    continue;
                }
                break;
            }
        }
        Picasso.with(this.getContext()).load(productColor.getImg()).fit().into(tag.colorImage);
        ((GradientDrawable)tag.colorImage.getBackground()).setColor(0);
        return inflate;
    }
    
    public int getCount() {
        return this.productColorList.size();
    }
    
    public View getDropDownView(final int n, final View view, final ViewGroup viewGroup) {
        return this.getCustomView(n, view, viewGroup);
    }
    
    public ProductColor getItem(final int n) {
        return this.productColorList.get(n);
    }
    
    public long getItemId(final int n) {
        return this.productColorList.get(n).getId();
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        return this.getCustomView(n, view, viewGroup);
    }
    
    public void setProductColorList(final List<ProductColor> list) {
        if (list != null) {
            this.productColorList.addAll(list);
            this.notifyDataSetChanged();
            return;
        }
        Timber.e("Trying set null color list in " + this.getClass().getSimpleName(), new Object[0]);
    }
    
    static class ListItemHolder
    {
        RoundedImageView colorImage;
        LinearLayout colorStroke;
        TextView colorText;
    }
}
