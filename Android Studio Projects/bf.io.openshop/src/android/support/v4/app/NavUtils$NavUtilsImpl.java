package android.support.v4.app;

import android.app.*;
import android.content.*;
import android.content.pm.*;

interface NavUtilsImpl
{
    Intent getParentActivityIntent(final Activity p0);
    
    String getParentActivityName(final Context p0, final ActivityInfo p1);
    
    void navigateUpTo(final Activity p0, final Intent p1);
    
    boolean shouldUpRecreateTask(final Activity p0, final Intent p1);
}
