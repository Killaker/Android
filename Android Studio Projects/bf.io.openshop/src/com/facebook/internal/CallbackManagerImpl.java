package com.facebook.internal;

import java.util.*;
import android.content.*;
import com.facebook.*;

public final class CallbackManagerImpl implements CallbackManager
{
    private static Map<Integer, Callback> staticCallbacks;
    private Map<Integer, Callback> callbacks;
    
    static {
        CallbackManagerImpl.staticCallbacks = new HashMap<Integer, Callback>();
    }
    
    public CallbackManagerImpl() {
        this.callbacks = new HashMap<Integer, Callback>();
    }
    
    private static Callback getStaticCallback(final Integer n) {
        synchronized (CallbackManagerImpl.class) {
            return CallbackManagerImpl.staticCallbacks.get(n);
        }
    }
    
    public static void registerStaticCallback(final int n, final Callback callback) {
        synchronized (CallbackManagerImpl.class) {
            Validate.notNull(callback, "callback");
            if (!CallbackManagerImpl.staticCallbacks.containsKey(n)) {
                CallbackManagerImpl.staticCallbacks.put(n, callback);
            }
        }
    }
    
    private static boolean runStaticCallback(final int n, final int n2, final Intent intent) {
        final Callback staticCallback = getStaticCallback(n);
        return staticCallback != null && staticCallback.onActivityResult(n2, intent);
    }
    
    @Override
    public boolean onActivityResult(final int n, final int n2, final Intent intent) {
        final Callback callback = this.callbacks.get(n);
        if (callback != null) {
            return callback.onActivityResult(n2, intent);
        }
        return runStaticCallback(n, n2, intent);
    }
    
    public void registerCallback(final int n, final Callback callback) {
        Validate.notNull(callback, "callback");
        this.callbacks.put(n, callback);
    }
    
    public interface Callback
    {
        boolean onActivityResult(final int p0, final Intent p1);
    }
    
    public enum RequestCodeOffset
    {
        AppGroupCreate(5), 
        AppGroupJoin(6), 
        AppInvite(7), 
        GameRequest(4), 
        Like(3), 
        Login(0), 
        Message(2), 
        Share(1);
        
        private final int offset;
        
        private RequestCodeOffset(final int offset) {
            this.offset = offset;
        }
        
        public int toRequestCode() {
            return FacebookSdk.getCallbackRequestCodeOffset() + this.offset;
        }
    }
}
