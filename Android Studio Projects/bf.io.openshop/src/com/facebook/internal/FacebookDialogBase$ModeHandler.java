package com.facebook.internal;

protected abstract class ModeHandler
{
    public abstract boolean canShow(final CONTENT p0);
    
    public abstract AppCall createAppCall(final CONTENT p0);
    
    public Object getMode() {
        return FacebookDialogBase.BASE_AUTOMATIC_MODE;
    }
}
