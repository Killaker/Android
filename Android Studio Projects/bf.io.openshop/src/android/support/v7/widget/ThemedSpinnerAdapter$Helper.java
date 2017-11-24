package android.support.v7.widget;

import android.content.*;
import android.view.*;
import android.content.res.*;
import android.support.annotation.*;
import android.support.v7.view.*;

public static final class Helper
{
    private final Context mContext;
    private LayoutInflater mDropDownInflater;
    private final LayoutInflater mInflater;
    
    public Helper(@NonNull final Context mContext) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
    }
    
    @NonNull
    public LayoutInflater getDropDownViewInflater() {
        if (this.mDropDownInflater != null) {
            return this.mDropDownInflater;
        }
        return this.mInflater;
    }
    
    @Nullable
    public Resources$Theme getDropDownViewTheme() {
        if (this.mDropDownInflater == null) {
            return null;
        }
        return this.mDropDownInflater.getContext().getTheme();
    }
    
    public void setDropDownViewTheme(@Nullable final Resources$Theme resources$Theme) {
        if (resources$Theme == null) {
            this.mDropDownInflater = null;
            return;
        }
        if (resources$Theme == this.mContext.getTheme()) {
            this.mDropDownInflater = this.mInflater;
            return;
        }
        this.mDropDownInflater = LayoutInflater.from((Context)new ContextThemeWrapper(this.mContext, resources$Theme));
    }
}
