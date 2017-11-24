package android.support.v4.net;

import java.net.*;

interface TrafficStatsCompatImpl
{
    void clearThreadStatsTag();
    
    int getThreadStatsTag();
    
    void incrementOperationCount(final int p0);
    
    void incrementOperationCount(final int p0, final int p1);
    
    void setThreadStatsTag(final int p0);
    
    void tagSocket(final Socket p0) throws SocketException;
    
    void untagSocket(final Socket p0) throws SocketException;
}
