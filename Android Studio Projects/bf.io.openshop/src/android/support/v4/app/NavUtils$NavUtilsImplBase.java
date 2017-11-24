package android.support.v4.app;

import android.app.*;
import android.content.*;
import android.support.v4.content.*;
import android.util.*;
import android.content.pm.*;

static class NavUtilsImplBase implements NavUtilsImpl
{
    @Override
    public Intent getParentActivityIntent(final Activity activity) {
        final String parentActivityName = NavUtils.getParentActivityName(activity);
        if (parentActivityName == null) {
            return null;
        }
        final ComponentName component = new ComponentName((Context)activity, parentActivityName);
        Intent intent;
        try {
            if (NavUtils.getParentActivityName((Context)activity, component) == null) {
                intent = IntentCompat.makeMainActivity(component);
            }
            else {
                intent = new Intent().setComponent(component);
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + parentActivityName + "' in manifest");
            return null;
        }
        return intent;
    }
    
    @Override
    public String getParentActivityName(final Context context, final ActivityInfo activityInfo) {
        String string;
        if (activityInfo.metaData == null) {
            string = null;
        }
        else {
            string = activityInfo.metaData.getString("android.support.PARENT_ACTIVITY");
            if (string == null) {
                return null;
            }
            if (string.charAt(0) == '.') {
                return context.getPackageName() + string;
            }
        }
        return string;
    }
    
    @Override
    public void navigateUpTo(final Activity activity, final Intent intent) {
        intent.addFlags(67108864);
        activity.startActivity(intent);
        activity.finish();
    }
    
    @Override
    public boolean shouldUpRecreateTask(final Activity activity, final Intent intent) {
        final String action = activity.getIntent().getAction();
        return action != null && !action.equals("android.intent.action.MAIN");
    }
}
