package android.support.v7.widget;

import android.widget.*;
import android.view.*;
import android.support.v7.appcompat.*;

private static final class ChildViewCache
{
    public final ImageView mIcon1;
    public final ImageView mIcon2;
    public final ImageView mIconRefine;
    public final TextView mText1;
    public final TextView mText2;
    
    public ChildViewCache(final View view) {
        this.mText1 = (TextView)view.findViewById(16908308);
        this.mText2 = (TextView)view.findViewById(16908309);
        this.mIcon1 = (ImageView)view.findViewById(16908295);
        this.mIcon2 = (ImageView)view.findViewById(16908296);
        this.mIconRefine = (ImageView)view.findViewById(R.id.edit_query);
    }
}
