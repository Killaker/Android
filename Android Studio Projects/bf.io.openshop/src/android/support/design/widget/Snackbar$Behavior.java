package android.support.design.widget;

import android.view.*;

final class Behavior extends SwipeDismissBehavior<SnackbarLayout>
{
    @Override
    public boolean canSwipeDismissView(final View view) {
        return view instanceof SnackbarLayout;
    }
    
    @Override
    public boolean onInterceptTouchEvent(final CoordinatorLayout coordinatorLayout, final SnackbarLayout snackbarLayout, final MotionEvent motionEvent) {
        if (coordinatorLayout.isPointInChildBounds((View)snackbarLayout, (int)motionEvent.getX(), (int)motionEvent.getY())) {
            switch (motionEvent.getActionMasked()) {
                case 0: {
                    SnackbarManager.getInstance().cancelTimeout(Snackbar.access$200(Snackbar.this));
                    break;
                }
                case 1:
                case 3: {
                    SnackbarManager.getInstance().restoreTimeout(Snackbar.access$200(Snackbar.this));
                    break;
                }
            }
        }
        return super.onInterceptTouchEvent(coordinatorLayout, snackbarLayout, motionEvent);
    }
}
