package mbanje.kurt.fabbutton;

import android.support.v4.view.*;
import android.view.*;

class FabButton$Behavior$1 implements ViewPropertyAnimatorListener {
    @Override
    public void onAnimationCancel(final View view) {
        Behavior.access$002(Behavior.this, false);
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        Behavior.access$002(Behavior.this, false);
        view.setVisibility(8);
    }
    
    @Override
    public void onAnimationStart(final View view) {
        Behavior.access$002(Behavior.this, true);
    }
}