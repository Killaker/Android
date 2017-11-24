package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import android.widget.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.listeners.*;
import android.view.*;

public static class ViewHolderHeader extends ViewHolder
{
    public TextView userName;
    
    public ViewHolderHeader(final View view, final DrawerRecyclerInterface drawerRecyclerInterface) {
        super(view);
        this.userName = (TextView)view.findViewById(2131624319);
        view.setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                drawerRecyclerInterface.onHeaderSelected();
            }
        });
    }
}
