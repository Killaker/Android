package android.support.v7.widget.helper;

import android.view.*;
import android.support.v4.view.*;
import android.graphics.*;
import android.support.v7.widget.*;

static class Honeycomb implements ItemTouchUIUtil
{
    @Override
    public void clearView(final View view) {
        ViewCompat.setTranslationX(view, 0.0f);
        ViewCompat.setTranslationY(view, 0.0f);
    }
    
    @Override
    public void onDraw(final Canvas canvas, final RecyclerView recyclerView, final View view, final float n, final float n2, final int n3, final boolean b) {
        ViewCompat.setTranslationX(view, n);
        ViewCompat.setTranslationY(view, n2);
    }
    
    @Override
    public void onDrawOver(final Canvas canvas, final RecyclerView recyclerView, final View view, final float n, final float n2, final int n3, final boolean b) {
    }
    
    @Override
    public void onSelected(final View view) {
    }
}
