package android.support.v4.app;

import android.content.*;

private static class AppOpsManager23 extends AppOpsManagerImpl
{
    @Override
    public int noteOp(final Context context, final String s, final int n, final String s2) {
        return AppOpsManagerCompat23.noteOp(context, s, n, s2);
    }
    
    @Override
    public int noteProxyOp(final Context context, final String s, final String s2) {
        return AppOpsManagerCompat23.noteProxyOp(context, s, s2);
    }
    
    @Override
    public String permissionToOp(final String s) {
        return AppOpsManagerCompat23.permissionToOp(s);
    }
}
