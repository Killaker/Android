package bf.io.openshop.ux.adapters;

import bf.io.openshop.entities.filtr.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import timber.log.*;
import java.util.*;

public class FilterSelectSpinnerAdapter extends ArrayAdapter<FilterValueSelect>
{
    private static final int layoutID = 2130968684;
    private final LayoutInflater layoutInflater;
    public List<FilterValueSelect> selectValuesList;
    
    public FilterSelectSpinnerAdapter(final Context context) {
        super(context, 2130968684);
        this.selectValuesList = new ArrayList<FilterValueSelect>();
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
        if (this.selectValuesList.get(n) != null) {
            tag.sizeText.setText((CharSequence)this.selectValuesList.get(n).getValue());
            return inflate;
        }
        Timber.e("Received null productSize in " + this.getClass().getSimpleName(), new Object[0]);
        return inflate;
    }
    
    public int getCount() {
        return this.selectValuesList.size();
    }
    
    public View getDropDownView(final int n, final View view, final ViewGroup viewGroup) {
        return this.getCustomView(n, view, viewGroup);
    }
    
    public FilterValueSelect getItem(final int n) {
        return this.selectValuesList.get(n);
    }
    
    public long getItemId(final int n) {
        return this.selectValuesList.get(n).getId();
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        return this.getCustomView(n, view, viewGroup);
    }
    
    public void setSelectValuesList(final List<FilterValueSelect> list) {
        if (list != null) {
            this.selectValuesList.clear();
            this.selectValuesList.addAll(list);
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
