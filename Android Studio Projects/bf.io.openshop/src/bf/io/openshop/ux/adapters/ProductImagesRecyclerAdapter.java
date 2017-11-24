package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import android.content.*;
import bf.io.openshop.interfaces.*;
import java.util.*;
import com.squareup.picasso.*;
import android.widget.*;
import android.view.*;

public class ProductImagesRecyclerAdapter extends Adapter<ViewHolder>
{
    private final Context context;
    private LayoutInflater layoutInflater;
    private final ProductImagesRecyclerInterface productImagesRecyclerInterface;
    private List<String> productImagesUrls;
    
    public ProductImagesRecyclerAdapter(final Context context, final ProductImagesRecyclerInterface productImagesRecyclerInterface) {
        this.context = context;
        this.productImagesRecyclerInterface = productImagesRecyclerInterface;
        this.productImagesUrls = new ArrayList<String>();
    }
    
    private String getItem(final int n) {
        return this.productImagesUrls.get(n);
    }
    
    public void add(final int n, final String s) {
        this.productImagesUrls.add(n, s);
        ((RecyclerView.Adapter)this).notifyItemInserted(n);
    }
    
    public void addLast(final String s) {
        this.productImagesUrls.add(this.productImagesUrls.size(), s);
        ((RecyclerView.Adapter)this).notifyItemInserted(this.productImagesUrls.size());
    }
    
    public void clearAll() {
        final int size = this.productImagesUrls.size();
        if (size > 0) {
            this.productImagesUrls.clear();
            ((RecyclerView.Adapter)this).notifyItemRangeRemoved(0, size);
        }
    }
    
    @Override
    public int getItemCount() {
        return this.productImagesUrls.size();
    }
    
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final String item = this.getItem(position);
        viewHolder.setPosition(position);
        Picasso.with(this.context).load(item).fit().centerInside().placeholder(2130837697).error(2130837696).into(viewHolder.productImage);
    }
    
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        if (this.layoutInflater == null) {
            this.layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        return new ViewHolder(this.layoutInflater.inflate(2130968656, viewGroup, false), this.productImagesRecyclerInterface);
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        View aa;
        int position;
        ImageView productImage;
        
        public ViewHolder(final View view, final ProductImagesRecyclerInterface productImagesRecyclerInterface) {
            super(view);
            this.productImage = (ImageView)view.findViewById(2131624331);
            view.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    if (productImagesRecyclerInterface != null) {
                        productImagesRecyclerInterface.onImageSelected(view, ViewHolder.this.position);
                    }
                }
            });
        }
        
        public void setPosition(final int position) {
            this.position = position;
        }
    }
}
