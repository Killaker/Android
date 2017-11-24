package android.support.v4.net;

class TrafficStatsCompat$BaseTrafficStatsCompatImpl$1 extends ThreadLocal<SocketTags> {
    @Override
    protected SocketTags initialValue() {
        return new SocketTags();
    }
}