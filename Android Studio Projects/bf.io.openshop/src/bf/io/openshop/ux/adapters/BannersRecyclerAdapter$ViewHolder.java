package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import bf.io.openshop.entities.*;
import android.widget.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.listeners.*;
import android.os.*;
import android.view.*;

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
