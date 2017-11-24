package com.facebook.internal;

public interface WorkItem
{
    boolean cancel();
    
    boolean isRunning();
    
    void moveToFront();
}
