package com.facebook.login;

import android.app.*;
import android.os.*;
import android.content.*;
import android.support.v4.app.*;
import android.support.annotation.*;
import android.view.*;
import com.facebook.*;
import android.util.*;

public class LoginFragment extends Fragment
{
    static final String EXTRA_REQUEST = "request";
    private static final String NULL_CALLING_PKG_ERROR_MSG = "Cannot call LoginFragment with a null calling package. This can occur if the launchMode of the caller is singleInstance.";
    static final String RESULT_KEY = "com.facebook.LoginFragment:Result";
    private static final String SAVED_LOGIN_CLIENT = "loginClient";
    private static final String TAG = "LoginFragment";
    private String callingPackage;
    private LoginClient loginClient;
    private LoginClient.Request request;
    
    private void initializeCallingPackage(final Activity activity) {
        final ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity == null) {
            return;
        }
        this.callingPackage = callingActivity.getPackageName();
    }
    
    private void onLoginClientCompleted(final LoginClient.Result result) {
        this.request = null;
        int n;
        if (result.code == LoginClient.Result.Code.CANCEL) {
            n = 0;
        }
        else {
            n = -1;
        }
        final Bundle bundle = new Bundle();
        bundle.putParcelable("com.facebook.LoginFragment:Result", (Parcelable)result);
        final Intent intent = new Intent();
        intent.putExtras(bundle);
        if (this.isAdded()) {
            this.getActivity().setResult(n, intent);
            this.getActivity().finish();
        }
    }
    
    LoginClient getLoginClient() {
        return this.loginClient;
    }
    
    @Override
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        this.loginClient.onActivityResult(n, n2, intent);
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            (this.loginClient = (LoginClient)bundle.getParcelable("loginClient")).setFragment(this);
        }
        else {
            this.loginClient = new LoginClient(this);
        }
        this.loginClient.setOnCompletedListener((LoginClient.OnCompletedListener)new LoginClient.OnCompletedListener() {
            @Override
            public void onCompleted(final Result result) {
                LoginFragment.this.onLoginClientCompleted(result);
            }
        });
        final FragmentActivity activity = this.getActivity();
        if (activity != null) {
            this.initializeCallingPackage(activity);
            if (activity.getIntent() != null) {
                final Intent intent = activity.getIntent();
                intent.setExtrasClassLoader(LoginClient.Request.class.getClassLoader());
                this.request = (LoginClient.Request)intent.getParcelableExtra("request");
            }
        }
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, @Nullable final Bundle bundle) {
        final View inflate = layoutInflater.inflate(R.layout.com_facebook_login_fragment, viewGroup, false);
        this.loginClient.setBackgroundProcessingListener((LoginClient.BackgroundProcessingListener)new LoginClient.BackgroundProcessingListener() {
            @Override
            public void onBackgroundProcessingStarted() {
                inflate.findViewById(R.id.com_facebook_login_activity_progress_bar).setVisibility(0);
            }
            
            @Override
            public void onBackgroundProcessingStopped() {
                inflate.findViewById(R.id.com_facebook_login_activity_progress_bar).setVisibility(8);
            }
        });
        return inflate;
    }
    
    @Override
    public void onDestroy() {
        this.loginClient.cancelCurrentHandler();
        super.onDestroy();
    }
    
    @Override
    public void onPause() {
        super.onPause();
        this.getActivity().findViewById(R.id.com_facebook_login_activity_progress_bar).setVisibility(8);
    }
    
    @Override
    public void onResume() {
        super.onResume();
        if (this.callingPackage == null) {
            Log.e("LoginFragment", "Cannot call LoginFragment with a null calling package. This can occur if the launchMode of the caller is singleInstance.");
            this.getActivity().finish();
            return;
        }
        this.loginClient.startOrContinueAuth(this.request);
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("loginClient", (Parcelable)this.loginClient);
    }
}
