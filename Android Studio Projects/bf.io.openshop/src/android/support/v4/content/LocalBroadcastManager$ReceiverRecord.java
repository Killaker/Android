package android.support.v4.content;

import android.content.*;

private static class ReceiverRecord
{
    boolean broadcasting;
    final IntentFilter filter;
    final BroadcastReceiver receiver;
    
    ReceiverRecord(final IntentFilter filter, final BroadcastReceiver receiver) {
        this.filter = filter;
        this.receiver = receiver;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("Receiver{");
        sb.append(this.receiver);
        sb.append(" filter=");
        sb.append(this.filter);
        sb.append("}");
        return sb.toString();
    }
}
