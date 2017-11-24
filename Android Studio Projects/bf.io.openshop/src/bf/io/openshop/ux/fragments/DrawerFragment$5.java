package bf.io.openshop.ux.fragments;

import android.support.v7.app.*;
import android.app.*;
import android.support.v4.widget.*;
import android.support.v7.widget.*;
import android.view.*;

class DrawerFragment$5 extends ActionBarDrawerToggle {
    @Override
    public void onDrawerClosed(final View view) {
        super.onDrawerClosed(view);
        DrawerFragment.this.getActivity().invalidateOptionsMenu();
    }
    
    @Override
    public void onDrawerOpened(final View view) {
        super.onDrawerOpened(view);
        DrawerFragment.this.getActivity().invalidateOptionsMenu();
    }
    
    @Override
    public void onDrawerSlide(final View view, final float n) {
        super.onDrawerSlide(view, n);
    }
}