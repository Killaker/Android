package android.support.v7.widget;

import android.content.*;

private class ShareActivityChooserModelPolicy implements OnChooseActivityListener
{
    @Override
    public boolean onChooseActivity(final ActivityChooserModel activityChooserModel, final Intent intent) {
        if (ShareActionProvider.access$500(ShareActionProvider.this) != null) {
            ShareActionProvider.access$500(ShareActionProvider.this).onShareTargetSelected(ShareActionProvider.this, intent);
        }
        return false;
    }
}
