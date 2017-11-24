package android.support.v4.app;

import android.content.*;

private static class AppOpsManagerImpl
{
    public int noteOp(final Context context, final String s, final int n, final String s2) {
        return 1;
    }
    
    public int noteProxyOp(final Context context, final String s, final String s2) {
        return 1;
    }
    
    public String permissionToOp(final String s) {
        return null;
    }
}
