package com.facebook.internal;

import java.util.*;
import android.content.*;

public class AppCall
{
    private static AppCall currentPendingCall;
    private UUID callId;
    private int requestCode;
    private Intent requestIntent;
    
    public AppCall(final int n) {
        this(n, UUID.randomUUID());
    }
    
    public AppCall(final int requestCode, final UUID callId) {
        this.callId = callId;
        this.requestCode = requestCode;
    }
    
    public static AppCall finishPendingCall(final UUID uuid, final int n) {
        synchronized (AppCall.class) {
            AppCall currentPendingCall = getCurrentPendingCall();
            if (currentPendingCall == null || !currentPendingCall.getCallId().equals(uuid) || currentPendingCall.getRequestCode() != n) {
                currentPendingCall = null;
            }
            else {
                setCurrentPendingCall(null);
            }
            return currentPendingCall;
        }
    }
    
    public static AppCall getCurrentPendingCall() {
        return AppCall.currentPendingCall;
    }
    
    private static boolean setCurrentPendingCall(final AppCall currentPendingCall) {
        synchronized (AppCall.class) {
            final AppCall currentPendingCall2 = getCurrentPendingCall();
            AppCall.currentPendingCall = currentPendingCall;
            return currentPendingCall2 != null;
        }
    }
    
    public UUID getCallId() {
        return this.callId;
    }
    
    public int getRequestCode() {
        return this.requestCode;
    }
    
    public Intent getRequestIntent() {
        return this.requestIntent;
    }
    
    public boolean setPending() {
        return setCurrentPendingCall(this);
    }
    
    public void setRequestCode(final int requestCode) {
        this.requestCode = requestCode;
    }
    
    public void setRequestIntent(final Intent requestIntent) {
        this.requestIntent = requestIntent;
    }
}
