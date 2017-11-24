package bf.io.openshop.utils;

import android.support.v7.widget.*;
import android.graphics.drawable.*;
import android.content.*;
import android.content.res.*;
import android.support.v4.content.*;
import android.view.*;
import android.graphics.*;

public class RecyclerDividerDecorator extends ItemDecoration
{
    private static final int[] ATTRS;
    private Drawable mDivider;
    private final int marginHorizontal;
    
    static {
        ATTRS = new int[] { 16843284 };
    }
    
    public RecyclerDividerDecorator(final Context context) {
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(RecyclerDividerDecorator.ATTRS);
        this.mDivider = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        this.marginHorizontal = context.getResources().getDimensionPixelSize(2131361874);
    }
    
    public RecyclerDividerDecorator(final Context context, final int n) {
        this.mDivider = ContextCompat.getDrawable(context, n);
        this.marginHorizontal = context.getResources().getDimensionPixelSize(2131361874);
    }
    
    @Override
    public void getItemOffsets(final Rect rect, final View view, final RecyclerView recyclerView, final State state) {
        rect.set(this.marginHorizontal, 0, this.marginHorizontal, 0);
    }
    
    @Override
    public void onDraw(final Canvas canvas, final RecyclerView recyclerView, final State state) {
        final int paddingLeft = recyclerView.getPaddingLeft();
        final int n = recyclerView.getWidth() - recyclerView.getPaddingRight();
        for (int childCount = recyclerView.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = recyclerView.getChildAt(i);
            final int n2 = child.getBottom() + ((LayoutParams)child.getLayoutParams()).bottomMargin;
            this.mDivider.setBounds(paddingLeft, n2, n, n2 + this.mDivider.getIntrinsicHeight());
            this.mDivider.draw(canvas);
        }
    }
}
