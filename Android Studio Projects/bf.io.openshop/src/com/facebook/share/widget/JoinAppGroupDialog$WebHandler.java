package com.facebook.share.widget;

import android.os.*;
import com.facebook.internal.*;

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
