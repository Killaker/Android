package android.support.v7.widget;

import android.view.*;
import android.content.*;
import android.widget.*;

private class Callbacks implements AdapterView$OnItemClickListener, View$OnClickListener, View$OnLongClickListener, PopupWindow$OnDismissListener
{
    private void notifyOnDismissListener() {
        if (ActivityChooserView.access$1000(ActivityChooserView.this) != null) {
            ActivityChooserView.access$1000(ActivityChooserView.this).onDismiss();
        }
    }
    
    public void onClick(final View view) {
        if (view == ActivityChooserView.access$700(ActivityChooserView.this)) {
            ActivityChooserView.this.dismissPopup();
            final Intent chooseActivity = ActivityChooserView.access$000(ActivityChooserView.this).getDataModel().chooseActivity(ActivityChooserView.access$000(ActivityChooserView.this).getDataModel().getActivityIndex(ActivityChooserView.access$000(ActivityChooserView.this).getDefaultActivity()));
            if (chooseActivity != null) {
                chooseActivity.addFlags(524288);
                ActivityChooserView.this.getContext().startActivity(chooseActivity);
            }
            return;
        }
        if (view == ActivityChooserView.access$800(ActivityChooserView.this)) {
            ActivityChooserView.access$602(ActivityChooserView.this, false);
            ActivityChooserView.access$500(ActivityChooserView.this, ActivityChooserView.access$900(ActivityChooserView.this));
            return;
        }
        throw new IllegalArgumentException();
    }
    
    public void onDismiss() {
        this.notifyOnDismissListener();
        if (ActivityChooserView.this.mProvider != null) {
            ActivityChooserView.this.mProvider.subUiVisibilityChanged(false);
        }
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, int defaultActivity, final long n) {
        switch (((ActivityChooserViewAdapter)adapterView.getAdapter()).getItemViewType(defaultActivity)) {
            default: {
                throw new IllegalArgumentException();
            }
            case 1: {
                ActivityChooserView.access$500(ActivityChooserView.this, Integer.MAX_VALUE);
                break;
            }
            case 0: {
                ActivityChooserView.this.dismissPopup();
                if (ActivityChooserView.access$600(ActivityChooserView.this)) {
                    if (defaultActivity > 0) {
                        ActivityChooserView.access$000(ActivityChooserView.this).getDataModel().setDefaultActivity(defaultActivity);
                        return;
                    }
                    break;
                }
                else {
                    if (!ActivityChooserView.access$000(ActivityChooserView.this).getShowDefaultActivity()) {
                        ++defaultActivity;
                    }
                    final Intent chooseActivity = ActivityChooserView.access$000(ActivityChooserView.this).getDataModel().chooseActivity(defaultActivity);
                    if (chooseActivity != null) {
                        chooseActivity.addFlags(524288);
                        ActivityChooserView.this.getContext().startActivity(chooseActivity);
                        return;
                    }
                    break;
                }
                break;
            }
        }
    }
    
    public boolean onLongClick(final View view) {
        if (view == ActivityChooserView.access$700(ActivityChooserView.this)) {
            if (ActivityChooserView.access$000(ActivityChooserView.this).getCount() > 0) {
                ActivityChooserView.access$602(ActivityChooserView.this, true);
                ActivityChooserView.access$500(ActivityChooserView.this, ActivityChooserView.access$900(ActivityChooserView.this));
            }
            return true;
        }
        throw new IllegalArgumentException();
    }
}
