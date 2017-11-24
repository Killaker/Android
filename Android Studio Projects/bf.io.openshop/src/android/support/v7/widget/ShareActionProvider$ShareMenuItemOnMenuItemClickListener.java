package android.support.v7.widget;

import android.view.*;
import android.content.*;

private class ShareMenuItemOnMenuItemClickListener implements MenuItem$OnMenuItemClickListener
{
    public boolean onMenuItemClick(final MenuItem menuItem) {
        final Intent chooseActivity = ActivityChooserModel.get(ShareActionProvider.access$100(ShareActionProvider.this), ShareActionProvider.access$200(ShareActionProvider.this)).chooseActivity(menuItem.getItemId());
        if (chooseActivity != null) {
            final String action = chooseActivity.getAction();
            if ("android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action)) {
                ShareActionProvider.access$300(ShareActionProvider.this, chooseActivity);
            }
            ShareActionProvider.access$100(ShareActionProvider.this).startActivity(chooseActivity);
        }
        return true;
    }
}
