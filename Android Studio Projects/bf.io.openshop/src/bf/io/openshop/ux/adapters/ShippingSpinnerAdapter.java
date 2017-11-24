package bf.io.openshop.ux.adapters;

import android.content.*;
import android.widget.*;
import android.support.v4.content.*;
import android.view.*;
import android.os.*;
import bf.io.openshop.ux.dialogs.*;
import android.support.v4.app.*;
import bf.io.openshop.entities.delivery.*;
import java.util.*;

public class ShippingSpinnerAdapter extends BaseAdapter
{
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    public Context context;
    public ShippingDialogFragment fragment;
    private LayoutInflater layoutInflater;
    private TreeSet<Integer> sectionHeader;
    private long selectedId;
    private List<Shipping> shippingList;
    
    public ShippingSpinnerAdapter(final Context context, final ShippingDialogFragment fragment) {
        this.shippingList = new ArrayList<Shipping>();
        this.sectionHeader = new TreeSet<Integer>();
        this.selectedId = -131L;
        this.context = context;
        this.fragment = fragment;
        this.layoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }
    
    public int getCount() {
        return this.shippingList.size();
    }
    
    public Shipping getItem(final int n) {
        return this.shippingList.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public int getItemViewType(final int n) {
        if (this.sectionHeader.contains(n)) {
            return 1;
        }
        return 0;
    }
    
    public View getView(final int n, View view, final ViewGroup viewGroup) {
        final int itemViewType = this.getItemViewType(n);
        ViewHolder tag;
        if (view == null) {
            tag = new ViewHolder();
            switch (itemViewType) {
                default: {
                    view = this.layoutInflater.inflate(2130968660, viewGroup, false);
                    tag.title = (TextView)view.findViewById(2131624351);
                    break;
                }
                case 0: {
                    view = this.layoutInflater.inflate(2130968659, viewGroup, false);
                    tag.title = (TextView)view.findViewById(2131624351);
                    tag.description = (TextView)view.findViewById(2131624352);
                    tag.price = (TextView)view.findViewById(2131624353);
                    tag.isSelected = view.findViewById(2131624350);
                    tag.separator = view.findViewById(2131624349);
                    tag.shopInfo = (ImageView)view.findViewById(2131624354);
                    break;
                }
            }
            view.setTag((Object)tag);
        }
        else {
            tag = (ViewHolder)view.getTag();
        }
        final Shipping shipping = this.shippingList.get(n);
        if (itemViewType == 1) {
            tag.title.setText((CharSequence)shipping.getName());
            return view;
        }
        if (n == 0) {
            tag.separator.setVisibility(8);
        }
        else {
            tag.separator.setVisibility(0);
        }
        if (this.selectedId != -131L && shipping.getId() == this.selectedId) {
            tag.title.setTextColor(ContextCompat.getColor(this.context, 2131558426));
            tag.isSelected.setVisibility(0);
        }
        else {
            tag.title.setTextColor(ContextCompat.getColor(this.context, 2131558531));
            tag.isSelected.setVisibility(4);
        }
        tag.title.setText((CharSequence)shipping.getName());
        if (shipping.getPrice() == 0) {
            tag.price.setText(2131230944);
        }
        else {
            tag.price.setText((CharSequence)this.context.getResources().getString(2131230937, new Object[] { shipping.getPriceFormatted() }));
        }
        if (shipping.getBranch() != null) {
            if (shipping.getBranch().getName() != null && !shipping.getBranch().getName().isEmpty()) {
                tag.title.setText((CharSequence)shipping.getBranch().getName());
            }
            tag.description.setText((CharSequence)shipping.getBranch().getAddress());
            tag.description.setVisibility(0);
            tag.shopInfo.setVisibility(0);
            tag.shopInfo.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                private long mLastClickTime = 0L;
                
                public void onClick(final View view) {
                    if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1000L) {
                        return;
                    }
                    this.mLastClickTime = SystemClock.elapsedRealtime();
                    final FragmentManager fragmentManager = ShippingSpinnerAdapter.this.fragment.getFragmentManager();
                    final MapDialogFragment instance = MapDialogFragment.newInstance(ShippingSpinnerAdapter.this.fragment, shipping, shipping.getBranch());
                    instance.setRetainInstance(true);
                    instance.show(fragmentManager, MapDialogFragment.class.getSimpleName());
                }
            });
            return view;
        }
        tag.shopInfo.setVisibility(4);
        if (shipping.getDescription() != null && !shipping.getDescription().isEmpty()) {
            tag.description.setText((CharSequence)shipping.getDescription());
            tag.description.setVisibility(0);
            return view;
        }
        tag.description.setVisibility(8);
        return view;
    }
    
    public int getViewTypeCount() {
        return 2;
    }
    
    public void preselectShipping(final Shipping shipping) {
        if (shipping != null) {
            this.selectedId = shipping.getId();
        }
    }
    
    public void setData(final List<DeliveryType> list) {
        this.shippingList.clear();
        this.sectionHeader.clear();
        for (final DeliveryType deliveryType : list) {
            if (deliveryType.getId() != -131L) {
                this.shippingList.add(new Shipping(deliveryType.getName()));
                this.sectionHeader.add(-1 + this.shippingList.size());
            }
            final Iterator<Shipping> iterator2 = deliveryType.getShippingList().iterator();
            while (iterator2.hasNext()) {
                this.shippingList.add(iterator2.next());
            }
        }
        this.notifyDataSetChanged();
    }
    
    public static class ViewHolder
    {
        public TextView description;
        public View isSelected;
        public TextView price;
        public View separator;
        public ImageView shopInfo;
        public TextView title;
    }
}
