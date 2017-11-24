package com.facebook.appevents;

private static class FlushStatistics
{
    public int numEvents;
    public FlushResult result;
    
    private FlushStatistics() {
        this.numEvents = 0;
        this.result = FlushResult.SUCCESS;
    }
}
