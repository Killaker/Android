package com.google.android.gms.common;

import android.content.*;

public class UserRecoverableException extends Exception
{
    private final Intent mIntent;
    
    public UserRecoverableException(final String s, final Intent mIntent) {
        super(s);
        this.mIntent = mIntent;
    }
    
    public Intent getIntent() {
        return new Intent(this.mIntent);
    }
}
