package bf.io.openshop.ux.fragments;

import android.view.animation.*;

class DrawerFragment$10 implements Animation$AnimationListener {
    final /* synthetic */ Animation val$slideAwayAppear;
    
    public void onAnimationEnd(final Animation animation) {
        DrawerFragment.access$1000(DrawerFragment.this).setVisibility(8);
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
        DrawerFragment.access$700(DrawerFragment.this).setVisibility(0);
        DrawerFragment.access$700(DrawerFragment.this).startAnimation(this.val$slideAwayAppear);
    }
}