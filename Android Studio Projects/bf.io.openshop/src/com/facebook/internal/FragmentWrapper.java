package com.facebook.internal;

import android.app.*;
import android.content.*;

public class FragmentWrapper
{
    private Fragment nativeFragment;
    private android.support.v4.app.Fragment supportFragment;
    
    public FragmentWrapper(final Fragment nativeFragment) {
        Validate.notNull(nativeFragment, "fragment");
        this.nativeFragment = nativeFragment;
    }
    
    public FragmentWrapper(final android.support.v4.app.Fragment supportFragment) {
        Validate.notNull(supportFragment, "fragment");
        this.supportFragment = supportFragment;
    }
    
    public final Activity getActivity() {
        if (this.supportFragment != null) {
            return this.supportFragment.getActivity();
        }
        return this.nativeFragment.getActivity();
    }
    
    public Fragment getNativeFragment() {
        return this.nativeFragment;
    }
    
    public android.support.v4.app.Fragment getSupportFragment() {
        return this.supportFragment;
    }
    
    public void startActivityForResult(final Intent intent, final int n) {
        if (this.supportFragment != null) {
            this.supportFragment.startActivityForResult(intent, n);
            return;
        }
        this.nativeFragment.startActivityForResult(intent, n);
    }
}
