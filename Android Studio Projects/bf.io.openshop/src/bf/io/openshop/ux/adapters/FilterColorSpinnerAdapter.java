package bf.io.openshop.ux.adapters;

import bf.io.openshop.entities.filtr.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import timber.log.*;
import java.util.*;

public class FilterColorSpinnerAdapter extends ArrayAdapter<FilterValueColor>
{
    private static final int layoutID = 2130968684;
    public List<FilterValueColor> colorValuesList;
    private final LayoutInflater layoutInflater;
    
    public FilterColorSpinnerAdapter(final Context context) {
        super(context, 2130968684);
        this.colorValuesList = new ArrayList<FilterValueColor>();
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
        if (this.colorValuesList.get(n) != null) {
            tag.sizeText.setText((CharSequence)this.colorValuesList.get(n).getValue());
            return inflate;
        }
        Timber.e("Received null productSize in " + this.getClass().getSimpleName(), new Object[0]);
        return inflate;
    }
    
    public int getCount() {
        return this.colorValuesList.size();
    }
    
    public View getDropDownView(final int n, final View view, final ViewGroup viewGroup) {
        return this.getCustomView(n, view, viewGroup);
    }
    
    public FilterValueColor getItem(final int n) {
        return this.colorValuesList.get(n);
    }
    
    public long getItemId(final int n) {
        return this.colorValuesList.get(n).getId();
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        return this.getCustomView(n, view, viewGroup);
    }
    
    public void setColorValuesList(final List<FilterValueColor> list) {
        if (list != null) {
            this.colorValuesList.clear();
            this.colorValuesList.addAll(list);
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
