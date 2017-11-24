package com.facebook.share.widget;

import com.facebook.share.model.*;
import android.app.*;
import java.util.*;
import com.facebook.*;
import android.os.*;
import android.content.*;
import com.facebook.share.internal.*;
import com.facebook.internal.*;

public class CreateAppGroupDialog extends FacebookDialogBase<AppGroupCreationContent, Result>
{
    private static final int DEFAULT_REQUEST_CODE = 0;
    private static final String GAME_GROUP_CREATION_DIALOG = "game_group_create";
    
    public CreateAppGroupDialog(final Activity activity) {
        super(activity, CreateAppGroupDialog.DEFAULT_REQUEST_CODE);
    }
    
    public CreateAppGroupDialog(final Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    public CreateAppGroupDialog(final android.support.v4.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    private CreateAppGroupDialog(final FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, CreateAppGroupDialog.DEFAULT_REQUEST_CODE);
    }
    
    public static boolean canShow() {
        return true;
    }
    
    public static void show(final Activity activity, final AppGroupCreationContent appGroupCreationContent) {
        ((FacebookDialogBase<AppGroupCreationContent, RESULT>)new CreateAppGroupDialog(activity)).show(appGroupCreationContent);
    }
    
    public static void show(final Fragment fragment, final AppGroupCreationContent appGroupCreationContent) {
        show(new FragmentWrapper(fragment), appGroupCreationContent);
    }
    
    public static void show(final android.support.v4.app.Fragment fragment, final AppGroupCreationContent appGroupCreationContent) {
        show(new FragmentWrapper(fragment), appGroupCreationContent);
    }
    
    private static void show(final FragmentWrapper fragmentWrapper, final AppGroupCreationContent appGroupCreationContent) {
        ((FacebookDialogBase<AppGroupCreationContent, RESULT>)new CreateAppGroupDialog(fragmentWrapper)).show(appGroupCreationContent);
    }
    
    @Override
    protected AppCall createBaseAppCall() {
        return new AppCall(this.getRequestCode());
    }
    
    @Override
    protected List<ModeHandler> getOrderedModeHandlers() {
        final ArrayList<WebHandler> list = (ArrayList<WebHandler>)new ArrayList<ModeHandler>();
        list.add(new WebHandler());
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
                    facebookCallback.onSuccess(new Result(bundle.getString("id")));
                }
            };
        }
        callbackManagerImpl.registerCallback(this.getRequestCode(), (CallbackManagerImpl.Callback)new CallbackManagerImpl.Callback() {
            @Override
            public boolean onActivityResult(final int n, final Intent intent) {
                return ShareInternalUtility.handleActivityResult(CreateAppGroupDialog.this.getRequestCode(), n, intent, resultProcessor);
            }
        });
    }
    
    public static final class Result
    {
        private final String id;
        
        private Result(final String id) {
            this.id = id;
        }
        
        public String getId() {
            return this.id;
        }
    }
    
    private class WebHandler extends ModeHandler
    {
        public boolean canShow(final AppGroupCreationContent appGroupCreationContent) {
            return true;
        }
        
        public AppCall createAppCall(final AppGroupCreationContent appGroupCreationContent) {
            final AppCall baseAppCall = CreateAppGroupDialog.this.createBaseAppCall();
            DialogPresenter.setupAppCallForWebDialog(baseAppCall, "game_group_create", WebDialogParameters.create(appGroupCreationContent));
            return baseAppCall;
        }
    }
}
