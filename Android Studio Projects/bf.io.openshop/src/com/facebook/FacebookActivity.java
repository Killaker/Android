package com.facebook;

import android.os.*;
import android.content.*;
import android.content.res.*;
import com.facebook.internal.*;
import com.facebook.login.*;
import android.support.v4.app.*;

public class FacebookActivity extends FragmentActivity
{
    private static String FRAGMENT_TAG;
    public static String PASS_THROUGH_CANCEL_ACTION;
    private Fragment singleFragment;
    
    static {
        FacebookActivity.PASS_THROUGH_CANCEL_ACTION = "PassThrough";
        FacebookActivity.FRAGMENT_TAG = "SingleFragment";
    }
    
    private void handlePassThroughError() {
        final Intent intent = this.getIntent();
        this.setResult(0, NativeProtocol.createProtocolResultIntent(intent, null, NativeProtocol.getExceptionFromErrorData(NativeProtocol.getMethodArgumentsFromIntent(intent))));
        this.finish();
    }
    
    public Fragment getCurrentFragment() {
        return this.singleFragment;
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.singleFragment != null) {
            this.singleFragment.onConfigurationChanged(configuration);
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.com_facebook_activity_layout);
        final Intent intent = this.getIntent();
        if (FacebookActivity.PASS_THROUGH_CANCEL_ACTION.equals(intent.getAction())) {
            this.handlePassThroughError();
            return;
        }
        final FragmentManager supportFragmentManager = this.getSupportFragmentManager();
        Fragment fragmentByTag = supportFragmentManager.findFragmentByTag(FacebookActivity.FRAGMENT_TAG);
        if (fragmentByTag == null) {
            if ("FacebookDialogFragment".equals(intent.getAction())) {
                final FacebookDialogFragment facebookDialogFragment = new FacebookDialogFragment();
                facebookDialogFragment.setRetainInstance(true);
                facebookDialogFragment.show(supportFragmentManager, FacebookActivity.FRAGMENT_TAG);
                fragmentByTag = facebookDialogFragment;
            }
            else {
                fragmentByTag = new LoginFragment();
                fragmentByTag.setRetainInstance(true);
                supportFragmentManager.beginTransaction().add(R.id.com_facebook_fragment_container, fragmentByTag, FacebookActivity.FRAGMENT_TAG).commit();
            }
        }
        this.singleFragment = fragmentByTag;
    }
}
