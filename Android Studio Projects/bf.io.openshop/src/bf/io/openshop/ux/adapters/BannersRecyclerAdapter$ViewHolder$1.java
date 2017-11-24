package bf.io.openshop.ux.adapters;

import bf.io.openshop.listeners.*;
import bf.io.openshop.interfaces.*;
import android.view.*;
import android.os.*;

class BannersRecyclerAdapter$ViewHolder$1 extends OnSingleClickListener {
    final /* synthetic */ BannersRecyclerInterface val$bannersRecyclerInterface;
    
    @Override
    public void onSingleClick(final View view) {
        new Handler().postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                OnSingleClickListener.this.val$bannersRecyclerInterface.onBannerSelected(ViewHolder.access$000(ViewHolder.this));
            }
        }, 200L);
    }
}