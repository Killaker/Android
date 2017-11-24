package android.support.v4.net;

import java.net.*;

static class BaseTrafficStatsCompatImpl implements TrafficStatsCompatImpl
{
    private ThreadLocal<SocketTags> mThreadSocketTags;
    
    BaseTrafficStatsCompatImpl() {
        this.mThreadSocketTags = new ThreadLocal<SocketTags>() {
            @Override
            protected SocketTags initialValue() {
                return new SocketTags();
            }
        };
    }
    
    @Override
    public void clearThreadStatsTag() {
        this.mThreadSocketTags.get().statsTag = -1;
    }
    
    @Override
    public int getThreadStatsTag() {
        return this.mThreadSocketTags.get().statsTag;
    }
    
    @Override
    public void incrementOperationCount(final int n) {
    }
    
    @Override
    public void incrementOperationCount(final int n, final int n2) {
    }
    
    @Override
    public void setThreadStatsTag(final int statsTag) {
        this.mThreadSocketTags.get().statsTag = statsTag;
    }
    
    @Override
    public void tagSocket(final Socket socket) {
    }
    
    @Override
    public void untagSocket(final Socket socket) {
    }
    
    private static class SocketTags
    {
        public int statsTag;
        
        private SocketTags() {
            this.statsTag = -1;
        }
    }
}
