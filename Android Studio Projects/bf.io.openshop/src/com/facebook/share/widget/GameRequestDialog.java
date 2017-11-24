package com.facebook.share.widget;

import com.facebook.share.model.*;
import android.app.*;
import java.util.*;
import com.facebook.*;
import android.os.*;
import android.content.*;
import com.facebook.share.internal.*;
import com.facebook.internal.*;

public class GameRequestDialog extends FacebookDialogBase<GameRequestContent, Result>
{
    private static final int DEFAULT_REQUEST_CODE = 0;
    private static final String GAME_REQUEST_DIALOG = "apprequests";
    
    public GameRequestDialog(final Activity activity) {
        super(activity, GameRequestDialog.DEFAULT_REQUEST_CODE);
    }
    
    public GameRequestDialog(final Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    public GameRequestDialog(final android.support.v4.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    private GameRequestDialog(final FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, GameRequestDialog.DEFAULT_REQUEST_CODE);
    }
    
    public static boolean canShow() {
        return true;
    }
    
    public static void show(final Activity activity, final GameRequestContent gameRequestContent) {
        ((FacebookDialogBase<GameRequestContent, RESULT>)new GameRequestDialog(activity)).show(gameRequestContent);
    }
    
    public static void show(final Fragment fragment, final GameRequestContent gameRequestContent) {
        show(new FragmentWrapper(fragment), gameRequestContent);
    }
    
    public static void show(final android.support.v4.app.Fragment fragment, final GameRequestContent gameRequestContent) {
        show(new FragmentWrapper(fragment), gameRequestContent);
    }
    
    private static void show(final FragmentWrapper fragmentWrapper, final GameRequestContent gameRequestContent) {
        ((FacebookDialogBase<GameRequestContent, RESULT>)new GameRequestDialog(fragmentWrapper)).show(gameRequestContent);
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
                    if (bundle != null) {
                        facebookCallback.onSuccess(new Result(bundle));
                        return;
                    }
                    this.onCancel(appCall);
                }
            };
        }
        callbackManagerImpl.registerCallback(this.getRequestCode(), (CallbackManagerImpl.Callback)new CallbackManagerImpl.Callback() {
            @Override
            public boolean onActivityResult(final int n, final Intent intent) {
                return ShareInternalUtility.handleActivityResult(GameRequestDialog.this.getRequestCode(), n, intent, resultProcessor);
            }
        });
    }
    
    public static final class Result
    {
        String requestId;
        List<String> to;
        
        private Result(final Bundle bundle) {
            this.requestId = bundle.getString("request");
            this.to = new ArrayList<String>();
            while (bundle.containsKey(String.format("to[%d]", this.to.size()))) {
                this.to.add(bundle.getString(String.format("to[%d]", this.to.size())));
            }
        }
        
        public String getRequestId() {
            return this.requestId;
        }
        
        public List<String> getRequestRecipients() {
            return this.to;
        }
    }
    
    private class WebHandler extends ModeHandler
    {
        public boolean canShow(final GameRequestContent gameRequestContent) {
            return true;
        }
        
        public AppCall createAppCall(final GameRequestContent gameRequestContent) {
            GameRequestValidation.validate(gameRequestContent);
            final AppCall baseAppCall = GameRequestDialog.this.createBaseAppCall();
            DialogPresenter.setupAppCallForWebDialog(baseAppCall, "apprequests", WebDialogParameters.create(gameRequestContent));
            return baseAppCall;
        }
    }
}
