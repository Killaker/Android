package android.support.v4.media.session;

import android.os.*;

private static final class Command
{
    public final String command;
    public final Bundle extras;
    public final ResultReceiver stub;
    
    public Command(final String command, final Bundle extras, final ResultReceiver stub) {
        this.command = command;
        this.extras = extras;
        this.stub = stub;
    }
}
