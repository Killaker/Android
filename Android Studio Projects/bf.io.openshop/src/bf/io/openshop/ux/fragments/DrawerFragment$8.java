package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import android.support.annotation.*;
import bf.io.openshop.entities.drawerMenu.*;
import android.support.v7.widget.*;

class DrawerFragment$8 implements Listener<DrawerResponse> {
    public void onResponse(@NonNull final DrawerResponse drawerResponse) {
        DrawerFragment.access$600(DrawerFragment.this).addDrawerItem(new DrawerItemCategory(-123L, -123L, DrawerFragment.this.getString(2131230835)));
        DrawerFragment.access$600(DrawerFragment.this).addDrawerItemList(drawerResponse.getNavigation());
        DrawerFragment.access$600(DrawerFragment.this).addPageItemList(drawerResponse.getPages());
        ((RecyclerView.Adapter)DrawerFragment.access$600(DrawerFragment.this)).notifyDataSetChanged();
        if (DrawerFragment.access$300(DrawerFragment.this) != null) {
            DrawerFragment.access$300(DrawerFragment.this).prepareSearchSuggestions(drawerResponse.getNavigation());
        }
        DrawerFragment.access$002(DrawerFragment.this, false);
        if (DrawerFragment.access$700(DrawerFragment.this) != null) {
            DrawerFragment.access$700(DrawerFragment.this).setVisibility(0);
        }
        if (DrawerFragment.access$800(DrawerFragment.this) != null) {
            DrawerFragment.access$800(DrawerFragment.this).setVisibility(8);
        }
    }
}