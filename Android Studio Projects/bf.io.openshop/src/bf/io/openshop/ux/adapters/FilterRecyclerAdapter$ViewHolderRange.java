package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import android.widget.*;
import android.view.*;

public static class ViewHolderRange extends ViewHolder
{
    public TextView rangeName;
    public TextView rangeResult;
    public LinearLayout seekBarLayout;
    
    public ViewHolderRange(final View view) {
        super(view);
        this.rangeName = (TextView)view.findViewById(2131624320);
        this.rangeResult = (TextView)view.findViewById(2131624322);
        this.seekBarLayout = (LinearLayout)view.findViewById(2131624321);
    }
}
