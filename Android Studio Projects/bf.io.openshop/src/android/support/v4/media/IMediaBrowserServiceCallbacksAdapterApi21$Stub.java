package android.support.v4.media;

import android.os.*;
import java.lang.reflect.*;

static class Stub
{
    static Method sAsInterfaceMethod;
    
    static {
        try {
            Stub.sAsInterfaceMethod = Class.forName("android.service.media.IMediaBrowserServiceCallbacks$Stub").getMethod("asInterface", IBinder.class);
        }
        catch (ClassNotFoundException ex) {}
        catch (NoSuchMethodException ex2) {
            goto Label_0024;
        }
    }
    
    static Object asInterface(final IBinder binder) {
        try {
            return Stub.sAsInterfaceMethod.invoke(null, binder);
        }
        catch (IllegalAccessException ex) {}
        catch (InvocationTargetException ex2) {
            goto Label_0019;
        }
    }
}
