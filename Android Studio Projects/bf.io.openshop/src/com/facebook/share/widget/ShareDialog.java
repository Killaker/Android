package com.facebook.share.widget;

import com.facebook.share.*;
import android.app.*;
import android.content.*;
import com.facebook.share.model.*;
import com.facebook.appevents.*;
import android.os.*;
import com.facebook.internal.*;
import java.util.*;
import com.facebook.*;
import com.facebook.share.internal.*;

public final class ShareDialog extends FacebookDialogBase<ShareContent, Result> implements Sharer
{
    private static final int DEFAULT_REQUEST_CODE = 0;
    private static final String FEED_DIALOG = "feed";
    private static final String WEB_OG_SHARE_DIALOG = "share_open_graph";
    private static final String WEB_SHARE_DIALOG = "share";
    private boolean isAutomaticMode;
    private boolean shouldFailOnDataError;
    
    public ShareDialog(final Activity activity) {
        super(activity, ShareDialog.DEFAULT_REQUEST_CODE);
        this.shouldFailOnDataError = false;
        this.isAutomaticMode = true;
        ShareInternalUtility.registerStaticShareCallback(ShareDialog.DEFAULT_REQUEST_CODE);
    }
    
    ShareDialog(final Activity activity, final int n) {
        super(activity, n);
        this.shouldFailOnDataError = false;
        this.isAutomaticMode = true;
        ShareInternalUtility.registerStaticShareCallback(n);
    }
    
