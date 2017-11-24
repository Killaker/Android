package android.support.v4.app;

import android.app.*;
import android.content.*;
import android.content.pm.*;

static class NavUtilsImplJB extends NavUtilsImplBase
{
    @Override
    public Intent getParentActivityIntent(final Activity activity) {
        Intent intent = NavUtilsJB.getParentActivityIntent(activity);
        if (intent == null) {
            intent = this.superGetParentActivityIntent(activity);
        }
        return intent;
    }
    
    @Override
    public String getParentActivityName(final Context context, final ActivityInfo activityInfo) {
        String s = NavUtilsJB.getParentActivityName(activityInfo);
        if (s == null) {
            s = super.getParentActivityName(context, activityInfo);
        }
        return s;
    }
    
    @Override
    public void navigateUpTo(final Activity activity, final Intent intent) {
        NavUtilsJB.navigateUpTo(activity, intent);
    }
    
    @Override
    public boolean shouldUpRecreateTask(final Activity activity, final Intent intent) {
        return NavUtilsJB.shouldUpRecreateTask(activity, intent);
    }
    
    Intent superGetParentActivityIntent(final Activity activity) {
        return super.getParentActivityIntent(activity);
    }
}
