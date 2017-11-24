package android.support.v4.app;

import java.io.*;
import android.view.*;
import android.support.annotation.*;
import android.app.*;
import android.content.*;
import android.os.*;

class HostCallbacks extends FragmentHostCallback<FragmentActivity>
{
    public HostCallbacks() {
        super(FragmentActivity.this);
    }
    
    public void onAttachFragment(final Fragment fragment) {
        FragmentActivity.this.onAttachFragment(fragment);
    }
    
    @Override
    public void onDump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        FragmentActivity.this.dump(s, fileDescriptor, printWriter, array);
    }
    
    @Nullable
    @Override
    public View onFindViewById(final int n) {
        return FragmentActivity.this.findViewById(n);
    }
    
    @Override
    public FragmentActivity onGetHost() {
        return FragmentActivity.this;
    }
    
    @Override
    public LayoutInflater onGetLayoutInflater() {
        return FragmentActivity.this.getLayoutInflater().cloneInContext((Context)FragmentActivity.this);
    }
    
    @Override
    public int onGetWindowAnimations() {
        final Window window = FragmentActivity.this.getWindow();
        if (window == null) {
            return 0;
        }
        return window.getAttributes().windowAnimations;
    }
    
    @Override
    public boolean onHasView() {
        final Window window = FragmentActivity.this.getWindow();
        return window != null && window.peekDecorView() != null;
    }
    
    @Override
    public boolean onHasWindowAnimations() {
        return FragmentActivity.this.getWindow() != null;
    }
    
    @Override
    public void onRequestPermissionsFromFragment(@NonNull final Fragment fragment, @NonNull final String[] array, final int n) {
        FragmentActivity.access$000(FragmentActivity.this, fragment, array, n);
    }
    
    @Override
    public boolean onShouldSaveFragmentState(final Fragment fragment) {
        return !FragmentActivity.this.isFinishing();
    }
    
    @Override
    public boolean onShouldShowRequestPermissionRationale(@NonNull final String s) {
        return ActivityCompat.shouldShowRequestPermissionRationale(FragmentActivity.this, s);
    }
    
    @Override
    public void onStartActivityFromFragment(final Fragment fragment, final Intent intent, final int n) {
        FragmentActivity.this.startActivityFromFragment(fragment, intent, n);
    }
    
    @Override
    public void onStartActivityFromFragment(final Fragment fragment, final Intent intent, final int n, @Nullable final Bundle bundle) {
        FragmentActivity.this.startActivityFromFragment(fragment, intent, n, bundle);
    }
    
    @Override
    public void onSupportInvalidateOptionsMenu() {
        FragmentActivity.this.supportInvalidateOptionsMenu();
    }
}
