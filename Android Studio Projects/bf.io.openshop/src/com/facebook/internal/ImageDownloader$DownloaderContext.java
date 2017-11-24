package com.facebook.internal;

private static class DownloaderContext
{
    boolean isCancelled;
    ImageRequest request;
    WorkQueue.WorkItem workItem;
}
