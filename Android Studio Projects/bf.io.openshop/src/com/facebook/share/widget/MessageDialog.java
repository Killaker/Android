package com.facebook.share.widget;

import com.facebook.share.*;
import android.app.*;
import com.facebook.share.model.*;
import com.facebook.internal.*;
import java.util.*;
import com.facebook.*;
import android.os.*;
import com.facebook.share.internal.*;

public final class MessageDialog extends FacebookDialogBase<ShareContent, Result> implements Sharer
{
    private static final int DEFAULT_REQUEST_CODE;
    private boolean shouldFailOnDataError;
    
    static {
        DEFAULT_REQUEST_CODE = CallbackManagerImpl.RequestCodeOffset.Message.toRequestCode();
    }
    
    public MessageDialog(final Activity activity) {
        super(activity, MessageDialog.DEFAULT_REQUEST_CODE);
        this.shouldFailOnDataError = false;
        ShareInternalUtility.registerStaticShareCallback(MessageDialog.DEFAULT_REQUEST_CODE);
    }
    
    MessageDialog(final Activity activity, final int n) {
        super(activity, n);
        this.shouldFailOnDataError = false;
        ShareInternalUtility.registerStaticShareCallback(n);
    }
    
    public MessageDialog(final Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    MessageDialog(final Fragment fragment, final int n) {
        this(new FragmentWrapper(fragment), n);
    }
    
    public MessageDialog(final android.support.v4.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    MessageDialog(final android.support.v4.app.Fragment fragment, final int n) {
        this(new FragmentWrapper(fragment), n);
    }
    
    private MessageDialog(final FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, MessageDialog.DEFAULT_REQUEST_CODE);
        this.shouldFailOnDataError = false;
        ShareInternalUtility.registerStaticShareCallback(MessageDialog.DEFAULT_REQUEST_CODE);
    }
    
    private MessageDialog(final FragmentWrapper fragmentWrapper, final int n) {
        super(fragmentWrapper, n);
        this.shouldFailOnDataError = false;
        ShareInternalUtility.registerStaticShareCallback(n);
    }
    
    public static boolean canShow(final Class<? extends ShareContent> clazz) {
        final DialogFeature feature = getFeature(clazz);
        return feature != null && DialogPresenter.canPresentNativeDialogWithFeature(feature);
    }
    
    private static DialogFeature getFeature(final Class<? extends ShareContent> clazz) {
        if (ShareLinkContent.class.isAssignableFrom(clazz)) {
            return MessageDialogFeature.MESSAGE_DIALOG;
        }
        if (SharePhotoContent.class.isAssignableFrom(clazz)) {
            return MessageDialogFeature.PHOTOS;
        }
        if (ShareVideoContent.class.isAssignableFrom(clazz)) {
            return MessageDialogFeature.VIDEO;
        }
        if (ShareOpenGraphContent.class.isAssignableFrom(clazz)) {
            return OpenGraphMessageDialogFeature.OG_MESSAGE_DIALOG;
        }
        return null;
    }
    
    public static void show(final Activity activity, final ShareContent shareContent) {
        ((FacebookDialogBase<ShareContent, RESULT>)new MessageDialog(activity)).show(shareContent);
    }
    
    public static void show(final Fragment fragment, final ShareContent shareContent) {
        show(new FragmentWrapper(fragment), shareContent);
    }
    
    public static void show(final android.support.v4.app.Fragment fragment, final ShareContent shareContent) {
        show(new FragmentWrapper(fragment), shareContent);
    }
    
    private static void show(final FragmentWrapper fragmentWrapper, final ShareContent shareContent) {
        ((FacebookDialogBase<ShareContent, RESULT>)new MessageDialog(fragmentWrapper)).show(shareContent);
    }
    
    @Override
    protected AppCall createBaseAppCall() {
        return new AppCall(this.getRequestCode());
    }
    
    @Override
    protected List<ModeHandler> getOrderedModeHandlers() {
        final ArrayList<NativeHandler> list = (ArrayList<NativeHandler>)new ArrayList<ModeHandler>();
        list.add(new NativeHandler());
        return (List<ModeHandler>)list;
    }
    
    @Override
    public boolean getShouldFailOnDataError() {
        return this.shouldFailOnDataError;
    }
    
    @Override
    protected void registerCallbackImpl(final CallbackManagerImpl callbackManagerImpl, final FacebookCallback<Result> facebookCallback) {
        ShareInternalUtility.registerSharerCallback(this.getRequestCode(), callbackManagerImpl, facebookCallback);
    }
    
    @Override
    public void setShouldFailOnDataError(final boolean shouldFailOnDataError) {
        this.shouldFailOnDataError = shouldFailOnDataError;
    }
    
    private class NativeHandler extends ModeHandler
    {
        public boolean canShow(final ShareContent shareContent) {
            return shareContent != null && MessageDialog.canShow(shareContent.getClass());
        }
        
        public AppCall createAppCall(final ShareContent shareContent) {
            ShareContentValidation.validateForMessage(shareContent);
            final AppCall baseAppCall = MessageDialog.this.createBaseAppCall();
            final boolean shouldFailOnDataError = MessageDialog.this.getShouldFailOnDataError();
            MessageDialog.this.getActivityContext();
            DialogPresenter.setupAppCallForNativeDialog(baseAppCall, (DialogPresenter.ParameterProvider)new DialogPresenter.ParameterProvider() {
                @Override
                public Bundle getLegacyParameters() {
                    return LegacyNativeDialogParameters.create(baseAppCall.getCallId(), shareContent, shouldFailOnDataError);
                }
                
                @Override
                public Bundle getParameters() {
                    return NativeDialogParameters.create(baseAppCall.getCallId(), shareContent, shouldFailOnDataError);
                }
            }, getFeature(shareContent.getClass()));
            return baseAppCall;
        }
    }
}
