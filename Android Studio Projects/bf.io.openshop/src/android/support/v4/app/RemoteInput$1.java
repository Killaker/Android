package android.support.v4.app;

import android.os.*;

static final class RemoteInput$1 implements Factory {
    public RemoteInput build(final String s, final CharSequence charSequence, final CharSequence[] array, final boolean b, final Bundle bundle) {
        return new RemoteInput(s, charSequence, array, b, bundle, null);
    }
    
    public RemoteInput[] newArray(final int n) {
        return new RemoteInput[n];
    }
}