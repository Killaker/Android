package android.support.v7.app;

import android.support.v4.app.*;
import android.content.*;
import android.os.*;

public static class Builder extends android.support.v4.app.NotificationCompat.Builder
{
    public Builder(final Context context) {
        super(context);
    }
    
    @Override
    protected BuilderExtender getExtender() {
        if (Build$VERSION.SDK_INT >= 21) {
            return new LollipopExtender();
        }
        if (Build$VERSION.SDK_INT >= 16) {
            return new JellybeanExtender();
        }
        if (Build$VERSION.SDK_INT >= 14) {
            return new IceCreamSandwichExtender();
        }
        return super.getExtender();
    }
}
