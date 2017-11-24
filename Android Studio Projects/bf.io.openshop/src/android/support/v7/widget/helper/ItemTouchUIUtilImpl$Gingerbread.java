package android.support.v7.widget.helper;

import android.graphics.*;
import android.support.v7.widget.*;
import android.view.*;

static class Gingerbread implements ItemTouchUIUtil
{
    private void draw(final Canvas canvas, final RecyclerView recyclerView, final View view, final float n, final float n2) {
        canvas.save();
        canvas.translate(n, n2);
        recyclerView.drawChild(canvas, view, 0L);
        canvas.restore();
    }
    
    @Override
    public void clearView(final View view) {
        view.setVisibility(0);
    }
    
    @Override
    public void onDraw(final Canvas canvas, final RecyclerView recyclerView, final View view, final float n, final float n2, final int n3, final boolean b) {
        if (n3 != 2) {
            this.draw(canvas, recyclerView, view, n, n2);
        }
    }
    
    @Override
    public void onDrawOver(final Canvas canvas, final RecyclerView recyclerView, final View view, final float n, final float n2, final int n3, final boolean b) {
        if (n3 == 2) {
            this.draw(canvas, recyclerView, view, n, n2);
        }
    }
    
    @Override
    public void onSelected(final View view) {
        view.setVisibility(4);
    }
}
