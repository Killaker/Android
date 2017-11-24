package com.facebook.share.widget;

import android.app.*;
import java.util.*;
import com.facebook.*;
import android.os.*;
import android.content.*;
import com.facebook.share.internal.*;
import com.facebook.internal.*;

public class JoinAppGroupDialog extends FacebookDialogBase<String, Result>
{
    private static final int DEFAULT_REQUEST_CODE = 0;
    private static final String JOIN_GAME_GROUP_DIALOG = "game_group_join";
    
    public JoinAppGroupDialog(final Activity activity) {
        super(activity, JoinAppGroupDialog.DEFAULT_REQUEST_CODE);
    }
    
    public JoinAppGroupDialog(final Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    public JoinAppGroupDialog(final android.support.v4.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    private JoinAppGroupDialog(final FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, JoinAppGroupDialog.DEFAULT_REQUEST_CODE);
    }
    
    public static boolean canShow() {
        return true;
    }
    
    public static void show(final Activity activity, final String s) {
        ((FacebookDialogBase<String, RESULT>)new JoinAppGroupDialog(activity)).show(s);
    }
    
    public static void show(final Fragment fragment, final String s) {
        show(new FragmentWrapper(fragment), s);
    }
    
    public static void show(final android.support.v4.app.Fragment fragment, final String s) {
        show(new FragmentWrapper(fragment), s);
    }
    
    private static void show(final FragmentWrapper fragmentWrapper, final String s) {
        ((FacebookDialogBase<String, RESULT>)new JoinAppGroupDialog(fragmentWrapper)).show(s);
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
                    facebookCallback.onSuccess(new Result(bundle));
                }
            };
        }
        callbackManagerImpl.registerCallback(this.getRequestCode(), (CallbackManagerImpl.Callback)new CallbackManagerImpl.Callback() {
            @Override
            public boolean onActivityResult(final int n, final Intent intent) {
                return ShareInternalUtility.handleActivityResult(JoinAppGroupDialog.this.getRequestCode(), n, intent, resultProcessor);
            }
        });
    }
    
    public static final class Result
    {
        private final Bundle data;
        
        private Result(final Bundle data) {
            this.data = data;
        }
        
        public Bundle getData() {
            return this.data;
        }
    }
    
    private class WebHandler extends ModeHandler
    {
        public boolean canShow(final String s) {
            return true;
        }
        
        public AppCall createAppCall(final String s) {
            final AppCall baseAppCall = JoinAppGroupDialog.this.createBaseAppCall();
            final Bundle bundle = new Bundle();
            bundle.putString("id", s);
            DialogPresenter.setupAppCallForWebDialog(baseAppCall, "game_group_join", bundle);
            return baseAppCall;
        }
    }
}
