package android.support.v4.app;

import android.os.*;
import android.app.*;
import java.util.*;

public static final class Builder
{
    private final Bundle mExtras;
    private final int mIcon;
    private final PendingIntent mIntent;
    private ArrayList<RemoteInput> mRemoteInputs;
    private final CharSequence mTitle;
    
    public Builder(final int n, final CharSequence charSequence, final PendingIntent pendingIntent) {
        this(n, charSequence, pendingIntent, new Bundle());
    }
    
    private Builder(final int mIcon, final CharSequence charSequence, final PendingIntent mIntent, final Bundle mExtras) {
        this.mIcon = mIcon;
        this.mTitle = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
        this.mIntent = mIntent;
        this.mExtras = mExtras;
    }
    
    public Builder(final Action action) {
        this(action.icon, action.title, action.actionIntent, new Bundle(Action.access$300(action)));
    }
    
    public Builder addExtras(final Bundle bundle) {
        if (bundle != null) {
            this.mExtras.putAll(bundle);
        }
        return this;
    }
    
    public Builder addRemoteInput(final RemoteInput remoteInput) {
        if (this.mRemoteInputs == null) {
            this.mRemoteInputs = new ArrayList<RemoteInput>();
        }
        this.mRemoteInputs.add(remoteInput);
        return this;
    }
    
    public Action build() {
        RemoteInput[] array;
        if (this.mRemoteInputs != null) {
            array = this.mRemoteInputs.toArray(new RemoteInput[this.mRemoteInputs.size()]);
        }
        else {
            array = null;
        }
        return new Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, array);
    }
    
    public Builder extend(final Extender extender) {
        extender.extend(this);
        return this;
    }
    
    public Bundle getExtras() {
        return this.mExtras;
    }
}
