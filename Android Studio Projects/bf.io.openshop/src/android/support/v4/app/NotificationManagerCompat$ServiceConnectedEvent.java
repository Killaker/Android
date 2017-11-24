package android.support.v4.app;

import android.content.*;
import android.os.*;

private static class ServiceConnectedEvent
{
    final ComponentName componentName;
    final IBinder iBinder;
    
    public ServiceConnectedEvent(final ComponentName componentName, final IBinder iBinder) {
        this.componentName = componentName;
        this.iBinder = iBinder;
    }
}
