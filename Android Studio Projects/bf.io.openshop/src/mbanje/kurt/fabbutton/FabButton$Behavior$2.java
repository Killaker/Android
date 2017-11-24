package mbanje.kurt.fabbutton;

import android.view.animation.*;

class FabButton$Behavior$2 extends AnimationListenerAdapter {
    final /* synthetic */ FabButton val$button;
    
    @Override
    public void onAnimationEnd(final Animation animation) {
        Behavior.access$002(Behavior.this, false);
        this.val$button.setVisibility(8);
    }
    
    @Override
    public void onAnimationStart(final Animation animation) {
        Behavior.access$002(Behavior.this, true);
    }
}