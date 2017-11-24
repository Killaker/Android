package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import bf.io.openshop.entities.*;
import bf.io.openshop.interfaces.*;
import android.content.*;
import java.util.*;
import timber.log.*;
import com.squareup.picasso.*;
import android.widget.*;
import bf.io.openshop.listeners.*;
import android.os.*;
import android.view.*;

public class BannersRecyclerAdapter extends Adapter<ViewHolder>
{
    private List<Banner> banners;
    private final BannersRecyclerInterface bannersRecyclerInterface;
    private final Context context;
    LayoutInflater layoutInflater;
    
    public BannersRecyclerAdapter(final Context context, final BannersRecyclerInterface bannersRecyclerInterface) {
        this.banners = new ArrayList<Banner>();
        this.context = context;
        this.bannersRecyclerInterface = bannersRecyclerInterface;
    }
    
    private Banner getBannerItem(final int n) {
        return this.banners.get(n);
    }
    
    public void addBanners(final List<Banner> list) {
        if (list != null && !list.isEmpty()) {
            this.banners.addAll(list);
            ((RecyclerView.Adapter)this).notifyDataSetChanged();
            return;
        }
        Timber.e("Adding empty banner list.", new Object[0]);
    }
    
    public void clear() {
        this.banners.clear();
    }
    
    @Override
    public int getItemCount() {
        return this.banners.size();
    }
    
    public boolean isEmpty() {
        return this.banners == null || this.banners.size() == 0;
    }
    
    public void onBindViewHolder(final ViewHolder viewHolder, final int n) {
        final Banner bannerItem = this.getBannerItem(n);
        viewHolder.bindContent(bannerItem);
        Picasso.with(this.context).load(bannerItem.getImageUrl()).placeholder(2130837697).fit().centerInside().into(viewHolder.bannerImage);
    }
    
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        if (this.layoutInflater == null) {
            this.layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        return new ViewHolder(this.layoutInflater.inflate(2130968643, viewGroup, false), this.bannersRecyclerInterface);
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private Banner banner;
        public ImageView bannerImage;
        
        public ViewHolder(final View view, final BannersRecyclerInterface bannersRecyclerInterface) {
            super(view);
            this.bannerImage = (ImageView)view.findViewById(2131624302);
            view.setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
                @Override
                public void onSingleClick(final View view) {
                    new Handler().postDelayed((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            bannersRecyclerInterface.onBannerSelected(ViewHolder.this.banner);
                        }
                    }, 200L);
                }
            });
        }
        
        public void bindContent(final Banner banner) {
            this.banner = banner;
        }
    }
}