    public ShareDialog(final Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    ShareDialog(final Fragment fragment, final int n) {
        this(new FragmentWrapper(fragment), n);
    }
    
    public ShareDialog(final android.support.v4.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    ShareDialog(final android.support.v4.app.Fragment fragment, final int n) {
        this(new FragmentWrapper(fragment), n);
    }
    
    private ShareDialog(final FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, ShareDialog.DEFAULT_REQUEST_CODE);
        this.shouldFailOnDataError = false;
        this.isAutomaticMode = true;
        ShareInternalUtility.registerStaticShareCallback(ShareDialog.DEFAULT_REQUEST_CODE);
    }
    
    private ShareDialog(final FragmentWrapper fragmentWrapper, final int n) {
        super(fragmentWrapper, n);
        this.shouldFailOnDataError = false;
        this.isAutomaticMode = true;
        ShareInternalUtility.registerStaticShareCallback(n);
    }
    
    public static boolean canShow(final Class<? extends ShareContent> clazz) {
        return canShowWebTypeCheck(clazz) || canShowNative(clazz);
    }
    
    private static boolean canShowNative(final Class<? extends ShareContent> clazz) {
        final DialogFeature feature = getFeature(clazz);
        return feature != null && DialogPresenter.canPresentNativeDialogWithFeature(feature);
    }
    
    private static boolean canShowWebTypeCheck(final Class<? extends ShareContent> clazz) {
        return ShareLinkContent.class.isAssignableFrom(clazz) || ShareOpenGraphContent.class.isAssignableFrom(clazz);
    }
    
    private static DialogFeature getFeature(final Class<? extends ShareContent> clazz) {
        if (ShareLinkContent.class.isAssignableFrom(clazz)) {
            return ShareDialogFeature.SHARE_DIALOG;
        }
        if (SharePhotoContent.class.isAssignableFrom(clazz)) {
            return ShareDialogFeature.PHOTOS;
        }
        if (ShareVideoContent.class.isAssignableFrom(clazz)) {
            return ShareDialogFeature.VIDEO;
        }
        if (ShareOpenGraphContent.class.isAssignableFrom(clazz)) {
            return OpenGraphActionDialogFeature.OG_ACTION_DIALOG;
        }
        return null;
    }
    
    private void logDialogShare(final Context context, final ShareContent shareContent, Mode automatic) {
        if (this.isAutomaticMode) {
            automatic = Mode.AUTOMATIC;
        }
        String s = null;
        switch (automatic) {
            default: {
                s = "unknown";
                break;
            }
            case AUTOMATIC: {
                s = "automatic";
                break;
            }
            case WEB: {
                s = "web";
                break;
            }
            case NATIVE: {
                s = "native";
                break;
            }
        }
        final DialogFeature feature = getFeature(shareContent.getClass());
        String s2;
        if (feature == ShareDialogFeature.SHARE_DIALOG) {
            s2 = "status";
        }
        else if (feature == ShareDialogFeature.PHOTOS) {
            s2 = "photo";
        }
        else if (feature == ShareDialogFeature.VIDEO) {
            s2 = "video";
        }
        else if (feature == OpenGraphActionDialogFeature.OG_ACTION_DIALOG) {
            s2 = "open_graph";
        }
        else {
            s2 = "unknown";
        }
        final AppEventsLogger logger = AppEventsLogger.newLogger(context);
        final Bundle bundle = new Bundle();
        bundle.putString("fb_share_dialog_show", s);
        bundle.putString("fb_share_dialog_content_type", s2);
        logger.logSdkEvent("fb_share_dialog_show", null, bundle);
    }
    
    public static void show(final Activity activity, final ShareContent shareContent) {
        ((FacebookDialogBase<ShareContent, RESULT>)new ShareDialog(activity)).show(shareContent);
    }
    
    public static void show(final Fragment fragment, final ShareContent shareContent) {
        show(new FragmentWrapper(fragment), shareContent);
    }
    
    public static void show(final android.support.v4.app.Fragment fragment, final ShareContent shareContent) {
        show(new FragmentWrapper(fragment), shareContent);
    }
    
    private static void show(final FragmentWrapper fragmentWrapper, final ShareContent shareContent) {
        ((FacebookDialogBase<ShareContent, RESULT>)new ShareDialog(fragmentWrapper)).show(shareContent);
    }
    
    public boolean canShow(final ShareContent shareContent, Mode base_AUTOMATIC_MODE) {
        if (base_AUTOMATIC_MODE == Mode.AUTOMATIC) {
            base_AUTOMATIC_MODE = (Mode)ShareDialog.BASE_AUTOMATIC_MODE;
        }
        return ((FacebookDialogBase<ShareContent, RESULT>)this).canShowImpl(shareContent, base_AUTOMATIC_MODE);
    }
    
    @Override
    protected AppCall createBaseAppCall() {
        return new AppCall(this.getRequestCode());
    }
    
    @Override
    protected List<ModeHandler> getOrderedModeHandlers() {
        final ArrayList<NativeHandler> list = (ArrayList<NativeHandler>)new ArrayList<ModeHandler>();
        list.add(new NativeHandler());
        list.add((NativeHandler)new FeedHandler());
        list.add((NativeHandler)new WebShareHandler());
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
    
    public void show(final ShareContent shareContent, Mode base_AUTOMATIC_MODE) {
        this.isAutomaticMode = (base_AUTOMATIC_MODE == Mode.AUTOMATIC);
        if (this.isAutomaticMode) {
            base_AUTOMATIC_MODE = (Mode)ShareDialog.BASE_AUTOMATIC_MODE;
        }
        ((FacebookDialogBase<ShareContent, RESULT>)this).showImpl(shareContent, base_AUTOMATIC_MODE);
    }
    
    private class FeedHandler extends ModeHandler
    {
        public boolean canShow(final ShareContent shareContent) {
            return shareContent instanceof ShareLinkContent || shareContent instanceof ShareFeedContent;
        }
        
        public AppCall createAppCall(final ShareContent shareContent) {
            ShareDialog.this.logDialogShare((Context)ShareDialog.this.getActivityContext(), shareContent, Mode.FEED);
            final AppCall baseAppCall = ShareDialog.this.createBaseAppCall();
            Bundle bundle;
            if (shareContent instanceof ShareLinkContent) {
                final ShareLinkContent shareLinkContent = (ShareLinkContent)shareContent;
                ShareContentValidation.validateForWebShare(shareLinkContent);
                bundle = WebDialogParameters.createForFeed(shareLinkContent);
            }
            else {
                bundle = WebDialogParameters.createForFeed((ShareFeedContent)shareContent);
            }
            DialogPresenter.setupAppCallForWebDialog(baseAppCall, "feed", bundle);
            return baseAppCall;
        }
        
        @Override
        public Object getMode() {
            return Mode.FEED;
        }
    }
    
    public enum Mode
    {
        AUTOMATIC, 
        FEED, 
        NATIVE, 
        WEB;
    }
    
    private class NativeHandler extends ModeHandler
    {
        public boolean canShow(final ShareContent shareContent) {
            return shareContent != null && canShowNative(shareContent.getClass());
        }
        
        public AppCall createAppCall(final ShareContent shareContent) {
            ShareDialog.this.logDialogShare((Context)ShareDialog.this.getActivityContext(), shareContent, Mode.NATIVE);
            ShareContentValidation.validateForNativeShare(shareContent);
            final AppCall baseAppCall = ShareDialog.this.createBaseAppCall();
            DialogPresenter.setupAppCallForNativeDialog(baseAppCall, (DialogPresenter.ParameterProvider)new DialogPresenter.ParameterProvider() {
                final /* synthetic */ boolean val$shouldFailOnDataError = ShareDialog.this.getShouldFailOnDataError();
                
                @Override
                public Bundle getLegacyParameters() {
                    return LegacyNativeDialogParameters.create(baseAppCall.getCallId(), shareContent, this.val$shouldFailOnDataError);
                }
                
                @Override
                public Bundle getParameters() {
                    return NativeDialogParameters.create(baseAppCall.getCallId(), shareContent, this.val$shouldFailOnDataError);
                }
            }, getFeature(shareContent.getClass()));
            return baseAppCall;
        }
        
        @Override
        public Object getMode() {
            return Mode.NATIVE;
        }
    }
    
    private class WebShareHandler extends ModeHandler
    {
        private String getActionName(final ShareContent shareContent) {
            if (shareContent instanceof ShareLinkContent) {
                return "share";
            }
            if (shareContent instanceof ShareOpenGraphContent) {
                return "share_open_graph";
            }
            return null;
        }
        
        public boolean canShow(final ShareContent shareContent) {
            return shareContent != null && canShowWebTypeCheck(shareContent.getClass());
        }
        
        public AppCall createAppCall(final ShareContent shareContent) {
            ShareDialog.this.logDialogShare((Context)ShareDialog.this.getActivityContext(), shareContent, Mode.WEB);
            final AppCall baseAppCall = ShareDialog.this.createBaseAppCall();
            ShareContentValidation.validateForWebShare(shareContent);
            Bundle bundle;
            if (shareContent instanceof ShareLinkContent) {
                bundle = WebDialogParameters.create((ShareLinkContent)shareContent);
            }
            else {
                bundle = WebDialogParameters.create((ShareOpenGraphContent)shareContent);
            }
            DialogPresenter.setupAppCallForWebDialog(baseAppCall, this.getActionName(shareContent), bundle);
            return baseAppCall;
        }
        
        @Override
        public Object getMode() {
            return Mode.WEB;
        }
    }
}
