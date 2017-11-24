package android.support.v4.net;

import java.net.*;

static class IcsTrafficStatsCompatImpl implements TrafficStatsCompatImpl
{
    @Override
    public void clearThreadStatsTag() {
        TrafficStatsCompatIcs.clearThreadStatsTag();
    }
    
    @Override
    public int getThreadStatsTag() {
        return TrafficStatsCompatIcs.getThreadStatsTag();
    }
    
    @Override
    public void incrementOperationCount(final int n) {
        TrafficStatsCompatIcs.incrementOperationCount(n);
    }
    
    @Override
    public void incrementOperationCount(final int n, final int n2) {
        TrafficStatsCompatIcs.incrementOperationCount(n, n2);
    }
    
    @Override
    public void setThreadStatsTag(final int threadStatsTag) {
        TrafficStatsCompatIcs.setThreadStatsTag(threadStatsTag);
    }
    
    @Override
    public void tagSocket(final Socket socket) throws SocketException {
        TrafficStatsCompatIcs.tagSocket(socket);
    }
    
    @Override
    public void untagSocket(final Socket socket) throws SocketException {
        TrafficStatsCompatIcs.untagSocket(socket);
    }
}
