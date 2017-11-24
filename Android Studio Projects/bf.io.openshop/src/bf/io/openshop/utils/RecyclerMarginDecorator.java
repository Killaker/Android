package bf.io.openshop.utils;

import android.support.v7.widget.*;
import android.content.*;
import android.graphics.*;
import android.view.*;

public class RecyclerMarginDecorator extends ItemDecoration
{
    private int marginHorizontal;
    private int marginVertical;
    
    public RecyclerMarginDecorator(final int n) {
        this.marginHorizontal = 0;
        this.marginVertical = 0;
        this.marginVertical = n;
        this.marginHorizontal = n;
    }
    
    public RecyclerMarginDecorator(final Context context, final ORIENTATION orientation) {
        this.marginHorizontal = 0;
        this.marginVertical = 0;
        if (orientation == ORIENTATION.VERTICAL) {
            this.marginVertical = context.getResources().getDimensionPixelSize(2131361874);
            return;
        }
        if (orientation == ORIENTATION.HORIZONTAL) {
            this.marginHorizontal = context.getResources().getDimensionPixelSize(2131361874);
            return;
        }
        this.marginVertical = context.getResources().getDimensionPixelSize(2131361874);
        this.marginHorizontal = this.marginVertical;
    }
    
    @Override
    public void getItemOffsets(final Rect rect, final View view, final RecyclerView recyclerView, final State state) {
        rect.set(this.marginHorizontal, this.marginVertical, this.marginHorizontal, this.marginVertical);
    }
    
    public enum ORIENTATION
    {
        BOTH, 
        HORIZONTAL, 
        VERTICAL;
    }
}
