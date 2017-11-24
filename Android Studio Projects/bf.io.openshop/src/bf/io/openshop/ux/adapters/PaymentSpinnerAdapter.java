package bf.io.openshop.ux.adapters;

import bf.io.openshop.entities.delivery.*;
import android.content.*;
import java.util.*;
import android.view.*;
import android.widget.*;
import android.support.v4.content.*;

public class PaymentSpinnerAdapter extends ArrayAdapter<Payment>
{
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Payment> payments;
    private long selectedId;
    
    public PaymentSpinnerAdapter(final Context context, final List<Payment> payments) {
        super(context, 17367048, (List)payments);
        this.selectedId = -131L;
        this.context = context;
        this.payments = payments;
        this.layoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }
    
    public int getCount() {
        return this.payments.size();
    }
    
    public Payment getItem(final int n) {
        return this.payments.get(n);
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        ViewHolder tag;
        if (inflate == null) {
            tag = new ViewHolder();
            inflate = this.layoutInflater.inflate(2130968655, viewGroup, false);
            tag.title = (TextView)inflate.findViewById(2131624337);
            tag.description = (TextView)inflate.findViewById(2131624339);
            tag.isSelected = inflate.findViewById(2131624336);
            tag.separator = inflate.findViewById(2131624335);
            tag.price = (TextView)inflate.findViewById(2131624338);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ViewHolder)inflate.getTag();
        }
        final Payment item = this.getItem(n);
        if (n == 0) {
            tag.separator.setVisibility(8);
        }
        else {
            tag.separator.setVisibility(0);
        }
        if (this.selectedId != -131L && item.getId() == this.selectedId) {
            tag.title.setTextColor(ContextCompat.getColor(this.context, 2131558426));
            tag.isSelected.setVisibility(0);
        }
        else {
            tag.title.setTextColor(ContextCompat.getColor(this.context, 2131558531));
            tag.isSelected.setVisibility(4);
        }
        tag.title.setText((CharSequence)item.getName());
        if (item.getDescription() != null && !item.getDescription().isEmpty()) {
            tag.description.setText((CharSequence)item.getDescription());
            tag.description.setVisibility(0);
        }
        else {
            tag.description.setVisibility(4);
        }
        if (item.getPrice() == 0.0) {
            tag.price.setText(2131230944);
            tag.price.setTextColor(ContextCompat.getColor(this.context, 2131558426));
            return inflate;
        }
        tag.price.setText((CharSequence)this.context.getResources().getString(2131230937, new Object[] { item.getPriceFormatted() }));
        tag.price.setTextColor(ContextCompat.getColor(this.context, 2131558531));
        return inflate;
    }
    
    public void preselectPayment(final Payment payment) {
        if (payment != null) {
            this.selectedId = payment.getId();
        }
    }
    
    public static class ViewHolder
    {
        public TextView description;
        public View isSelected;
        public TextView price;
        public View separator;
        public TextView title;
    }
}
