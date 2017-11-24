package com.facebook.appevents;

import java.io.*;

private static class SerializationProxyV1 implements Serializable
{
    private static final long serialVersionUID = 6L;
    private final int interruptionCount;
    private final long lastResumeTime;
    private final long lastSuspendTime;
    private final long millisecondsSpentInSession;
    
    SerializationProxyV1(final long lastResumeTime, final long lastSuspendTime, final long millisecondsSpentInSession, final int interruptionCount) {
        this.lastResumeTime = lastResumeTime;
        this.lastSuspendTime = lastSuspendTime;
        this.millisecondsSpentInSession = millisecondsSpentInSession;
        this.interruptionCount = interruptionCount;
    }
    
    private Object readResolve() {
        return new FacebookTimeSpentData(this.lastResumeTime, this.lastSuspendTime, this.millisecondsSpentInSession, this.interruptionCount, (FacebookTimeSpentData$1)null);
    }
}
