package com.facebook.share.widget;

import com.facebook.share.model.*;
import android.app.*;
import android.os.*;
import com.facebook.internal.*;
import java.util.*;
import com.facebook.*;
import com.facebook.share.internal.*;
import android.content.*;
import android.util.*;

public class AppInviteDialog extends FacebookDialogBase<AppInviteContent, Result>
{
    private static final int DEFAULT_REQUEST_CODE = 0;
    private static final String TAG = "AppInviteDialog";
    
    public AppInviteDialog(final Activity activity) {
        super(activity, AppInviteDialog.DEFAULT_REQUEST_CODE);
    }
    
    public AppInviteDialog(final Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    public AppInviteDialog(final android.support.v4.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    private AppInviteDialog(final FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, AppInviteDialog.DEFAULT_REQUEST_CODE);
    }
    
    public static boolean canShow() {
        return canShowNativeDialog() || canShowWebFallback();
    }
    
    private static boolean canShowNativeDialog() {
        return DialogPresenter.canPresentNativeDialogWithFeature(getFeature());
    }
    
    private static boolean canShowWebFallback() {
        return DialogPresenter.canPresentWebFallbackDialogWithFeature(getFeature());
    }
    
    private static Bundle createParameters(final AppInviteContent appInviteContent) {
        final Bundle bundle = new Bundle();
        bundle.putString("app_link_url", appInviteContent.getApplinkUrl());
        bundle.putString("preview_image_url", appInviteContent.getPreviewImageUrl());
        return bundle;
    }
    
    private static DialogFeature getFeature() {
        return AppInviteDialogFeature.APP_INVITES_DIALOG;
    }
    
    public static void show(final Activity activity, final AppInviteContent appInviteContent) {
        ((FacebookDialogBase<AppInviteContent, RESULT>)new AppInviteDialog(activity)).show(appInviteContent);
    }
    
    public static void show(final Fragment fragment, final AppInviteContent appInviteContent) {
        show(new FragmentWrapper(fragment), appInviteContent);
    }
    
    public static void show(final android.support.v4.app.Fragment fragment, final AppInviteContent appInviteContent) {
        show(new FragmentWrapper(fragment), appInviteContent);
    }
    
    private static void show(final FragmentWrapper fragmentWrapper, final AppInviteContent appInviteContent) {
        ((FacebookDialogBase<AppInviteContent, RESULT>)new AppInviteDialog(fragmentWrapper)).show(appInviteContent);
    }
    
    @Override
    protected AppCall createBaseAppCall() {
        return new AppCall(this.getRequestCode());
    }
    
    @Override
    protected List<ModeHandler> getOrderedModeHandlers() {
        final ArrayList<NativeHandler> list = (ArrayList<NativeHandler>)new ArrayList<ModeHandler>();
        list.add(new NativeHandler());
        list.add((NativeHandler)new WebFallbackHandler());
        return (List<ModeHandler>)list;
    }
    
    @Override
    protected void registerCallbackImpl(final CallbackManagerImpl callbackManagerImpl, final FacebookCallback<Result> facebookCallback) {
        ResultProcessor resultProcessor;
        if (facebookCallback == null) {
            resultProcessor = null;
        }
        else {
            resultProcessor = new ResultProcessor(facebookCallback) {
                @Override
                public void onSuccess(final AppCall appCall, final Bundle bundle) {
                    if ("cancel".equalsIgnoreCase(ShareInternalUtility.getNativeDialogCompletionGesture(bundle))) {
                        facebookCallback.onCancel();
                        return;
                    }
                    facebookCallback.onSuccess(new Result(bundle));
                }
            };
        }
        callbackManagerImpl.registerCallback(this.getRequestCode(), (CallbackManagerImpl.Callback)new CallbackManagerImpl.Callback() {
            @Override
            public boolean onActivityResult(final int n, final Intent intent) {
                return ShareInternalUtility.handleActivityResult(AppInviteDialog.this.getRequestCode(), n, intent, resultProcessor);
            }
        });
    }
    
    private class NativeHandler extends ModeHandler
    {
        public boolean canShow(final AppInviteContent appInviteContent) {
            return canShowNativeDialog();
        }
        
        public AppCall createAppCall(final AppInviteContent appInviteContent) {
            final AppCall baseAppCall = AppInviteDialog.this.createBaseAppCall();
            DialogPresenter.setupAppCallForNativeDialog(baseAppCall, (DialogPresenter.ParameterProvider)new DialogPresenter.ParameterProvider() {
                @Override
                public Bundle getLegacyParameters() {
                    Log.e("AppInviteDialog", "Attempting to present the AppInviteDialog with an outdated Facebook app on the device");
                    return new Bundle();
                }
                
                @Override
                public Bundle getParameters() {
                    return createParameters(appInviteContent);
                }
            }, getFeature());
            return baseAppCall;
        }
    }
    
    public static final class Result
    {
        private final Bundle bundle;
        
        public Result(final Bundle bundle) {
            this.bundle = bundle;
        }
        
        public Bundle getData() {
            return this.bundle;
        }
    }
    
    private class WebFallbackHandler extends ModeHandler
    {
        public boolean canShow(final AppInviteContent appInviteContent) {
            return canShowWebFallback();
        }
        
        public AppCall createAppCall(final AppInviteContent appInviteContent) {
            final AppCall baseAppCall = AppInviteDialog.this.createBaseAppCall();
            DialogPresenter.setupAppCallForWebFallbackDialog(baseAppCall, createParameters(appInviteContent), getFeature());
            return baseAppCall;
        }
    }
}
