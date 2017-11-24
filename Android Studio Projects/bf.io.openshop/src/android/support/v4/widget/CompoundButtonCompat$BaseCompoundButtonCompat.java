package android.support.v4.widget;

import android.widget.*;
import android.graphics.drawable.*;
import android.content.res.*;
import android.graphics.*;

static class BaseCompoundButtonCompat implements CompoundButtonCompatImpl
{
    @Override
    public Drawable getButtonDrawable(final CompoundButton compoundButton) {
        return CompoundButtonCompatDonut.getButtonDrawable(compoundButton);
    }
    
    @Override
    public ColorStateList getButtonTintList(final CompoundButton compoundButton) {
        return CompoundButtonCompatDonut.getButtonTintList(compoundButton);
    }
    
    @Override
    public PorterDuff$Mode getButtonTintMode(final CompoundButton compoundButton) {
        return CompoundButtonCompatDonut.getButtonTintMode(compoundButton);
    }
    
    @Override
    public void setButtonTintList(final CompoundButton compoundButton, final ColorStateList list) {
        CompoundButtonCompatDonut.setButtonTintList(compoundButton, list);
    }
    
    @Override
    public void setButtonTintMode(final CompoundButton compoundButton, final PorterDuff$Mode porterDuff$Mode) {
        CompoundButtonCompatDonut.setButtonTintMode(compoundButton, porterDuff$Mode);
    }
}
