package com.facebook.messenger;

import java.util.*;

public class MessengerThreadParams
{
    public final String metadata;
    public final Origin origin;
    public final List<String> participants;
    public final String threadToken;
    
    public MessengerThreadParams(final Origin origin, final String threadToken, final String metadata, final List<String> participants) {
        this.threadToken = threadToken;
        this.metadata = metadata;
        this.participants = participants;
        this.origin = origin;
    }
    
    public enum Origin
    {
        COMPOSE_FLOW, 
        REPLY_FLOW, 
        UNKNOWN;
    }
}
