package com.facebook.internal;

import com.facebook.*;

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
