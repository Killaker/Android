package com.facebook.share.internal;

import android.app.*;
import android.os.*;
import com.facebook.internal.*;
import java.util.*;
import com.facebook.*;
import android.content.*;
import android.util.*;

public class LikeDialog extends FacebookDialogBase<LikeContent, Result>
{
    private static final int DEFAULT_REQUEST_CODE = 0;
    private static final String TAG = "LikeDialog";
    
    public LikeDialog(final Activity activity) {
        super(activity, LikeDialog.DEFAULT_REQUEST_CODE);
    }
    
    public LikeDialog(final Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    public LikeDialog(final android.support.v4.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    public LikeDialog(final FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, LikeDialog.DEFAULT_REQUEST_CODE);
    }
    
    public static boolean canShowNativeDialog() {
        return DialogPresenter.canPresentNativeDialogWithFeature(getFeature());
    }
    
    public static boolean canShowWebFallback() {
        return DialogPresenter.canPresentWebFallbackDialogWithFeature(getFeature());
    }
    
    private static Bundle createParameters(final LikeContent likeContent) {
        final Bundle bundle = new Bundle();
        bundle.putString("object_id", likeContent.getObjectId());
        bundle.putString("object_type", likeContent.getObjectType());
        return bundle;
    }
    
    private static DialogFeature getFeature() {
        return LikeDialogFeature.LIKE_DIALOG;
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
                    facebookCallback.onSuccess(new Result(bundle));
                }
            };
        }
        callbackManagerImpl.registerCallback(this.getRequestCode(), (CallbackManagerImpl.Callback)new CallbackManagerImpl.Callback() {
            @Override
            public boolean onActivityResult(final int n, final Intent intent) {
                return ShareInternalUtility.handleActivityResult(LikeDialog.this.getRequestCode(), n, intent, resultProcessor);
            }
        });
    }
    
    private class NativeHandler extends ModeHandler
    {
        public boolean canShow(final LikeContent likeContent) {
            return likeContent != null && LikeDialog.canShowNativeDialog();
        }
        
        public AppCall createAppCall(final LikeContent likeContent) {
            final AppCall baseAppCall = LikeDialog.this.createBaseAppCall();
            DialogPresenter.setupAppCallForNativeDialog(baseAppCall, (DialogPresenter.ParameterProvider)new DialogPresenter.ParameterProvider() {
                @Override
                public Bundle getLegacyParameters() {
                    Log.e("LikeDialog", "Attempting to present the Like Dialog with an outdated Facebook app on the device");
                    return new Bundle();
                }
                
                @Override
                public Bundle getParameters() {
                    return createParameters(likeContent);
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
        public boolean canShow(final LikeContent likeContent) {
            return likeContent != null && LikeDialog.canShowWebFallback();
        }
        
        public AppCall createAppCall(final LikeContent likeContent) {
            final AppCall baseAppCall = LikeDialog.this.createBaseAppCall();
            DialogPresenter.setupAppCallForWebFallbackDialog(baseAppCall, createParameters(likeContent), getFeature());
            return baseAppCall;
        }
    }
}
