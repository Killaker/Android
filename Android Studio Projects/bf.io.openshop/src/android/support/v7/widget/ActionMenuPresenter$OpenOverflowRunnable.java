package android.support.v7.widget;

import android.view.*;

private class OpenOverflowRunnable implements Runnable
{
    private OverflowPopup mPopup;
    
    public OpenOverflowRunnable(final OverflowPopup mPopup) {
        this.mPopup = mPopup;
    }
    
    @Override
    public void run() {
        ActionMenuPresenter.access$900(ActionMenuPresenter.this).changeMenuMode();
        final View view = (View)ActionMenuPresenter.access$1000(ActionMenuPresenter.this);
        if (view != null && view.getWindowToken() != null && this.mPopup.tryShow()) {
            ActionMenuPresenter.access$202(ActionMenuPresenter.this, this.mPopup);
        }
        ActionMenuPresenter.access$302(ActionMenuPresenter.this, null);
    }
}
