package com.facebook.login;

import com.facebook.internal.*;
import android.app.*;
import android.content.*;

private static class FragmentStartActivityDelegate implements StartActivityDelegate
{
    private final FragmentWrapper fragment;
    
    FragmentStartActivityDelegate(final FragmentWrapper fragment) {
        Validate.notNull(fragment, "fragment");
        this.fragment = fragment;
    }
    
    @Override
    public Activity getActivityContext() {
        return this.fragment.getActivity();
    }
    
    @Override
    public void startActivityForResult(final Intent intent, final int n) {
        this.fragment.startActivityForResult(intent, n);
    }
}
